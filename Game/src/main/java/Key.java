import java.util.List;
import java.util.Map;

public class Key extends Item {
    
    public Key(String name, List<String> types, String desc, String use, String act) {
        super(name, types, desc, use, act);
    }

    public void unlock(Room room, Map<String, Item> items, Map<String, Room> rooms, List<Item> inventory) { 
        inventory.remove(this);
        room.contents.remove(items.get("locked door"));
        rooms.get("Mysterious Cave").doors.put("pink", "Chamber of Curses");
        System.out.println("The door is now unlocked. Check again to see if you can pass through the door.");
    }
}