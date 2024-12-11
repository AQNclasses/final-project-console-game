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

        //beginning flavor text
        // printSlow("Welcome, "+name+".");
        // System.out.println("");
        // printSlow("You've been studying for your CSCI241 Final when you stumble across an area of your neighborhood you haven't seen before on a walk.");
        // System.out.println("");
        // printSlow("You wander through an entrance into a room and find a dusty book titled 'The First Scroll of Java'. As you read, you feel the room shift around you, the air growing colder, the light dimming.");
        // System.out.println("");
        // printSlow("A feeling compels you to explore, a force pulling you further into the entirety of the room. Something feels... odd.");
                
        while (!state.finished) {
            System.out.println("");
            System.out.println("What do you want to do next?");
            System.out.println("[1]: Look around the room.");
            System.out.println("[2]: Move to a new room.");
            System.out.println("[3]: Pick up an object from the room.");
            System.out.println("[4]: Examine my inventory.");
            System.out.println("[5]: Use an object from my inventory.");
            System.out.println("[6]: Use an object in the room.");

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
                        String rtemp = state.room.doors.get(door);
                        state.room = state.rooms.get(rtemp);
                        printSlow("You step through the " + door + " door. You have entered the " + state.room.name + ".");
                    } catch (Exception e) {
                        printSlow("Unknown door.");
                    }
                    break;
                case 3:
                    printSlow("Which item?");
                    itemp = myObj.nextLine();
                        try {
                            Item item = state.items.get(itemp);
                            if ((item != null) && !(item instanceof Trap) && !(item instanceof Animal) && state.room.contents.contains(item)) {
                                state.room.contents.remove(item);
                                state.rooms.put(state.room.name, state.room);
                                state.inventory.add(item);
                                printSlow("You pick up the " + item.name + ". " + item.desc + ".");
                            } else {
                                printSlow("That item is not findable/able to be picked up.");
                            }
                        } catch (Exception e) {
                            printSlow("You are unable to grab this item.");
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
                        }
                        else {
                            printSlow("Unknown item.");
                        }
                    } catch (Exception e) {
                        printSlow("Unknown item.");
                    }
                    break;
                case 6: //Added case.
                    printSlow("What item would you like to interact with?"); 
                    itemp = myObj.nextLine();
                    try {
                        Item item = state.items.get(itemp);
                        if (item != null && (item.types.contains(ItemType.Trap) || item.types.contains(ItemType.Animal))) {
                            item.used = true;
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
    }
}
