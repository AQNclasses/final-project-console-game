import java.util.List;

public class Scroll extends Item {
    int power;

    public Scroll(String name, List<String> type, String desc, String use, String act, int power) {
        super(name, type, desc, use, act);
        this.power = power;
    }

    @Override
    public void use() {
        super.use();
        if (action.equals("read")) {
            GameState.playerKnowledge += power;
        } else if (action.equals("detect")) {

        } else if (action.equals("cast")) {

        }
    }
}