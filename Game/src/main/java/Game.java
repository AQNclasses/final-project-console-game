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
        /**
        printSlow("Welcome, "+name+".");
        System.out.println("");
        printSlow("You've been studying in the library for hours and decide to take a break by walking around.");
        System.out.println("");
        printSlow("You go downstairs into the basement, find an archive room, and get distracted by an old book describing the first version of Java (\'The Java Tutorial\' by Mary Campione and Kathy Walrath, published in 1997).");
        System.out.println("");
        printSlow("After reading for a while, you look up and notice that the room looks... different. The lighting seems a little dimmer, the room smells of cigarettes, and you could have sworn the carpet was a different pattern when you first walked into this room.");
        */

        while (!state.finished) {
            System.out.println("");
            System.out.println("What do you want to do next?");
            System.out.println("[1]: Look around the room.");
            System.out.println("[2]: Move to a new room.");
            System.out.println("[3]: Pick up an object from the room.");
            System.out.println("[4]: Examine my inventory.");
            System.out.println("[5]: Use an object from my inventory.");

            String input = myObj.nextLine();
            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                printSlow("Invalid input. Please enter a number between 1 and 5.");
                continue; 
            }

            switch (choice) {
                case 1:
                    printSlow("You can see the following items:");
                    if (state.room.contents.isEmpty()) {
                        printSlow(" - No items in this room.");
                    } else {
                        for (Item c : state.room.contents) printSlow("- " + c.name);
                    }
                    printSlow("You also notice that this room has doors:");
                    for (String d : state.room.doors.keySet()) {
                        printSlow("- " + d);
                    }
                    break;
                case 2:
                    printSlow("Which door?");
                    String door = myObj.nextLine();
                    if (state.room.doors.containsKey(door)) {
                        if (state.locked(door)) {
                            printSlow("You step through the " + door + " door. You realize this room is the " + state.room.name + ".");
                        } else {
                            printSlow("The " + door + "door won't open, but I notice a keyhole in the handle.");
                        }
                    } else {
                        printSlow("Unknown door.");
                        printSlow("The doors avaiable are: ");
                        for (String d : state.room.doors.keySet()) {
                            printSlow("- " + d);
                        }
                    }
                    break;
                case 3:
                    printSlow("Which item?");
                    itemp = myObj.nextLine();
                    try {
                        Item item = state.items.get(itemp);
                        if (item == null) {
                            printSlow("Unknown item.");
                            break;
                        }
                        if (state.room.contents.contains(item)) {
                            state.room.contents.remove(item);
                            state.inventory.add(item);
                            printSlow("You pick up the " + item.name + ". " + item.desc + ".");
                        } else {
                            printSlow("You cannot seem to find " + item.name);
                        }
                    } catch (Exception e) {
                        printSlow("Unknown item.");
                    }
                    break;
                case 4:
                    printSlow("Your inventory:");
                    if (state.inventory.isEmpty()) {
                        printSlow(" - Empty");
                    } else {
                        for (Item inv : state.inventory) {
                            printSlow("- " + inv.name);
                        }
                    }
                    break;
                case 5:
                    printSlow("Which item?");
                    itemp = myObj.nextLine();
                    try {
                        Item item = state.items.get(itemp);

                        if (item == null) {
                            printSlow("Unknown item.");
                            break;
                        }

                        if (state.inventory.contains(item)) {
                            item.use(state); 
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
        printSlow("You win!");
    }
}
