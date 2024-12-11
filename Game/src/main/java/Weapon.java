import java.util.Random;
import java.util.List;

public class Weapon extends Item {
    int min;
    int max;
    private Random rn;
    private int durability = 100;

    public Weapon(String name, List<String> types, String desc, String use, String act, int min_damage, int max_damage) {
        super(name, types, desc, use, act);
        min = min_damage;
        max = max_damage;
        rn = new Random();
    }

    @Override
    public void use() {
        super.use();
        if (action.equals("attack")) {
            int damage = attack();
            gameState.enemyHealth -= damage;
            durability -= 10;
            if (durability <= 0) {
                gameState.inventory.remove(this);
                gameState.destroyedWeapons.add(this.name);
            }
        } else if (action.equals("repair")) {
            durability = Math.min(100, durability + 25);
            gameState.playerGold -= 10;
        }
    }
    public int attack() {
        int var = min + rn.nextInt((max-min) + 1);
        return var;
    }

}
