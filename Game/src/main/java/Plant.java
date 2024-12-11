import java.util.List;

public class Plant extends Item {
    public Plant(String name, List<String> types, String desc, String use, String act) {
        super(name, types, desc, use, act);
    }

    public void use(GameState state) {
        super.use();

    }
    
}
