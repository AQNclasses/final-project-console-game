import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.HashMap;

public class GameTest {

    @Test
    public void testYAML() {
        LoadYAML yl = new LoadYAML("Stella");
        Room room1 = yl.rooms.get("Foyer");
        assertEquals(room1.name, "Foyer");
    }

}
