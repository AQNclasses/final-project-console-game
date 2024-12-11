import java.util.List;

public class Armor extends Item{
    int protection;
    
    public Armor(String name, List<String> type, String desc, String use, String act, int protection){
        super(name, type, desc, use, act);
        this.protection = protection;
    }
}
