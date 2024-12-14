import java.util.*;

// Tracks global game state
// Useful for implementing state-based behavior (ex: see something new on second visit to room)

public class GameState {

    HashMap<Room, Boolean> visited = new HashMap<Room, Boolean>();

    //the name of the room
    String name;

    boolean finished;

    //the current room you're in
    Room room;

    //you're inventory
    List<Item> inventory = new ArrayList<Item>();
    Map<String, Room> rooms; // global list of rooms
    Map<String, Item> items; // global list of known items

    ArrayList<String> barredItems = new ArrayList<String>();
    ArrayList<String> lockedRooms = new ArrayList<String>();
    ArrayList<Item> usedKeys = new ArrayList<Item>();

    public boolean canGrab(String item){

        //if the item is in the list of barred items you can't grab it
        if(barredItems.contains(item) == true){

            return false;

        } else {

            return true;

        }

    }

    // update state and check for winning condition
    public String update() {

        //Win Condition
        if (room.contents.contains(items.get("poison frog")) && room.contents.contains(items.get("book")) ){

            finished = true;
            String finaltext =  """
                                The frog hops slowly over to the book and hops on top. Suddenly the book and the
                                frog begin to glow. The room starts spinning and you shut your eyes out of fear.
                                When you open them, you're back in the original basement room! When you open the
                                door, you find yourself back in the modern-day library. As you leave and the door
                                swings shut, you think you hear a faint \"ribbet\"....
                                """;
            return finaltext;
        }

        if (room.toString().compareTo("Boss Room") == 0 && inventory.contains(items.get("excalibur")) != true){

            finished = true;
            String finaltext =  """
                                As you walk into the room you are met with a giant knight, 
                                you reach for your weapon but before you can you are smashed into the ground by the knight.\n
                                Everything goes black.
                                """;
            return finaltext;
        }

        if (room.toString().compareTo("Boss Room") == 0 && inventory.contains(items.get("excalibur")) == true){

            finished = true;
            String finaltext =  """
                                As you walk into the room you are met with a giant knight.\n 
                                You pull out the mighty excalibur, the knight rushes at you but you cut him down in one slice.\n
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

        barredItems.add("red key");
        barredItems.add("green key");
        barredItems.add("blue key");
        barredItems.add("copper sword");
        barredItems.add("vines");

        lockedRooms.add("Bright Room");

    }
}
