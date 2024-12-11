import java.util.List;

public class Food extends Item{
    int replenishment;

    public Food(String name, List<String> type, String desc, String use, String act, int replenishment){
        super(name, type, desc, use, act);
        this.replenishment = replenishment;
    }
}
