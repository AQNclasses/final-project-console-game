import java.util.List;
import java.util.Random;

public class Enemy extends Item{
    int min;
    int max;
    private Random rn;
    int health;
    public Enemy(String name, List<String> types, String desc, String use, String act, int min_damage, int max_damage, int stevesHealth){
        super(name, types, desc, use, act);
        health = stevesHealth;
        min = min_damage;
        max = max_damage;
        rn = new Random();
    }
    public int attack() {
        int var = min + rn.nextInt((max-min) + 1);
        return var;
    }
}