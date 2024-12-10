import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.HashMap;
import java.util.List;

public class GameTest {

    public void traverse(Room start, HashMap<String, Room> r, HashMap<String, Room> v) {
        if(v.containsKey(start.name)){
            return;
        }
        v.put(start.name, start);
        for(String door: start.doors.keySet()){
            traverse(r.get(start.doors.get(door)), r, v);
        }
    }

    @Test
    public void testYAML() {
        LoadYAML yl = new LoadYAML();
        Room room1 = yl.rooms.get("Starting Room");
        assertEquals(room1.name, "Starting Room");
    }

    // Checks that every door goes both ways
    // @Test
    // public void testNumDoors() {
    //     LoadYAML y1 = new LoadYAML();
    //     HashMap<String,Room> rooms = y1.loadRooms();
    //     HashMap<String, Room> visited = new HashMap<String, Room>();
        
    // }

    // // Checks that every room is accessible
    @Test
    public void testAccessiblity() {
        LoadYAML y1 = new LoadYAML();
        HashMap<String,Room> rooms = y1.loadRooms();
        HashMap<String, Room> visited = new HashMap<String, Room>();

        traverse(rooms.get("Starting Room"), rooms, visited);

        assertEquals(rooms.size(), visited.size());
    }

}
