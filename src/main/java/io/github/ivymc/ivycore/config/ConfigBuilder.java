package io.github.ivymc.ivycore.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;

import java.nio.file.Files;
import java.nio.file.Path;

public class ConfigBuilder {
    private final Path configPath;


    public static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    public ConfigBuilder(Path configPath) {
        this.configPath = FabricLoader.getInstance().getConfigDir().resolve(configPath);
    }
    public void loadConfig() throws Exception {
        if(Files.notExists(configPath)) {
            Files.createDirectories(configPath);
        }
    }
    public <T extends ConfigData> ConfigKey createConfigKey(Path path, Class<T> clazz) {
        return new ConfigKey<>(clazz, this.configPath.resolve(path), GSON);
    }
}