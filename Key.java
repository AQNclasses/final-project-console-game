import java.util.List;

public class Key extends Item {

    public Key(String name, List<String> type, String desc, String use, String act, int min_damage, int max_damage) {
        super(name, type, desc, use, act);
    }

    public void unlockDoor(Room room){
        room.locked = false;
    }
}
