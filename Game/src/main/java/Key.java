import java.util.List;

public class Key extends Item {
    String room;

    public Key(String name, String type, String desc, String use, String act, String room) {
        super(name, type, desc, use, act);
        this.room = room;
    }

}
