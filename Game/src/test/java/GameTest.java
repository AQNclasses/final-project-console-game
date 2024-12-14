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

    @Test
    public void testBarredItems(){

        GameState state = new GameState("Test Player");

        assertTrue(state.barredItems.contains("red key"));
        assertTrue(state.barredItems.contains("green key"));
        assertTrue(state.barredItems.contains("blue key"));
        assertTrue(state.barredItems.contains("copper sword"));
        assertTrue(state.barredItems.contains("vines"));
        assertTrue(state.barredItems.contains("red key"));

    }

    @Test
    public void testAllRooms(){

        GameState state = new GameState("Test Player");

        assertTrue(state.rooms.get("Starting Room") != null);
        assertTrue(state.rooms.get("Garden") != null);
        assertTrue(state.rooms.get("Pond") != null);
        assertTrue(state.rooms.get("Forge") != null);
        assertTrue(state.rooms.get("Bright Room") != null);
        assertTrue(state.rooms.get("Boss Room") != null);


    }

}
