import java.util.List;

public class Plant extends Item {
    boolean drop;

    public Plant(String name, List<String> type, String desc, String use, String act, boolean drop) {
        super(name, type, desc, use, act);
        this.drop = drop;    
    }   

    public boolean hasDropped() {
        return drop;
    }
    public void drop() {
        drop = true; 
    }  
    public String notdroppedString() {
        if (!drop) {
        return use;
        } else {
            return "notdroppedString";
        }
      
    }
    public String droppedString() {
        if (drop) {
            String dropAgain = "It slips out of your fingers again.";
            return dropAgain;
        } else {
            return "droppedString";
        }    
    } 
}
