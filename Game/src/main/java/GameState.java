import java.util.*;

// Tracks global game state
// Useful for implementing state-based behavior (ex: see something new on second visit to room)

public class GameState {
    HashMap<Room, Boolean> visited = new HashMap<>();
    String name;
    boolean finished;
    Room room;
    List<Item> inventory = new ArrayList<>();
    Map<String, Room> rooms; // global list of rooms
    Map<String, Item> items; // global list of known items
    private int health = 20;
    private int enemy = 5;
    private boolean blueDoorUnlocked = false;
    private boolean revealed = false;

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

        // Winning condition 2
        if (inventory.contains(items.get("key")) && inventory.contains(items.get("TV"))) {
            finished = true;
            String finaltext = """
                                As you place the key into slot on the back of TV, 
                                melodic circles start to form on the screen, 
                                sucking you into a swirling portal of color.
                                You find yourself walking through the screen 
                                to a serene land of grassy fields and warm rays of sun.
                                A comforting voice echoes, "Enjoy the end, you have earned it." 
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
    }

    

    public boolean locked(String door) {
        if (!room.doors.containsKey(door)) {
            System.out.println("Nope. Look somewhere else.");
            return false;
        }

        String nextName = room.doors.get(door);

        if ("blue".equalsIgnoreCase(door)) {
            if (!blueDoorUnlocked) {
                if (inventory.contains(items.get("key"))) {
                    System.out.println("You pull the key out of your inventory and insert it into the keyhole.");
                    unlockBlueDoor();
                } else {
                    return false;
                }
            }
        }

        Room nextRoom = rooms.get(nextName);
        if (nextRoom == null) {
            System.out.println("Room not found.");
            return false;
        }

        room = nextRoom;
        visited.put(room, true);
        return true;
    }

    public void unlockBlueDoor() {
        if (!blueDoorUnlocked) {
            blueDoorUnlocked = true;
            System.out.println("The blue door is unlocked.");
        } else {
            System.out.println("This door was already unlocked.");
        }
    }

    public void modifyHealth(int amount) {
        health += amount;
        health = Math.max(health, 0);
        System.out.println("Health: " + health);
    }

    public void enemyHealth(int amount){
        enemy += amount;
        enemy = Math.max(enemy, 0);
    }

    public void reveal(){
        if (!revealed && room.hidden != null){
            revealed = true;
            System.out.println("Something seems new about this room.");
            room.contents.add(room.hidden);
            room.hidden = null;
        } else if (revealed) {
            System.out.println("It has already been revealed.");
        } else {
            System.out.println("Nothing to reveal here.");
        }
    }

    public void interact(Item item) {
        if (item == null) {
            System.out.println("Can't interact here");
            return;
        }

        if ("interact".equalsIgnoreCase(item.action)) {
            System.out.println(item.useText);
        } else {
            System.out.println("The " + item.name + " can't do that.");
        }
    }

    public void dropItem(Item item) {
        if (room != null) {
            room.contents.add(item);
            inventory.remove(item);
            System.out.println("You have dropped the " + item.name + ".");
        }
    }
}
