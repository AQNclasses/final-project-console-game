import java.util.List;
import java.util.ArrayList;

// Define types of items that share actions / properties
enum ItemType {
    Weapon,
    Healing,
    Key,
    Animal,
    Plant,
    Item,
    Stationary;

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
            case "Stationary":
                return ItemType.Stationary;
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

    Item(String name, List<String> types, String desc, String use, String action) {
        this.name = name;
        for (String ty : types) this.types.add(ItemType.toType(ty));
        this.desc = desc;
        this.use = use;
        this.action = action;
    }

    public String inspect() {
        String alltypes = "";
        for (ItemType t: types) alltypes += t.name() + " ";
        String message = "This is a " + this.name + ", a kind of " + alltypes + ". Description: " + this.desc;
        return message;
    }

    public void use(GameState state) {
        if (use != null) {
            System.out.println(use); // Print the use text from YAML
        }
        // Default behavior (if any)
        used = true;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
