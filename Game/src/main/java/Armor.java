import java.util.List;

public class Armor extends Item{
    static int shield;

    public Armor(String name, List<String> type, String desc, String use, String act, int shi){
        super(name, type, desc, use, act);
        shield = shi;
    }
}
