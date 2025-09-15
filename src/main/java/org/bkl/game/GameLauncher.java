package org.bkl.game;

/**
 * @author LongWei
 * @date 2025/9/15 12:10
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class GameLauncher {

    public static void gameLauncher(String version, String minecraftDir) throws Exception {

        String classpath = ClasspathBuilder.buildClasspath(version, minecraftDir);

        File classpathFile = new File("classpath.txt");

        // 构建完整的命令列表
        List<String> command = new ArrayList<>();
        command.add("java");
        command.add("-Xmx2G");
        command.add("-Djava.library.path=" + minecraftDir + "/versions/" + version + "/" + version + "-natives");
        command.add("-jar");
        command.add("G:\\BlockLauncher\\BlockLauncher\\BlockLauncher\\minecraft-1.21.8-merged.jar");
        command.add("net.minecraft.client.main.Main");
        command.add("--username");
        command.add("Player");
        command.add("--version");
        command.add(version);
        command.add("--gameDir");
        command.add(minecraftDir);
        command.add("--assetsDir");
        command.add(minecraftDir + "/assets");
        command.add("--assetIndex");
        command.add("26");
        command.add("--uuid");
        command.add("00000000-0000-0000-0000-000000000000");
        command.add("--accessToken");
        command.add("null");
        command.add("--userType");
        command.add("legacy");

        System.out.println("执行命令: " + String.join(" ", command));

        if (command.isEmpty()) {
            throw new IllegalStateException("命令列表不能为空");
        }

        ProcessBuilder pb = new ProcessBuilder(command);
        pb.redirectErrorStream(true);
        Process process = pb.start();

        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }

        int exitCode = process.waitFor();
        System.out.println("进程退出代码: " + exitCode);
    }
}
