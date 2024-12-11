import java.util.List;
import java.util.ArrayList;

// Define types of items that share actions / properties
enum ItemType {
    Weapon,
    Healing,
    Key,
    Animal,
    Plant,
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
    String associatedDoor;

    Item(String n, List<String> ts, String d, String u, String a) {
        name = n;
        for (String ty : ts)
            types.add(ItemType.valueOf(ty));
        desc = d;
        use = u;
        action = a;
    }

    public String getAssocciatedDoor() {
        return associatedDoor;
    }

    public String useKey(GameState state) {
        // check if key can unlock door
        if (state.room.doors.containsKey(associatedDoor)) {
            return "You use the " + name + " to unlock the " + associatedDoor + " door.";
        }
        return "This key doesn't seem to work here.";
    }

    // add perform action for items
    public String performAction(GameState state) {
        if (action == null) {
            return "No action available.";
        }

        switch (action) {
            case "drink":
                return handleDrink(state);
            case "sit":
                return handleSit(state);
            case "drop":
                return handleDrop(state);
            case "unlock":
                return useKey(state);
            default:
                return "Did you think something was going to happen?";

        }
    }

    // apply contructor that is called when user wants to use item specifically
    protected String handleDrink(GameState state) {
        used = true;
        return "you drink the " + name + ".";
    }

    protected String handleSit(GameState state) {
        if (state.inventory.contains(this)) {
            state.inventory.remove(this);
            return "You sit on the chair and take a moment to rest.  The chair breaks under your weight and is now unusable";
        }
        return "the chair isn't in your inventory";
    }

    protected String handleDrop(GameState state) {
        state.room.contents.add(this);
        state.inventory.remove(this);
        return "You drop the " + name + ".";
    }

    public String inspect() {
        String alltypes = "";
        for (ItemType t : types)
            alltypes += t.name() + " ";
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
}
