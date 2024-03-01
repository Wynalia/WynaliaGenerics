package fr.wynalia.citedestemples.teams;

import fr.wynalia.citedestemples.Main;
import fr.wynalia.citedestemples.teams.classes.TeamManagement;
import org.bukkit.scheduler.BukkitRunnable;

public class TeamManager {
    public TeamManager(Main main) {
        TeamManagement.getInstance().loadTeams();

        new BukkitRunnable() {
            @Override
            public void run() {
                TeamManagement.getInstance().updateTeams();
                TeamManagement.getInstance().loadTeams();
            }
        }.runTaskTimer(main, 0L, 18000L);
    }
}
