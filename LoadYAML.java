import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import org.yaml.snakeyaml.Yaml;

public class LoadYAML {

    String fname;
    HashMap<String, Object> data;
    HashMap<String, Room> rooms = new HashMap<>();
    HashMap<String, Item> items = new HashMap<>();

    public HashMap<String, Room> loadRooms() {
        data = load("rooms.yaml");
        for (String name : data.keySet()) {
            List<Item> contents = new ArrayList<>();
            Map<String, Object> inRoom = (HashMap) data.get(name);

            List<String> contemps = (ArrayList) inRoom.get("contents");
            for (String it : contemps)
                contents.add(items.get(it));

            Map<String, String> doors = (HashMap) inRoom.get("doors");

            // Gets the locked status (all of the key stuff isnt working for some reason?)
            boolean locked = inRoom.containsKey("locked") && (boolean) inRoom.get("locked");

            // Gets the key if it's present
            String key = inRoom.containsKey("key") ? (String) inRoom.get("key") : null;

            Room room = new Room(name, contents, doors, locked);
            if (key != null) {
                room.setKey(key); // Makes sure the key is correctly assigned
            }

            // Updates hasGoldenNugget based on contents
            room.updateContents();

            rooms.put(name, room);
        }
        return rooms;
    }

    public HashMap<String, Item> loadItems() {
        data = load("items.yaml");
        for (String name : data.keySet()) {
            Map<String, Object> properties = (HashMap) data.get(name);
            String desc = (String) properties.get("description");
            String useText = (String) properties.get("use.text");
            String action = (String) properties.get("use.action");
            List<String> types = (ArrayList) properties.get("type");
    
            if (types.contains("Treasure")) {
                int value = (int) properties.get("value");
                items.put(name, new Treasure(name, types, desc, useText, action, value));
            } 

            else if (types.contains("Key")) {
                items.put(name, new Key(name, types, desc, useText, action));
            } 
            
            else {
                items.put(name, new Item(name, types, desc, useText, action));
            }
        }
        return items;
    }
    
    public HashMap<String, Object> load(String fname) {
        Yaml yaml = new Yaml();
        File file = new File("./config/" + fname);
        try {
            FileInputStream inputStream = new FileInputStream(file);
            data = yaml.load(inputStream);
        } catch (FileNotFoundException e) {
            System.out.println("Couldn't find file");
        }
        return data;
    }

    public LoadYAML() {
        items = loadItems();
        rooms = loadRooms();
    }
}
