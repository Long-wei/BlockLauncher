import org.bkl.game.GameLauncher;
import org.bkl.ui.FirstPage;

/**
 * @author LongWei
 * @date 2025/9/14 21:36
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("HelloBlockLauncher");


        // 获取本机mc路径
        String minecraftDir = System.getenv("APPDATA") + "\\.minecraft";
        try {
            GameLauncher.gameLauncher("1.21.8", minecraftDir);
        } catch (Exception e) {
            e.printStackTrace();
        }
        FirstPage.launchUi(args);
    }
}
