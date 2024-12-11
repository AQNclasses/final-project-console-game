import java.util.Random;
import java.util.List;

public class Enemy extends Weapon {
    int hp;
    String item;
    public Enemy(String name, List<String> type, String desc, String use, String act, String[] s) {
        super(name, type, desc, use, act, s);
        item = s[4];
    }

    public Enemy(Enemy other) {
        super(other);
        hp = other.hp;
    }


	public String attack(GameState s){
		int damage = rollDamage();
		s.hp -= damage;
		return name + " attacked you. You took " + damage + " damage!";

	}
    public String remove(GameState s){
        s.battleField.remove(this);
        Item gift = s.items.get(item);
        return gift.pickUp(s);
        
    }
    public String pickUp(GameState s){
        return "You cannot pick this up";
    }
}
