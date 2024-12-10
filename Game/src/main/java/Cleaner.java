public class Cleaner extends Item {
    boolean wet;

    public Cleaner(String name, String type, String desc, String use, String act, boolean wet) {
        super(name, type, desc, use, act);
        this.wet = wet;
    }

    @Override
    public void use(GameState state){
        String floor = state.room.floor;
        switch (floor) {
            case "carpet":
                if(wet){
                    state.room.floorModifier = "soggy ";
                }else{
                    state.room.floorModifier = "";
                }
                break;

            case "hardwood":
            case "concrete":
            case "asphalt":
                if(wet){
                    state.room.floorModifier = "damp ";
                }else{
                    state.room.floorModifier = "dusted ";
                }
                break;

            case "grass":
            case "dirt":
                if(wet){
                    state.room.floorModifier = "muddy ";
                }else{
                    state.room.floorModifier = "dusty ";
                }
                break;
        
            default:
                break;
        }
    }
}
