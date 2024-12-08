import java.util.List;
import java.util.Random;

public class Animal extends Item {
    int min;
    int max;
    int health;
    private Random rn;

    public Animal(String name, List<String> type, String desc, String use, String act, boolean lock, int min_damage, int max_damage, int health) {
        super(name, type, desc, use, act, lock);
        min = min_damage;
        max = max_damage;
        this.health = health;
        rn = new Random();
    }

    // uniformly distributed random number
    public int attack() {
        int var = min + rn.nextInt((max-min) + 1);
        return var;
    }

}
