package fr.wynalia.citedestemples.teams;

import fr.wynalia.citedestemples.Main;
import fr.wynalia.citedestemples.teams.classes.Team;
import fr.wynalia.citedestemples.teams.classes.TeamManagement;
import org.bukkit.scheduler.BukkitRunnable;

public class TeamManager {
    public TeamManager(Main main) {
        TeamManagement.getInstance().loadTeams();

        new BukkitRunnable() {
            @Override
            public void run() {
                for (Team team : TeamManagement.getInstance().getTeams().values()) {
                    TeamManagement.getInstance().update(team.getId());
                }

                TeamManagement.getInstance().loadTeams();
            }
        }.runTaskTimer(Main.getInstance(), 0L, 18000L);
    }
}
