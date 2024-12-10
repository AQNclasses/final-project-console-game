
import java.util.List;

public class Animal extends Item {
    public boolean soft;

    public Animal(String name, List<String> type, String desc, String use, String act, boolean soft) {
        super(name, type, desc, use, act);
        this.soft = soft;
    }

    public String pet() {
        String outcome;
        if (soft) {
            outcome = "You reach down and pet the little guy. It's soft fur makes for a fairly pleasent experience!";
        } else {
            outcome = "You reach down and pet the little guy. As you run your hands along it, you think about why you pet something like this.";
        }
        return outcome;
    }

}
