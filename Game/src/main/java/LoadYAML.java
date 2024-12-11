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
    HashMap<String, Enemy> enemies = new HashMap<>();
    HashMap<String, Weapon> weapons = new HashMap<>();

    // load room data from yaml file
    // could do this more cleverly with packaged class definitions
    // Something like this:
    //InputStream stream = new FileInputStream(fname);
    //Room room = (new Yaml(new Constructor(Room.class))).load(stream);
    
    public HashMap<String,Room> loadRooms() {
        data = load("rooms.yaml");
        for (String name : data.keySet()) {
        	
            List<Item> contents = new ArrayList<>();
            List<Enemy> enemy = new ArrayList<>();
            
            Map<String, Object> inRoom = (HashMap) data.get(name);
            List<String> contemps = (ArrayList) inRoom.get("contents");
            
            boolean locked = (boolean) inRoom.get("locked");
            
            List<String> enem = (ArrayList) inRoom.get("enemies");
            
            for (String it : contemps) contents.add(items.get(it));
            
            for(String en : enem) enemy.add(enemies.get(en));
            Map<String, String> doors = (HashMap) inRoom.get("doors");
            rooms.put(name, new Room(name, contents, doors, locked, enemy));
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


            for(String type : types){
            	switch(type) {
            	case "Key":
            		String color = (String) properties.get("color");
            		items.put(name, new Key(name, types, desc, usetext, useaction, color));
            		break;
            	case "Animal": 
            		int minDamageAnimal = (int) properties.get("min-damage");
            		int maxDamageAnimal = (int) properties.get("max-damage");
            		items.put(name, new Animal(name, types, desc, usetext, useaction, minDamageAnimal, maxDamageAnimal));
            		break;
            	case "CleaningItem":
            		items.put(name, new CleaningItem(name, types, desc, usetext, useaction));
            		break;
            	case "PrintMaterial":
            		items.put(name, new PrintMaterial(name, types, desc, usetext, useaction));
            		break;
            	default:
            		items.put(name, new Item(name, types, desc, usetext, useaction));
            	}
            }
        }
        return items;
    }
    
    public HashMap<String,Enemy> loadEnemies() {
        data = load("enemies.yaml");
        for (String name : data.keySet()) {
            Map<String, Object> properties = (HashMap) data.get(name);
        	int health = (int) properties.get("health");
        	String desc = (String) properties.get("desc");
            enemies.put(name, new Enemy(name, desc, health));
        }
        return enemies;
    }
    
    public HashMap<String, Weapon> loadWeapons() {
        data = load("items.yaml");
        for (String name : data.keySet()) {
            Map<String, Object> properties = (HashMap) data.get(name);
            String desc = (String) properties.get("description");
            Map<String, Object> use = (HashMap) properties.get("use");
            String usetext = (String) use.get("text");
            String useaction = (String) use.get("action");
            List<String> types = (ArrayList) properties.get("type");


            for(String type : types){
            	if(type.equals("Weapon")) {
            		int minDamageWeapon = (int) properties.get("min-damage");
            		int maxDamageWeapon = (int) properties.get("max-damage");
            		weapons.put(name, new Weapon(name, types, desc, usetext, useaction, minDamageWeapon, maxDamageWeapon));
            	}
            }
        }
        return weapons;
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
        enemies = loadEnemies();
        rooms = loadRooms();
        weapons = loadWeapons();
    }
}
