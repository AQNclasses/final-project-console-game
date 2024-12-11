import java.util.*;

// Tracks global game state
// Useful for implementing state-based behavior (ex: see something new on second visit to room)

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
            room.contents.contains(items.get("book")) ){
            finished = true;
            String finaltext =  """
                                The frog hops slowly over to the book and hops on top. Suddenly the book and the
                                frog begin to glow. The room starts spinning and you shut your eyes out of fear.
                                When you open them, you're back in your room! As you begin to ponder what just 
                                happened, you think you hear a faint \"ribbet\".... (Main ending)
                                """;
            return finaltext;
        }
        if (room.contents.contains(items.get("excalibur"))&&
        room.contents.contains(items.get("rock")) ){
            finished = true;
            String finaltext =  """
                                You take the sword out of its scabbard and look down at the blade. 
                                You realise that it is the mighty excalibur. The rock sitting in the 
                                back of the room catches your eye, as you notice a slit in the stone, 
                                just about the size of a sword. You stick you blade into the rock and 
                                with a flash of light you are returned to your bedroom. (Secret ending)
                                """;
            return finaltext;
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
}
