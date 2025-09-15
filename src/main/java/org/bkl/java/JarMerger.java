package org.bkl.java;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.jar.*;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;

public class JarMerger {

    private static final String MINECRAFT_MAIN_CLASS = "net.minecraft.client.main.Main";
    private static final String MANIFEST_ENTRY = "META-INF/MANIFEST.MF";

    // Log4j相关包名（需要优先保留Minecraft自带版本）
    private static final List<String> LOG4J_PACKAGES = Arrays.asList(
            "org/apache/logging/log4j/",
            "org/slf4j/",
            "com/mojang/logging/"
    );

    /**
     * 合并JAR包并处理Log4j版本冲突
     */
    public static String mergeAndGetCommand(String version, String minecraftDir) throws IOException {
        String outputJarName = "minecraft-" + version + "-merged.jar";
        File outputJar = new File(System.getProperty("user.dir"), outputJarName).getCanonicalFile();
        String outputJarPath = outputJar.getAbsolutePath();

        validateOutputPath(outputJar);

        Path tempDir = Files.createTempDirectory("minecraft-jar-merge");

        try {
            // 收集JAR文件，区分版本JAR和普通依赖（优先保留版本JAR中的Log4j）
            List<File> jarFiles = collectJarFiles(version, minecraftDir);
            File versionJar = findVersionJar(jarFiles, version, minecraftDir);

            System.out.println("发现 " + jarFiles.size() + " 个JAR包，开始合并...");

            // 先解压普通依赖（排除Log4j相关文件）
            for (File jarFile : jarFiles) {
                if (jarFile.equals(versionJar)) continue; // 版本JAR最后处理
                unzipJar(jarFile, tempDir, true, true); // 第四个参数：排除Log4j文件
            }

            // 最后解压版本JAR（包含正确的Log4j版本，不排除）
            unzipJar(versionJar, tempDir, true, false);

            // 创建合并JAR
            createExecutableJar(tempDir, outputJarPath);

            if (!outputJar.exists() || outputJar.length() == 0) {
                throw new IOException("JAR文件生成失败！路径: " + outputJarPath);
            }
            System.out.println("JAR包生成成功，大小: " + formatFileSize(outputJar.length()));
            System.out.println("文件路径: " + outputJarPath);

            return generateCommand(version, minecraftDir, outputJarPath);
        } finally {
            deleteDirectory(tempDir.toFile());
        }
    }

    /**
     * 找到版本JAR（包含正确的Log4j版本）
     */
    private static File findVersionJar(List<File> jarFiles, String version, String minecraftDir) {
        File versionJar = new File(minecraftDir + "/versions/" + version + "/" + version + ".jar");
        for (File jar : jarFiles) {
            if (jar.equals(versionJar)) {
                return jar;
            }
        }
        throw new IllegalArgumentException("版本JAR未找到在列表中");
    }

    /**
     * 验证输出路径
     */
    private static void validateOutputPath(File outputJar) throws IOException {
        if (!outputJar.getParentFile().exists() && !outputJar.getParentFile().mkdirs()) {
            throw new IOException("无法创建输出目录: " + outputJar.getParentFile().getAbsolutePath());
        }

        if (!outputJar.getParentFile().canWrite()) {
            throw new IOException("没有写入权限: " + outputJar.getParentFile().getAbsolutePath());
        }

        if (outputJar.exists() && !outputJar.delete()) {
            throw new IOException("旧JAR文件无法删除，请关闭正在使用该文件的程序");
        }
    }

    /**
     * 收集JAR文件
     */
    private static List<File> collectJarFiles(String version, String minecraftDir) throws IOException {
        List<File> jarFiles = new ArrayList<>();

        Path librariesDir = Paths.get(minecraftDir, "libraries");
        if (!Files.exists(librariesDir)) {
            throw new FileNotFoundException("依赖库目录不存在: " + librariesDir);
        }

        try (Stream<Path> pathStream = Files.walk(librariesDir)) {
            pathStream
                    .filter(Files::isRegularFile)
                    .filter(p -> p.toString().toLowerCase().endsWith(".jar"))
                    .forEach(jarPath -> jarFiles.add(jarPath.toFile()));
        }

        File versionJar = new File(minecraftDir + "/versions/" + version + "/" + version + ".jar");
        if (!versionJar.exists()) {
            throw new FileNotFoundException("版本JAR不存在: " + versionJar.getAbsolutePath());
        }
        jarFiles.add(versionJar);

        return jarFiles;
    }

    /**
     * 解压JAR文件（新增Log4j过滤）
     * @param excludeLog4j 是否排除Log4j相关文件
     */
    private static void unzipJar(File jarFile, Path targetDir, boolean excludeManifest, boolean excludeLog4j) throws IOException {
        try (JarFile jar = new JarFile(jarFile)) {
            Enumeration<JarEntry> entries = jar.entries();

            while (entries.hasMoreElements()) {
                JarEntry entry = entries.nextElement();
                String entryName = entry.getName();

                // 排除Manifest和签名文件
                if ((excludeManifest && MANIFEST_ENTRY.equals(entryName)) ||
                        (entryName.startsWith("META-INF/") &&
                                (entryName.endsWith(".SF") || entryName.endsWith(".RSA") || entryName.endsWith(".DSA")))) {
                    continue;
                }

                // 排除Log4j相关文件（如果需要）
                if (excludeLog4j && isLog4jRelated(entryName)) {
                    continue;
                }

                // 跳过空目录和特殊文件
                if (entryName.isEmpty() || entryName.endsWith("/")) {
                    continue;
                }

                Path entryPath = targetDir.resolve(entryName);
                Files.createDirectories(entryPath.getParent());

                if (!entry.isDirectory()) {
                    try (InputStream in = jar.getInputStream(entry);
                         OutputStream out = Files.newOutputStream(entryPath,
                                 StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)) {

                        byte[] buffer = new byte[8192];
                        int bytesRead;
                        while ((bytesRead = in.read(buffer)) != -1) {
                            out.write(buffer, 0, bytesRead);
                        }
                    }
                }
            }
        }
    }

    /**
     * 判断是否为Log4j相关文件
     */
    private static boolean isLog4jRelated(String entryName) {
        for (String pkg : LOG4J_PACKAGES) {
            if (entryName.startsWith(pkg)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 创建可执行JAR（修复getEntry()方法不存在的问题）
     */
    private static void createExecutableJar(Path sourceDir, String outputJarPath) throws IOException {
        Manifest manifest = new Manifest();
        manifest.getMainAttributes().put(Attributes.Name.MANIFEST_VERSION, "1.0");
        manifest.getMainAttributes().put(Attributes.Name.MAIN_CLASS, MINECRAFT_MAIN_CLASS);
        // 添加Class-Path确保依赖可见
        manifest.getMainAttributes().put(Attributes.Name.CLASS_PATH, ".");

        // 使用Set记录已添加的条目，解决JarOutputStream没有getEntry()方法的问题
        Set<String> addedEntries = new HashSet<>();

        try (JarOutputStream jarOut = new JarOutputStream(new BufferedOutputStream(
                new FileOutputStream(outputJarPath)), manifest)) {

            Files.walk(sourceDir)
                    .filter(Files::isRegularFile)
                    .forEach(filePath -> {
                        try {
                            String entryName = sourceDir.relativize(filePath).toString().replace(File.separator, "/");

                            // 跳过Maven元数据
                            if (entryName.startsWith("META-INF/maven/") ||
                                    entryName.startsWith("META-INF/gradle/") ||
                                    MANIFEST_ENTRY.equals(entryName)) {
                                return;
                            }

                            // 检查是否已添加（使用Set来跟踪，替代getEntry()方法）
                            if (addedEntries.contains(entryName)) {
                                return;
                            }

                            JarEntry entry = new JarEntry(entryName);
                            entry.setTime(Files.getLastModifiedTime(filePath).toMillis());
                            jarOut.putNextEntry(entry);

                            Files.copy(filePath, jarOut);
                            jarOut.closeEntry();

                            // 将已添加的条目加入Set
                            addedEntries.add(entryName);
                        } catch (IOException e) {
                            System.err.println("添加文件失败: " + filePath.getFileName() + " - " + e.getMessage());
                        }
                    });
        }
    }

    /**
     * 生成执行命令
     */
    private static String generateCommand(String version, String minecraftDir, String jarPath) {
        String nativesDir = new File(minecraftDir, "versions/" + version + "/" + version + "-natives")
                .getAbsolutePath().replace("/", File.separator);
        String assetsDir = new File(minecraftDir, "assets").getAbsolutePath().replace("/", File.separator);
        String gameDir = new File(minecraftDir).getAbsolutePath().replace("/", File.separator);

        // 从版本JSON中获取正确的assetIndex（示例为26，实际可能不同）
        String assetIndex = "26";

        StringBuilder command = new StringBuilder();
        command.append("java -Xmx2G ")
                .append("-Djava.library.path=\"").append(nativesDir).append("\" ")
                .append("-jar \"").append(jarPath).append("\" ")
                .append(MINECRAFT_MAIN_CLASS).append(" ")
                .append("--username Player ")
                .append("--version ").append(version).append(" ")
                .append("--gameDir \"").append(gameDir).append("\" ")
                .append("--assetsDir \"").append(assetsDir).append("\" ")
                .append("--assetIndex ").append(assetIndex).append(" ")
                .append("--uuid 00000000-0000-0000-0000-000000000000 ")
                .append("--accessToken null ")
                .append("--userType legacy");

        return command.toString();
    }

    /**
     * 格式化文件大小
     */
    private static String formatFileSize(long bytes) {
        if (bytes < 1024) return bytes + " B";
        if (bytes < 1024 * 1024) return String.format("%.1f KB", bytes / 1024.0);
        return String.format("%.1f MB", bytes / (1024.0 * 1024));
    }

    /**
     * 删除目录
     */
    private static void deleteDirectory(File directory) {
        if (directory == null || !directory.exists()) return;

        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    deleteDirectory(file);
                } else {
                    file.delete();
                }
            }
        }
        directory.delete();
    }

    public static void main(String[] args) {
        try {
            String version = "1.21.8";
            String minecraftDir = System.getenv("APPDATA") + "/.minecraft";

            String runCommand = mergeAndGetCommand(version, minecraftDir);

            System.out.println("\n生成的执行命令:");
            System.out.println("----------------------------------------");
            System.out.println(runCommand);
            System.out.println("----------------------------------------");
        } catch (IOException e) {
            System.err.println("操作失败: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
