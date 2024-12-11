public class Tool extends Item {

    Item pair;

    public Tool(String name, String type, String desc, String use, String act, Item pair) {
        super(name, type, desc, use, act);
        this.pair = pair;
    }

}
