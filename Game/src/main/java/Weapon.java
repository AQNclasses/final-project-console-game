import java.util.Random;
import java.util.List;

public class Weapon extends Item {
    int min;
    int max;
    int numAttacks;
    protected Random rn;

    public Weapon(String name, List<String> types, String desc, String use, String act, String[] s) {
        super(name, types, desc, use, act);
        min = Integer.valueOf(s[varNum++]);
        max = Integer.valueOf(s[varNum++]);
        numAttacks = Integer.valueOf(s[varNum++]);
        rn = new Random();
    }
    public Weapon(Weapon other){
        super(other);
        min = other.min;
        max = other.max;
        rn = other.rn;
        varNum += 3;
    }
        
    // uniformly distributed random number
    public int rollDamage() {
        int var = min + rn.nextInt((max-min) + 1);
        return var;
    }
    public String attack(GameState s){
        String text ="";
        if(!s.battleField.isEmpty()){
            int damage = rollDamage();
            //target random enemy
            Enemy target = s.battleField.get(rn.nextInt(s.battleField.size()));
            target.hp -= damage;
            text += "You attack " + target.name + "!  They lose " + damage + " hp.";

            if(target.hp <= 0){
                text += "   You defeated " + target.name + "!";
                target.remove(s);
            }
        }else{
            text += "There is no need for that...";
        }
        return text;
    }
    public String use(GameState s){
        String text = use () + "\n";
        int numActions = rn.nextInt(numAttacks) + 1;
        int i;
        String temp = "";
        for(i = 0; (i < numAttacks && !s.battleField.isEmpty() ); i++){
            temp += attack(s) + "\n";
        }
        text += "You attacked " + i + " times.";
        return text + temp;
    }

    public String pickUp(GameState s){
        return defaultPickUp(s);
    }
}
