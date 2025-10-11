import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.bkl.game.MinecraftPath;
import org.bkl.modloader.FabricChecker;
import org.bkl.modloader.FabricRemover;
import org.bkl.modloader.FabricVersionFetcher;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileReader;


public class FabricTest {

    @Test
    public void versionJonsIncludeMainClass() throws Exception{
        new MinecraftPath();

        File versionJson = new File(MinecraftPath.minecraftPath + "/versions/1.21.7/1.21.7.json");
        FileReader reader = new FileReader(versionJson);
        JsonObject asJsonObject = JsonParser.parseReader(reader).getAsJsonObject();
        System.out.println(asJsonObject.get("mainClass").getAsString());

    }


    @Test
    public void isFabricInstall() {
        boolean fabricInstall = FabricChecker.isFabricInstall("1.21.7");
        System.out.println(fabricInstall);
    }


    @Test
    public void removeFabric() {
        FabricRemover.remove("1.21.1");
    }

    @Test
    public void getCompatibleFabricLoaders () {
        System.out.println(FabricVersionFetcher.getCompatibleFabricLoaders("1.20.1"));
    }

}
