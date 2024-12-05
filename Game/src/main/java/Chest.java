import java.util.List;

public class Chest extends Item {

    boolean locked;
        public Chest(String name, List<String> type, String desc, String use, String act, boolean lock){
            super(name, type, desc, use, act, lock);

        }
    
}
