// Ending Location

import java.util.*;

// Tracks global game state
// Useful for implementing state-based behavior (ex: see something new on second visit to room)

public class GameState {
    HashMap<Room, Boolean> visited = new HashMap<Room, Boolean>();
    String name;
    boolean finished;
    boolean locked;
    Room room;
    List<Item> inventory = new ArrayList<Item>();
    Map<String, Room> rooms; // global list of rooms
    Map<String, Item> items; // global list of known items
    int health = 100;

    // update state and check for winning condition
    public String update() {
        // if you place the book down in the room with the poison frog
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

        if (room.contents.contains(items.get("mysterious seeds")) &&
            (!room.contents.contains(items.get("carnivorous plant")))) {
            finished = true;
            String finaltext = """
                    After successfully defeating the plant, you pick up its seeds, and the remaining roots and vines disintegrate, revealing a door to the outside, which you open and find yourself in a new world... It may get rough from here, but you have a weird feeling that everything will be alright. 
                    """;
            
            return finaltext;
        }
        
        return "";
        
    }


    /**
     * Updates the health of the user either reduces or increases
     * @param a
     */
    public void updateHealth(int a) {
        this.health += a;
        // make sure the health stays at 100
        if (this.health > 100) {
            this.health = 100;
            
        }
        // if the health goes below 0
        if (this.health <= 0) {
            // game over
            this.finished = true;
            
        }

    }

    /**
     * Handles unlocking doors with key item
     * @param name
     */
    public void unlockDoor(String keyName) {
        // for each room 
        for (Room room : rooms.values()) {
            // if the lockeddoors contain the key name
            if (room.lockedDoors.containsValue(keyName)) {
                // for each entry in the locked doors
                for (Map.Entry<String, String> entry : room.lockedDoors.entrySet()) {
                    // if the value equals the key name
                    if (entry.getValue().equals(keyName)) {
                        // remove the door from the locked doors list
                        room.lockedDoors.remove(entry.getKey());
                        break;
                        
                    }
                    
                }
                
            }
            
        }

    }

    public void applyMagicEffect(String effect) {
        switch (effect) {
            case "gain knowledge":
                gainKnowledge();
                break;
            case "cast fireball":
                castFireball();
                break;
            case "invisibility":
                gainInvis();
                break;
        }


    }

    
    private void castFireball() {

    }

    private void gainKnowledge() {

    }

    private void gainInvis() {

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
