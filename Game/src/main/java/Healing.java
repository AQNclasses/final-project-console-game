public class Healing extends Item{
    int factor;

    public Healing(String name, String type, String desc, String use, String act, int heal_amount) {
        super(name, type, desc, use, act);
        factor = heal_amount;
    }

    // uniformly distributed random number
    public int heal() {
        return factor;
    }

    @Override
    public String inspect(){
        String message = super.inspect() + ". Healing factor: " + factor;
        return message;
    }
}
