package fr.wynalia.citedestemples;

import fr.wynalia.citedestemples.teams.TeamManager;
import fr.wynalia.database.classes.Database;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.sql.Connection;

public final class Main extends JavaPlugin {
    private static Database database;

    public Main() {
        saveDefaultConfig();
    }

    @Override
    public void onLoad() {
        FileConfiguration config = YamlConfiguration.loadConfiguration(new File(getDataFolder(), "config.yml"));
        database = Database.getInstance(config.getString("db.url"), config.getString("db.user"), config.getString("db.password"));
    }

    @Override
    public void onEnable() {
        database.connect();

        new TeamManager(this);
    }

    @Override
    public void onDisable() {
        database.disconnect();
    }

    public static Connection getConnection() {
        return database.getConnection();
    }
}
