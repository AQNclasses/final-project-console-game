public class Key extends Item{
    int code;

    public Key(String name, String type, String desc, String use, String act, int num) {
        super(name, type, desc, use, act);
        code = num;
    }

    // uniformly distributed random number
    public boolean unlock(int num) {
        return num == code;
    }

    @Override
    public String inspect(){
        String message = "";
        if(code % 2 == 0){
            message = super.inspect() + ". Unlocks a vehicle.";
        } else {
            message = super.inspect() + ". Unlocks a door";
        }
        return message;
    }
}
