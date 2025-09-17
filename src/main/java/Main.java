import org.bkl.game.GameLauncher;
import org.bkl.game.MCVersionChecker;
import org.bkl.game.MinecraftPath;
import org.bkl.os.SystemUtils;
import org.bkl.ui.FirstPage;

/**
 * @author LongWei
 * @date 2025/9/14 21:36
 */
public class Main {
    public static void main(String[] args) {
        SystemUtils systemUtils = new SystemUtils();
        MinecraftPath minecraftPath = new MinecraftPath();
        MCVersionChecker mcVersionChecker = new MCVersionChecker();
        GameLauncher gameLauncher = new GameLauncher("", "ID", MinecraftPath.minecraftPath);
        FirstPage.launchUi(args);
    }
}
