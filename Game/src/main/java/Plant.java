import java.util.List;

public class Plant extends Item {
    int health;

    public Plant(String name, List<String> types, String desc, String use, String action, int health) {
        super(name, types, desc, use, action);
        this.health = health;
    }

    public void takeDamage(int dmg, GameState state) {
        health -= dmg;
        if (health <= 0) {
            // Plant destroyed, perform logic (e.g., drop coconut)
            System.out.println("You chop down the " + name + "!");
            // Remove from room
            state.room.contents.remove(this);
            // Add coconut (healing item) to room
            Item coconut = state.items.get("coconut");
            if (coconut != null) {
                state.room.contents.add(coconut);
                System.out.println("A coconut falls from the tree and lands on the ground.");
            }
        } else {
            System.out.println("You strike the " + name + ". It looks weakened.");
        }
    }

    @Override
    public void use(GameState state) {
        // Using a plant doesn't do much, unless we define something special
        if (use != null) System.out.println(use);
        else System.out.println("You can't use the " + this.name + " in that way.");
    }
}