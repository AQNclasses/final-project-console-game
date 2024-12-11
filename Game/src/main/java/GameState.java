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
    int playerHealth;
    int playerHunger;
    int hungerCounter;
    boolean died;

    // update state and check for winning condition
    public String update() {
        playerHunger -= 5;

        if (playerHunger == 0 || playerHealth == 0){
            died = true;
        }

        if (died){
            finished = true;
            String finaltext = """
                    You have died.
                    """;
            return finaltext;
        }
        if (room.contents.contains(items.get("poison frog")) &&
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
        }
        if (room == rooms.get("Treasure Room")){
            finished = true;
            String finalText = """
                    You unlock and push open the glistening gold door. As it opens it reveals a small room
                    with a table in the middle. On the table there is an old and dingy looking book. Disappointed
                    yet hopeful, you approach the table and open the book. After dusting it off, you make 
                    a realization; This is better than gold, it's an introductory course to data structures in 
                    Java. Your smile lights up the moldy room and your mind races with thoughts of self-balancing
                    binary trees and abstract data types. As you turn to go home you're already imagining how
                    much fun it will be to finally be able to write a depth-first search algorithm for a directed
                    graph. But don't get ahead of yourself, you should probably start with a linked list.
                    """;
            return finalText;
        }
        if (room == rooms.get("Starting Room")){
            finished = false;
            String roomText = """
                    You find yourself in a small and dimmly lit room. Mossy grey bricks serve as walls. Here is
                    where the dungeon begins.
                    """;
            return roomText;
        }
        if (room == rooms.get("Closet")){
            finished = false;
            String roomText = """
                    You enter a small janitorial closet, finding it surprising that dungeons have janitors.
                    """;
            return roomText;
        }
        if (room == rooms.get("Kitchen")){
            finished = false;
            String roomText = """
                    You enter the kitchen. It doesn't look like it was ever in very good shape, and now is no
                    exception.
                    """;
            return roomText;
        }
        if (room == rooms.get("Dungeon")){
            finished = false;
            String roomText = """
                    You find yourself in one of the many dungeon chambers. Hopefully you find something
                    worthwhile.
                    """;
            return roomText;
        }
        if (room == rooms.get("Dungeon 2")){
            finished = false;
            String roomText = """
                    You find yourself in another dungeon chamber.
                    """;
            return roomText;
        }
        if (room == rooms.get("Dungeon 3")){
            finished = false;
            String roomText = """
                    You find yourself in yet another dungeon chamber.
                    """;
            return roomText;
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
        inventory.add(items.get("bread"));
        playerHealth = 100;
        playerHunger = 100;
        died = false;
    }
}
