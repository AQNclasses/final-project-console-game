import java.util.List;

public class Healing extends Item {
   public int healAmount;

    public Healing(String name, List<String> types, String desc, String use, String action, int healAmount) {
        super(name, types, desc, use, action);
        this.healAmount = healAmount;
    }

    @Override
    public void use(GameState state) {
        // Only print flavor text here, do not modify health or inventory
        if (use != null) {
            System.out.println(use);
        }
    }
}
