package download;

import org.bkl.download.VersionManifestDownloader;
import org.junit.jupiter.api.Test;

public class VersionManifestDownloaderTest {
    @Test
    public void downloadVersionManifest() {
        VersionManifestDownloader.downloadVersionManifest("1.20.4");
    }
}
