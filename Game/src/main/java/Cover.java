public class Cover extends Item {
    boolean inUse = false;

    public Cover(String name, String type, String desc, String use, String act) {
        super(name, type, desc, use, act);
    }

    @Override
    public void use() {
        super.use();
        inUse = !inUse;
    }
}
