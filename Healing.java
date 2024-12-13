import java.util.Random;
import java.util.List;

public class Healing extends Item {
    int min;
    int max;
    private Random rn;

    public Healing(String name, List<String> types, String desc, String use, String act, int min_heal, int max_heal) {
        super(name, types, desc, use, act);
        min = min_heal;
        max = max_heal;
        rn = new Random();
    }

    // uniformly distributed random number
    public int heal() {
        int var = min + rn.nextInt((max-min) + 1);
        return var;
    }

}
