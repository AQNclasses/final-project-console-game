import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import java.util.HashMap;

public class GameTest {

    

    @Test
    public void testYAML() {
        LoadYAML yl = new LoadYAML();

    Room room1 = yl.rooms.get("Unending Desert");

    assertEquals(room1.name, "Unending Desert");
    }
    @Test
    public void testUseLighterOnUnlitTorch() {
        GameState state = new GameState("TestPlayer");
        // retreieve items
        Item lighter = state.items.get("lighter");
        Item unlitTorch = state.items.get("unlit torch");
        Item litTorch = state.items.get("lit torch");

        // add items to player inventory
        state.inventory.add(lighter);
        state.inventory.add(unlitTorch);

        // use lighter
        String result = state.useItem("lighter");

        // Check the returned message matches expected outcome
        assertEquals("You use the lighter to ignite the unlit torch, creating a lit torch.", result);

        assertFalse("Unlit torch should be removed from inventory", state.inventory.contains(unlitTorch));
        assertFalse("Lighter should be removed from inventory", state.inventory.contains(lighter));

        // ensure lit torch is added to inventory
        assertTrue("Lit torch should be added to inventory", state.inventory.contains(litTorch));
    }
    @Test
    public void testUseLitTorchOnBrazier() {
        // Initialize a new GameState for testing
        GameState state = new GameState("TestPlayer");
        
        // Retrieve the lit torch and brazier items from the loaded items map
        Item litTorch = state.items.get("lit torch");
        Item unlitBrazier = state.items.get("unlit brazier");
        
        
        // Initialize itemStates for the brazier as unlit
        state.itemStates.put("BRAZIER_LIT", false);
        assertFalse("Brazier should initially be unlit", state.itemStates.get("BRAZIER_LIT"));
        
        // Add the lit torch to the player's inventory
        state.inventory.add(litTorch);
        assertTrue("Inventory should contain lit torch", state.inventory.contains(litTorch));
        
        // Add the unlit brazier to the current room's contents
        state.room.contents.add(unlitBrazier);
        assertTrue("Room should contain unlit brazier", state.room.contents.contains(unlitBrazier));
        
        // Use the lit torch on the unlit brazier
        String result = state.useItem("lit torch");
        
        // test if the expectedMessage equals the result/ test passes
        String expectedMessage = "You use the lit torch to ignite the brazier. It flames to life!\n" +
                                 "Nearby, the strange statue comes to life, fire roaring from its mouth and eyes as it speaks: 'Come forth, bearer of the flame...'";
        assertEquals("Returned message should indicate the brazier has been lit and the statue awakened.", 
                     expectedMessage, result);
        
    }
}
