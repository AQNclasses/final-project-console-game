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
}

public abstract class Item {
    String name;
    List<ItemType> types = new ArrayList<>();
    String desc;
    String useText;
    String action;
    Boolean used = false;

    Item(String n, List<String> ts, String d, String u, String a) {
        name = n;
        for (String ty : ts) types.add(ItemType.valueOf(ty));
        desc = d;
        useText = u;
        action = a;
    }


    public String inspect() {
        StringBuilder allTypes = new StringBuilder();
        for (int i = 0; i < types.size(); i++) {
            allTypes.append(types.get(i).name());
            if (i < types.size() - 1) {
                allTypes.append(", ");
            }
        }
        String message = "This is a " + this.name + ", a kind of " + allTypes + ". Description: " + this.desc;
        return message;
    }


    public abstract void use(GameState gameState);

    @Override
    public String toString() {
        return this.name;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Item other = (Item) obj;
        if (name == null) {
            return other.name == null;
        } else
            return name.equals(other.name);
    }

    @Override
    public int hashCode() {
        if (name == null)
            return 0;
        return name.hashCode();
    }
}
