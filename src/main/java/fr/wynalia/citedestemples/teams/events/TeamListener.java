package fr.wynalia.citedestemples.teams.events;

import fr.wynalia.citedestemples.Main;
import fr.wynalia.citedestemples.teams.classes.TeamManagement;
import fr.wynalia.templates.classes.Template;
import fr.wynalia.templates.classes.TemplateReader;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class TeamListener implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        Template template = TemplateReader.read(Main.getInstance().getDataFolder(), "config:chat.player-join");
        template.set("username", player.getName());
        event.setJoinMessage(template.render());
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        Template template = TemplateReader.read(Main.getInstance().getDataFolder(), "config:chat.player-quit");
        template.set("username", player.getName());
        event.setQuitMessage(template.render());
    }

    @EventHandler
    public void onTalk(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        Template template = TemplateReader.read(Main.getInstance().getDataFolder(), "config:chat.player-talk");

        if (player.isOp()) {
            template = TemplateReader.read(Main.getInstance().getDataFolder(), "config:chat.player-op-talk");
        } else {
            template.set("team", TeamManagement.getInstance().getTeamByMember(player.getUniqueId()).getName());
        }

        template.set("username", player.getName());
        template.set("message", event.getMessage());
        event.setFormat(template.render());
    }
}
