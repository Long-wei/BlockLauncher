package org.bkl.download;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.bkl.log.Logger;
import org.bkl.log.LoggerFactory;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class VersionManifestDownloader {
    private final static Logger log = LoggerFactory.getLogger(VersionManifestDownloader.class.getName());
    private static HttpClient client = HttpClient.newHttpClient();

    public static void downloadVersionManifest(String mcVersion) {
        JsonObject gameInfo = GameVersionManifest.getVersionInfo(mcVersion);
        if (gameInfo == null) {
            log.info("Game version " + mcVersion + " not found in manifest.");
            return;
        }

        String versionUrl = gameInfo.get("url").getAsString();
        if (versionUrl == null) {
            log.info("No URL found for version " + mcVersion);
            return;
        }
        JsonObject gameVersionJsonObject = JsonParser.parseString(httpGet(versionUrl)).getAsJsonObject();

        System.out.println(gameVersionJsonObject);

    }

    private static String httpGet(String url) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Accept", "application/json")
                .build();
        HttpResponse<String> resp = null;
        try {
             resp = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            log.info("fail fetch game version manifest: " + e.getMessage());
        }
        if (resp == null || resp.statusCode() != 200) {
            log.info("Failed to fetch game version manifest: HTTP " + resp.statusCode());
        }
        return resp.body();
    }
}
