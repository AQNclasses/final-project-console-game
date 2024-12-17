import java.util.List;
import java.util.Map;

public class Room {
    public String name;
    public List<Item> contents;
    public Map<String, String> doors;
    public boolean locked;

    public Room(String name, List<Item> contents, Map<String, String> doors) {
        this.name = name;
        this.contents = contents;
        this.doors = doors;
        if (name == "Library" || name == "Greenhouse") {
            locked = true;
        } else {
            locked = false;
        }
    }

    public String toString() {
        return name;
    }
}
