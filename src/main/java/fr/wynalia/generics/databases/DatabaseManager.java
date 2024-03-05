package fr.wynalia.generics.databases;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {
    private static DatabaseManager instance;
    private static Database database;
    private static Connection connection;

    private DatabaseManager(Plugin plugin) {
        FileConfiguration config = plugin.getConfig();
        database = new Database(config.getString("db.url"), config.getString("db.user"), config.getString("db.password"));
    }

    public static DatabaseManager getInstance(Plugin plugin) {
        if (instance == null) instance = new DatabaseManager(plugin);
        return instance;
    }

    public static void connect() {
        try {
            connection = DriverManager.getConnection(database.getUrl(), database.getUser(), database.getPassword());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void disconnect() {
        try {
            if (connection != null && !connection.isClosed()) connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() {
        return connection;
    }
}
