import java.util.*;

// Tracks global game state
// Useful for implementing state-based behavior (ex: see something new on second visit to room)

public class GameState {
    HashMap<Room, Boolean> visited = new HashMap<Room, Boolean>();
    String name;
    boolean finished;
    int health = 20;
    boolean battle = false;
    Weapon weapon;
    Room room;
    List<Item> inventory = new ArrayList<Item>();
    Map<String, Room> rooms; // global list of rooms
    Map<String, Item> items; // global list of known items

    // update state and check for winning condition
    public String update() {
        Chest ches = (Chest)items.get("treasure chest");
        if (!ches.locked){
            finished = true;
            String finaltext =  """
                                As the lid creaks open you see a bright light shining from inside.
                                After a moment, the chest starts shaking. You feel yourself being sucked into the chest.
                                You close your eyes, to prepare for what's coming...
                                Moments later, you slowly open your eyes...
                                

                                It was just a dream.
                                """;
            return finaltext;
        }else if(health <= 0){
            finished = true;
            String finaltext =  """
                                YOU DIED!
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
