public class Plant extends Item {
    String plantType;

    public Plant(String name, String type, String desc, String use, String act, String plantType) {
        super(name, type, desc, use, act);
        this.plantType = plantType;
    }

    public String observe(GameState state) {
        state.app = true;
        return use;
    }

    @Override
    public String inspect() {
        return "This is a " + name + ". It is a plant, and a type of" + plantType + "!";
    }

}
