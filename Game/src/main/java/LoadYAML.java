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
            Map<String, Boolean> locks = (HashMap) inRoom.get("locks");
            String floor = inRoom.get("floor") == null ? "" : inRoom.get("floor").toString();
            rooms.put(name, new Room(name, contents, doors, locks, floor));
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
            String type = (String)((ArrayList) properties.get("type")).get(0);
            switch (type) {
                case "Weapon":
                    int min = (int)properties.get("min-damage");
                    int max = (int)properties.get("max-damage");
                    items.put(name, new Weapon(name, type, desc, usetext, useaction, min, max));
                    break;
                
                case "Healing":
                    int factor = (int)properties.get("heal-factor");
                    items.put(name, new Healing(name, type, desc, usetext, useaction, factor));
                    break;
                
                case "Key":
                    int code = (int)properties.get("code");
                    items.put(name, new Key(name, type, desc, usetext, useaction, code));
                    break;

                case "Animal":
                    int aHealth = (int)properties.get("health");
                    items.put(name, new Animal(name, type, desc, usetext, useaction, aHealth));
                    break;

                case "Plant":
                    int pHealth = (int)properties.get("health");
                    items.put(name, new Plant(name, type, desc, usetext, useaction, pHealth));
                    break;
                
                case "Cleaner":
                    break;

                case "Cover":
                    break;

                case "Note":
                    break;
                
                default:
                    items.put(name, new Item(name, type, desc, usetext, useaction));
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
