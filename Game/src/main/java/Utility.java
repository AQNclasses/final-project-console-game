import java.util.Random;
import java.util.List;

public class Utility extends Item {
    int min;
    int max;
    private Random rn;

    public Utility(String name, List<String> types, String desc, String use, String act, int min_damage, int max_damage) {
        super(name, types, desc, use, act);
        min = min_damage;
        max = max_damage;
        rn = new Random();
    }

    // attack
    public int attack() {
        int var = min + rn.nextInt((max-min) + 1);
        return var;
    }

    public void use(GameState state) {
        if (use != null) {
            System.out.println(use); 
        }
       
    }

}
