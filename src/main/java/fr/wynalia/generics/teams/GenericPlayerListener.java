package fr.wynalia.generics.teams;

import fr.wynalia.generics.templates.Template;
import fr.wynalia.generics.templates.TemplateReader;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;

public class GenericPlayerListener implements Listener {
    private final Plugin plugin;

    public GenericPlayerListener(Plugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        Template template = TemplateReader.read(plugin, "config:chat.player-join");
        template.set("username", player.getName());
        event.setJoinMessage(template.render());
    }
}
