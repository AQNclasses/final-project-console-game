import java.util.Random;
import java.util.List;

public class Plant extends Item {
    int health;

    public Plant(String name, List<String> type, String desc, String use, String act, int totalHealth) {

        super(name, type, desc, use, act);

        health = totalHealth;

    }

}
