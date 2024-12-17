import java.util.Random;
import java.util.List;

public class Enemy extends Weapon {
    int hp;
    String item;
    public Enemy(String name, List<String> type, String desc, String use, String act, String[] s) {
        super(name, type, desc, use, act, s);
	hp = Integer.parseInt(s[varNum++]);
        item = s[varNum++];
    }

    public Enemy(Enemy other) {
        super(other);
        hp = other.hp;
	item = item + "";
	varNum += 2;
    }

	public String attack(GameState s){
		int numActions = rn.nextInt(numAttacks) + 1;
		int  i;
		int damage = 0;
		for(i = 0; i < numActions; i++){
			damage = rollDamage();
			s.hp -= damage;
		}
		return name + " attacked you. You took " + damage + " damage!";


	}
    public String remove(GameState s){
	String text = "\n";

        Item gift = s.items.get(item);

        s.battleField.remove(this);
	if(gift != null)
		text += "\n" + gift.pickUp(s);
	else{
		System.out.println(gift.toString());
	}
	return text;
    }
    public String pickUp(GameState s){
        return "You cannot pick this up";
    }
}
