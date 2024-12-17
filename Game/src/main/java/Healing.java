import java.util.List;
import java.util.Random;

public class Healing extends Item{ //Added by me
    static int min;
    static int max;
    private static Random rn;

    public Healing(String name, List<String> types, String desc, String use, String act, int min_heal, int max_heal){
        super(name, types, desc, use, act);
        min = min_heal;
        max = max_heal;
        rn = new Random();
    }

    public static int Heal(){
        int var = min + rn.nextInt((max-min) + 1);
        return var;
    }
}
