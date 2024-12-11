import java.util.List;

public class Animal extends Item {
    boolean retrieve;

    public Animal(String name, List<String> type, String desc, String use, String act, boolean retrieve) {
        super(name, type, desc, use, act);
        this.retrieve = retrieve;
    }
    public boolean isretrieveKey() {
        return retrieve;
    }
    public void retrievedKey() {
        retrieve = true;
    }

}
