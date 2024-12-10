import java.util.List;

public class Plant extends Item {
    int health;

    public Plant(String name, List<String> types, String desc, String use, String action, int health) {
        super(name, types, desc, use, action);
        this.health = health;
    }

    /**
     * Method to apply damage to the plant.
     * @param dmg Damage to apply.
     * @param state Current game state.
     * @return true if plant is destroyed, false otherwise.
     */
    public boolean takeDamage(int dmg, GameState state) {
        health -= dmg;
        if (health <= 0) {
            System.out.println("You chop down the " + name + "!");
            state.room.contents.remove(this);
            return true;
        } else {
            System.out.println("You strike the " + name + ".");
            return false;
        }
    }

    @Override
    public void use(GameState state) {
        
        System.out.println("You can't use the " + this.name + " directly.");
    }
}
