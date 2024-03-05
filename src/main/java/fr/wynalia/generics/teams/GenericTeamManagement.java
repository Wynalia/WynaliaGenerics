package fr.wynalia.generics.teams;

import fr.wynalia.database.classes.Database;
import org.bukkit.plugin.Plugin;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class GenericTeamManagement {
    private final Plugin plugin;
    private static GenericTeamManagement instance;
    private final Database database;
    private final Map<Integer, GenericTeam> teams = new HashMap<>();

    private GenericTeamManagement(Plugin plugin) {
        this.plugin = plugin;

        database = Database.getInstance(plugin.getConfig().getString("db.url"),
                plugin.getConfig().getString("db.user"),
                plugin.getConfig().getString("db.password"));

        database.connect();
    }

    public static GenericTeamManagement getInstance(Plugin plugin) {
        if (instance == null) instance = new GenericTeamManagement(plugin);
        return instance;
    }

    public Database getDatabase() {
        return database;
    }

    public void load() {
        String event = plugin.getConfig().getString("db.event");
        String sql = "SELECT * FROM " + event + "_teams t " +
                "LEFT JOIN " + event + "_players p " +
                "ON p.team_id = t.id";

        try (PreparedStatement statement = database.getConnection().prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("t.id");
                String name = resultSet.getString("t.name");
                String color = resultSet.getString("t.color");
                UUID member = UUID.fromString(resultSet.getString("p.uuid"));

                GenericTeam team = teams.getOrDefault(id, new GenericTeam(id, name, color, new ArrayList<>()));
                team.getMembers().add(member);

                teams.put(id, team);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public GenericTeam getTeam(UUID uuid) {
        return teams.values().stream()
                .filter(team -> team.getMembers().contains(uuid))
                .findAny()
                .orElse(null);
    }
}
