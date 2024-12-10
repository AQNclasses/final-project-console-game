public class Healing extends Item{
    int factor;

    public Healing(String name, String type, String desc, String use, String act, int heal_amount) {
        super(name, type, desc, use, act);
        factor = heal_amount;
    }

    // Calls the heal function for every animal in the room
    @Override
    public void use(GameState state) {
        super.use(state);
        for(int i = 0; i < state.room.contents.size(); i++){
            if(state.room.contents.get(i).type == ItemType.Animal){
                ((Animal)state.room.contents.get(i)).heal(factor);
            }
        }
        for(int i = 0; i < state.inventory.size(); i++){
            if(state.inventory.get(i).type == ItemType.Animal){
                ((Animal)state.inventory.get(i)).heal(factor);
            }
        }
    }

    @Override
    public String inspect(){
        String message = super.inspect() + ". Healing factor: " + factor;
        return message;
    }
}
