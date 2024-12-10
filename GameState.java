import java.util.*;

// Tracks global game state
// Useful for implementing state-based behavior (ex: see something new on second visit to room)
// File path: OneDrive\School\WWU School Work\WWU Year 3\Q1\CS241\final project\final-project-console-game-main

public class GameState {
    HashMap<Room, Boolean> visited = new HashMap<Room, Boolean>();
    String name;
    boolean finished;
    Room room;
    List<Item> inventory = new ArrayList<Item>();
    Map<String, Room> rooms; // global list of rooms
    Map<String, Item> items; // global list of known items

    // update state and check for winning condition
    public String update() {
        if (room.contents.contains(items.get("poison frog")) &&
                room.contents.contains(items.get("book"))) {
            finished = true;
            String finaltext = """
                    The frog hops slowly over to the book and hops on top. Suddenly the book and the
                    frog begin to glow. The room starts spinning and you shut your eyes out of fear.
                    When you open them, you're back in the original basement room! When you open the
                    door, you find yourself back in the modern-day library. As you leave and the door
                    swings shut, you think you hear a faint \"ribbet\"....";
                    """;
            return finaltext;
        }
        // The new ending to the game
        if (room.name.equals("Secret Room") && inventory.contains(items.get("Golden Nugget"))) {
            finished = true;
            String secretEnding = """
                    As you step into the Secret Room, the Golden Nugget in your hand starts to glow.
                    The room trembles, and you feel a rush of excitement. You have unlocked the ultimate treasure!
                    This is the secret ending of your journey.
                    """;
            return secretEnding;
        }
        return "";
    }

    // Moves between rooms
    public String moveToRoom(String doorColor) {
        if (!room.doors.containsKey(doorColor)) {
            return "There is no door of that color in this room.";
        }

        String targetRoomName = room.doors.get(doorColor);
        Room targetRoom = rooms.get(targetRoomName);

        // Checks if the room is locked
        if (targetRoom.locked) {
            boolean hasKey = inventory.stream().anyMatch(item -> item.name.equals(targetRoom.key));
            if (!hasKey) {
                return "The door to " + targetRoomName + " is locked. You need the " + targetRoom.key + " to unlock it.";
            }

            // Makes sure you use the correct key to unlock the room
            inventory.removeIf(item -> item.name.equals(targetRoom.key));
            targetRoom.locked = false;
            return "You used the " + targetRoom.key + " to unlock the door to " + targetRoomName + ".";
        }

        // Move to the new room
        room = targetRoom;
        visited.put(room, true);
        return "You have entered " + room.name + ".";
    }

    public GameState(String name) {
        this.name = name;
        finished = false;
        LoadYAML yl = new LoadYAML();
        rooms = yl.rooms;
        items = yl.items;
        room = rooms.get("Starting Room");
        visited.put(room, true);
        inventory.add(items.get("book")); // Adds an initial item if required
    }
}
