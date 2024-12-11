import java.util.Random;

public class Weapon extends Item {
    int min;
    int max;
    private Random rn;

    public Weapon(String name, String type, String desc, String use, String act, int min_damage, int max_damage) {
        super(name, type, desc, use, act);
        min = min_damage;
        max = max_damage;
        rn = new Random();
    }

    // uniformly distributed random number
    public int attack() {
        int var = min + rn.nextInt((max-min) + 1);
        return var;
    }

    // Calls the attack function for every animal and plant in the room (excluding player's inventory)
    @Override
    public void use(GameState state) {
        super.use(state);
        for(int i = 0; i < state.room.contents.size(); i++){
            if(state.room.contents.get(i).type == ItemType.Animal){
                ((Animal)state.room.contents.get(i)).attack(this.attack());
            } else if(state.room.contents.get(i).type == ItemType.Plant){
                ((Plant)state.room.contents.get(i)).attack(this.attack());
            }
        }
    }

    @Override
    public String inspect(){
        String message = super.inspect() + ". Damage range: " + min + " - " + max;
        return message;
    }
}
