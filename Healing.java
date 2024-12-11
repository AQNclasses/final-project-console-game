import java.util.List;

public class Healing extends Item {
    int healAmount;

    public Healing(String name, List<String> type, String desc, String use, String act, int heal){
        super(name, type, desc, use, act);
        healAmount = heal;
    }
}
