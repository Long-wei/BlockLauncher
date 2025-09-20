package org.bkl.game;


import javafx.concurrent.Task;
import javafx.scene.layout.Pane;
import org.bkl.ui.FirstPage;
import org.bkl.ui.ProgressDialog;
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

    private static String version = null;
    private static String minecraftDir = null;
    private static String auth = null;
    private static Boolean isStart = false;

    public GameLauncher(String version, String auth, String minecraftDir) {
        GameLauncher.version = version;
        GameLauncher.minecraftDir = minecraftDir;
        GameLauncher.auth = auth;
    }

    public static void start(ProgressDialog progressDialog) {
        if (GameLauncher.isStart ) {
            return;
        }
        GameLauncher.isStart = true;

        GameLauncher.gameLauncher(new LauncherCallback() {
            @Override
            public void onProgress(String message, double progress) {
                ProgressDialog.updateProgress(progress / 100);
            }

            @Override
            public void onSuccess() {
            }

            @Override
            public void onError(String errorMessage) {

            }

            @Override
            public void onLog(String logMessage) {

            }
        });
    }

    private static void gameLauncher(LauncherCallback callback) {
        new Thread(() -> {
            try {
                callback.onProgress("初始化起动器", 10);
                Launcher launcher = LauncherBuilder.buildDefault();

                callback.onProgress("设置Minecraft目录", 30);
                MinecraftDirectory minecraftDirectory = new MinecraftDirectory(minecraftDir);

                callback.onProgress("创建启动项", 50);
                LaunchOption option = new LaunchOption(version, new OfflineAuthenticator(auth), minecraftDirectory);

                callback.onProgress("游戏启动中", 70);
                launcher.launch(option);

                callback.onProgress("游戏启动成功", 100);
                callback.onSuccess();

            } catch ( Exception e) {
                GameLauncher.isStart = false;
                callback.onError("游戏启动失败");
                e.printStackTrace();
            }
        }).start();


    }

    public static void setVersion(String version) {
        GameLauncher.version = version;
    }

}
