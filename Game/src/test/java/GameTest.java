import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class GameTest extends Game{

    @Test
    public void testYAML() {
        LoadYAML yl = new LoadYAML();
        Room room1 = yl.rooms.get("Starting Room");
        assertEquals(room1.name, "Starting Room");
    }


    public void SpeedRunner() {
        // Set up game state with health <= 0
        GameState state = new GameState("speed runner");
        state.health = 0;  // Manually set health to 0 for this test

        assertTrue(state.finished);  // Ensure the game is finished
    }
    public void SummonedShenron() {
        // Set up game state with the player in the correct room
        GameState state = new GameState("Test Player");

        // Simulate player having the Dragon Balls and the Book in the room
        state.room.contents.add(state.items.get("Dragon Balls"));
        state.room.contents.add(state.items.get("book"));

        
        // Check that the game recognizes the ending condition
        assertTrue(state.finished);  // Ensure the game is finished
    }
    public void HelpedGoku() {
        // Set up game state
        GameState state = new GameState("You");

        // Simulate the NPC giving the player the Empty Sacred Container
        state.room.contents.add(state.items.get("red pole"));
        state.room.contents.add(state.items.get("Unnamed Kid"));
        
        // Check if the player received the Empty Sacred Container
        assertTrue(state.inventory.contains(state.items.get("Empty Sacred Container")));
    }


}
