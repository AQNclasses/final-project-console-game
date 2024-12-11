public class Plant extends Item {
    String plantType;

    public Plant(String name, String type, String desc, String use, String act, String plantType) {
        super(name, type, desc, use, act);
        this.plantType = plantType;
    }
}
