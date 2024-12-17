import java.util.List;
import java.util.ArrayList;

// Define types of items that share actions / properties
enum ItemType {
    Weapon,
    Healing,
    Key,
    Animal,
    Plant,
    Damage,
    Phone,
    Dragon,
    Couch,
    
    Item;

    public static ItemType toType(String s) {
        switch (s) {
            case "Weapon":
                return ItemType.Weapon;
            case "Healing":
                return ItemType.Healing;
            case "Key":
                return ItemType.Key;
            case "Animal":
                return ItemType.Animal;
            case "Plant":
                return ItemType.Plant;
            case "Couch":
                return ItemType.Damage;
            case "Dragon":
                return ItemType.Dragon;
            case "Phone":
                return ItemType.Phone;
            default:
                return ItemType.Item;

        }
    }
}

// Object defining how general items work in your game
// All other item classes should inherit this class
public class Item {
    String name;
    ArrayList<ItemType> types = new ArrayList<ItemType>();
    String desc;
    String use;
    String action;
    Boolean used = false;

    Item(String n, List<String> ts, String d, String u, String a) {
        name = n;
        for (String ty : ts) types.add(ItemType.valueOf(ty));
        desc = d;
        use = u;
        action = a;
    }

    public String inspect() {
        String alltypes = "";
        for (ItemType t: types) alltypes += t.name() + " ";
        String message = "This is a " + this.name + ", a kind of " + alltypes + ". Description: " + this.desc;
        return message;
    }

    public void use() {
        used = true;
    }
    @Override
    public String toString() {
        return this.name;
    }
    
    public class Dragon extends Item{
        public int fireBreathDamage;
        public int flightSpeed;
        public Dragon(String name, List<String> types, String desc, String use, String action, int fireBreathDamage, int flightSpeed) {
            super(name, types, desc, use, action);
            this.fireBreathDamage = fireBreathDamage;
            this.flightSpeed = flightSpeed;

        }
    public int getFireBreathDamage() {
        return fireBreathDamage;
    }

    public int getFlightSpeed() {
        return flightSpeed;
    }


    @Override
    public String toString() {
        return this.name;
    }

}
}
