public class Note extends Item {
    String text;

    public Note(String name, String type, String desc, String use, String act) {
        super(name, type, desc, use, act);
        text = desc;
    }

    public String read(){
        return text;
    }
}
