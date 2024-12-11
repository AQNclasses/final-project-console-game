import java.util.*;

// Tracks global game state
// Useful for implementing state-based behavior (ex: see something new on second visit to room)

public class GameState {
    GameState state;
    public int health = 100;
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
        return "";
    }
    public int healthMod(){
        return health;
    }

    public String update1() {
        if(health<=0)
        {
            System.out.println("Your health is below 0 you die");
            finished = true;
        }
        if  (inventory.contains(items.get("dragon"))){
            System.out.println("You ride the dragon into heaven clinging for you life on its scalely back happy to have found a pet \n You Win! ");
            finished = true;
        }
        if  (inventory.contains(items.get("dragon2"))){
            System.out.println("Thank you for returning to find me young master \n You Win!" );
            finished = true;
        }
        if  (inventory.contains(items.get("dragon3"))){
            System.out.println("You die a slow death in the stomach of the dragon for trying to tame it \n Goodbye...");
            take10Damage();
            Heal10Damage();
            finished = true;
        }
        
            
            
        if (inventory.contains(items.get("chair")) &&
            inventory.contains(items.get("book"))) {
            if (room.name.equals("Secret Room")) {
                    room = rooms.get("Last Room"); 
                    finished = true;
                    return """
                            You have unlocked the "Last Room" welcome in

                            You Win!!
                            """;
            }
        }
        return room.name;
    }
    public int take10Damage()
        {
            return health = health - 10;
        }
        public int Heal10Damage()
        {
            return health = health + 10;
        }
    public String myCallFunction(){
        inventory.add(items.get("mother"));
        return name;
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
