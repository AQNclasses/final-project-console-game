import java.util.List;

public class Plant extends Item{
    String effect;

    public Plant(String name, List<String> type, String desc, String use, String act, String effect){
        super(name, type, desc, use, act);
        this.effect = effect;
    }
}
