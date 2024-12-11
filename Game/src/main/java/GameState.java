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
    int health = 10;
    Room currentRoom;
    // update state and check for winning condition
    public String update() {
        if(health <= 0){
            finished = true;
             String deathtext = """
                     it seems like your health droped below zero I dont really know how that happened but your probably gonne need to run gradle again
                     """;
                     return deathtext;



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
        
   
        if(room.contents.contains(items.get("red pole")) &&
            room.contents.contains(items.get("Unnamed Kid")) ) {
                finished = false;
                String text = """
                            'thanks for getting my  red power pole for me, I kinda lost it and mr.popos gets real mad when i lose things'
                            the young boy reaches  around and grabs a small empty bottle from his pocket and hands it to you
                            'this might help you drink the water in the fountain without getting mr.popo mad'
                            the young boy shivers at the idea of upsetting his gracious teacher

                            You Have Recieved the: Empty Sacred Container
                            
                            
                            """;

                            inventory.add(items.get("Empty Sacred Container"));
                            room.contents.remove(items.get("red pole"));
                
                        
                       
              return text;
            }
            if(room.contents.contains(items.get("Empty Sacred Container")) &&
            room.contents.contains(items.get("Holy Fountain")) ) {
                finished = false;
                String text = """
                        as you place this empty container into the fountain you see it fill it up with this clean crystaline water, drinking this might give you the chance to open that heavy door
                        

                        You have Recieved the: Filled Sacred Container
                        """;
                            
                            
                            
                            

                            inventory.add(items.get("Filled Sacred Container"));
                            room.contents.remove(items.get("Empty Sacred Container"));
                        
                       
              return text;
            }
            if(room.contents.contains(items.get("Dragon Balls")) && 
            room.contents.contains((items.get("book") ))){
                finished = true; 
                String wish ="""
                                    As you say the words rm rf a mighy dragon rises in golden glory from the dragon balls, mr pop laughs in the corner because he is current reading an
                                    xtcf comic about Tar Commands and knows you probably wouldnt understand it.
                                    the green dragon adorned with orange scales and mighty horns and angelic strands of hair understand your wish and your need to go home
                                    the dragons eyes begin to glow and you reappear back at your desk at the Csci Lab.
                        
                        """;
                        return wish;

            }
       /*  if(items.get("Empty Glass Vial").isUsed() && room.contents.contains(items.get"Holy Fountain")){
            finished = false;
            String text "You put the";
*/
        
        return "";
    }
    public Room getCurrentRoom() {
        return currentRoom;
    }

    // Use an item in the current room (apply healing or unlock door)
    public void useItem(Item item) {
        item.use(this);  // Pass the current game state to the item's use method
    }

    // Set the current room
    public void setCurrentRoom(Room room) {
        currentRoom = room;
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