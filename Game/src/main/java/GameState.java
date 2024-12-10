import java.util.*;

// Tracks global game state
// Useful for implementing state-based behavior (ex: see something new on second visit to room)

public class GameState {
    HashMap<Room, Boolean> visited = new HashMap<Room, Boolean>();
    String name;
    boolean finished;
    Room room;
    Weapon weapon;
    Enemy enemy = null;
    boolean dead = false;
    boolean playerDead = false;
    int myHealth;
    int health;
    List<Item> inventory = new ArrayList<Item>();
    Map<String, Room> rooms; // global list of rooms
    Map<String, Item> items; // global list of known items

    public String update() {
        if (room.contents.contains(items.get("lamborghini")) &&
            inventory.contains(items.get("car keys")) && items.get("lamborghini").used == true ){    
            finished = true;
            String finaltext =  """
                                As the sun dipped below the horizon, you slid into the plush, leather seat of your 
                                matte black Lamborghini SVJ, its engine growling to life with an exhilarating roar. 
                                The garage door opened, revealing a vast collection of wealth that was just as extraordinary 
                                as the supercar you now owned—an endless sea of gold, stacks of cash, and priceless treasures.
                                The world outside seemed to blur as you hit the accelerator, the SVJ catapulting forward, 
                                the roar of its engine echoing through the night. With every turn of the wheel and press of 
                                the pedal, you felt the weight of your newfound fortune, the kind that would keep you rich 
                                and powerful forever. No more worries, no more limitations—just the open road and the endless 
                                possibilities ahead. You couldn/’t help but smile, knowing that this wealth, this life, was 
                                yours to keep. Cha-ching! The sound of success, of a future secured, rang in your ears. 
                                As you sped away, you realized that nothing could ever hold you back again. You were free, 
                                forever wealthy, and unstoppable.
                                """;
            return finaltext;
        }
        else if (room.contents.contains(items.get("poison frog")) &&
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
        else if (dead == true) {
            finished = true;
            String finaltext = """
                    You win! You killed Steve!
                    """;
        }
        else if (playerDead == true) {
            finished = true;
            String finaltext = """
                    You DEAD! Steve beat the goofy outta you!
                    """;
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
