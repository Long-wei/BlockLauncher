package org.bkl.modloader;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * 从远程获取Fabric版本信息
 * @author LongWei
 * @date 2025/10/11 0:20
 */
public class FabricVersionFetcher {
    // Fabric 版本元数据 API 基础地址
    private static final String FABRIC_LOADER_API = "https://meta.fabricmc.net/v2/versions/loader/";
    private static final HttpClient client = HttpClient.newHttpClient();
    private static final Gson gson = new Gson();

    /**
     * 获取指定 Minecraft 版本兼容的 Fabric Loader 版本列表
     * @param mcVersion Minecraft 版本（如 "1.20.1"）
     * @return 兼容的 Fabric Loader 版本列表（如 ["0.15.11", "0.15.10"...]）
     */
    public static List<String> getCompatibleFabricLoaders(String mcVersion) {
        ArrayList<String> fabricLoaders = new ArrayList<>();

        String apiUrl = FABRIC_LOADER_API + mcVersion;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .header("Accept", "application/json")
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                new RuntimeException("Failed to fetch Fabric versions: HTTP " + response.statusCode());
            }

            JsonArray jsonArray = gson.fromJson(response.body(), JsonArray.class);
            for (int i = 0; i < jsonArray.size(); i++) {
                JsonObject loaderObj = jsonArray.get(i).getAsJsonObject();
                String loaderVersion = loaderObj.get("loader").getAsJsonObject().get("version").getAsString();
                fabricLoaders.add(loaderVersion);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return fabricLoaders;
    }

    public static JsonObject getLibraries(String mcVersion, String loaderVersion) {
        String apiUrl = FABRIC_LOADER_API + mcVersion + "/" + loaderVersion;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .header("Accept", "application/json")
                .build();

        try{
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != 200) {
                new RuntimeException("Failed to fetch Fabric loader details: HTTP " + response.statusCode());
            }
            JsonObject jsonObject = gson.fromJson(response.body(), JsonObject.class);

            if (jsonObject.has("launcherMeta")) {
                JsonObject asJsonObject = jsonObject.get("launcherMeta").getAsJsonObject();

                if (asJsonObject.has("libraries")) {
                    JsonObject asJsonObject1 = asJsonObject.get("libraries").getAsJsonObject();

                    if (asJsonObject1.has("common")) {
                        return asJsonObject1;
                    }
                }
            }
        }catch (Exception e){

        }
        return null;
    }

    public static JsonObject getFabricLoaderName(String mcVersion, String loaderVersion) {
        String apiUrl = FABRIC_LOADER_API + mcVersion + "/" + loaderVersion;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .header("Accept", "application/json")
                .build();

        try{
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != 200) {
                new RuntimeException("Failed to fetch Fabric loader details: HTTP " + response.statusCode());
            }
            JsonObject jsonObject = gson.fromJson(response.body(), JsonObject.class);

            if (jsonObject.has("launcherMeta")) {
                JsonObject asJsonObject = jsonObject.get("launcherMeta").getAsJsonObject();

                if (asJsonObject.has("mainClass")) {
                    return  asJsonObject.get("mainClass").getAsJsonObject();
                }
            }
        }catch (Exception e){

        }
        return null;
    }

}
