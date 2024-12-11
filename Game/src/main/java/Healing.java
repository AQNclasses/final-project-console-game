import java.util.List;

public class Healing extends Item {
    private int healAmount;

    public Healing(String name, List<String> types, String desc, String use, String act, int healAmount) {
        super(name, types, desc, use, act);
        this.healAmount = healAmount;
    }

    @Override
    public void use() {
        super.use();
        if (action.equals("heal")) {
            gameState.playerHealth = Math.min(100, gameState.playerHealth + healAmount);
            gameState.inventory.remove(this);
        }
    }
}
