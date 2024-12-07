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

        //runs as long as game has not finished
        while (!state.finished) {

            System.out.println("");
            System.out.println("What do you want to do next?");
            System.out.println("[1]: Look around the room.");
            System.out.println("[2]: Move to a new room.");
            System.out.println("[3]: Pick up an object from the room.");
            System.out.println("[4]: Examine my inventory.");
            System.out.println("[5]: Use an object from my inventory.");

            choice = myObj.nextInt();
            myObj.nextLine(); // consume newline from above

            switch (choice) {

                //look around the room
                case 1:

                    printSlow("You can see the following items:");
                    for (Item c : state.room.contents) printSlow(c.name);
                    printSlow("You also notice that this room has doors:");
                    for (String c : state.room.doors.keySet()) printSlow(c);
                    break;

                //move to a new room
                case 2:

                    printSlow("Which door?");
                    String door = myObj.nextLine();

                    try {

                        String rtemp = state.room.doors.get(door);
                        state.room = state.rooms.get(rtemp);
                        printSlow("You step through the " + door + " door. You realize this room is the " + state.room.name + ".");

                    } catch (Exception e) {

                        printSlow("Unknown door.");

                    }

                    break;

                //pick up an object in the room
                case 3:

                    printSlow("Which item?");
                    itemp = myObj.nextLine();

                    try {

                        Item item = state.items.get(itemp);
                        state.room.contents.remove(item);
                        state.rooms.put(state.room.name, state.room);
                        state.inventory.add(item);
                        printSlow("You pick up the " + item.name + ". " + item.desc + ".");

                    } catch (Exception e) {

                        printSlow("Unknown item.");

                    }
                    break;

                //examine your inventory
                case 4:

                    printSlow("Your inventory:");
                    printSlow(state.inventory.toString());
                    break;
                    
                //use an item
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

                        } else {

                            printSlow("Unknown item.");

                        }

                    } catch (Exception e) {

                        printSlow("Unknown item.");

                    }

                    break;

                //anything else besides one of th above inputs
                default:
                    printSlow("Unidentified input, try again?");
            }

            //Check to see if the win condition has been achieved
            String update = state.update();

            
            printSlow(update);
        }

        //win text
        printSlow("You win!");
    }
}
