package org.bkl.download;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.bkl.log.Logger;
import org.bkl.log.LoggerFactory;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class GameVersionManifest {
    private static final Logger log = LoggerFactory.getLogger(GameVersionManifest.class.getName());
    private static final String MANIFEST_URL = "https://launchermeta.mojang.com/mc/game/version_manifest_v2.json";
    private static final HttpClient client = HttpClient.newHttpClient();
    private static JsonObject manifest;

    public static JsonObject getManifestFetcher() {
        JsonObject manifest = new JsonObject();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(MANIFEST_URL))
                .header("Accept", "application/json")
                .build();
        HttpResponse<String> resp;
        try {
             resp = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (resp.statusCode() != 200) {
                log.info("Failed to fetch game version manifest: HTTP " + resp.statusCode());
            }

            GameVersionManifest.manifest = JsonParser.parseString(resp.body()).getAsJsonObject();
            return GameVersionManifest.manifest;
        } catch (Exception e) {
            log.info("fail fetch game version manifest: " + e.getMessage());
        }

        return null;
    }
}
