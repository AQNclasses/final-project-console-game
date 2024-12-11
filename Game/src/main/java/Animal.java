import java.util.Random;
import java.util.List;

public class Animal extends Item {
    int animalHP;
    int min;
    int max;
    private Random rn;

    public Animal(String name, List<String> type, String desc, String use, String act, int min_damage, int max_damage, int animalHP) {
        super(name, type, desc, use, act);
        min = min_damage;
        max = max_damage;
        rn = new Random();
        this.animalHP = animalHP;
    }

    // uniformly distributed random number
    public int attack() { 
        if (this.name.equals("bloodthirsty wasp")) {
            System.out.println("The wasp lunges towards you in the air, its stinger faced directly at you as it comes towards you full speed. It pierces your skin, sending an instant searing pain through your entire nervous system as you stumble back from the blow, attempting to create distance between yourself and this beast.");
        }
        int var = min + rn.nextInt((max-min) + 1);
        return var;
    }

    public void sing() { 
        if (this.name.equals("bloodthirsty wasp")) {
            System.out.println("Mysteriously, the wasp starts buzzing to the tune of 'All I want for Christmas is You' by Mariah Carey. Its wings shake along to the tune. Does it make you want to attack the wasp less?");
        } 
        this.used = false;    
    }

    public int returnHealth(){
        return animalHP;
    }
}
