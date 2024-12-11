import java.util.List;

public class NPC extends Item {
    static String dialogue;  // Dialogue or interaction text for the NPC

    public NPC(String name, List<String> types, String desc, String use, String act, String dialogue) {
        super(name, types, desc, use, act);
        this.dialogue = dialogue;
    }

    public  String interact() {
        return dialogue;  // Return the NPC's dialogue when interacted with
    }
}
//npcs cant attack the only attacking things will be considered animals
