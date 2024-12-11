import java.util.List;
import java.util.ArrayList;

public class Potion extends Item {
    String potionEffect;

    public Potion(String n, List<String> ts, String d, String u, String a, String effect) {
        super(n, ts, d, u, a);
        this.potionEffect = effect;
    }

    @Override
    public void use() {
        switch (potionEffect) {
            case "death":
                Game.printSlow("You drink the ominous black liquid.");
                Game.printSlow("Probably not the best idea...");
                GameState.playerHealth = 0;
                break;
            case "buff":
                Game.printSlow("You drink the shimmering gold liquid.");
                Game.printSlow("You feel a surge of power from within! Your attacks will now deal double damage!");
                GameState.buffMultiplier = 2;
                break;
            case "damage":
                Game.printSlow("You drink the oozing purple liquid.");
                int damNum = 5;
                GameState.playerHealth -= damNum;
                Game.printSlow("Your throat burns! You take " + damNum + " damage. Your health is now " + GameState.playerHealth + ".");
                break;
            default:
                Game.printSlow("Nothing happens.");
        }
    }
}