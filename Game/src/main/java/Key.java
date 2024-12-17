import java.util.List;

public class Key extends Item {
    String srcRoom;
    String destRoom;
    String door;

    public Key(String name, List<String> types, String desc, String use, String act, String[] s) {
        super(name, types, desc, use, act);
        srcRoom = s[varNum++];
        destRoom = s[varNum++];
        this.door = s[varNum++];
    }
 
    public String unlock(GameState state){
        if(state.room.name.equals(srcRoom)){
            //state.room.doors.remove(door);
            state.room.doors.put(door,destRoom);
            System.out.println(state.room.doors.get(door));
            state.rooms.get("_secretState").contents.add(this);
            state.inventory.remove(this);
            return "you unlock the door";
        }else{
            return "that doesnt seem to work here";
        }
    }
    public String use(GameState s){
        return use() + "\n" + unlock(s);
    }
    public String pickUp(GameState s){
        return defaultPickUp(s);
    }
}