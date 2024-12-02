import java.util.List;

// Stationary class for objects that cannot be picked up
public class Stationary extends Item {

    public Stationary(String name, List<String> types, String desc, String use, String action) {
        super(name, types, desc, use, action);
    }

    
    public void use(GameState state) {
        System.out.println("You can't use the " + this.name + " in this way.");
    }
}