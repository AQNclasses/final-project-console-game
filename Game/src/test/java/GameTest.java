import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class GameTest {

    @Test
    public void testYAML() {
        LoadYAML yl = new LoadYAML();
        Room room1 = yl.rooms.get("Study");
        assertEquals(room1.name, "Study");
    }

    public void testGameState() {
        GameState state = new GameState("Test");
        List<Item> inventory = new ArrayList();
        assertEquals(state.inventory, inventory);
        assertEquals("Test", state.name);

    }

    public void testAnimals() {
        GameState state = new GameState("Test");
        Animal e = new Animal("Test", "Test", "Test", "Test", "Test", "Test");
        String result = e.pet(state);
        assertEquals(state.pet, true);
        assertEquals(result, e.use);
    }

}
