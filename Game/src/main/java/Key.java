public class Key extends Item{
    int code;

    public Key(String name, String type, String desc, String use, String act, int num) {
        super(name, type, desc, use, act);
        code = num;
    }

    public boolean unlock(int num) {
        return num == code;
    }

    // Unlocks every connected room
    // This function will need to be changed if there are multiple room keys
    @Override
    public void use(GameState state){
        if(code % 2 == 1){
            for (String c : state.room.doors.values()){
                state.rooms.get(c).locked = false;
            }
        }
    }

    @Override
    public String inspect(){
        String message = "";
        if(code % 2 == 0){
            message = super.inspect() + " Unlocks a vehicle.";
        } else {
            message = super.inspect() + " Unlocks a door";
        }
        return message;
    }
}
