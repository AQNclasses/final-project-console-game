import java.util.List;
//key should unlockwhater object its working with and return simple dialoged

public class Key extends Item {
    String unlocks;  // The item or area that this key unlocks

    public Key(String name, List<String> types, String desc, String use, String act, String unlocks) {
        super(name, types, desc, use, act);
        this.unlocks = unlocks;
    }

    public String unlock() {
        return "You unlock " + unlocks;  // A message telling what the key unlocks
    }

 }