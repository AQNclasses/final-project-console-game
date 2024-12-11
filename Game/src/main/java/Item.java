import java.util.List;
import java.util.ArrayList;

// Define types of items that share actions / properties
enum ItemType {
    Weapon,
    Healing,
    Key,
    Enemy,
    Note,
    Container,
    Decor,
    Item;

    public static ItemType toType(String s) {
        switch (s) {
            case "Weapon":
                return ItemType.Weapon;
            case "Healing":
                return ItemType.Healing;
            case "Key":
                return ItemType.Key;
            case "Enemy":
                return ItemType.Enemy;
            case "Note":
                return ItemType.Note;
            case "Container":
                return ItemType.Container;
            case "Decor":
                return ItemType.Decor;
            default:
                return ItemType.Item;
        }
    }
}

// Object defining how general items work in your game
// All other item classes should inherit this class
abstract public class Item {
    String name;
    ArrayList<ItemType> types = new ArrayList<ItemType>();
    String desc;
    String use;
    String action;
    boolean used = false;
    int varNum;

    Item(String n, List<String> ts, String d, String u, String a) {
        name = n;
        for (String ty : ts) types.add(ItemType.valueOf(ty));
        desc = d;
        use = u;
        action = a;
        varNum = 0;
    }

    Item(Item other) {
        name = other.name + "";
        types = other.types;
        desc = other.desc + "";
        use = other.use + "";
        action = other.action + "";
    }

    public String inspect() {
        String alltypes = "";
        for (ItemType t: types) alltypes += t.name() + " ";
        String message = this.name + ": " + this.desc;
        return message;
    }

    public String use() {
        used = true;
        return use;
    }

    abstract public String pickUp(GameState s);

    public String defaultPickUp(GameState s){
        s.room.contents.remove(this);
        s.rooms.put(s.room.name, s.room);
        s.inventory.add(this);
        return "\n" + desc + "\nYou obtained the " + name + "!";
    }

    abstract public String use(GameState s);
    
    @Override
    public String toString() {
        return this.name;
    }
}
