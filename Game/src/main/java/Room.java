import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Room {
	public String name;
    public List<Item> contents;
    public Map<String,String> doors;
    public boolean locked;
    public List<Enemy> enemies;

    public Room(String name, List<Item> contents, Map<String, String> doors, boolean locked, List<Enemy> enemies) {
        this.name = name;
        this.contents = contents;
        this.doors = doors;
        this.locked = locked;
        this.enemies = enemies;
    }

    public String toString() {
        return name;
    }
}
