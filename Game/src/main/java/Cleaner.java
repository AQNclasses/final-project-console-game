public class Cleaner extends Item {
    boolean wet;
    int maxHealth;
    boolean alive = true;

    public Cleaner(String name, String type, String desc, String use, String act, boolean wet) {
        super(name, type, desc, use, act);
        this.wet = wet;
    }

    @Override
    public void use(GameState state){
        String floor = state.room.floor;
        switch (floor) {
            case "carpet":
                
                break;

            case "hardwood":

                break;
        
            default:
                break;
        }
    }
}
