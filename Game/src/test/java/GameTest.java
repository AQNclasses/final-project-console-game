import org.junit.Test;
import static org.junit.Assert.assertEquals;
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
    public void testItem() {
        LoadYAML yl = new LoadYAML();
        GameState state = new GameState("test");
        state.items = yl.items;

        Item goldPot = state.items.get("gold potion");
        goldPot.use();

        assertEquals(2, GameState.buffMultiplier);
    }

    @Test
    public void testEnemy(){
        LoadYAML yl = new LoadYAML();
        Room finalRoom = yl.rooms.get("Final Room");

        GameState.enemyPresent = false;
        if (finalRoom.hasEnemy()) {
            GameState.spawnEnemy(finalRoom.enemyHealth);
        }

        assertTrue(GameState.enemyPresent);
    }
}
