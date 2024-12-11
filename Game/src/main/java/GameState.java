import java.util.*;

// Tracks global game state
// Useful for implementing state-based behavior (ex: see something new on second visit to room)

public class GameState {
    HashMap<Room, Boolean> visited = new HashMap<Room, Boolean>();
    String name;
    public static boolean finished;
    public static Room room;
    List<Item> inventory = new ArrayList<Item>();
    Map<String, Room> rooms; // global list of rooms
    public static Map<String, Item> items; // global list of known items

    public static int maxHealth;
    public static int playerHealth;
    public static boolean enemyPresent = false;
    public static int enemyHealth;
    public static boolean enemyAttacked = false;
    public static int buffMultiplier = 1;

    // update state and check for winning condition
    public String update() {
        checkDeath();
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

        if (room.contents.contains(items.get("poison frog")) && room.contents.contains(items.get("crown")) ){
            finished = true;
            String finaltext = """
                               The frog hops slowly over to the crown and climbs on top of it. The room begins to glow with an intense golden light. The frog is radiating with a sense of regal authority. The room begins to spin and you shut your eyes due to the blinding golden light. When you open your eyes, you're back in the original basement room! You open the door and find yourself back in the library. As you head out back towards your normal life you notice there is something in your pocket that wasn't there before. You pull out a $20 bill that has some purple residue on it and smile.
                               """;
            return finaltext;
        }
        return "";
    }

    public boolean unlockDoor(String label) {
        if (room.isDoorLocked(label)) {
            for (Item item : inventory) {
                if (item.name.equalsIgnoreCase("key")) {
                    room.unlockDoor(label);
                    Game.printSlow("You used the key to unlock the " + label + " door.");
                    return true;
                }
            }
            Game.printSlow("You don't have the key to unlock the " + label + " door.");
            return false;
        }
        return true;
    }

    public static void increaseMaxHealth(int boost) {
        int oldMax = maxHealth;
        maxHealth += boost;
        playerHealth = maxHealth;
        Game.printSlow("Your max health was " + oldMax + " but has now increased to " + maxHealth + "!");
    }

    public static void checkDeath() {
        if (playerHealth <= 0) {
            finished = true;
            Game.printSlow("Your vision starts to fade and you feel yourself falling over...");
            Game.printSlow("You have died.");
        }
    }

    public static void spawnEnemy(int health) {
        enemyPresent = true;
        enemyHealth = health;
        Game.printSlow("An enemy wearing a golden crown appears before you! It has " + health + " health.");
    }

    public static void enemyAttack() {
        if (enemyPresent) {
            if (!enemyAttacked) {
                enemyAttacked = true;
                Game.printSlow("The enemy prepares his attack!");
                return;
            }

            int damage = (int) (Math.random() * 5) + 5;
            playerHealth -= damage;
            Game.printSlow("The enemy attacks you for " + damage + " damage!");
            Game.printSlow("Your health is now " + playerHealth + ".");
        }
    }

    public GameState(String name) {
        this.name = name;
        maxHealth = 10;
        playerHealth = 10;
        finished = false;
        LoadYAML yl = new LoadYAML();
        rooms = yl.rooms;
        items = yl.items;
        room = rooms.get("Starting Room");
        visited.put(room, true);
        inventory.add(items.get("book"));
    }
}
