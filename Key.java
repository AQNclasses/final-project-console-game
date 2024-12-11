import java.util.Random;
import java.util.List;

public class Key extends Item {
    public int goldenKey;
    public int keyWorn;

    public Key(String name, List<String> types, String desc, String use, String action, int goldenKey, int keyWorn) {
        super(name, types, desc, use, action);
        this.goldenKey = goldenKey;
    this.keyWorn = keyWorn;
       
    }
    


public int getFireBreathDamage() {
return goldenKey;
}

public int getFlightSpeed() {
return keyWorn;
}
@Override
public String toString() {
    return this.name;
}
}

    



