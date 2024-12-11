import java.util.List;

public class Magic extends Item {
    String effect;

    public Magic(String name, List<String> types, String desc, String use, String act, String effect) {
        super(name, types, desc, use, act);
        this.effect = effect;

    }

    public void use(GameState state) {
        state.applyMagicEffect(this.effect);
        super.use();
    }

    
}
