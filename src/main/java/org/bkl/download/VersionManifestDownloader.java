package org.bkl.download;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.bkl.game.MinecraftPath;
import org.bkl.log.Logger;
import org.bkl.log.LoggerFactory;
import org.bkl.util.GsonUtil;
import org.bkl.util.HashUtil;

import java.io.File;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;

public class VersionManifestDownloader {
    private final static Logger log = LoggerFactory.getLogger(VersionManifestDownloader.class.getName());
    private static HttpClient client = HttpClient.newHttpClient();
    protected static JsonObject gameVersionJsonObject = null;
    private static String gameInstallPath = null;

    private static void init(String mcVersion) {
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
        VersionManifestDownloader.gameVersionJsonObject = JsonParser.parseString(httpGet(versionUrl)).getAsJsonObject();
        VersionManifestDownloader.gameInstallPath = MinecraftPath.getDefaultMinecraftPath();
    }

    public static void downloadVersionManifest(String mcVersion) {
        if (gameVersionJsonObject == null) {
            init(mcVersion);
        }

        File parentPath = new File(gameInstallPath);
        if (!parentPath.exists()) {
            parentPath.mkdirs();
        }
        File versionPath = new File(gameInstallPath + "/versions/");
        if (!versionPath.exists()) {
            versionPath.mkdirs();
        }
        File mcVersionPath = new File(gameInstallPath + "/versions/" + mcVersion + "/");
        if (!mcVersionPath.exists()) {
            mcVersionPath.mkdirs();
        }

        String versionJsonPath = VersionManifestDownloader.gameInstallPath + "/versions/" + mcVersion + "/" + mcVersion + ".json";
        File versionJsonFile = new File(versionJsonPath);
        if (!versionJsonFile.exists()) {
            try {
                versionJsonFile.createNewFile();
            } catch (Exception e) {
                log.info("Failed to create version json file: " + e.getMessage());
            }
        }

        GsonUtil.jsonObjectWriter(VersionManifestDownloader.gameVersionJsonObject, versionJsonPath);

    }

    public static void downloadClientJar(String mcVersion) {
        if (VersionManifestDownloader.gameVersionJsonObject == null) {
            init(mcVersion);
        }
        JsonObject downloads = VersionManifestDownloader.gameVersionJsonObject.getAsJsonObject("downloads");
        if (downloads == null) {
            log.info("No downloads section found in version JSON.");
            return;
        }
        JsonObject client = downloads.getAsJsonObject("client");
        if (client == null) {
            log.info("No client download info found in version JSON.");
            return;
        }
        String clientUrl = client.get("url").getAsString();
        if (clientUrl == null) {
            log.info("No URL found for client jar.");
            return;
        }

        String clientJarPath = VersionManifestDownloader.gameInstallPath + "/versions/" + mcVersion + "/" + mcVersion + ".jar";
        File clientJarFile = new File(clientJarPath);
        if (!clientJarFile.exists()) {
            try {
                clientJarFile.createNewFile();
            } catch (Exception e) {
                log.info("Failed to create client jar file: " + e.getMessage());
            }
        }

        String sha1 = client.get("sha1").getAsString();
        if (sha1 == null) {
            log.info("No SHA-1 hash found for client jar.");
            return;
        }
        if (!HashUtil.verifyHash(Path.of(clientJarPath), sha1, "SHA-1")) {
            log.info("Client jar hash verification failed.");
        } else {
            log.info("Client jar already exists and hash is verified.");
            return;
        }

        // Download the client jar
        byte[] jarData = httpGetBytes(clientUrl);
        if (jarData != null) {
            try {
                java.nio.file.Files.write(clientJarFile.toPath(), jarData);
            } catch (Exception e) {
                log.info("Failed to write client jar file: " + e.getMessage());
            }
        }

    }

    public static void downloadLibraries() {

    }

    private static byte[] httpGetBytes(String url) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Accept", "application/json")
                .build();
        HttpResponse<byte []> resp = null;
        try {
            resp = client.send(request, HttpResponse.BodyHandlers.ofByteArray());
        } catch (Exception e) {
            log.info("fail fetch game version manifest: " + e.getMessage());
        }
        if (resp == null || resp.statusCode() != 200) {
            log.info("Failed to fetch game version manifest: HTTP " + resp.statusCode());
        }
        return resp.body();
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
