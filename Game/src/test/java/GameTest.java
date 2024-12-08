import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.HashMap;

public class GameTest {

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
        
    // }

    // // Checks that every room is accessible
    // @Test
    // public void testAccessiblity() {
    //     LoadYAML y1 = new LoadYAML();
    //     HashMap<String,Room> rooms = y1.loadRooms();
    // }

}
