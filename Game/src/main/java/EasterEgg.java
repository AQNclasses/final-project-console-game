import java.util.List;

public class EasterEgg extends Item{
        int completion = 0;
    public EasterEgg(String name, List<String> types, String desc, String use, String act) {
        super(name, types, desc, use, act);
    }
    public int secrets(int add){
        completion = completion + add;
        return completion;
    }

}