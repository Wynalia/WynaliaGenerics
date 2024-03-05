package fr.wynalia.generics.teams;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public class GenericTeamManager {
    public static void register(Plugin plugin) {
        Bukkit.getPluginManager().registerEvents(new GenericPlayerListener(plugin), plugin);
        GenericTeamManagement.getInstance(plugin);
    }

    public static void unregister(Plugin plugin) {
        GenericTeamManagement.getInstance(plugin).getDatabase().disconnect();
    }
}
