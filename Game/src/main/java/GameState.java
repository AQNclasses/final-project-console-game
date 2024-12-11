import java.util.*;

// Tracks global game state
// Useful for implementing state-based behavior (ex: see something new on second visit to room)

public class GameState {
    HashMap<Room, Boolean> visited = new HashMap<Room, Boolean>();
    String name;
    public boolean finished;
    public Room room;
    public List<Item> inventory = new ArrayList<Item>();
    public Map<String, Room> rooms; // global list of rooms
    public Map<String, Item> items; // global list of known items

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
                    swings shut, you think you hear a faint \"ribbet\"....

                    You Win!
                    """;
            return finaltext;
        }

        if (inventory.contains(items.get("mug")) && items.get("mug").used == true) {
            finished = true;
            String deathMessage = """
                    You drink the unknown liquid from the mug.  As you are drinking the liquid you feel a weird sensation, you start to hallucinate...

                    *You fall unconcious*

                    You Lose.
                    """;
            return deathMessage;
        }

        if (room.name.equals("Super Secret Lair") &&
                inventory.contains(items.get("gilded 2h sword"))) {
            finished = true;
            String endingtext = """
                    You pick up the sword with one hand.  Though you can lift it, the weight feels unstable.
                    You steady the blade with your second hand, and sudden pulse of energy ripples through you.
                    Everything begins to glow, gold flares start to radiate from the sword,
                    casting the shadows around you into oblivion.

                    Your feet lift off the ground as an immense power fills the air.
                    The final remnants of your surrounding dissolve into the light.

                    *The screen fades to black*

                    The End.

                    You Win!
                    """;
            return endingtext;
        }
        return "";
    }

    public GameState(String name) {
        this.name = name;
        finished = false;
        LoadYAML yl = new LoadYAML();
        rooms = yl.rooms;
        items = yl.items;
        room = rooms.get("Starting Room");
        visited.put(room, true);
        inventory.add(items.get("book"));
    }

    // locked door implementation
    public boolean lockedDoor(String doorType) {
        if (doorType.equals("blue")) {
            return inventory.stream()
                    .anyMatch(item -> item.types.contains(ItemType.Key) && item.name.equals("rustic bronze key"));
        }
        return false;
    }

    public void moveRoom(String doorType) {
        if (room.doors.containsKey(doorType)) {
            // check if the door is locked
            if (doorType.equals("blue") && !openLockedDoor(doorType)) {
                System.out.println("The " + doorType + " door is locked.");
                return;
            }

            // if room is not, move to next room
            String nextRoomName = room.doors.get(doorType);
            Room nextRoom = rooms.get(nextRoomName);
            if (room != null) {
                room = nextRoom;
                visited.put(nextRoom, true);
                System.out.println("You step through the " + doorType + " door.");
                System.out.println("You are now in the " + nextRoom.name + ".");
            } else {
                System.out.println("This is not a valid way.");
            }
        }
    }

    // open the locked door method
    public boolean openLockedDoor(String doorType) {
        return inventory.stream().anyMatch(item -> item.types.contains(ItemType.Key) &&
                item.name.equals("rustic bronze key"));
    }
}
