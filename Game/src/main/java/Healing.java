import java.util.List;

public class Healing extends Item {
    int healingAmount;

    public Healing(String name, List<String> types, String desc, String use, String act, int healingAmount) {
        super(name, types, desc, use, act);
        this.healingAmount = healingAmount;

    }

    public void use(GameState state) {
        state.updateHealth(this.healingAmount);
        super.use();

    }
    
}
