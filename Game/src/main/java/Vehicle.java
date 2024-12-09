import java.util.List;

public class Vehicle extends Item{
    boolean locked = false;
    public Vehicle(String name, List<String> type, String desc, String use, String act){
        super(name, type, desc, use, act);
    }
    public void locked(){
        locked = true;
    }
}