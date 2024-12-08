import java.util.List;

public class Healing extends Item {
    int healAmount;

    public Healing(String name, List<String> types, String desc, String use, String action, int healAmount) {
        super(name, types, desc, use, action);
        this.healAmount = healAmount;
    }

    @Override
    public void use(GameState state) {
        super.use(state);
        if (state.health < 100) {
            state.health = Math.min(100, state.health + healAmount);
            System.out.println("You feel refreshed. Your health is now: " + state.health);
            // Remove the healing item after use
            state.inventory.remove(this);
        } else {
            System.out.println("Your health is already at maximum.");
        }
    }
}
