import java.util.Random;
import java.util.List;

public class Plant extends Item {
    int min;
    int max;
    private Random rn;

    public Plant(String name, List<String> types, String desc, String use, String act, int min_heal, int max_heal) {
        super(name, types, desc, use, act);
        min = min_heal;
        max = max_heal;
        rn = new Random();
    }

    public int eat(){
        int var = min + rn.nextInt((max-min) + 1);
        if(rn.nextInt(1)==1){
        return var;
        } else {
        return -var;
        }
    }

}
