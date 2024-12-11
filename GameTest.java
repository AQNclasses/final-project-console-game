import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.HashMap;

public class GameTest {
    LoadYAML yl = new LoadYAML();

    @Test
    public void testYAML() {
        Room room1 = yl.rooms.get("Starting Room");
        assertEquals(room1.name, "Starting Room");
    }

    @Test
    public void testItemSubclasses(){
        Item bread = yl.items.get("bread");
        assertTrue(bread instanceof Food);
    }

    @Test
    public void testRoomContents(){
        Room dungeon3 = yl.rooms.get("Kitchen");
        assertTrue(dungeon3.contents.get(0) instanceof Armor);
        assertTrue(dungeon3.contents.get(1) instanceof Food);
        assertTrue(dungeon3.contents.get(2) instanceof Food);
        assertTrue(dungeon3.contents.get(3) instanceof Food);
    }
}
