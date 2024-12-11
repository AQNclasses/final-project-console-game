import java.util.Random;
import java.util.List;

public class Animal extends Item {
    int min;
    int max;
    private Random rn;
    private int health = 100;


    public Animal(String name, List<String> type, String desc, String use, String act, int min_damage, int max_damage) {
        super(name, type, desc, use, act);
        min = min_damage;
        max = max_damage;
        rn = new Random();
    }

    @Override
    public void use() {
        super.use();
        if (action.equals("drop")) {
            gameState.room.contents.add(this);
            gameState.inventory.remove(this);
        } else if (action.equals("feed")) {
            health += 10;
            gameState.playerScore += 5;
        } else if (action.equals("attack")) {
            int damage = attack();
            gameState.playerHealth -= damage;
        }
    }
    public int attack() {
        int var = min + rn.nextInt((max-min) + 1);
        return var;
    }

}
