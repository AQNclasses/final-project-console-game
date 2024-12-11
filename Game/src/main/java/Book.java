public class Book extends Item {

    String tip;

    public Book(String name, String type, String desc, String use, String act, String tip) {
        super(name, type, desc, use, act);
        this.tip = tip;
    }
}
