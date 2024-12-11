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

    boolean drive = false;

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
    

        // Check if the player is in the Rocket room with rocket keys in inventory
        if (room.name.equals("Rocket") && inventory.contains("rocket keys")){
            finished = true;
            String finalText = """
                                You leave Earth with Elon Musk and will now thrive on Mars!!  You make are on the journey for 4 days and then the rocket explodes and it's over.
                                """;
            return finalText;
        }
        if(room.name.equals("Rocket") && !(inventory.contains("rocket keys"))){
            room = rooms.get("Outside");
            return """
                    rocket can't be open
                    """;
        }

        return "";
    }


    private boolean checksforrocketkeys(){
        for (Item item : inventory) {
            if (item.name.equalsIgnoreCase("rocket keys")) {
                return true;
            }
        }
        return false;
    }
    public String lockedDoor(String doorName) {
        if (room.name.equals("Outside") && doorName.equals("green") && checksforrocketkeys() == false) {
            return "You need the rocket keys to enter the Rocket";
        }
    
        // This is the main change
        if (room.doors.get(doorName).equals("Rocket") && checksforrocketkeys() == false) {
            return "You need the rocket keys to enter the Rocket";
        }
    
        Room targetRoom = rooms.get(room.doors.get(doorName));
        // No changes here for locked doors
        if (targetRoom != null) {
            room = targetRoom;
            return "You step through the " + doorName + " door. You realize this room is the " + room.name + ".";
        } else {
            return "unknown door.";
        }
    }

    public String dropItem(String itemName){
        try{
            Item item = items.get(itemName);

            if (inventory.contains(item)){
                inventory.remove(item);
                room.contents.add(item);
                rooms.put(room.name, room);
                return "You drop the" + item.name + ".";
            } 
            else{
                return "You don't have that item in your inventory";
            }
        } 
        catch (Exception e){
            return "Unknown item.";
        }
    }
    public String driveCar(String carName) {
        try {
            Item item = items.get(carName);
            if (room.contents.contains(item) && item.types.contains(ItemType.Vehicle)){//checks if the vehicles are in the room
                finished = true;
                switch (carName) {
                    case "mclaren":
                        return "you drive to walmart and do not survive";
                    case "jeep wrangler":
                        return "you drive to the mountains hoping that you escape but you don't";
                    case "bugatti":
                        return "you get into the bugatti and drive off to beverly hills you dont survive";
                    default:
                        return "You get in the " + item.name + " and drive away. You have escaped!";
                }
            } else{
                return "That car is not in this room or it's not a drivable thing.";
            }
        } catch (Exception e) {
            return "Unknown item.";
        }
    }
    public void drives(){
        drive = false;
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
