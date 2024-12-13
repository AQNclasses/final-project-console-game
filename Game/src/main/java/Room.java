import java.util.List;
import java.util.Map;

// Added in locked doors
public class Room {
    public String name;
    public List<Item> contents;
    public Map<String,String> doors;
    public Map<String, String> lockedDoors;

    public Room(String name, List<Item> contents, Map<String, String> doors, Map<String, String> lockedDoors) {
        this.name = name;
        this.contents = contents;
        this.doors = doors;
        this.lockedDoors = lockedDoors;
    }

    public String toString() {
        return name;
    }
}
