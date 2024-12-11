import java.util.List;
import java.util.Map;

public class Room {
    public String name;
    public List<Item> contents;
    public Map<String,String> doors;
    public boolean locked;
    public String floor;
    public String floorModifier;

    public Room(String name, List<Item> contents, Map<String, String> doors, Boolean lock, String floor) {
        this.name = name;
        this.contents = contents;
        this.doors = doors;
        this.locked = lock;
        this.floor = floor;
        floorModifier = "";
    }

    public String toString() {
        return name;
    }
}
