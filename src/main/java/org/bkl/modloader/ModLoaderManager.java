package org.bkl.modloader;

import java.util.ArrayList;
import java.util.List;

/**
 * 一个版本游戏不允许存在多个模组加载器
 */
public class ModLoaderManager {
    private List<ModLoaderType> installed = new ArrayList<>();

    public ModLoaderManager() {
    }

    public List<ModLoaderType> checkInstalledModLoaders(String mcVersion) {
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

}
