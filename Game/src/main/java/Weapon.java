import java.util.Random;

public class Weapon extends Item {
    int min;
    int max;
    private Random rn;

    public Weapon(String name, String type, String desc, String use, String act, int min_damage, int max_damage) {
        super(name, type, desc, use, act);
        min = min_damage;
        max = max_damage;
        rn = new Random();
    }

    // uniformly distributed random number
    public int attack() {
        int var = min + rn.nextInt((max-min) + 1);
        return var;
    }

    @Override
    public String inspect(){
        String message = super.inspect() + ". Damage range: " + min + " - " + max;
        return message;
    }
}
