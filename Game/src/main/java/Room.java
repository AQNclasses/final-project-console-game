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
    public Map<String, Boolean> lockedDoors;
    public Integer enemyHealth;
    public boolean enemyDefeated;

    public Room(String name, List<Item> contents, Map<String, String> doors, Map<String, Boolean> lockedDoors) {
        this.name = name;
        this.contents = contents;
        this.doors = doors;
        this.lockedDoors = lockedDoors;
        this.enemyHealth = null;
        this.enemyDefeated = false;
    }

    public boolean isDoorLocked(String label) {
        return lockedDoors.getOrDefault(label, false);
    }

    public void unlockDoor(String label) {
        lockedDoors.put(label, false);
    }

    public boolean hasEnemy(){
        return enemyHealth != null && enemyHealth > 0 && !enemyDefeated;
    }

    public String toString() {
        return name;
    }
}
