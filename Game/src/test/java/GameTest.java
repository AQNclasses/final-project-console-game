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
    public void one(){ // Added by me, checks to see if "Closet" contains the item "rusty key"
        LoadYAML yl = new LoadYAML();
        Room room1 = yl.rooms.get("Closet");
        Item[] arr = new Item[room1.contents.size()];

        for(int i = 0; i < room1.contents.size(); i++){
            arr[i] = room1.contents.get(i);
        }

        int var = -1;
        
        for(int i = 0; i < arr.length; i++){
            if(room1.contents.get(i).name.equals("rusty key")){
               var += 1 + i; 
            }
        }

        assertEquals(room1.contents.get(var), "rusty key");
    }

    @Test
    public void two(){ // Added by me, checks to see if you lose the game and the game ends when your health is reduced to 0
        GameState game = new GameState("test");
        game.health = 0;
        game.update();
        assertEquals(game.finished, true);
        assertEquals(game.update(), """
                                As you get hit, you feel an intense pain in your chest. You fall to the ground and
                                your eyes slowly close and you die. Maybe you should have been more careful...
                                """);
    }
}
