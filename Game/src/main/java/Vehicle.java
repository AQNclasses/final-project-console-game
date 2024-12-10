public class Vehicle extends Item {
    String text;
    String altText;
    boolean running = false;

    public Vehicle(String name, String type, String desc, String text, String altText, String act) {
        super(name, type, desc, text, act);
        this.text = text;
        this.altText = altText;
    }

    // Toggles vision based on weather or the cover is in use
    @Override
    public void use(GameState state) {
        if(state.inventory.contains(state.items.get("car key"))){
            super.use = text;
            running = true;
        }else{
            super.use = altText;
            running = false;
        }
    }
}
