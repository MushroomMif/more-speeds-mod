package com.github.mushroommif.morespeeds;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;

import java.io.*;

public class MoreSpeedsMod implements ModInitializer {

    public static Config config;

    @Override
    public void onInitialize() {
        File configFile = FabricLoader.getInstance().getConfigDir()
                .resolve("more-speeds.json")
                .toFile();
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        if (!configFile.exists()) {
            try {
                configFile.createNewFile();

                config = Config.createDefault();
                String defaultConfigJson = gson.toJson(config);

                try (FileWriter writer = new FileWriter(configFile)) {
                    writer.write(defaultConfigJson);
                }
            } catch (IOException e) {
                throw new RuntimeException("Failed to create more speeds config file", e);
            }

            return;
        }

        try (FileReader reader = new FileReader(configFile)) {
            config = gson.fromJson(reader, Config.class);
            config.validate();
        } catch (IOException e) {
            throw new RuntimeException("Failed to read more speeds config file", e);
        }
    }
}
