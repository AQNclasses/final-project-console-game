import java.util.List;

public class Key extends Item {
    static String doorName; // The door that this key unlocks

    // Constructor for Key items
    public Key(String name, List<String> types, String desc, String use, String action, String doorName) {
        super(name, types, desc, use, action);
        this.doorName = doorName;
    }

    // Override use method to unlock the door in the room
    @Override
    public void use(GameState state) {
        // Get the current room from the game state (assuming GameState has a reference to the current room)
        Room currentRoom = state.currentRoom;
        // Check if the current room has a door that matches the key's doorName
        if (currentRoom.doors.containsKey(doorName)) {
            currentRoom.unlock(); // Unlock the door in the room
            System.out.println("You use the " + this.name + " to unlock the " + doorName + " door.");
        } else {
            System.out.println("This key doesn't seem to fit any doors in this room.");
        }

        used = true; // Mark the item as used

    }
    public String getDoorName() {
        return doorName;  // Return the door name
    }
    //most of this ended up being not used to make the locked doors work
    
}
