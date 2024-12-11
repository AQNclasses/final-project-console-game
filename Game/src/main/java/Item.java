// Define types of items that share actions / properties
enum ItemType {
    Weapon,
    Healing,
    Key,
    Animal,
    Plant,
    Cleaner,
    Cover,
    Note,
    Vehicle,
    Potion,
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
            case "Cleaner":
                return ItemType.Cleaner;
            case "Cover":
                return ItemType.Cover;
            case "Note":
                return ItemType.Note;
            case "Vehicle":
                return ItemType.Vehicle;
            case "Potion":
                return ItemType.Potion;
            default:
                return ItemType.Item;
        }
    }
}

// Object defining how general items work in your game
// All other item classes should inherit this class
public class Item {
    String name;
    ItemType type;
    String desc;
    String use;
    String action;
    Boolean used = false;

    Item(String n, String t, String d, String u, String a) {
        name = n;
        type = ItemType.valueOf(t);
        desc = d;
        use = u;
        action = a;
    }

    public String inspect() {
        String message = "This is a " + this.name + ", a kind of " + type + ". Description: " + this.desc + ".";
        return message;
    }

    public void use(GameState state) {
        used = true;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
