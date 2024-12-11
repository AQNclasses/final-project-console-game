import java.util.List;
import java.util.Random;

public class Healing extends Item {
    Random r = new Random();
    
    public Healing(String name, List<String> types, String desc, String use, String act) {
        super(name, types, desc, use, act);
    }

    public int drink(int playerHP) { 
        if (r.nextInt(1) == 1) {
            if (this.name.equals("purple frog")) {
                System.out.println("You take a chance, taking the small frog into your mouth and swallowing it all in one go. You instantly feel dizzy, and with the world spinning around you, you fall to the floor, gasping for air.");
            }
            if (this.name.equals("elixir")) {
                System.out.println("Your body starts breaking out in a cold sweat, and you feel your vision starting to become unclear. You fall to the cold ground and start shaking, watching the space around you start to get fuzzy.");
            }
            return playerHP;
        } else {
            if (this.name.equals("purple frog")) {
                System.out.println("You take a chance, taking the small frog into your mouth and swallowing it all in one go. While it looks scary, you feel a degree better. You feel rejuvenated, your body feeling lighter. Some muscle aches disappear and your brain clears away some fatigue. Your health has been fully restored.");
            }
            if (this.name.equals("elixir")) {
                System.out.println("You tip the glowing liquid into your mouth, feeling it flow down your throat. You feel rejuvenated, your body feeling lighter. Any muscle aches disappear and your brain clears away from some fatigue. Your health has been fully restored.");
            }
        return (- (10 - playerHP)); 
        }
    }
}    