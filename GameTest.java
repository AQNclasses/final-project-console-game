import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class GameTest {

    @Test
    public void testYAML() {
        LoadYAML yl = new LoadYAML();
        Room room1 = yl.rooms.get("Starting Room");
        assertEquals(room1.name, "Starting Room");
    }

    @Test
    public void testGame() {
        GameState state = new GameState("name"); // created a new game state
        Item item = state.items.get("poison apple"); // creates a poison apple object
        item.use(); 
        state.update();
        assertTrue(!state.won); // checks if using the apple killed the player

    }

    @Test
    public void testItem() {
        List<String> types = new ArrayList<String>(); 
        types.add("Weapon"); 
        Item newItem = new Item("", types, "", "",""); // creates new item 
        assertEquals(newItem.types.get(0), "Weapon"); // checks if the correct type is found

    }

}
