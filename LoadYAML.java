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
            String type = types.get(0);
            int min; int max; 

            switch(type){
                case "Weapon":
                    min = (int) properties.get("min-damage");
                    max = (int) properties.get("max-damage");
                    items.put(name, new Weapon(name, types, desc, usetext, useaction, min, max));
                    break;
                case "Healing":
                    min = (int) properties.get("min-heal");
                    max = (int) properties.get("max-heal");
                    items.put(name, new Healing(name, types, desc, usetext, useaction, min, max));
                    break;
                case "Key":
                    String door = (String) properties.get("door");
                    items.put(name, new Key(name, types, desc, usetext, useaction, door));
                    break;
                case "Animal":
                    min = (int) properties.get("min-damage");
                    max = (int) properties.get("max-damage");
                    items.put(name, new Animal(name, types, desc, usetext, useaction, min,max));
                    break;
                case "Plant":
                    min = (int) properties.get("min-heal");
                    max = (int) properties.get("max-heal");
                    items.put(name, new Plant(name, types, desc, usetext, useaction, min, max));
                    break;
                case "Potion":
                    String room = (String) properties.get("room");
                    items.put(name, new Potion(name, types, desc, usetext, useaction, room));
                    break;
                default:
                    items.put(name, new Item(name, types, desc, usetext, useaction));
                    break;
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
