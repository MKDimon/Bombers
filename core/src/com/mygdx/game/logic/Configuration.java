package com.mygdx.game.logic;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class Configuration {
    private final String pathToTextureBomberOne;
    private final String pathToTextureDeadBomberOne;
    private final String pathToTextureBomberTwo;
    private final String pathToTextureDeadBomberTwo;
    private final String pathToMap;

    public Configuration(String pathToTextureBomberOne, String pathToTextureDeadBomberOne,
                         String pathToTextureBomberTwo, String pathToTextureDeadBomberTwo,
                         String pathToMap) {
        this.pathToTextureBomberOne = pathToTextureBomberOne;
        this.pathToTextureBomberTwo = pathToTextureBomberTwo;
        this.pathToTextureDeadBomberOne = pathToTextureDeadBomberOne;
        this.pathToTextureDeadBomberTwo = pathToTextureDeadBomberTwo;
        this.pathToMap = pathToMap;
    }

    public static Configuration loadConfigs(String pathToConfigFile) {
        Gson gson = new Gson();
        try {
            JsonReader reader = new JsonReader(new FileReader(pathToConfigFile));
            return gson.fromJson(reader, Configuration.class);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getPathToTextureBomberOne() {
        return pathToTextureBomberOne;
    }

    public String getPathToTextureDeadBomberOne() {
        return pathToTextureDeadBomberOne;
    }

    public String getPathToTextureBomberTwo() {
        return pathToTextureBomberTwo;
    }

    public String getPathToTextureDeadBomberTwo() {
        return pathToTextureDeadBomberTwo;
    }

    public String getPathToMap() { return pathToMap; }
}
