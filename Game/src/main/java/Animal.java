import java.util.Random;
import java.util.List;

public class Animal extends Item {
    int health;
    int min;
    int max;
    private Random rn;

    public Animal(String name, List<String> types, String desc, String use, String action, int health, int minDamage, int maxDamage) {
        super(name, types, desc, use, action);
        this.health = health;
        this.min = minDamage;
        this.max = maxDamage;
        rn = new Random();
    }

    // Random damage between min and max (inclusive)
    public int attack() {
        return min + rn.nextInt((max - min) + 1);
    }

    public boolean takeDamage(int damage, GameState state) {
        health -= damage;
        
        if (health <= 0) {
            return true; // Animal is dead
        }
        System.out.println("The " + this.name + " has " + this.health + " health.");
        return false; // Animal is still alive
    }

    @Override
    public void use(GameState state) {
        if (use != null) {
            System.out.println(use); // Print the use text from YAML
        }
        int damage = attack();
        System.out.println("The " + this.name + " lashes out at you!");
        System.out.println("You take " + damage + " damage!");
        state.health -= damage; // Reduce player's health
        System.out.println("Your current health is now: " + state.health);
    }
}

