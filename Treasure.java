import java.util.*;

public class Treasure extends Item {
    int value; // Represents the treasure's value or worth

    public Treasure(String name, List<String> types, String desc, String use, String act, int value) {
        super(name, types, desc, use, act);
        this.value = value;
    }

    // Returns the value of the treasure
    public int getValue() {
        return value;
    }

    public String toString() {
        return "Treasure: " + name + " (Value: " + value + ")";
    }
}
