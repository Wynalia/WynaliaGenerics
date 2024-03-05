package fr.wynalia.generics.teams;

import java.util.List;
import java.util.UUID;

public class GenericTeam {
    private final int id;
    private final String name;
    private final String color;
    private final List<UUID> members;

    public GenericTeam(int id, String name, String color, List<UUID> members) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.members = members;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public List<UUID> getMembers() {
        return members;
    }
}
