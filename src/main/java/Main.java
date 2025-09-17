import org.bkl.game.GameLauncher;
import org.bkl.game.MCVersionChecker;
import org.bkl.ui.FirstPage;

/**
 * @author LongWei
 * @date 2025/9/14 21:36
 */
public class Main {
    public static void main(String[] args) {
        // 获取本机mc路径
        String minecraftDir = System.getenv("APPDATA") + "\\.minecraft";

        // 初始化版本检查
        MCVersionChecker mcVersionChecker = new MCVersionChecker();
        // 初始化启动类
        GameLauncher gameLauncher = new GameLauncher("", "ID", minecraftDir);

        FirstPage.launchUi(args);
    }
}
