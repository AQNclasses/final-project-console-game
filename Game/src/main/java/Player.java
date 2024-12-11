import java.util.ArrayList;
import java.util.List;
//start of player but I might need to put some of this out in the game java to get it to actually function
public class Player {
    //start of player declaring global variables

    private String name;
    private int health;
    private int maxHealth;
    //for storing items current being held
    private List<Item> inventory;
    private Item equippedItem;
    public Player(String name, int maxHealth) {
        this.name = name;
        this.maxHealth = maxHealth;
        this.health = maxHealth;
        this.inventory = new ArrayList<>();
        this.equippedItem = null;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }
    //adding item to  inventory list
    public void addItemToInventory(Item item) {
        inventory.add(item);
    }

    public void displayStatus() {
        System.out.println("Player " + name + " - Health: " + health + "/" + maxHealth);
    }
    //checks if inventory is empty if not it returns in to string format
    public void displayInventory() {
        if (inventory.isEmpty()) {
            System.out.println("Your inventory is empty.");
        } else {
            System.out.println("Your inventory:");
            for (Item item : inventory) {
                System.out.println("- " + item.toString() + " (" + item.getClass().getSimpleName() + ")");
            }
        }
    }

}
