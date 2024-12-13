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
    // InputStream stream = new FileInputStream(fname);
    // Room room = (new Yaml(new Constructor(Room.class))).load(stream);
    public HashMap<String, Room> loadRooms() {
        data = load("rooms.yaml");
        for (String name : data.keySet()) {
            List<Item> contents = new ArrayList<>();
            Map<String, Object> inRoom = (HashMap) data.get(name);
            List<String> contemps = (ArrayList) inRoom.get("contents");
            for (String it : contemps)
                contents.add(items.get(it));
            Map<String, String> doors = (HashMap) inRoom.get("doors");
            rooms.put(name, new Room(name, contents, doors));
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
            String type = (String) properties.get("type");
            switch (type) {
                case "Animal":
                    String color = (String) properties.get("soft");
                    items.put(name, new Animal(name, type, desc, usetext, useaction, color));
                case "Food":
                    String flavor = (String) properties.get("flavor");
                    String foodType = (String) properties.get("food type");
                    boolean hot = (boolean) properties.get("hot");
                    items.put(name, new Food(name, type, desc, usetext, useaction, flavor, hot, foodType));
                case "Key":
                    String room = (String) properties.get("room");
                    String useRoom = (String) properties.get("useRoom");
                    items.put(name, new Key(name, type, desc, usetext, useaction, room, useRoom));
                case "Plant":
                    String plantType = (String) properties.get("plant type");
                    items.put(name, new Plant(name, type, desc, usetext, useaction, plantType));
                case "Tool":
                    String pair = (String) properties.get("pair");
                    items.put(name, new Tool(name, type, desc, usetext, useaction, pair));
                case "Book":
                    String tip = (String) properties.get("tip");
                    items.put(name, new Book(name, type, desc, usetext, useaction, tip));

            }
            items.put(name, new Item(name, type, desc, usetext, useaction));
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

    public static void main(String[] args) {
        LoadYAML yl = new LoadYAML();
    }
}
