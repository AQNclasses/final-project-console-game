import java.util.Random;
import java.util.List;

public class Container extends Item {
    String item;

    public Container(String name, List<String> types, String desc, String use, String act, String[] s) {
        super(name, types, desc, use, act);
        item = s[varNum++];
    }

@Override
    public String pickUp(GameState state) {
        return use(state);
    }
 public String obtain(GameState state) {
        Item gift = state.items.get(item);
        
        state.inventory.add( state.items.get(item) );
        return "You obtained the " + item + "!\n" + gift.desc;
    }
@Override
    public String use(GameState s){
        if(used){
            return "its empty";
        }else{
            return desc + "\n" + use() + "\n\n" + obtain(s);
        }
        
    }


}
