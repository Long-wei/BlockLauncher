package org.bkl.modloader;

import com.google.gson.JsonObject;

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

        try {
            HttpResponse<InputStream> resp = client.send(request, HttpResponse.BodyHandlers.ofInputStream());
            if (resp.statusCode() != 200) {
                throw new RuntimeException("Failed to download Fabric Loader: HTTP " + resp.statusCode());
            }

            String versionId = String.format("/%s/", loaderVersion, loaderVersion);
            Path versionDir = Paths.get(mcPath, "libraries/net/fabricmc/fabric-loader", versionId);
            Files.createDirectories(versionDir);

            String jarFileName = String.format("fabric-loader-%s.jar", loaderVersion);
            Path jarTarget = versionDir.resolve(jarFileName);
            try (InputStream is = resp.body()) {
                Files.copy(is, jarTarget, StandardCopyOption.REPLACE_EXISTING);
            }

            JsonObject libraries = FabricVersionFetcher.getLibraries(mcVersion, loaderVersion);


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
