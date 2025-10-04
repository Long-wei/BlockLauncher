package org.bkl.modloader;

import java.util.ArrayList;
import java.util.List;

public class ModLoaderChecker {
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
