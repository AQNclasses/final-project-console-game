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
    public void testDamage()
    {
        GameState gameState = new GameState("Test1");
        gameState.take10Damage();
        int h = gameState.healthMod();
        assertEquals(90, h);
    }
    public void HealDamage()
    {
        GameState gameState = new GameState("Test2");
        gameState.Heal10Damage();
        int l = gameState.healthMod();
        assertEquals(100, l);
    }
}
