
import java.util.List;

public class Animal extends Item {
    String color;

    public Animal(String name, String type, String desc, String use, String act, String color) {
        super(name, type, desc, use, act);
        this.color = color;
    }

}
