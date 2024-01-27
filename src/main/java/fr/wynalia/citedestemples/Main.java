package fr.wynalia.citedestemples;

import fr.wynalia.database.classes.Database;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.sql.Connection;

public final class Main extends JavaPlugin {
    private static Main instance;
    private FileConfiguration configuration;
    private Database database;

    public Main() {
        saveDefaultConfig();
        instance = this;
    }

    @Override
    public void onLoad() {
        configuration = YamlConfiguration.loadConfiguration(new File(getDataFolder(), "config.yml"));
        database = Database.getInstance(configuration.getString("db.url"), configuration.getString("db.user"), configuration.getString("db.password"));
    }

    @Override
    public void onEnable() {
        database.connect();
    }

    @Override
    public void onDisable() {
        database.disconnect();
    }

    public static Main getInstance() {
        return instance;
    }

    public FileConfiguration getConfiguration() {
        return configuration;
    }

    public Connection getConnection() {
        return database.getConnection();
    }
}
