package org.bkl.game;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author LongWei
 * @date 2025/9/15 10:59
 */
public class MCVersionChecker {

    private static List<File> versionFolder = null;
    private static List<String> versionNameList = null;

    public MCVersionChecker () {
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
            MCVersionChecker.versionNameList.add(versionFolder.getName());
        }

        MCVersionChecker.versionFolder = List.of(mcVersionFolder);
    }

    public static List<File> getVersionFolder() {
        return MCVersionChecker.versionFolder;
    }


    public static void main(String[] args) {
    }

}
