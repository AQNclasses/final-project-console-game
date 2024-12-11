import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
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
    public void useitemtest() {
        LoadYAML yl = new LoadYAML();
        Item item = yl.items.get("mclaren");
        assertFalse(item.used);
        item.use();
        assertTrue(item.used);
    }
  @Test
    public void startwithbook(){
        LoadYAML y1 = new LoadYAML();
        Item item1 = y1.items.get("book");
        assertEquals(item1.name, "book");
    }

}
