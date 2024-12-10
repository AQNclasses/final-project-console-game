import java.util.List;

// plant class for new subclass of items
public class Plant extends Item {

    // initialize plant 
    public Plant(String name, List<String> types, String desc, String use, String act){
        // bring the properties
        super(name, types, desc, use, act);

    }

    @Override 
    public void use(GameState state){
        // touch action
        if("touch".equals(action)){
            //print touch action statement
            Game.printSlow("You touch the " + this.name + " and it feels soothing to the touch.");
        }

        // drop action
        else if("drop".equals(action)){
            //print out drop statement
            Game.printSlow("You kneel down and place the " + this.name + " on the ground, guess we should leave it be.");

            //add it to the room now that you've dropped it
            state.room.contents.add(this);
        }
    }


}







