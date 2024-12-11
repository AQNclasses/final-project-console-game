// package Game.src.main.java;

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

    // load room data from yaml file
    // could do this more cleverly with packaged class definitions
    // Something like this:
    //InputStream stream = new FileInputStream(fname);
    //Room room = (new Yaml(new Constructor(Room.class))).load(stream);
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
            int min_damage = 0;
            int max_damage = 10;
            int roomIndex = 1;
            int animalHP =  10;

            
            for (String type : types) {
                switch(type) {
                    case "Weapon":
                        items.put(name, new Weapon(name, types, desc, usetext, useaction, min_damage, max_damage)); //global variables?
                        break;
                    case "Healing":
                        items.put(name, new Healing(name, types, desc, usetext, useaction));
                        break;
                    case "Readable":
                        items.put(name, new Readable(name, types, desc, usetext, useaction));
                        break;                        
                    case "Tool":
                        items.put(name, new Tool(name, types, desc, usetext, useaction));
                        break;                        
                    case "Trap":
                        items.put(name, new Trap(name, types, desc, usetext, useaction, roomIndex));
                        break;
                    case "Plant":
                        items.put(name, new Plant(name, types, desc, usetext, useaction));
                        break;
                    case "Animal":
                        items.put(name, new Animal(name, types, desc, usetext, useaction, min_damage, max_damage, animalHP));
                        break;
                    case "Key":
                        items.put(name, new Key(name, types, desc, usetext, useaction)); 
                        break;
                    default:
                        items.put(name, new Item(name, types, desc, usetext, useaction));  
                }
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
