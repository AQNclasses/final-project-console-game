import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class GameTest {
 
    @Test
    public void testYAML() {
        LoadYAML yl = new LoadYAML();
        Room room1 = yl.rooms.get("library door");
        assertEquals(room1.name, "library door");
    }

    @Test
    public void testCloset() {
        LoadYAML yl = new LoadYAML();
        Item item1 = yl.items.get("book");
        assertEquals(item1.action, "vanish");
    }

@Test
public void testDamage() {
    LoadYAML yl = new LoadYAML();
    Item dagger = yl.items.get("dagger");
    
    assertTrue(dagger instanceof Weapon);  
    Weapon weapon = (Weapon) dagger;  
    assertEquals(5, weapon.damage);
}



}
