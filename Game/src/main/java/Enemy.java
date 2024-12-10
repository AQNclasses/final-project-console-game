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

    
    public int attack() {
        return minDamage + rn.nextInt((maxDamage - minDamage) + 1);
    }

    // Method to receive damage
    public boolean takeDamage(int damage, GameState state) {
        health -= damage;
        if (health <= 0) {
            return true; // enemy is defeated
        } else {
            System.out.println("The " + name + " takes " + damage + " damage and has " + health + " health remaining.");
            return false; // enemy is still alive
        }
    }

    @Override
    public void use(GameState state) {
        if (use != null) {
            System.out.println(use); 
        }
        int damage = attack();
        state.health -= damage; // Rreduce players health
        System.out.println("You take " + damage + " damage!");
        System.out.println("Your current health is now: " + state.health);
    }
    public void counterAttack(GameState state) {
        int damage = this.attack(); // call enemys attack
        state.health -= damage;
        System.out.println("The " + this.name + " counterattacks and deals " + damage + " damage to you!");
        System.out.println("Your current health is now: " + state.health);
        if (state.health <= 0) {
            System.out.println("You have died. Game Over.");
            state.finished = true;
        }
    }
}
