import java.util.List;

public class Key extends Item {
    public Key(String name, List<String> types, String desc, String use, String action) {
        super(name, types, desc, use, action);
    }

    @Override
    public void use(GameState gameState) {
        if ("unlock".equalsIgnoreCase(action)) {
            System.out.println(useText); 
            gameState.unlockBlueDoor();
        } else {
            System.out.println(name + " doesn't belong here.");
        }
        used = true;
    }
}
