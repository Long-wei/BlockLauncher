package org.bkl.game;


import org.to2mbn.jmccc.auth.OfflineAuthenticator;
import org.to2mbn.jmccc.launch.Launcher;
import org.to2mbn.jmccc.launch.LauncherBuilder;
import org.to2mbn.jmccc.option.LaunchOption;
import org.to2mbn.jmccc.option.MinecraftDirectory;

/**
 * @author LongWei
 * @date 2025/9/15 12:10
 */


public class GameLauncher {

    private static String version;
    private static String minecraftDir;
    private static String auth;

    public GameLauncher(String version, String auth, String minecraftDir) {
        GameLauncher.version = version;
        GameLauncher.minecraftDir = minecraftDir;
        GameLauncher.auth = auth;
    }

    public static void start() {
        GameLauncher.gameLauncher();
    }

    private static void gameLauncher() {
        try {
            Launcher launcher = LauncherBuilder.buildDefault();
            MinecraftDirectory minecraftDirectory = new MinecraftDirectory(minecraftDir);
            LaunchOption option
                    = new LaunchOption(version, new OfflineAuthenticator(auth), minecraftDirectory);
            launcher.launch(option);
        } catch ( Exception e) {

        }
    }


}
