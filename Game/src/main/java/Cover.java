public class Cover extends Item {
    boolean inUse = false;
    String use1;
    String use2;

    public Cover(String name, String type, String desc, String use1, String use2, String act) {
        super(name, type, desc, use1, act);
        this.use1 = use1;
        this.use2 = use2;
    }

    // Toggles vision based on weather or the cover is in use
    @Override
    public void use(GameState state) {
        super.use(state);
        if(inUse){
            super.use = use2;
        } else {
            super.use = use1;
        }
        state.sight = inUse;
        inUse = !inUse;
    }
}
