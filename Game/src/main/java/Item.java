import java.util.List;
import java.util.ArrayList;

// Define types of items that share actions / properties
enum ItemType {
    Key,
    Animal,
    Plant,
    Tool,
    Item;

    public static ItemType toType(String s) {
        switch (s) {
            case "Tool":
                return ItemType.Tool;
            case "Key":
                return ItemType.Key;
            case "Animal":
                return ItemType.Animal;
            case "Plant":
                return ItemType.Plant;
            default:
                return ItemType.Item;
        }
    }
}

// Object defining how general items work in your game
// All other item classes should inherit this class
public class Item {
    String name;
    String type;
    String desc;
    String use;
    String action;
    Boolean used = false;

    Item(String n, String t, String d, String u, String a) {
        name = n;
        type = t;
        desc = d;
        use = u;
        action = a;
    }

    public String inspect() {
        String message = "This is a " + this.name + ", a kind of " + type + ". Description: " + this.desc;
        return message;
    }

    public void use() {
        used = true;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
