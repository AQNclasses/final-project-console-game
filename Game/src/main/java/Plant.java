import java.util.List;

public class Plant extends Item{
    int waters;
    public Plant(String name, List<String> types, String desc, String use, String act, int water) {
        super(name, types, desc, use, act);
        waters = water;
    }
    public int watered (){
        return waters;
    }
}