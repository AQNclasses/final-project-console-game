//package Game.src.main.java;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

public class LoadYAML {

    String fname;
    HashMap<String, Object> data;
    HashMap<String, Room> rooms = new HashMap<>();
    HashMap<String, Item> items = new HashMap<>();

    // load room data from yaml file
    // could do this more cleverly with packaged class definitions
    // Something like this:
    //InputStream stream = new FileInputStream(fname);
    //Room room = (new Yaml(new Constructor(Room.class))).load(stream);
    public HashMap<String, Room> loadRooms() {
        data = load("rooms.yaml");
        for (String name : data.keySet()) {
            List<Item> contents = new ArrayList<>();
            Map<String, Object> inRoom = (HashMap) data.get(name);
            List<String> contemps = (ArrayList) inRoom.get("contents");
            for (String it : contemps) {
                contents.add(items.get(it));
            }
            Map<String, String> doors = (HashMap) inRoom.get("doors");
            Map<String, String> lockedDoors = (HashMap) inRoom.get("lockedDoors");
            rooms.put(name, new Room(name, contents, doors, lockedDoors));

        }
        return rooms;
    }

    public HashMap<String, Item> loadItems() {
        data = load("items.yaml");
        for (String name : data.keySet()) {
            Map<String, Object> properties = (HashMap) data.get(name);
            String desc = (String) properties.get("description");
            Map<String, Object> use = (HashMap) properties.get("use");
            String usetext = (String) use.get("text");
            String useaction = (String) use.get("action");
            List<String> types = (ArrayList) properties.get("type");

            // Subclass assignments 
            if (types.contains("Weapon")) {
                int minDamage = (int) properties.get("min_damage");
                int maxDamage = (int) properties.get("max_damage");
                items.put(name, new Weapon(name, types, desc, usetext, useaction, minDamage, maxDamage));
            } else if (types.contains("Animal")) {
                int minDamage = (int) properties.get("min_damage");
                int maxDamage = (int) properties.get("max_damage");
                items.put(name, new Animal(name, types, desc, usetext, useaction, minDamage, maxDamage));
            } else if (types.contains("Book")) {
                String contents = (String) properties.get("contents");
                String subject = (String) properties.get("subject");
                items.put(name, new Book(name, types, desc, usetext, useaction, contents, subject));
            } else {
                // Generic fallback for undefined types
                items.put(name, new Item(name, types, desc, usetext, useaction));
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
