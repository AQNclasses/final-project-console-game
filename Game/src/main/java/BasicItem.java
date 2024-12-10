import java.util.List;

public class BasicItem extends Item {
    public BasicItem(String name, List<String> types, String desc, String use, String action) {
        super(name, types, desc, use, action);
    }

    public void use(GameState gameState) {
        switch (action.toLowerCase()) {
            case "interact":
                System.out.println(useText);
                break;
            case "drop":
                System.out.println("You dropped the " + name + ".");
                gameState.dropItem(this);
                break;
            default:
                System.out.println("Try another way to use " + name );
                break;
        }
        used = true;
    }
}
