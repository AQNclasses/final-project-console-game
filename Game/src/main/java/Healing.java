import java.util.List;
public class Healing extends Item{
    int count;
    int hpRestore;
    public Healing(String name, List<String> types, String desc, String use, String act, String[] s) {
        super(name, types, desc, use, act);
        hpRestore = Integer.valueOf(s[varNum++]);
        count = 1;
    }

    public String use(GameState s){
        return use() + heal(s);
    }
    

    public String heal(GameState s){
        String text = "";
        if(count > 0){
            s.hp += hpRestore;
            count--;
            text += hpRestore + " hp restored. \nCurrent hp: " + s.hp;
        }
        if(count <= 0){
            s.inventory.remove(this);
        }
        return text;
    }

@Override
    public String pickUp(GameState s){
        if(s.inventory.contains(this)){
            count++;
        }else{
            super.defaultPickUp(s);
            count = 1;
        }
        return "You obtained the " + name + "!\n" + desc;
    }
    
}
