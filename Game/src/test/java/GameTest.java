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
    @Test
    public void testRoomContents() {
        LoadYAML yl = new LoadYAML();
        Room startingRoom = yl.rooms.get("Starting Room");
        assertEquals(1, startingRoom.contents.size());
        assertTrue(startingRoom.contents.get(0).name.equals("poison frog"));
    }

    @Test
    public void testHiddenItem() {
        LoadYAML yl = new LoadYAML();
        Room outdoorPatio = yl.rooms.get("Outdoor Patio");
        assertEquals("key", outdoorPatio.hidden.name);
    }

}
