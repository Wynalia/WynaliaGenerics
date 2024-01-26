package fr.wynalia.citedestemples.teams.classes;

import java.util.List;
import java.util.UUID;

public class Team {
    private final int id;
    private final String name;
    private int money;
    private final List<UUID> members;

    public Team(int id, String name, List<UUID> members) {
        this.id = id;
        this.name = name;
        this.members = members;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public List<UUID> getMembers() {
        return members;
    }
}
