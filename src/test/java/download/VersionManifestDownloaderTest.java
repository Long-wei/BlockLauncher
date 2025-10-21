package download;

import org.bkl.download.VersionManifestDownloader;
import org.bkl.game.MinecraftPath;
import org.bkl.util.HashUtil;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.nio.file.Path;

public class VersionManifestDownloaderTest {
    @Test
    public void downloadVersionManifest() {
        VersionManifestDownloader.downloadVersionManifest("1.21.10");
    }

    @Test
    public void downloadClientJar() {
        VersionManifestDownloader.downloadClientJar("1.21.10");
    }

    @Test
    public void hashUt() {
        System.out.println(HashUtil.verifyHash(Path.of(MinecraftPath.getMinecraftPath() + "/versions/1.21.10/1.21.10.jar"), "d3bdf582a7fa723ce199f3665588dcfe6bf9aca8", "SHA-1"));
    }
}
