import java.util.List;

public class keyItem extends Item {
    public keyItem(String name, List<String> types, String desc, String use, String act) {
        super(name, types, desc, use, act);

    }

    public void use(GameState state) {
        state.unlockDoor(this.name);
        super.use();

    }

    
}
