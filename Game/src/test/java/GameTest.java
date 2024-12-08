import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class GameTest {

    @Test
    public void testYAML() {
        LoadYAML yl = new LoadYAML();
        Room room1 = yl.rooms.get("Starting Room");
        assertEquals(room1.name, "Starting Room");
    }
    @Test
    public void testRooms() {
        boolean noDoors = true;
        LoadYAML yl = new LoadYAML();
        Room room1 = yl.rooms.get("Starting Room");
        if(room1.doors.equals(null)){
            noDoors = false;
        }
        else{
            noDoors = true;
        }
        assertEquals(noDoors, true);
    }
    @Test
    public void testContainItems() {
        boolean containItems = true;
        LoadYAML yl = new LoadYAML();
        Room room1 = yl.rooms.get("Starting Room");
        if(room1.contents.equals(null)){
            containItems = false;
        }
        else{
            containItems = true;
        }
        assertEquals(containItems, true);
    }

}
