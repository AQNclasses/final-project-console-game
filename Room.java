import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Room {
    public String name;
    public List<Item> contents;
    public Map<String, String> doors;
    public boolean locked;
    public boolean hasGoldenNugget;
    public String key;

    public Room(String name, List<Item> contents, Map<String, String> doors, boolean locked) {
        this.name = name;
        this.contents = contents;
        this.doors = doors;
        this.locked = locked;
        this.key = "";  // Makes sure the key is initialized properly
        this.updateContents(); // Makes sure hasGoldenNugget is set correctly
    }

    public boolean isLocked() {
        return locked;
    }

    public void unlock() {
        locked = false;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    // Updates hasGoldenNugget based on the rooms contents
    public void updateContents() {
        this.hasGoldenNugget = contents.stream().anyMatch(item -> item.name.equals("Golden Nugget"));
    }

    public String interact() {
        // If the room is locked and needs a key
        if (locked) {
            return "The door to Secret Room is locked. You need a key to unlock it.";
        }        

        // If the room is the Secret Room and you have the Golden Nugget
        if (this.name.equals("Secret Room") && this.hasGoldenNugget) {
            return "You find a treasure hidden in the Secret Room! This is the secret ending!";
        }        

        return "You explore the room but find nothing special.";
    }

    @Override
    public String toString() {
        return name;
    }
}
