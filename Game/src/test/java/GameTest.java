import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;

public class GameTest {

    // traverses all the rooms via doors and adds each room to a given hashmap
    public int traverse(Room start, HashMap<String, Room> r, HashMap<String, Room> v) {
        if(v.containsKey(start.name) || start.doors == null){
            return -1;
        }
        v.put(start.name, start);
        for(String door: start.doors.keySet()){
            traverse(r.get(start.doors.get(door)), r, v);
        }
        return v.size();
    }

    // traverses all rooms via doors and returns if all the doors go both ways
    public boolean traverseDoor(Room start, HashMap<String, Room> r, HashMap<String, Room> v) {
        if(v.containsKey(start.name)){
            return doorCheck(start, r);
        }
        if(start.doors == null){
            return false;
        }
        v.put(start.name, start);
        boolean allDoor = true;
        for(String door : start.doors.keySet()){
            allDoor = allDoor && traverseDoor(r.get(start.doors.get(door)), r, v);
        }
        return allDoor;
    }

    // returns true if all doors in a room go both ways
    public boolean doorCheck(Room room, HashMap<String, Room> r){
        boolean pass = true;
        for(String ajoin : room.doors.values()){
            pass = pass && r.get(ajoin).doors.containsValue(room.name);
        }
        return pass;
    }

    @Test
    public void testYAML() {
        LoadYAML yl = new LoadYAML();
        Room room1 = yl.rooms.get("Starting Room");
        assertEquals(room1.name, "Starting Room");
    }

    // // Checks that every room is accessible
    @Test
    public void testAccessiblity() {
        LoadYAML y1 = new LoadYAML();
        HashMap<String,Room> rooms = y1.loadRooms();
        HashMap<String, Room> visited = new HashMap<String, Room>();

        assertEquals(rooms.size(), traverse(rooms.get("Starting Room"), rooms, visited));
    }

    // Checks that every door goes both ways
    @Test
    public void testNumDoors() {
        LoadYAML y1 = new LoadYAML();
        HashMap<String,Room> rooms = y1.loadRooms();
        HashMap<String, Room> visited = new HashMap<String, Room>();

        assertTrue(traverseDoor(rooms.get("Starting Room"), rooms, visited));   
    }
}
