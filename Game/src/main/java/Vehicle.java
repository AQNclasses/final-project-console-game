import java.util.List;

public class Vehicle extends Item {
    String place;

    public Vehicle(String name, List<String> types, String desc, String use, String act, String destination) {
        super(name, types, desc, use, act);
        place = destination;
    }
}