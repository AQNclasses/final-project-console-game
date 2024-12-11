import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class GameTest {
    @Test
    public void testDoorLocking() {
        GameState state = new GameState("TestPlayer");

        assertFalse(state.canOpenDoor("iron", state.rooms.get("Library")));

        Item key = state.items.get("magic key");
        state.inventory.add(key);

        assertTrue(state.canOpenDoor("iron", state.rooms.get("Library")));
    public void testYAML() {
        LoadYAML yl = new LoadYAML();
        Room room1 = yl.rooms.get("Starting Room");
        assertEquals(room1.name, "Starting Room");
    }

        public void testScrollKnowledge() {
        GameState state = new GameState("TestPlayer");

        assertEquals(0, GameState.playerKnowledge);

        Scroll scroll = new Scroll("test scroll", Arrays.asList("Scroll"),
                "Test scroll", "You read the scroll", "read", 5);
        scroll.use();

        assertEquals(5, GameState.playerKnowledge);
    }
}