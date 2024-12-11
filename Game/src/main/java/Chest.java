import java.util.ArrayList;
import java.util.List;

public class Chest extends Item {

    boolean locked;
    List<String> insidess = new ArrayList<>();
        public Chest(String name, List<String> type, String desc, String use, String act, boolean lock, List<String> insides){
            super(name, type, desc, use, act);
            this.locked = lock;
            for(String g:insides){
                insidess.add(g);
            }
        }
        }
    

