package org.bkl.modloader;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.bkl.game.MinecraftPath;

import java.io.File;
import java.io.FileReader;

/**
 * @author LongWei
 * @date 2025/10/3 14:57
 */
public class FabricChecker {

    public FabricChecker() {
    }

    /**
     * 检查指定版本是否已经安装fabric
     * @param mcVersion 检查版本
     * @return 已经安装fabric
     */
    public static boolean isFabricInstall(String mcVersion) {
        // 检查游戏版本文件夹
        File versionDir = new File(MinecraftPath.getMinecraftPath() + "/versions/");
        if (!versionDir.exists()) {
            return false;
        }

        File[] versionFolder = versionDir.listFiles();
        if (versionFolder != null) {
            for (File folder : versionFolder) {
                if (folder.isDirectory() && folder.getName().contains("fabric") && folder.getName().contains(mcVersion)) {
                    File versionJson = new File(folder, folder.getName() + ".json");
                    if (versionJson.exists() && checkJsonForFabric(versionJson)) {
                        return true;
                    }
                }
            }
        }

        // 2. 检查libraries目录下是否有Fabric核心库
        File fabricLibDir = new File(MinecraftPath.minecraftPath + "/libraries/net/fabricmc/fabric-loader");
        return fabricLibDir.exists() && fabricLibDir.listFiles() != null && fabricLibDir.listFiles().length > 0;
    }

    /**
     * 解析版本JSON文件，确认是否包含Fabric依赖
     * @param versionJson 版本JSON文件
     * @return 是否包含Fabric依赖
     */
    private static boolean checkJsonForFabric(File versionJson) {
        try(FileReader reader = new FileReader(versionJson)) {
            JsonObject json = JsonParser.parseReader(reader).getAsJsonObject();
            if (json.has("libraries")) {
                JsonArray libraries = json.getAsJsonArray("libraries");
                for (int i = 0; i < libraries.size(); i++) {
                    JsonObject lib = libraries.get(i).getAsJsonObject();
                    if (lib.has("name")) {
                        String name = lib.get("name").getAsString();
                        if (name.startsWith("net.fabricmc:fabric-loader")) {
                            return true;
                        }
                    }
                }
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
