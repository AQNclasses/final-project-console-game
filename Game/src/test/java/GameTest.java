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
    public void testStartItems(){
        LoadYAML y2 = new LoadYAML();
        Item item1 = y2.items.get("book");
        assertEquals(item1.name, "book");

    }
    @Test
    public void testNoKey(){
        LoadYAML y3 = new LoadYAML();
        assert !y3.items.containsKey("key");
    }
}
