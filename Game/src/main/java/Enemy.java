import java.util.List;
import java.util.Random;

public class Enemy extends Item {
    int health;
    int minDamage;
    int maxDamage;
    private Random rn;

    public Enemy(String name, List<String> types, String desc, String use, String action, int health, int minDamage, int maxDamage) {
        super(name, types, desc, use, action);
        this.health = health;
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
        rn = new Random();
    }

    // Random damage between min and max (inclusive)
    public int attack() {
        return minDamage + rn.nextInt((maxDamage - minDamage) + 1);
    }

    // Method to receive damage
    public boolean takeDamage(int damage, GameState state) {
        health -= damage;
        if (health <= 0) {
            System.out.println("The " + name + " has been defeated!");
            return true; // Enemy is defeated
        } else {
            System.out.println("The " + name + " takes " + damage + " damage and has " + health + " health remaining.");
            return false; // Enemy is still alive
        }
    }

    @Override
    public void use(GameState state) {
        if (use != null) {
            System.out.println(use); // Print the use text from YAML
        }
        int damage = attack();
        System.out.println("The " + this.name + " attacks you!");
        System.out.println("You take " + damage + " damage!");
        state.health -= damage; // Reduce player's health
        System.out.println("Your current health is now: " + state.health);
    }
}
