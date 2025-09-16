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

    public static void gameLauncher(String version, String minecraftDir) throws Exception {

        Launcher launcher = LauncherBuilder.buildDefault();

        MinecraftDirectory minecraftDirectory = new MinecraftDirectory(minecraftDir);

        LaunchOption option
                = new LaunchOption("1.21.8", new OfflineAuthenticator("Id"), minecraftDirectory);

        launcher.launch(option);

    }
}
