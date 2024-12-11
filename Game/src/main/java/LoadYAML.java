//package Game.src.main.java;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.checkerframework.checker.units.qual.min;
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
            if(types.get(0).equals("Animal")){
                int min_damage = (Integer) properties.get("min-damage");
                int max_damage = (Integer) properties.get("max-damage");
                int health = (Integer) properties.get("health");
                List<Item> conts = new ArrayList<>();
                List<String> con = (ArrayList) properties.get("drops");
                for(String g: con) conts.add(items.get(g));
                items.put(name, new Animal(name, types, desc, usetext, useaction, min_damage, max_damage, health, conts));
            }else if(types.get(0).equals("Chest")){
                String lock = (String) properties.get("locked");
                boolean locked = true;
                if(lock.equals("False")) locked = false;
                List<String> bon = (ArrayList) properties.get("contents");
                items.put(name, new Chest(name, types, desc, usetext, useaction, locked, bon));
            }else if(types.get(0).equals("Weapon")){
                int min_damage = (Integer) properties.get("min-damage");
                int max_damage = (Integer) properties.get("max-damage");
                items.put(name, new Weapon(name, types, desc, usetext, useaction, min_damage, max_damage));
            }
            else{
            items.put(name, new Item(name, types, desc, usetext, useaction));
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
