import java.util.List;
import java.util.ArrayList;

public class Plant extends Item {
    int healthBoost;

    public Plant(String n, List<String> ts, String d, String u, String a, int boost) {
        super(n, ts, d, u, a);
        this.healthBoost = boost;
    }
    @Override
    public void use() {
        System.out.println("You eat the " + name + " and feel your vitality increase!");
        GameState.increaseMaxHealth(healthBoost);
    }
}