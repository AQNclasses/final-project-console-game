import java.util.List;
import java.util.Random;

public class Animal extends Item {
    int min_damage;
    int max_damage;
    int health;
    List<Item> drops;
    private Random rn;

    public Animal(String name, List<String> type, String desc, String use, String act, int min_damage, int max_damage, int health, List<Item> drops) {
        super(name, type, desc, use, act);
        this.drops = drops;
        this.min_damage = min_damage;
        this.max_damage = max_damage;
        rn = new Random();
        this.health = health;
    }

    public int attack() {
        int var = min_damage + rn.nextInt((max_damage-min_damage) + 1);
        return var;
    }
    
    

}
