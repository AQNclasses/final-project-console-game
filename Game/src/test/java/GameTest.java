import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;

public class GameTest {

    @Test
    public void testYAML() {
        LoadYAML yl = new LoadYAML();
        Room room1 = yl.rooms.get("Starting Room");
        assertEquals(room1.name, "Starting Room");
    }

    /**
     * Tests to see if the user can move rooms successfully
     */
    @Test
    public void testRoomTraversal() {
        GameState state = new GameState("Tester");
        state.room = state.rooms.get("Starting Room");

        String door = "blue";
        String temp = state.room.doors.get(door);

        state.room = state.rooms.get(temp);
        assertEquals(state.room.name, "Green Room");
    }

    @Test
    public void testPickup() {
        GameState state = new GameState("Tester");
        state.room = state.rooms.get("Closet");

        Item item = state.items.get("brown key");
        assertTrue(state.room.contents.remove(item));

        state.room.contents.remove(item);
        state.inventory.add(item);

        assertTrue(state.inventory.contains(item));
        assertEquals(false, state.room.contents.contains(item));

    }



}
