import java.util.List;

public class Book extends Item {
    String contents;
    String subject;

    public Book(String name, List<String> types, String desc, String use, String act, String contents, String subject) {
        super(name, types, desc, use, act);
        this.contents = contents;
        this.subject = subject;
    }

    // Returns the contents of the book
    public String read() {
        return contents;
    }

    @Override
    public String inspect() {
        return super.inspect() + " It appears to be about " + subject;
    }
}