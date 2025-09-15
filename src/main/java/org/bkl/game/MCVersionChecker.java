package org.bkl.game;

import java.io.File;

/**
 * @author LongWei
 * @date 2025/9/15 10:59
 */
public class MCVersionChecker {

    public static void mcVersionChecker () {
        String versionFolderPath = System.getenv("APPDATA") + "\\.minecraft\\versions";
        File mcDir = new File(versionFolderPath);

        if (!mcDir.exists() || !mcDir.isDirectory()) {
            System.out.println("Minecraft installation not found.");
            return;
        }

        File[] mcVersionFolder = mcDir.listFiles(File::isDirectory);
        if (mcVersionFolder == null || mcVersionFolder.length == 0) {
            System.out.println("No Minecraft versions found.");
            return;
        }

        System.out.println("Installed Minecraft versions:");
        for (File versionFolder : mcVersionFolder) {
            System.out.println("- " + versionFolder.getName());
        }
    }

}
