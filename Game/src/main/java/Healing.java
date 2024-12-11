import java.util.List;

public class Healing extends Item{

    int min;
    int max;

    public Healing(String name, List<String> types, String desc, String use, String act, int min_healing, int max_healing) {
        super(name, types, desc, use, act);
        min = min_healing;
        max = max_healing;
    }

}