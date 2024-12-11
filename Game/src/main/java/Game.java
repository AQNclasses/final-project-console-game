import java.util.*;

public class Game {

    static String name;
    static int choice;
    static String itemp;

    // helper function for printing
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

        // only instantiate once
        Scanner myObj = new Scanner(System.in);

        System.out.println("What is your name?");
        name = myObj.nextLine();
        // init game state
        GameState state = new GameState(name);

        // beginning flavor text
        
        printSlow("Welcome, "+name+".");
        printSlow("One day while driving past by the Bass Pro Shops Pyramid in Memphis, Tennessee, you notice what seems to be the enterance to a dungeon.");
        printSlow("");
        printSlow("Intrigued, you decide to investigate.");
        printSlow("");
        printSlow("Upon closer inspection, you find that there is in fact a dungeon here! You decide to enter, in hopes of finding treasure.");
        printSlow("");
        
        while (!state.finished) {
            System.out.println("");
            System.out.println("What do you want to do next?");
            System.out.println("[1]: Look around the room.");
            System.out.println("[2]: Move to a new room.");
            System.out.println("[3]: Pick up an object from the room.");
            System.out.println("[4]: Examine my inventory.");
            System.out.println("[5]: Use an object from my inventory.");
            System.out.println("");
            System.out.println("Health: " + state.playerHealth);
            System.out.println("Hunger: " + state.playerHunger);

            choice = myObj.nextInt();
            myObj.nextLine(); // consume newline from above

            switch (choice) {
                case 1:
                    printSlow("You can see the following items:");
                    for (Item c : state.room.contents) printSlow(c.name);
                    printSlow("You also notice that this room has doors:");
                    for (String c : state.room.doors.keySet()) printSlow(c);
                    break;
                case 2:
                    printSlow("Which door?");
                    String door = myObj.nextLine();
                    try {
                        if (!door.equals("gold")){
                            String rtemp = state.room.doors.get(door);
                            state.room = state.rooms.get(rtemp);
                            printSlow("You step through the " + door + " door. You realize this room is the " + state.room.name + ".");
                        }
                        else{
                            printSlow("You need a key to open this door. If you have one you can use it from your inventory while in this room to unlock the door.");
                        }
                    } catch (Exception e) {
                        printSlow("Unknown door.");
                    }
                    break;
                case 3:
                    printSlow("Which item?");
                    itemp = myObj.nextLine();
                    try {
                        Item item = state.items.get(itemp);
                        if (!item.name.equals("slime")){
                            state.room.contents.remove(item);
                            state.rooms.put(state.room.name, state.room);
                            state.inventory.add(item);
                            printSlow("You pick up the " + item.name + ". " + item.desc + ".");
                        }
                        else{
                            state.playerHealth -= 10;
                            printSlow("The slime attacks you.");
                        }
                    } catch (Exception e) {
                        printSlow("Unknown item.");
                    }
                    break;
                case 4:
                    printSlow("Your inventory:");
                    printSlow(state.inventory.toString());
                    break;
                case 5:
                    printSlow("Which item?");
                    itemp = myObj.nextLine();
                    try {
                        Item item = state.items.get(itemp);
                        if (state.inventory.contains(item)) {
                            item.use();
                            printSlow(item.use);
                            if (item.action.equals("drop")) {
                                state.inventory.remove(item);
                                state.room.contents.add(item);
                                state.rooms.put(state.room.name, state.room);
                            }
                            else if (item.action.equals("unlock") && state.room.doors.containsKey("gold")){
                                String rtemp = state.room.doors.get("gold");
                                state.room = state.rooms.get(rtemp);
                            }
                            else if (item.action.equals("feast")){
                                Food food = (Food) item;
                                if (state.playerHunger == 100){
                                    printSlow("You are not hungry");
                                }
                                else if (state.playerHunger + food.replenishment >= 100){
                                    state.playerHunger = 100;
                                }
                                else{
                                    state.playerHunger += food.replenishment;
                                }
                            }
                            else if (item.action.equals("wear")){
                                Armor armor = (Armor) item;
                                state.playerHealth += armor.protection;
                            }
                            else if (item.action.equals("attack")){
                                boolean containsSlime = false;
                                for (Item currentItem : state.room.contents){
                                    if (currentItem.action.equals("fight")){
                                        printSlow("You vanquish the slime with your " + item + ".");
                                        state.room.contents.remove(currentItem);
                                        containsSlime = true;
                                        break;
                                    }
                                }
                                if (!containsSlime){
                                    printSlow("You swing your " + item + " at nothing.");
                                }
                            }
                        }
                        else {
                            printSlow("Unknown item.");
                        }
                    } catch (Exception e) {
                        printSlow("Unknown item.");
                    }
                    break;
                default:
                    printSlow("Unidentified input, try again?");
            }

            String update = state.update();
            printSlow(update);
        }
        if (!state.died){
            printSlow("You win!");
        }
    }
}
