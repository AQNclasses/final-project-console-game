import java.util.List;

public class Key extends Item {
    boolean unlocked;

    public Key(String name, List<String> type, String desc, String use, String act, boolean unlocked) {
        super(name, type, desc, use, act);
        this.unlocked = unlocked;    
    }   

    public boolean isUnlock() {
        return unlocked;
    }
    public void unlock() {
        if (unlocked == false) {
        unlocked = true;
        }
    }
}
