package fr.wynalia.citedestemples.teams.classes;

import fr.wynalia.citedestemples.Main;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TeamManagement {
    private static TeamManagement instance;
    private final Map<Integer, Team> teams = new HashMap<>();

    public static TeamManagement getInstance() {
        if (instance == null) instance = new TeamManagement();
        return instance;
    }

    public Map<Integer, Team> getTeams() {
        return teams;
    }

    public void loadTeams() {
        teams.clear();
        String sql = "SELECT * FROM cite_des_temples_teams t " +
                "LEFT JOIN cite_des_temples_players p " +
                "ON p.team_id = t.id";

        try (PreparedStatement statement = Main.getConnection().prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("t.id");
                String name = resultSet.getString("t.name");
                int money = resultSet.getInt("t.money");
                UUID member = UUID.fromString(resultSet.getString("p.uuid"));

                Team team = teams.getOrDefault(id, new Team(id, name, new ArrayList<>()));
                team.setMoney(money);
                team.getMembers().add(member);

                teams.put(id, team);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateTeam(int id) {
        Team team = getTeam(id);
        String sql = "UPDATE cite_des_temples_teams t" +
                "SET t.money = ? " +
                "WHERE t.id = ?";

        try (PreparedStatement statement = Main.getConnection().prepareStatement(sql)) {
            statement.setInt(1, team.getMoney());
            statement.setInt(2, team.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateTeams() {
        teams.values().forEach(team -> updateTeam(team.getId()));
    }

    public Team getTeam(int id) {
        return teams.values().stream()
                .filter(team -> team.getId() == id)
                .findAny()
                .orElse(null);
    }
}
