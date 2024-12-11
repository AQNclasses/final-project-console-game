import java.util.List;

public class Key extends Item{
    String opens;
    
    public Key(String name, List<String> type, String desc, String use, String act, String opens){
        super(opens, type, desc, use, act);
        this.opens = opens;
    }
}
