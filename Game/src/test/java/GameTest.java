
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class GameTest {

    @Test
    public void testYAML() {
        LoadYAML yl = new LoadYAML();
        Room room1 = yl.rooms.get("Starting Room");
        assertEquals(room1.name, "Starting Room");
    }

    @Test
    public void testItemLoading() {
        LoadYAML yl = new LoadYAML();
        Item excalibur = yl.items.get("excalibur");

        // Validate that the item is a Weapon and its properties are correct
        assertEquals("excalibur", excalibur.name);
        assertEquals("Ye sword of olde", excalibur.desc);
        assertEquals(Weapon.class, excalibur.getClass()); // Ensure it's a Weapon

        Weapon sword = (Weapon) excalibur; // Cast to access Weapon-specific properties
        assertEquals(5, sword.min);
        assertEquals(15, sword.max);
    }

    @Test
    public void testRoomConnections() {
        LoadYAML yl = new LoadYAML();
        Room startingRoom = yl.rooms.get("Starting Room");

        // Validate that the starting room has the correct doors
        assertEquals("Starting Room", startingRoom.name);
        assertEquals("Closet", startingRoom.doors.get("green"));
        assertEquals("Throne Room", startingRoom.doors.get("red"));

        // Validate that the connected room (Closet) also exists and connects back
        Room closet = yl.rooms.get("Closet");
        assertEquals("Starting Room", closet.doors.get("green"));
    }

}
