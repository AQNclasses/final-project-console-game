import java.util.List;

public class Weapon extends Item {
    int damage;


    public Weapon(String name, List<String> types, String desc, String use, String act, int damage) {
        super(name, types, desc, use, act);
       this.damage = damage;
    }

    public int attack() {
        return damage;
    }

}
