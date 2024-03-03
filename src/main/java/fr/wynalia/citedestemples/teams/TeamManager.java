package fr.wynalia.citedestemples.teams;

import fr.wynalia.citedestemples.Main;
import fr.wynalia.citedestemples.teams.classes.TeamManagement;
import fr.wynalia.citedestemples.teams.commands.TeamCommand;
import fr.wynalia.citedestemples.teams.events.TeamListener;
import org.bukkit.scheduler.BukkitRunnable;

public class TeamManager {
    public TeamManager() {
        Main.getInstance().getCommand("team").setExecutor(new TeamCommand());

        Main.getInstance().getServer().getPluginManager().registerEvents(new TeamListener(), Main.getInstance());

        new BukkitRunnable() {
            @Override
            public void run() {
                TeamManagement.getInstance().updateTeams();
                TeamManagement.getInstance().loadTeams();
            }
        }.runTaskTimer(Main.getInstance(), 0L, 18000L);
    }
}
