import java.util.*;

// Tracks global game state
// Useful for implementing state-based behavior (ex: see something new on second visit to room)

public class GameState {
    HashMap<Room, Boolean> visited = new HashMap<Room, Boolean>();
    String name;
    boolean finished;
    boolean sight = true;
    int exitState;
    Room room;
    List<Item> inventory = new ArrayList<Item>();
    Map<String, Room> rooms; // global list of rooms
    Map<String, Item> items; // global list of known items

    // update state and check for winning condition
    public String update() {
        if (room.contents.contains(items.get("poison frog")) &&
            room.contents.contains(items.get("book")) ){
            finished = true;
            exitState = 1;
            String finaltext =  """
                                The frog hops slowly over to the book and hops on top. Suddenly the book and the
                                frog begin to glow. The room starts spinning and you shut your eyes out of fear.
                                When you open them, you're back in the original basement room! When you open the
                                door, you find yourself back in the modern-day library. As you leave and the door
                                swings shut, you think you hear a faint \"ribbet\"....
                                """;
            return finaltext;
        }
        if (((Vehicle)items.get("car")).running){
            finished = true;
            exitState = 1;
            String finaltext =  """
                                You were able to leave the house and make a quick get away.
                                It was certainly a weird house with some interesting things, 
                                but the smell of smoke was starting to get to you.
                                """;
            return finaltext;
        }
        if (items.size() == 0){
            finished = true;
            exitState = 0;
            String finaltext =  """
                                What have you done! That potion has caused all of the items to vanish.
                                Without any items there is no way to complete the game.
                                That's it you had a chance and you blew it. All the rooms are empty.
                                There were two other endings that would have ended better for you, but
                                noooooo, you had to go and drink the disappearing poisons and leave nothing left.
                                """;
            return finaltext;
        }
        return "";
    }

    public GameState(String name) {
        this.name = name;
        finished = false;
        exitState = -1;
        LoadYAML yl = new LoadYAML();
        rooms = yl.rooms;
        items = yl.items;
        room = rooms.get("Starting Room");
        visited.put(room, true);
        inventory.add(items.get("book"));
    }
}
