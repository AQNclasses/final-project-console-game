import java.util.*;

// Tracks global game state
// Useful for implementing state-based behavior (ex: see something new on second visit to room)

public class GameState {
    HashMap<Room, Boolean> visited = new HashMap<Room, Boolean>();
    String name;
    boolean finished;
    boolean won = true;
    Room room;
    List<Item> inventory = new ArrayList<Item>();
    Map<String, Room> rooms; // global list of rooms
    Map<String, Item> items; // global list of known items

    static HashMap<String, Boolean> isLocked = new HashMap<String, Boolean>(); // stores door lock state
    static HashMap<String, Integer> health = new HashMap<String, Integer>(); // stores entity health
    
       
    
    
        // update state and check for winning condition
        public String update() {
            
            for (Item item : inventory){
                //System.out.println(item.name);
                if(item.used){
                    switch (item.types.get(0)) {
                        case Weapon:
                            if (item instanceof Weapon) {
                                health.put("enemy1" , health.get("enemy1") - ((Weapon) item).attack());
                                }
                        case Healing:
                            if (item instanceof Healing) {
                                health.put("player" , health.get("player") + ((Healing) item).heal());
                                inventory.remove(item);
                                }
                        case Key:
                            if (item instanceof Key) {
                            ((Key) item).unlock();
                            }
                    
                        case Animal:
                            if (item instanceof Animal) {
                                health.put("enemy1" , health.get("enemy1") - ((Animal) item).attack());

                                }
                            
                        case Plant:
                            if (item instanceof Plant) {
                                health.put("player" , health.get("player") + ((Plant) item).eat());
                                inventory.remove(item);
                                }

                        case Potion:
                            if (item instanceof Potion) {
                                room = rooms.get(((Potion) item).drink());
                                inventory.remove(item);
                                
                            }
                        default:
                            
                    }
                    item.used = false;
                    break;
                }

                
            }

            if(health.get("player") <= 0){
                won = false;
                finished = true;

            } else if(health.get("enemy1") <= 0){
                finished = true;
                String finaltext =  """
                                    You defeated the enemy!
                                    """;
                return finaltext;
                
                
            } else if (room.contents.contains(items.get("poison frog")) &&
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
            } else if (inventory.contains(items.get("trophy"))) {
                finished = true;
                String finaltext =  """
                                    You have found the trophy, your mission is now complete!
                                    """;
                return finaltext;
            }
            return "";
        }
    
        public static boolean isLocked(String door){
            return isLocked.get(door); 
        }

        public static void unlock(String door){
            isLocked.put(door, false);
        }
        
        public static int getHealth(String entity){
            return health.get(entity);
        }


    public GameState(String name) {
        isLocked.put("green", false); 
        isLocked.put("blue", false); 
        isLocked.put("red", true);

        health.put("player", 20);
        health.put("enemy1", 10);


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
