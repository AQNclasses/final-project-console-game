import java.util.List;
import java.util.Map;

public class Room {
    public String name;
    public List<Item> contents;
    public Map<String, String> doors; // A map of door names to door descriptions
    static boolean isLocked; // Track if the room is locked or not

    // Constructor to initialize the room
    public Room(String name, List<Item> contents, Map<String, String> doors, boolean isLocked) {
        this.name = name;
        this.contents = contents;
        this.doors = doors;
        this.isLocked = isLocked; // Set the initial lock status of the room
    }

    // Unlock the room by changing the isLocked status
    public void unlock() {
        if (isLocked) {
            isLocked = false; // Unlock the door
            System.out.println("The door is now unlocked!");
        } else {
            System.out.println("The door is already unlocked.");
        }
    }

    // Check if the door is locked
    public boolean doorStatus(String name) {
        return isLocked;
    }

    // Get the door names (keys)
    public Map<String, String> getDoors() {
        return doors;
    }

    @Override
    public String toString() {
        return name;
    }
    //never got the is locked vs unlocked feature to work
}
