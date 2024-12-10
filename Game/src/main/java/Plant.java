import java.util.List;

public class Plant extends Item {
    public Plant(String name, List<String> types, String desc, String use, String action) {
        super(name, types, desc, use, action);
    }

    @Override
    public void use(GameState gameState) {
        if ("water".equalsIgnoreCase(action)) {
            System.out.println(useText); 
            gameState.reveal();
        } else {
            System.out.println(name + " doesn't need to be watered right now.");
        }
        used = true;
    }
}
