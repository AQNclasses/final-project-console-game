 import java.util.*;

public class Game {
    static String name;
    static int choice;
    static String itemp;
    

    // Helper function for printing
    private static void printSlow(String toPrint) {
        char[] chars = toPrint.toCharArray();
        for (int i=0; i < chars.length; i++) {
            System.out.print(chars[i]);
            try { Thread.sleep(25);} 
            catch (InterruptedException e) {Thread.currentThread().interrupt();}
        }
        System.out.println("");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) {
        Scanner myObj = new Scanner(System.in);

        System.out.println("What is your name?");
        name = myObj.nextLine();
        GameState state = new GameState(name);

        // Beginning flavor text
        printSlow("Welcome, " + name + ".");
        System.out.println("");
        printSlow("For three days and two restless nights, you've wandered through the unending desert.");
        System.out.println("");
        printSlow("The sun has shown no mercy, searing your skin by day, and as it dips below the horizon, the chill of night begins to set in.");
        System.out.println("");
        printSlow("In the distance, shadowy red rock silhouettes pierce the horizon, and beside them, a faint shimmer hints at the presence of an oasis.");

        System.out.println("");
        printSlow("With renewed hope and weary legs, you press onward.");
        
        System.out.println("");
        printSlow("All you have is a rusty sword and a small hatchet, but you are determined to escape the desert tonight.");

        while (!state.finished) {
            
            System.out.println("");
            System.out.println("What do you want to do next?");
            System.out.println("[1]: Look around the room.");
            System.out.println("[2]: Move to a new room.");
            System.out.println("[3]: Pick up or interact with an object from the room.");
            System.out.println("[4]: Examine my inventory.");
            System.out.println("[5]: Use an object from my inventory.");

            try {
                choice = myObj.nextInt();
                myObj.nextLine(); // consume newline from above
            } catch (InputMismatchException e) {
                myObj.nextLine(); // consume the invalid input
                printSlow("Invalid input. Please enter a number between 1 and 5.");
                continue;
            }

            switch (choice) {
            case 1:
             printSlow("You can see the following items:");
                for (Item c : state.room.contents) printSlow(c.name);

                    // if these conditions are met reveal hidden passage (end game room)
                if (state.room.name.equals("Oasis") 
                    && state.itemStates.getOrDefault("symbols_revealed", false) 
                    && state.itemStates.getOrDefault("torch_extinguished", false) 
                    && !state.itemStates.getOrDefault("veiled_passage_revealed", false)) {
                    state.itemStates.put("veiled_passage_revealed", true);
                    printSlow("As you look around, a veil lifts, revealing a hidden, vine-covered archway. The 'green' direction now leads to a Veiled Passage.");
                    }

                    printSlow("You also notice that this room has doors:"); 
                    for (String c : state.room.doors.keySet()) { // print doors in room
                    if (state.room.name.equals("Oasis") && c.equals("green") && state.itemStates.getOrDefault("veiled_passage_revealed", false)) { // special case for green door, only print if passage revealed
                        printSlow("green (veiled passage)");
                    } else {
                        printSlow(c);
                        }
                    }
                    break;

            case 2:
                    
                if (state.isEnemyPresent()) { // Prevent player from moving if enemies are present in the room
                printSlow("The enemy blocks your path! You must defeat it before you can leave.");
                break; 
                }

                printSlow("Which door?");
                String door = myObj.nextLine();
                    String rtemp = state.room.doors.get(door);

                
                if (state.room.name.equals("Red Rock Cavern") && door.equals("black")) { // special case for black door
                    
                 if (!state.itemStates.getOrDefault("door_unlocked", false)) { // if door is locked
                    printSlow("The black door is locked. You cannot pass through it yet."); 
                  break; // prevent movement
                }
                 }
                 if (rtemp == null) {
                 printSlow("Unknown door.");
                break;
                 }
                state.room = state.rooms.get(rtemp);
                    if (state.room == null) {
                    printSlow("Unknown door.");
                    break;
                     }
                    if (state.room.name.equals("Hidden Chamber")) { // if hidden chamber, print special text for enemy entrance
                     printSlow("You step through the black door into a hidden chamber shrouded in darkness."); 
                        printSlow("A sinister presence hangs in the air...");
                    } else {
                 printSlow("You step through the " + door + " door. You realize this room is the " + state.room.name + ".");
                 }

                 state.spawnEnemyInRoom(); // attempt tp spawn enemy if room has one 
                break;
                
            case 3:
            boolean enemyPresent = false;
                for (Item item : state.room.contents) { // check room for enemy 
                if (item.types.contains(ItemType.Enemy)) {
                enemyPresent = true;
                break;
            }
        }

            if (enemyPresent) { // if enemy is presnt you can't interact with items in the room
            printSlow("An enemy is present in the room. You must defeat it before you can interact with other items!");
            break; // end case 3
            }
   
            printSlow("Which item?");
            itemp = myObj.nextLine();

            try {
            Item item = state.items.get(itemp); 
            if (item == null) { // item doesnt exist
            printSlow("Unknown item.");
            break;
        }
        
        if (!state.room.contents.contains(item)) { // room doesn't contain item
            printSlow("That item is not in the room.");
            break;
        }

        if (item.types.contains(ItemType.Plant)) { // special case for plants, can't pick up
            printSlow("You can't pick up the " + item.name + ". It's firmly rooted.");
            break;
        }
        if (item.types.contains(ItemType.Animal)) { // special case for animals, get attacked if you attempt to pick up
            printSlow("You try to interact with the " + item.name + "!");
            item.use(state);
            break;
        }
        
        if (item.types.contains(ItemType.Stationary)) { // special case for stationary 
            printSlow("You interact with the " + item.name + ".");
            printSlow(item.desc); 

            if (item.name.equals("strange statue")) { // special case for strange statue
                boolean brazierLit = state.itemStates.getOrDefault("brazier_lit", false);
                boolean keyGiven = state.itemStates.getOrDefault("key_given", false);
                
                if (brazierLit && !keyGiven) { // if brazier is lit
                    Item key = state.items.get("key"); 
                    if (key != null && !state.inventory.contains(key)) {
                        state.inventory.add(key); // add key to inventory
                        state.itemStates.put("key_given", true); // flag that game objective is complete
                        printSlow("For giving me new life, take this key as a token of my gratitude. But beware of what lurks in the shadows...");
                        printSlow("You now hold a key.");
                    }
                } else if (brazierLit && keyGiven) { // interaction after key is given
                    printSlow("The strange statue stands silently, its purpose fulfilled.");
                } else {
                    printSlow("The strange statue stands silently, unmoved.");
                }
            }
            break; // End interaction after stationary item is interacted with
        }

        
        state.room.contents.remove(item); // remove item from room
        state.inventory.add(item); // pick up
        printSlow("You pick up the " + item.name + ". " + item.desc + ".");

    } catch (Exception e) {
        printSlow("An error occurred while interacting with the item.");
        e.printStackTrace();
    }
    break;

                case 4:
                printSlow("Your inventory:");
                printSlow(state.inventory.toString());
                break;

                    case 5:
    
                if (state.isEnemyPresent()) { // change text when enemy is in room
                printSlow("Which item will you use to fight the enemy?");
                 } else {
                 printSlow("Which item do you want to use?");
                }
    
             String itemp = myObj.nextLine();
            String useResult = state.useItem(itemp);
            if (!useResult.isEmpty()) {
             printSlow(useResult);
            }
            break;
                
                default:
                    printSlow("Unidentified input, try again? Please enter a number between 1 and 5.");
            }

            String update = state.update();
            if (!update.isEmpty()) {
                printSlow(update);
            }
        }

        if (state.endMessage != null && !state.endMessage.isEmpty()) {
            printSlow(state.endMessage);
        } else {
            printSlow("Game Over.");
        }

        myObj.close();
    }
} 
