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
	
	@Test
	public void testVariableName(){
		GameState gs = new GameState("testName");
		assertEquals(gs.name, "testName");
		assertEquals(gs.BestFriend, "Stella");
		
		gs = new GameState("Stella");
		assertEquals(gs.name, "Stella");
		assertEquals(gs.BestFriend, "Tabby");
		
	}
	
	@Test 
	public void testEnemy(){
		LoadYAML yl = new LoadYAML("Stella");
		
		Item i = yl.items.get("Annabelle");
		assertTrue(i != null);
		Enemy e = (Enemy) i;
		
		assertEquals(e.numAttacks,4);
	}

}
