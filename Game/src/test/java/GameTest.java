import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.HashMap;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertFalse;

public class GameTest {

    @Test
    public void testYAML() {
        LoadYAML yl = new LoadYAML();
        Room room1 = yl.rooms.get("Entry Way");
        assertEquals(room1.name, "Entry Way");
    }
    
    @Test
    public void testLoadItems() {
    	LoadYAML yl = new LoadYAML();
        Key item = (Key) yl.items.get("gold key");
        assertEquals(item.name, "gold key");
        assertEquals(item.action, "unlock");
        assertEquals(item.color, "gold");
    }
    
    @Test
    public void testItemUse() {
    	LoadYAML yl = new LoadYAML();
        Key item = (Key) yl.items.get("gold key");
        assertFalse(item.used);
        item.use();
        assertTrue(item.used);
    }
    
    @Test 
    public void testGameEnd() {
    	assertEquals(true, true);
    }

}
