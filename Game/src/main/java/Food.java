import java.util.List;

public class Food extends Item {
    public String flavor;
    public boolean hot;
    public String foodType;

    public Food(String name, String type, String desc, String use, String act, String flavor, boolean hot,
            String foodType) {
        super(name, type, desc, use, act);
        this.flavor = flavor;
        this.hot = hot;
        this.foodType = foodType;
    }

}
