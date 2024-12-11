// Healing Item class
import java.util.List;
public class Healing extends Item {
    static int healthEffect;

    // Constructor for Healing items
    public Healing(String name, List<String> types, String desc, String use, String action, int healthEffect) {
        super(name, types, desc, use, action);
        this.healthEffect = healthEffect;
    }

    // Override use method to apply healing to the player's health
    @Override
    public void use(GameState state) {
        // Correctly use the instance variable healthEffect (this refers to the current instance)
        state.health += this.healthEffect;
        if (state.health > 20) {
            state.health = 20;
        }

        System.out.println(this.use); // Print the use text (e.g., healing message)
        System.out.println("You have healed " + this.healthEffect + " points! Current health: " + state.health);
        used = true; // Mark the item as used
    }
}
