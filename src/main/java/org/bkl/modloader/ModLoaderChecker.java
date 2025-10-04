package org.bkl.modloader;

import java.util.ArrayList;
import java.util.List;

/**
 * 一个版本游戏不允许存在多个模组加载器
 */
public class ModLoaderChecker {
    private List<ModLoaderType> installed = new ArrayList<>();

    public ModLoaderChecker() {
    }

    public List<ModLoaderType> checkInstalledModLoaders(String mcVersion) {
        List<ModLoaderType> installed = new ArrayList<>();

        if (FabricChecker.isFabricInstall(mcVersion)) {
            installed.add(ModLoaderType.FABRIC);
        }

        return installed;
    }

}
