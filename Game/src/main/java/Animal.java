import java.util.Random;

import org.checkerframework.checker.units.qual.h;

import java.util.List;

public class Animal extends Item {
    int health;
    int maxHealth;
    boolean alive = true;

    public Animal(String name, List<String> type, String desc, String use, String act, int health) {
        super(name, type, desc, use, act);
        this.health = health;
        maxHealth = health;
    }

    // Dead or alive after taking damage
    public boolean attack(int damage) {
        health -= damage;
        alive = health > 0;
        return alive;
    }

    public void heal(int points) {
        health = Math.min(health + points, maxHealth);
    }

}
