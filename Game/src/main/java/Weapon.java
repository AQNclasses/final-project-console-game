import java.util.Random;
import java.util.List;

public class Weapon extends Item {
    int minDamage;
    int maxDamage;

    public Weapon(String n, List<String> ts, String d, String u, String a, int min, int max) {
        super(n, ts, d, u, a);
        this.minDamage = min;
        this.maxDamage = max;
    }

    @Override
    public void use() {
        if (GameState.enemyPresent) {
            int damage = (int) (Math.random() * (maxDamage - minDamage + 1)) + minDamage;
            int buffedDamage = (int) (damage * GameState.buffMultiplier);
            GameState.enemyHealth -= buffedDamage;
            Game.printSlow("You swing the " + name + " and deal " + buffedDamage + " damage!");
            if (GameState.enemyHealth <= 0) {
                GameState.enemyPresent = false;
                GameState.room.enemyDefeated = true;
                Game.printSlow("The enemy collapses, his golden crown falling from his head. You are victorious!");
                GameState.room.contents.add(GameState.items.get("crown"));
            } else {
                Game.printSlow("The enemy has " + GameState.enemyHealth + " health left.");
            }
        } else {
            Game.printSlow("You inspect the " + name + ". " + desc);
        }
    }
}
