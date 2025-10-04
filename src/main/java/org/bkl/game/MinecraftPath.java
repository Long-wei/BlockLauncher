package org.bkl.game;

import org.bkl.os.SystemUtils;

import java.io.File;
import java.util.Objects;

public class MinecraftPath {

    public static String minecraftPath = null;

    public MinecraftPath() {
        getMinecraftPath();
    }

    public static String getMinecraftPath() {
        String osName = SystemUtils.osName;
        if (osName == null) {
            osName = SystemUtils.getOsName();
        }

        if (osName.contains("win")) {
            MinecraftPath.minecraftPath = System.getenv("APPDATA") + "/.minecraft";
        } else if (osName.contains("nix") || osName.contains("nux") || osName.contains("mac")) {
            String home = System.getProperty("user.home");
            minecraftPath = home + "/.minecraft";

            File f = new File(minecraftPath + "/versions");
            if (!f.exists() || f.isDirectory() && f.list().length == 0) {
                minecraftPath = home + "/下载/.minecraft";
            }

        } else {
            minecraftPath = ".minecraft";
        }
        return minecraftPath;
    }

}
