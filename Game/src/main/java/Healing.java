import java.util.List;
import java.util.ArrayList;

public class Healing extends Item {
    int healAmount;

    public Healing(String n, List<String> ts, String d, String u, String a, int heal) {
        super(n, ts, d, u, a);
        this.healAmount = heal;
    }

    @Override
    public void use() {
        if (GameState.playerHealth < GameState.maxHealth) {
            GameState.playerHealth = Math.min(GameState.playerHealth + healAmount, GameState.maxHealth);
            Game.printSlow("You feel the gentle warmth of the healing stone. Your health is now " + GameState.playerHealth + ".");
        } else {
            Game.printSlow("Your health is full. The healing stone does nothing.");
        }
    }
}