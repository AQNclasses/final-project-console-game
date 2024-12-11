import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    int health = 10;
    int shield = 0;
    boolean yellowUnlock;

    // update state and check for winning condition
    public String update() {
        if(health == 0){ // New Ending
            finished = true;
            String finaltext = """
                                As you get hit, you feel an intense pain in your chest. You fall to the ground and
                                your eyes slowly close and you die. Maybe you should have been more careful...
                                """;
            return finaltext;
        }else if (room.contents.contains(items.get("poison frog")) &&
            room.contents.contains(items.get("book")) ){
            finished = true;
            String finaltext =  """
                                The frog hops slowly over to the book and hops on top. Suddenly the book and the
                                frog begin to glow. The room starts spinning and you shut your eyes out of fear.
                                When you open them, you're back in the original basement room! When you open the
                                door, you find yourself back in the modern-day library. As you leave and the door
                                swings shut, you think you hear a faint \"ribbet\"....
                                """;
            return finaltext;
        }else if(room.name.equals("Escape")){ // New Ending
            finished = true;
            String finaltext = """
                                You slowly open the yellow door. A bright light shines through. You try and look
                                through but it's blinding, so you close your eyes. Next thing you know, without 
                                your control your body lifts off the ground and pulls you into the door. You panic
                                and quickly open your eyes to see what happened. You're back in the original 
                                basement room! One of the library workers notices you and tells you that you 
                                shouldn't be down here and urges you to leave. Was that real..?
                                """;
            return finaltext;
        }
        return "";
    }

    public GameState(String name) {
        this.name = name;
        finished = false;
        yellowUnlock = false;
        LoadYAML yl = new LoadYAML();
        rooms = yl.rooms;
        items = yl.items;
        room = rooms.get("Starting Room");
        visited.put(room, true);
        inventory.add(items.get("book"));
    }
}
