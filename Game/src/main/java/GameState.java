import java.awt.print.Printable;
import java.util.*;

// Tracks global game state
// Useful for implementing state-based behavior (ex: see something new on second visit to room)

public class GameState {
    HashMap<Room, Boolean> visited = new HashMap<Room, Boolean>();
    String name;
    boolean finished;
    boolean dead;
    Room room;
    List<Item> inventory = new ArrayList<Item>();
    Map<String, Room> rooms; // global list of rooms
    Map<String, Item> items; // global list of known items
    int health = 100;

    public int Health(){
        return health;
    }
    public int changehealth(int change){
        health += change;
        return health;
    }
   
    
    public void Use(Item item){
        if(item.name.equals("excalibur") || item.name.equals("posion frog") ||
         item.name.equals("katana" )|| item.name.equals("spatula" )|| 
        item.name.equals("pick_of_destiny" )|| item.name.equals("dagger") || item instanceof Weapon){
            Weapon weapon = (Weapon) item;
            int damage = weapon.attack();
            changehealth(-damage);
        }
        else if(item.name.equals("another_mysterious_potion" )|| item instanceof Potion){
            Potion healingpotion = (Potion) item;
            changehealth(10);
        }
        else if (item.name.equals("mysterious_potion")){
            dead = true;
        }
    }

    // update state and check for winning condition
    public String update() {
        if(dead || health < 0){
            if(inventory.contains(items.get("bonus_life_potion"))){
                dead = false;
                return """
                        you drink your bonus life potion.    
                        you are now back in the game!
                        """;
            }
           else{ finished = true;
            String finaltext = """
                    YOU DIED
                    """;
                return finaltext;
           }
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
                                
                                You win!
                                """;
            return finaltext;
        }
        if((!(inventory.contains(items.get("old_mystery_key"))) || 
        !(inventory.contains(items.get("pick_of_destiny")))) && 
        room.name.equals("Outside")){
            room = rooms.get("Magic room");
        }
        if(inventory.contains(items.get("pick_of_destiny")) && 
        inventory.contains(items.get("old_mystery_key")) &&
        room.name.equals("Outside")){
            finished = true;
            String finaltext =
                            """
                                you unlock the door that leads to a beautiful scenery full of great views and a refreshing air that 
                                brings back memories of a simplier time. equiped with the pick of destiny you are no longer afraid of 
                                whats to come. Your future is bright with the ability to become the greatest rock guitarist that 
                                has every lived. with a plan to travel the world carlessly playing for the world, you take off
                                and start a great adventure into the unknown.
                                
                                You win!
                                """;
                                
            return finaltext;
            }
        

        return "";
    }

    public boolean haskey(){
        if(inventory.contains(items.get("pick_of_destiny")) && 
        inventory.contains(items.get("old_mystery_key")) ){
            return true;
        }
        return false;
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
