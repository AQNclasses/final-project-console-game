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
    boolean isLocked;
    int health;

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
            
            // Add items to the room
            for (String it : contemps) {
                contents.add(items.get(it));
            }
    
            // Load doors data
            Map<String, Object> doorsData = (HashMap) inRoom.get("doors");
            HashMap<String, String> doors = new HashMap<>();
            HashMap<String, Boolean> isLockedMap = new HashMap<>();
            
            // Iterate over each door and add its properties
            for (String doorName : doorsData.keySet()) {
                Map<String, Object> doorInfo = (HashMap) doorsData.get(doorName);
                String destination = (String) doorInfo.get("destination");
                boolean isLocked = (boolean) doorInfo.get("isLocked");
                
                // Store destination and locked status
                doors.put(doorName, destination);
                isLockedMap.put(doorName, isLocked);
            }
    
            // Now, create the room with the updated data
            rooms.put(name, new Room(name, contents, doors, isLocked));
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
            //finds the dialogue case

            String dialogue = (String) properties.get("dialogue");
            //types so we can set up the switch case 

            String type = types.isEmpty() ? "Unknown" : types.get(0);  // Fallback if no type is found

            String doorName = (String) properties.get("doorName");
            
            // Default values
            int min_damage = 0;
            int max_damage = 0;
            int health = 20;
    
            if (type.equals("Healing")) {
                health = (int) properties.get("health");
            }
    
            // Additional item type-specific properties
            if (type.equals("Weapon")) {
                min_damage = (int) properties.getOrDefault("min_damage", 0);
                max_damage = (int) properties.getOrDefault("max_damage", 0);
            }
    
            // Create item based on type
            switch (type) {
                case "Weapon":
                    items.put(name, new Weapon(name, types, desc, usetext, useaction, min_damage, max_damage));
                    break;
                case "NPC":
                    items.put(name, new NPC(name, types, desc, usetext, useaction, dialogue));
                    break;
                case "Table":
                    items.put(name, new Table(name, types, desc, usetext, useaction));
                    break;
                case "Animal":
                    items.put(name, new Animal(name, types, desc, usetext, useaction, min_damage, max_damage));
                    break;
                case "Healing":
                    items.put(name, new Healing(name, types, desc, usetext, useaction, health));
                    // Handle healing item, e.g., create a healing item with health
                    break;
                case "Key":
                    items.put(name, new Key(name, types, desc, usetext, useaction, doorName));
                    break;
                case "Item":
                    items.put(name, new Item(name, types, desc, usetext, useaction));
                    break;
                default:
                    System.out.println("Unknown item type: " + type);  // Debugging line
            }
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

    public LoadYAML() {
        items = loadItems();
        rooms = loadRooms();
    }
}