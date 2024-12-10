//package Game.src.main.java;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import org.yaml.snakeyaml.Yaml;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

public class LoadYAML {

    HashMap<String, Object> data;
    HashMap<String, Room> rooms = new HashMap<>();
    HashMap<String, Item> items = new HashMap<>();

    // load room data from yaml file
    // could do this more cleverly with packaged class definitions
    // Something like this:
    //InputStream stream = new FileInputStream(fname);
    //Room room = (new Yaml(new Constructor(Room.class))).load(stream);
    public LoadYAML() {
        items = loadItems();
        rooms = loadRooms();
        }

        public HashMap<String,Room> loadRooms() {
            data = load("rooms.yaml");
            for (String name : data.keySet()) {
                List<Item> contents = new ArrayList<>();
                Map<String, Object> inRoom = (HashMap) data.get(name);
                List<String> contemps = (ArrayList) inRoom.get("contents");
                for (String it : contemps) contents.add(items.get(it));
                Map<String, String> doors = (HashMap) inRoom.get("doors");
    
                String hiddenName = (String) inRoom.get("hiddenItem");
                Item hiddenItem = hiddenName != null ? items.get(hiddenName) : null;
                rooms.put(name, new Room(name, contents, doors, hiddenItem));
            }
            return rooms;
        }

        public HashMap<String,Item> loadItems() {
        data = load("items.yaml");
        for (String name : data.keySet()) {
            Map<String, Object> properties = (HashMap) data.get(name);
            String desc = (String) properties.get("description");
            Map<String, Object> use = (HashMap) properties.get("use");
            String usetext = (String) use.get("text");
            String useaction = (String) use.get("action");
            List<String> types = (ArrayList) properties.get("type");

            Item item;

            if (types.contains("Weapon")) {
                int minDamage = properties.containsKey("min-damage") ? (int) properties.get("min-damage") : 0;
                int maxDamage = properties.containsKey("max-damage") ? (int) properties.get("max-damage") : 0;
                if (types.contains("Animal")) { 
                    item = new Animal(name, types, desc, usetext, useaction, minDamage, maxDamage);
                } else {
                    item = new Weapon(name, types, desc, usetext, useaction, minDamage, maxDamage);
                }
            } else if (types.contains("Key")) {
                item = new Key(name, types, desc, usetext, useaction);
            } else if (types.contains("Plant")) {
                item = new Plant(name, types, desc, usetext, useaction);
            } else {
                item = new BasicItem(name, types, desc, usetext, useaction);
            }

            items.put(name, item);
        }
        return items;
    }

    public HashMap<String, Object> load(String fname) {
        Yaml yaml = new Yaml();
        File file = new File("./config/"+fname);
        try {
            FileInputStream inputStream = new FileInputStream(file);
            data = yaml.load(inputStream);
        } catch (FileNotFoundException e) {System.out.println("Couldn't find file");}
        return data;
    }

}
