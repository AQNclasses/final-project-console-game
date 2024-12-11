import java.util.List;
import java.util.Random;

public class Trap extends Item {
    Random r = new Random();
    
    public Trap(String name, List<String> types, String desc, String use, String act, int roomIndex) {
        super(name, types, desc, use, act);
    }

    public String movement(int roomIndex, int playerHP) { 
        if (this.name.equals("red button")) {
            System.out.println("You suddenly feel your footing become uneven, when the floor gives way beneath you. Falling through, your stomach does a flip before somehow landing on your feet. You look around, unsure of where you are. What will you do?");
        }
        if (this.name.equals("lever")) {
            System.out.println("You hear the sound of rusty gears turning, revealing a dimly lit stone path. You follow it, although unsure of where it is heading. Your sense of direction disappears, following the set path it takes you on. Emerging into a space, you look around, taking in your surroundings.");
        }
        if (this.name.equals("magic circle")) {
            System.out.println("In what feels like an instant, you feel every molecule in your body come apart and back together. Disoriented, you find yourself in a different space from the one you were just in.");
        }

        switch (roomIndex) {
            case 0:
                return "Starting Room";
            case 1:
                return "Closet";
            case 2:
                return "Mysterious Cave";
            case 3:
                return "Chamber of Curses";
        }

        if (r.nextInt(9) == 9){
            playerHP = playerHP - playerHP;
            switch (this.name) {
                case "red button": 
                    System.out.println("You suddenly feel your footing become uneven, the floor beneath you crumbling. As you plummet into the darkness, a cold sensation washes over you. Your body hits the ground with a sickening thud, and you struggle to stand. Your breath comes in short gasps as you look around, feeling disoriented.");
                    break;
                case "lever":
                    System.out.println("The sound of rusty gears turning echoes around you, but before you can react, the path beneath your feet begins to crumble. The dimly lit stone corridor fades into darkness as you lose your footing, tumbling into an unknown space. A sharp pain jolts through you as you hit the ground, your breath catching in your chest.");
                    break;
                case "magic circle":
                    System.out.println("In what feels like an instant, you feel every molecule in your body shudder, tearing apart with an agonizing jolt. The world around you blurs.");
                    break;
            }
        }
    return "";
}
}


