import java.util.Random;
import java.util.List;

public class Key extends Item {
    String Door;
    public Key(String name, List<String> types, String desc, String use, String act, String door) {
        super(name, types, desc, use, act);
        Door = door; 
  
    }

    public void unlock() {
        GameState.unlock(Door);

    }

}
