import java.util.Random;
import java.util.List;
import java.util.Map;

public class Weapon extends Item {
    int min;
    int max;
    private Random rn;

    public Weapon(String name, List<String> types, String desc, String use, String act, int min_damage, int max_damage) {
        super(name, types, desc, use, act);
        min = min_damage;
        max = max_damage;
        rn = new Random();
    }

    // uniformly distributed random number
    public int attack() { 
        System.out.println("The smell of iron hits your nose as you strike, hearing flesh give way to your blade. The sword is heavy, but it is your tool to conquer this challenge.");
        int var = min + rn.nextInt((max-min) + 1);
        this.used = false;
        return var;
    }
    public int throwWeapon(Room room, List<Item> inventory) {
        System.out.println("You throw excalibur, piercing the flesh of the wasp, your weapon clatters to the ground");
        int damage = 4 + rn.nextInt(5);
        inventory.remove(this);
        room.contents.add(this);
        return damage;
    }
}
