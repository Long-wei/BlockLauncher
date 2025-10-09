package org.bkl.modloader;


import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.bkl.game.MinecraftPath;

import java.io.File;
import java.io.FileReader;

public class FabricRemover {
    public FabricRemover () {
    }
    // net.minecraft.client.main.Main
    public static void remove(String mcVersion) {
        File versionFolder = new File(MinecraftPath.getMinecraftPath(), "/versions/");
        File[] files = versionFolder.listFiles();

        for (File f : files) {
            if (f.getName().contains(mcVersion)) {
                File f1 = new File(f.getPath(), "/" + mcVersion + ".json");

                if (!f1.exists()) {
                    break;
                }

                try(FileReader reader = new FileReader(f1)) {
                    JsonObject asJsonObject = JsonParser.parseReader(reader).getAsJsonObject();

                    if (asJsonObject.has("mainClass")) {
                        asJsonObject.addProperty("mainClass", "net.minecraft.client.main.Main");
                    }

                    if (asJsonObject.has("libraries")) {
                        JsonArray jsonArray = asJsonObject.get("libraries").getAsJsonArray();

                        for (int i = 0; i < jsonArray.size(); i++) {
                            JsonObject asJsonObjectLibraries = jsonArray.get(i).getAsJsonObject();

                            if (asJsonObjectLibraries.has("name")) {
                                String name = asJsonObjectLibraries.get("name").getAsString();
                                if (name.contains("fabric")) {
                                    jsonArray.remove(i);
                                    continue;
                                }
                            }

                            if (asJsonObjectLibraries.has("url")) {
                                String url = asJsonObjectLibraries.get("url").getAsString();
                                if (url.contains("fabric")) {
                                    jsonArray.remove(i);
                                    continue;
                                }
                            }

                        }

                        System.out.println(jsonArray.toString());
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
