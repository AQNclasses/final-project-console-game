import java.util.List;
public class defaultItem extends Item{
    defaultItem(String n, List<String> ts, String d, String u, String a){
        super(n,ts,d,u,a);
    }
    public String use(GameState s){
        return use();
    }
    public String pickUp(GameState s){
        return defaultPickUp(s);
    }
}