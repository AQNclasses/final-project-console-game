import java.util.List;
//like healing but more vague effect
public class Plant extends Item {
    String effect; //plants would be like a potion and have some effect

    public Plant(String name, List<String> types, String desc, String use, String act, String effect) {
        super(name, types, desc, use, act);
        this.effect = effect;
    }

    public String usePlant() {
        return "Using this plant results in: " + effect;  // Display the effect of using the plant
    }
}