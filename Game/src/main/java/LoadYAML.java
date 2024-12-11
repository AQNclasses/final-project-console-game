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

    String fname;
    HashMap<String, Object> data;
    HashMap<String, Room> rooms = new HashMap<>();
    HashMap<String, Item> items = new HashMap<>();


    public HashMap<String,Room> loadRooms() {
        data = load("rooms.yaml");
        for (String name : data.keySet()) {
            List<Item> contents = new ArrayList<>();
            Map<String, Object> inRoom = (HashMap) data.get(name);
            List<String> contemps = (ArrayList) inRoom.get("contents");
            for (String it : contemps) contents.add(items.get(it));
            Map<String, String> doors = (HashMap) inRoom.get("doors");
            rooms.put(name, new Room(name, contents, doors));
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

            if (types.contains("Animal")) {
                int minDamage = ((Integer) properties.get("min-damage"));
                int maxDamage = ((Integer) properties.get("max-damage"));
                items.put(name, new Animal(name, types, desc, usetext, useaction, minDamage, maxDamage));
            }
            else if (types.contains("Weapon")) {
                int minDamage = ((Integer) properties.get("min-damage"));
                int maxDamage = ((Integer) properties.get("max-damage"));
                items.put(name, new Weapon(name, types, desc, usetext, useaction, minDamage, maxDamage));
            }
            else if (types.contains("Healing")) {
                int healAmount = ((Integer) properties.get("heal-amount"));
                items.put(name, new Healing(name, types, desc, usetext, useaction, healAmount));
            }
            else if (types.contains("Key")) {
                String doorId = (String) properties.get("door-id");
                items.put(name, new Key(name, types, desc, usetext, useaction, doorId));
            }
            else {
                items.put(name, new Item(name, types, desc, usetext, useaction));
            }
        }
        return items;
    }


    public HashMap<String, Object> load(String fname) {
            Yaml yaml = new Yaml();
            File file = new File("Game/config/"+fname);
            try {
                FileInputStream inputStream = new FileInputStream(file);
                data = yaml.load(inputStream);
            } catch (FileNotFoundException e) {System.out.println("Couldn't find file");}
        return data;
    }

    public LoadYAML() {
        items = loadItems();
        rooms = loadRooms();
    }

}
