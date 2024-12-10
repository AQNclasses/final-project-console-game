import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.beans.Transient;
import java.util.HashMap;

public class GameTest {

    //test 1
    @Test
    public void testYAML() {
        LoadYAML yl = new LoadYAML();
        Room room1 = yl.rooms.get("Starting Room");
        assertEquals(room1.name, "Starting Room");
    }

    //test 2
    @Test 
    public void testItemUse(){
        // a test to check if using a certain item brings up something wrong with the code
        LoadYAML yl = new LoadYAML();
        GameState state = new GameState("Test Player");
        Item excalibur = yl.items.get("excalibur");
        state.inventory.add(excalibur);

        // run the item test
        excalibur.use(state);
        assertTrue(excalibur.used);
    }

    //test 3
    @Test 
    public void testPickUpItem(){
        // a test to check picking up an item
        LoadYAML yl = new LoadYAML();
        GameState state = new GameState("Test Player");

        //test the key item
        Item keyItem = yl.items.get("key");
        state.room.contents.add(keyItem);

        //this line is here to make sure the room has the key
        assertTrue(state.room.contents.contains(keyItem));

        // run the item pick up test
        String itemName = "key";
        state.inventory.add(keyItem);
        state.room.contents.remove(keyItem);

        // now have the check come in to see if the player picked up the key item
        assertTrue(state.inventory.contains(keyItem));
        assertFalse(state.room.contents.contains(keyItem));

    }

}
