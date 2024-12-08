import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Room {
    public String name;
    public List<Item> contents;
    public Map<String,String> doors;
    public Map<String, Boolean> locks;
    public String floor;

    public Room(String name, List<Item> contents, Map<String, String> doors, Map<String, Boolean> locks, String floor) {
        this.name = name;
        this.contents = contents;
        this.doors = doors;
        this.locks = locks == null ? new HashMap<String, Boolean>() : locks;
        this.floor = floor;
    }

    public String toString() {
        return name;
    }
}
