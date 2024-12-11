import org.junit.Test;

import static org.junit.Assert.*;

import java.util.HashMap;

// import java.util.ArrayList;

// import java.util.List;

public class GameTest {

    @Test
    public void testYAML() {
        LoadYAML yl = new LoadYAML();
        Room room1 = yl.rooms.get("Starting Room");
        assertEquals(room1.name, "Starting Room");
    }
    
    @Test
    // test to see if locked door key implementation
    public void testLockedDoor() {
        GameState state = new GameState("Player");
        // give use rustic bronze key to inventory
        state.inventory.add(state.items.get("rustic bronze key"));

        assertTrue("Locked door can be opened using a key", state.openLockedDoor("blue"));
    }

    @Test
    public void alternativeEndings() {
        LoadYAML yl = new LoadYAML();
        Item sword = yl.items.get("gilded 2h sword");
        
        // make sure there is a sword
        assertNotNull("Sword: ", sword);
        // make sure sword description is the same
        assertEquals("Description: ", "This is a shiny gold sword that looks very rare.", sword.desc);
        // confirm sword type; weapon, and item
        assertTrue("Sword Type Weapon: ", sword.types.contains(ItemType.Weapon));
        assertTrue("Sword Type Item: ", sword.types.contains(ItemType.Item));
    }      
}

