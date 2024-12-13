import java.util.Random;
import java.util.List;

public class Potion extends Item {
    String Room;

    public Potion(String name, List<String> types, String desc, String use, String act, String room) {
        super(name, types, desc, use, act);
        Room = room;
    }

    public String drink(){
        return Room;
    }


}
