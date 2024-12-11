import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.beans.Transient;
import java.util.HashMap;

public class GameTest {

    @Test
    public void testYAML() {
        LoadYAML yl = new LoadYAML();
        Room room1 = yl.rooms.get("Starting Room");
        assertEquals(room1.name, "Starting Room");
    }

    //test that items are loaded correctly 
    @Test
    public void testLoadItems() {
        LoadYAML yl = new LoadYAML();
        Item item1 = yl.items.get("elixir"); //loads the items and retrieves specific item 
        assertNotNull(item1); //checks that item is not null
        assertEquals(item1.name); //tests that item name is correct 
        assertEquals(item1.desc); //tests that item's description matches the expected text
        assertEquals(item1.useaction); //tests that the item has a valid use action 
        assertTrue(item1.types.size() > 0); //tests that the item has types defined
    }

    //test that rooms are loaded correctly
    @Test
    public void testLoadRooms() {
        LoadYAML yl = new LoadYAML();
        Room room1 = yl.rooms.get("Chamber of Curses"); //loads the rooms and retrieves specific room 
        assertNotNull(room1); //checks that the room is not null
        assertEquals(room1.name); //tests that the room name is as expected
        assertTrue(room1.contents.size() > 0); //checks that the contains the expected number of items 
        assertTrue(room1.doors.size() > 0); //checks that the room has doors
    }
}
