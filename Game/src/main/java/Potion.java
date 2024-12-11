public class Potion extends Item {
    int pType;

    public Potion(String name, String type, String desc, String use, String act, int pType) {
        super(name, type, desc, use, act);
        this.pType = pType;
    }

    // Toggles vision based on weather or the cover is in use
    @Override
    public void use(GameState state) {
        super.use(state);
        switch (pType) {
            case 0:
                state.room = state.rooms.get("Starting Room");
                break;

            case 1:
                Item temp;
                for(int i = state.inventory.size() - 1; i >= 0; i--){
                    if(!state.inventory.get(i).equals(state.items.get("green potion"))){
                        temp = state.inventory.get(i);
                        state.inventory.remove(i);
                        state.items.remove(temp.name);
                    }
                }
                break;

            case 2:
                state.items.clear();
                break;
        
            default:
                break;
        }
    }
}
