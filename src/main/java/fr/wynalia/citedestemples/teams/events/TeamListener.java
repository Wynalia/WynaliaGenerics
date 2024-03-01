package fr.wynalia.citedestemples.teams.events;

import fr.wynalia.citedestemples.Main;
import fr.wynalia.templates.classes.Template;
import fr.wynalia.templates.classes.TemplateReader;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class TeamListener implements Listener {
    private final Main main;

    public TeamListener(Main main) {
        this.main = main;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        Template template = TemplateReader.read(main.getDataFolder(), "config:chat.player-join");
        template.set("username", player.getName());
        event.setJoinMessage(template.render());
    }
}
