import java.util.List;

public class Key extends Item {
    private String doorId;

    public Key(String name, List<String> types, String desc, String use, String act, String doorId) {
        super(name, types, desc, use, act);
        this.doorId = doorId;
    }

    @Override
    public void use() {
        super.use();
        if (action.equals("unlock")) {
            gameState.unlockedDoors.put(doorId, true);
        }
    }
}