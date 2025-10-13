package org.bkl.modloader;

import javafx.application.Platform;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 一个版本游戏不允许存在多个模组加载器
 */
public class ModLoaderManager {
    private List<ModLoaderType> installed = new ArrayList<>();

    public ModLoaderManager() {
    }

    public static List<ModLoaderType> checkInstalledModLoaders(String mcVersion) {
        List<ModLoaderType> installed = new ArrayList<>();

        // 检查是否安装fabric
        if (FabricChecker.isFabricInstall(mcVersion)) {
            installed.add(ModLoaderType.FABRIC);
        }

        // 检查是否安装forge
        if (ForgeChecker.isForgeInstalled(mcVersion)) {
            installed.add(ModLoaderType.FORGE);
        }

        // 检查是否安装quilt
        if (QuiltChecker.isQuiltInstalled(mcVersion)) {
            installed.add(ModLoaderType.QUILT);
        }

        // 检查是否安装rift
        if (OptiFineChecker.isOptiFineInstalled(mcVersion)) {
            installed.add(ModLoaderType.OPTIFINE);
        }

        return installed;
    }

    public static List<String> getModLoaderType(String mcVersion) {
        List<String> installed = new ArrayList<>();

        if (FabricChecker.getModLoaderVersion(mcVersion) != null) {
            installed.add(FabricChecker.getModLoaderVersion(mcVersion));
        }
        return installed;
    }

    /**
     * @param mcVersion 游戏版本
     * @param modLoaderType 加载模组类型
     * @return 可用版本 List&ltString&gt
     */
    public static List<String> loadRemoteVersions(String mcVersion, ModLoaderType modLoaderType) {
        switch (modLoaderType) {
            case FABRIC -> {
                return FabricVersionFetcher.getCompatibleFabricLoaders(mcVersion);
            }
        }
        return null;
    }

    /**
     * @param mcVersion 游戏版本
     * @param modLoaderType 模组加载器类型
     */
    public static void removeModLoader(String mcVersion, ModLoaderType modLoaderType) {
        if (ModLoaderType.FABRIC.equals(modLoaderType)) {
            FabricRemover.remove(mcVersion);
        }
    }

}
