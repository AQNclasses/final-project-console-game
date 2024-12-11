import java.util.List;
import java.util.ArrayList;

public class Key extends Item {
    public Key(String n, List<String> ts, String d, String u, String a) {
        super(n, ts, d, u, a);
    }
    @Override
    public void use() {
        Game.printSlow("You hold the key in your hand. Maybe try moving through a locked door.");
    }
}