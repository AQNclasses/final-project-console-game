import java.util.*;


public class GameState {
    HashMap<Room, Boolean> visited = new HashMap<Room, Boolean>();
    String name;
    boolean finished;
    Room room;
    List<Item> inventory = new ArrayList<Item>();
    Map<String, Room> rooms; // global list of rooms
    Map<String, Item> items; // global list of known items
    static int playerKnowledge = 0;
    Map<String, Boolean> unlockedDoors = new HashMap<>();
    int playerHealth = 100;
    int enemyHealth = 100;
    int playerScore = 0;
    int playerGold = 50;
    Set<String> destroyedWeapons = new HashSet<>();

    public GameState(String name) {
        this.name = name;
        finished = false;
        LoadYAML yl = new LoadYAML();
        rooms = yl.rooms;
        items = yl.items;
        room = rooms.get("Starting Room");
        visited.put(room, true);
        inventory.add(items.get("book"));

        for (Item item : items.values()) {
            item.setGameState(this);
        }
    }
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
        if (playerKnowledge >= 15 && room.name.equals("Library")) {
            finished = true;
            return """
                   As your knowledge of the ancient texts grows, the library around you begins to shift.
                   The walls shimmer with arcane energy, and suddenly you understand all the mysterious
                   symbols you've been studying. You've uncovered the secret knowledge of the ancients!
                   """;
        }

        if (inventory.contains(items.get("crown")) && room.name.equals("Starting Room")) {
            finished = true;
            return """
                   The crown on your head begins to pulse with energy. The room fills with golden light,
                   and you feel yourself being transported through time. You've claimed your rightful place
                   as the ruler of this mysterious realm!
                   """;
        }
        return "";
    }
    public boolean canOpenDoor(String doorName, Room currentRoom) {
        if (doorName.equals("iron") && !unlockedDoors.getOrDefault("iron", false)) {
            return inventory.stream().anyMatch(item -> item.name.equals("magic key"));
        }
        return true;
    }

    public void unlockDoor(String doorName) {
        unlockedDoors.put(doorName, true);
    }


}
