import java.util.List;

public class Decor extends Item{
    Decor(String n, List<String> ts, String d, String u, String a, String[] s){
        super(n,ts,d,u,a);
    }

    public String use(GameState s){
        return use();
    }
    public String pickUp(GameState s){
        return inspect();
    }
}