import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class GameTest {

    @Test
    public void testYAML() {
        LoadYAML yl = new LoadYAML();
        Room room1 = yl.rooms.get("Starting Room");
        assertEquals(room1.name, "Starting Room");
    }

    @Test
    public void testingweapons(){
        GameState gamestateclass = new GameState("Test");

        Weapon excalibur = new Weapon("Excalibur", List.of("Weapon"), "Ye sword of olde",
        "You slash with the sword.", "attack", 1, 10);

        int health = gamestateclass.Health();
        gamestateclass.Use(excalibur);
        int finalHealth =gamestateclass.Health();
        
        assertTrue(finalHealth < health);
    }
    @Test
    public void testpotion(){
        GameState gamestateclass = new GameState("Test");

        Potion healthpotion = new Potion("another_mysterious_potion", List.of("Potion"), "a magical potion that gives you some ability",
        "you drink the potion..... it does not taste very good", "drink");

        int health = gamestateclass.Health();
        gamestateclass.Use(healthpotion);
        int finalHealth =gamestateclass.Health();
        
        assertTrue(finalHealth > health);
    }
}
