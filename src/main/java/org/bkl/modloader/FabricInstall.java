package org.bkl.modloader;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.bkl.util.GsonUtil;

import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;


public class FabricInstall {
    private static HttpClient client = HttpClient.newHttpClient();

    public FabricInstall() {}

    public static void installFabric(String mcPath, String mcVersion, String loaderVersion) {
        if (loaderVersion == null || loaderVersion.isBlank()) {
            return;
        }

        String apiUrl = String.format("https://maven.fabricmc.net/net/fabricmc/fabric-loader/%s/fabric-loader-%s.jar", loaderVersion, loaderVersion);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .header("Accept", "application/java-archive")
                .build();

        HttpResponse<InputStream> loaderResp = null;
        try {
            HttpResponse<InputStream> resp = client.send(request, HttpResponse.BodyHandlers.ofInputStream());
            if (resp.statusCode() != 200) {
                throw new RuntimeException("Failed to download Fabric Loader: HTTP " + resp.statusCode());
            }
            loaderResp = resp;
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            String versionId = String.format("/%s/", loaderVersion, loaderVersion);
            Path versionDir = Paths.get(mcPath, "libraries/net/fabricmc/fabric-loader", versionId);
            Files.createDirectories(versionDir);

            String jarFileName = String.format("fabric-loader-%s.jar", loaderVersion);
            Path jarTarget = versionDir.resolve(jarFileName);
            try (InputStream is = loaderResp.body()) {
                Files.copy(is, jarTarget, StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (Exception e) {

        }

        JsonObject libraries = FabricVersionFetcher.getLibraries(mcVersion, loaderVersion);

        String jsonPath = mcPath + "/versions/" + mcVersion + "/" + mcVersion + ".json";
        File jsonFile = new File(jsonPath);

        if (!jsonFile.exists()) {
            new RuntimeException("Minecraft version JSON not found: " + jsonPath);
        }

        JsonObject versionJsonObject = null;
        try(FileReader reader = new FileReader(jsonFile)) {
            versionJsonObject = JsonParser.parseReader(reader).getAsJsonObject();
        }  catch (Exception e) {

        }

        HttpResponse<InputStream> libResp = null;
        if (libraries.has("common")) {
            String mainClass = null;
            JsonObject fabricLoaderName = FabricVersionFetcher.getFabricLoaderName(mcVersion, loaderVersion);
            if (fabricLoaderName != null && fabricLoaderName.has("client")) {
                 mainClass = fabricLoaderName.get("client").getAsString();

                if (versionJsonObject.has("mainClass")) {
                    versionJsonObject.addProperty("mainClass", mainClass);
                }
                GsonUtil.jsonObjectWriter(versionJsonObject, jsonPath);
            }

            JsonObject patchesObject = new JsonObject();
            patchesObject.addProperty("id", "fabric");
            patchesObject.addProperty("mainClass", mainClass);
            JsonArray patchesArrayI = new JsonArray();
            JsonArray patchesArrayO = new JsonArray();

            JsonArray common = libraries.getAsJsonArray("common");
            for (int i = 0; i < common.size(); i++) {
                JsonObject asJsonObject = common.get(i).getAsJsonObject();

                String name = asJsonObject.get("name").getAsString();
                String url = asJsonObject.get("url").getAsString();

                String libUrl = url
                        + name.substring(0, name.lastIndexOf(":")).replace(".", "/").replace(":", "/") + "/"
                        + name.substring(name.lastIndexOf(":") + 1) + "/"
                        + name.substring(name.indexOf(":") +1).replace(":", "-") + ".jar";

                HttpRequest libRequest = HttpRequest.newBuilder()
                        .uri(URI.create(libUrl))
                        .header("Accept", "application/java-archive")
                        .build();
                try {
                    HttpResponse<InputStream> resp = client.send(libRequest, HttpResponse.BodyHandlers.ofInputStream());

                    if (resp.statusCode() != 200) {
                        throw new RuntimeException("Failed to download Fabric library: HTTP " + resp.statusCode());
                    }
                    libResp = resp;
                } catch (Exception e) {}

                try {
                    String libPath = mcPath + "/libraries/" + name.substring(0, name.lastIndexOf(":")).replace(".", "/").replace(":", "/") + "/" + name.substring(name.lastIndexOf(":") + 1);
                    Path libDir = Paths.get(libPath);
                    Files.createDirectories(libDir);

                    String libName = name.substring(name.indexOf(":") + 1).replace(":", "-") + ".jar";
                    Path target = libDir.resolve(libName);
                    try (InputStream is = libResp.body()) {
                        Files.copy(is, target, StandardCopyOption.REPLACE_EXISTING);
                    }
                } catch (Exception e) {

                }

                if (versionJsonObject.has("libraries")) {
                    JsonArray asJsonArray = versionJsonObject.getAsJsonArray("libraries");
                    asJsonArray.add(asJsonObject);
                    patchesArrayI.add(asJsonObject);
                }

                patchesObject.add("libraries", patchesArrayI);
                patchesArrayO.add(patchesObject);
                versionJsonObject.add("patches", patchesArrayO);

            }
        }
    }
}
