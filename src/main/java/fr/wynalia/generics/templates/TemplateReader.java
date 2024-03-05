package fr.wynalia.generics.templates;

import org.bukkit.plugin.Plugin;

public class TemplateReader {
    public static Template read(Plugin plugin, String path) {
        String[] splitPath = path.split(":");

        if (splitPath.length != 2) return NotFoundTemplateError();

        if(!plugin.getConfig().contains(splitPath[1])) return NotFoundMessageError();

        return new Template(plugin.getConfig().getString(splitPath[1]));
    }

    private static Template NotFoundTemplateError() {
        return new Template("Le fichier de template requis n'existe pas.");
    }

    private static Template NotFoundMessageError() {
        return new Template("Le template requis n'existe pas.");
    }
}
