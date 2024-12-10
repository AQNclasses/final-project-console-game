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
                                When you open them, you're back in the original basement room! When you open the
                                door, you find yourself back in the modern-day library. As you leave and the door
                                swings shut, you think you hear a faint \"ribbet\"....
                                """;
            return finaltext;
        }
        
   
        if(room.contents.contains(items.get("red pole")) &&
            room.contents.contains(items.get("Unnamed Kid")) ) {
                finished = false;
                String text = """
                            'thanks for getting my  red power pole for me, I kinda lost it and mr.popos gets real mad when i lose things'
                            the young boy reaches reaches around a grabs a small empty bottle from his pocket and hands it to you
                            'this might help you drink the water in the fountain without getting mr.popo mad'
                            the young boy shivers at the idea of upsetting his gracious teacher
                            
                            
                            """;

                            inventory.add(items.get("Empty Sacred Container"));
                            room.contents.remove("red pole");
                
                        
                       
              return text;
            }
            
       /*  if(items.get("Empty Glass Vial").isUsed() && room.contents.contains(items.get"Holy Fountain")){
            finished = false;
            String text "You put the";
*/
        
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