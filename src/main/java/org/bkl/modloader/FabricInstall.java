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
    private static final String MAVEN_URL = "https://maven.fabricmc.net/";

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
            JsonObject fabricLoaderName = FabricVersionFetcher.getFabricLoaderMainClassName(mcVersion, loaderVersion);
            if (fabricLoaderName != null && fabricLoaderName.has("client")) {
                 mainClass = fabricLoaderName.get("client").getAsString();

                if (versionJsonObject.has("mainClass")) {
                    versionJsonObject.addProperty("mainClass", mainClass);
                }
            }

            JsonObject patchesObject = new JsonObject();
            JsonArray patchesArrayI = new JsonArray();

            if (versionJsonObject.has("patches")) {
                patchesObject = versionJsonObject.getAsJsonArray("patches").get(0).getAsJsonObject();
                patchesObject.addProperty("id", "fabric");
                patchesObject.addProperty("mainClass", mainClass);

                if (patchesObject.has("libraries")) {
                    patchesArrayI = patchesObject.getAsJsonArray("libraries");
                }
            }

            JsonArray common = libraries.getAsJsonArray("common");
            JsonArray asJsonArray = versionJsonObject.getAsJsonArray("libraries");
            for (int i = 0; i < common.size(); i++) {
                JsonObject asJsonObject = common.get(i).getAsJsonObject();

                String name = asJsonObject.get("name").getAsString();
                String url = asJsonObject.get("url").getAsString();

                JsonObject libJsonObject = new JsonObject();
                libJsonObject.addProperty("name", name);
                libJsonObject.addProperty("url", url);

                if (versionJsonObject.has("libraries")) {
                    JsonArray ja1 = versionJsonObject.getAsJsonArray("libraries");

                    boolean flag = false;
                    for (int j = 0; j < ja1.size(); j++) {
                        JsonObject ja2 = ja1.get(j).getAsJsonObject();
                        if (libJsonObject.get("name").getAsString() != null && ja2.get("name").getAsString().contains(libJsonObject.get("name").getAsString())) {
                            flag = true;
                        }
                    }
                    if (!flag) {
                        asJsonArray.add(libJsonObject);
                    }
                }

                boolean flag1 = false;
                for (int j = 0; j < patchesArrayI.size(); j++) {
                    JsonObject asJsonObject1 = patchesArrayI.get(j).getAsJsonObject();
                    if (libJsonObject.get("name").getAsString() != null && asJsonObject1.get("name").getAsString().contains(libJsonObject.get("name").getAsString())) {
                        flag1 = true;
                    }
                }
                if (!flag1) {
                    patchesArrayI.add(libJsonObject);
                }

                String libPath = mcPath + "/libraries/" + name.substring(0, name.lastIndexOf(":")).replace(".", "/").replace(":", "/") + "/" + name.substring(name.lastIndexOf(":") + 1);
                String libName = name.substring(name.indexOf(":") + 1).replace(":", "-") + ".jar";

                if ((new File(libPath + "/" +  libName)).exists()) {
                    continue;
                }

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
                    Path libDir = Paths.get(libPath);
                    Files.createDirectories(libDir);

                    Path target = libDir.resolve(libName);
                    try (InputStream is = libResp.body()) {
                        Files.copy(is, target, StandardCopyOption.REPLACE_EXISTING);
                    }
                } catch (Exception e) {

                }
            }

            String intermediaryName = FabricVersionFetcher.getIntermediaryName(mcVersion, loaderVersion);
            String intermediaUrl = intermediaryName.substring(0, intermediaryName.lastIndexOf(":")).replace(".", "/").replace(":", "/") + "/"
                    + intermediaryName.substring(intermediaryName.lastIndexOf(":") + 1) + "/"
                    + intermediaryName.substring(intermediaryName.indexOf(":") + 1).replace(":", "-") + ".jar";

            HttpRequest requestIntermedia = HttpRequest.newBuilder()
                    .uri(URI.create(MAVEN_URL + intermediaUrl))
                    .header("Accept", "application/java-archive")
                    .build();

            HttpResponse<InputStream> intermediaryResp = null;
            try {
                HttpResponse<InputStream> resp = client.send(requestIntermedia, HttpResponse.BodyHandlers.ofInputStream());
                if  (resp.statusCode() != 200) {
                    throw new RuntimeException("Failed to download Fabric library: HTTP " + resp.statusCode());
                }
                intermediaryResp = resp;
            } catch (Exception e) {

            }

            try {
                Path path = Paths.get(mcPath, intermediaUrl);
                Files.createDirectories(path.getParent());

                String name = String.format("intermediary-%s", mcVersion);
                Path resolve = path.resolve(name);
                try (InputStream is = intermediaryResp.body()) {
                    Files.copy(is, resolve, StandardCopyOption.REPLACE_EXISTING);
                }

            } catch (Exception e) {

            }

            boolean flag = false;
            for (int i = 0; i < asJsonArray.size(); i++) {
                JsonObject asJsonObject = asJsonArray.get(i).getAsJsonObject();
                if (asJsonObject.has("name") && asJsonObject.get("name").getAsString().contains(intermediaryName)) {
                    flag = true;
                }
            }
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("name", intermediaryName);
            jsonObject.addProperty("url", MAVEN_URL);
            if (!flag) {
                asJsonArray.add(jsonObject);
            }

            boolean flag2 = false;
            for (int i = 0; i < patchesArrayI.size(); i++) {
                JsonObject asJsonObject = patchesArrayI.get(i).getAsJsonObject();
                if (asJsonObject.get("name").getAsString() != null && jsonObject.get("name").getAsString().contains(asJsonObject.get("name").getAsString())) {
                    flag2 = true;
                }
            }
            if (!flag2) {
                patchesArrayI.add(jsonObject);
            }

            String fabricLoaderName1 = FabricVersionFetcher.getFabricLoaderName(mcVersion, loaderVersion);
            boolean flag1 = false;
            for (int i = 0; i < asJsonArray.size(); i++) {
                JsonObject asJsonObject = asJsonArray.get(i).getAsJsonObject();
                if (asJsonObject.has("name") && asJsonObject.get("name").getAsString().contains(fabricLoaderName1)) {
                    flag1 = true;
                }
            }
            JsonObject jsonObject1 = new JsonObject();
            jsonObject1.addProperty("name", fabricLoaderName1);
            jsonObject1.addProperty("url", MAVEN_URL);
            if (!flag1) {
                asJsonArray.add(jsonObject1);
            }
            boolean flag3 = false;
            for (int i = 0; i < patchesArrayI.size(); i++) {
                JsonObject asJsonObject = patchesArrayI.get(i).getAsJsonObject();
                if (asJsonObject.get("name").getAsString() != null && jsonObject1.get("name").getAsString().contains(asJsonObject.get("name").getAsString())) {
                    flag3 = true;
                }
            }
            if (!flag3) {
                patchesArrayI.add(jsonObject1);
            }
            GsonUtil.jsonObjectWriter(versionJsonObject, jsonPath);
        }
    }
}
