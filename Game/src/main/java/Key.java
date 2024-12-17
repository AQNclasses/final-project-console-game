import java.util.List;

public class Key extends Item {
    Room room;
    String roomName;
    String useRoom;

    public Key(String name, String type, String desc, String use, String act, String room, String useRoom) {
        super(name, type, desc, use, act);
        roomName = room;
        this.useRoom = useRoom;
    }

    public String unlock(GameState state) {
        if (used) {
            return "You already used this key.";
        }
        room = state.rooms.get(roomName);
        if (state.rooms.get(useRoom) == state.room) {
            state.room.locked = false;
            used = true;
            return use;
        } else {
            return "The door this key unlocks doesn't seem to be here. Maybe try the " + useRoom + "?";
        }
    }

}
