import java.util.List;
import java.util.Random;

public class Readable extends Item {
    Random r = new Random();
    
    public Readable(String name, List<String> types, String desc, String use, String act) {
        super(name, types, desc, use, act);
    }

    public void read() { 
    if (this.name.equals("book")) {
        System.out.println("Opening the books contents, you begin reading on a random page, and see some confusing diagrams with an upside down tree like structure. Flipping through a few more pages, you observe some more terms that you do not recognize, and ponder what to do with it.");
    }
    if (this.name.equals("poster")) {
        System.out.println("You survey the contents of the poster in the dim lighting, seeing flickers of color in the weathered ink. The picture on the poster reflects a headshot of Jungkook, the hotshot of Korea. You could do a lot of things with this poster. You ruffle the paper to spread it out fully.");
    }
    this.used = false;
    }
}