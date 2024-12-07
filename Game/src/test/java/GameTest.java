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
    public void testLocked(){
        LoadYAML y1 = new LoadYAML();
        Item item1 = y1.items.get("treasure chest");
        assertEquals(item1.locked, false);
    }

}
