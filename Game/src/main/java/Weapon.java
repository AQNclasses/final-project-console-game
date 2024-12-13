import java.util.List;
import java.util.Random;

public class Weapon extends Item {
    int min;
    int max;
    private Random rn;

    public Weapon(String name, List<String> types, String desc, String use, String act, int min_damage, int max_damage) {
        super(name, types, desc, use, act);
        this.min = min_damage;
        this.max = max_damage;
        this.rn = new Random();
    }

    // uniformly distributed random number
    public int attack() {
        return min + rn.nextInt((max - min) + 1);
    }

    @Override
    public String inspect() {
        return super.inspect() + " It deals damage between " + min + " and " + max + ".";
    }
}