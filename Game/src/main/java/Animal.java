
import java.util.List;
import java.util.Random;

public class Animal extends Item {

    int min;
    int max;
    private Random rn;

    public Animal(String name, List<String> type, String desc, String use, String act, int min_damage, int max_damage) {
        super(name, type, desc, use, act);
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
        return super.inspect() + " It's a cute little guy";
    }

}
