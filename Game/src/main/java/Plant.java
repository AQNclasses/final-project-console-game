import java.util.List;
import java.util.Random;

public class Plant extends Item {
    Random r = new Random();
    
    public Plant(String name, List<String> types, String desc, String use, String act) {
        super(name, types, desc, use, act);
    }

    public int eat(int playerHP) { 
        if (r.nextInt(3) == 1) {
            System.out.println("As you bite into the Ivy, you feel a crunch. Your mouth erupts in the flavor of the exotic plant, unfamiliar. It drips with the residue from the cave walls. You see small critters crawling across the surface of the remaining ivy, as your brain begins to feel slower. You fall to the ground.");
            return playerHP;
        } 
        System.out.println("As you bite into the Ivy, you feel a crunch. Your mouth erupts in the flavor of the exotic plant, unfamiliar. It drips with the residue from the cave walls. It was definitely an interesting experience. What do you do next?");
        return 0;        
    } 
}    

