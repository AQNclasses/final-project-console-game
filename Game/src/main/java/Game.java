import java.util.Scanner;

public class Game {

    static String name;
    static int choice;
    static String itemp;

    // helper function for printing
    private static void printSlow(String toPrint) {
        char[] chars = toPrint.toCharArray();
        for (int i=0; i < chars.length; i++) {
            System.out.print(chars[i]);
            try { Thread.sleep(10);} 
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
            System.out.println("[6]: Drop item from inventory.");
            System.out.println("[7]: Examine an Object in the room.");

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
                        printSlow("You step through the " + door + " door. You realize this room is the " + state.room.name + ".");
                    } catch (Exception e) {
                        printSlow("Unknown door.");
                    }
                    break;
                case 3:
                    printSlow("Which item?");
                    itemp = myObj.nextLine();
                    try {
                        Item item = state.items.get(itemp);
                       if(item.types.contains(ItemType.Chest)){
                        printSlow("This item is too heavy to pick up.");
                       }else{
                        state.room.contents.remove(item);
                        state.rooms.put(state.room.name, state.room);
                        state.inventory.add(item);
                        printSlow("You pick up the " + item.name + ". " + item.desc + ".");
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
                        }
                        else {
                            printSlow("Unknown item.");
                        }
                    } catch (Exception e) {
                        printSlow("Unknown item.");
                    }
                    break;
                case 6: 
                    printSlow("Which item?");
                    itemp = myObj.nextLine();
                    try {
                        Item item = state.items.get(itemp);
                        if (state.inventory.contains(item)) {
                            state.inventory.remove(item);
                            printSlow("You dropped the " + item.name + "." );
                        }else{
                            printSlow("Unknown item.");
                        }
                        
                    } catch (Exception e) {
                        printSlow("Unknown item.");
                    }
                    break;
                case 7:
                    printSlow("Which object?");
                    itemp = myObj.nextLine();
                    try {
                        Item item = state.items.get(itemp);
                        if (state.room.contents.contains(item)){
                            printSlow(item.desc);
                            if(item.types.contains(ItemType.Chest)){
                                System.out.println("");
                                System.out.println("Do you want to Open the Chest?");
                                System.out.println("Yes [1]");
                                System.out.println("No [2]");
                                int c = myObj.nextInt();
                                myObj.nextLine();
                                switch(c){
                                    case 1:
                                    boolean key = false;
                                    for(Item i:state.inventory){
                                        if(i.types.contains(ItemType.Key)) key=true;
                                    }
                                    if(key){
                                        state.items.get("treasure chest").locked = false;
                                        printSlow("you opened the chest!");
                                    }else{
                                        printSlow("you dont have a key...");
                                    }
                                    break;
                                    case 2:
                                    break;
                                    default:
                                    break;
                                }
                            }
                        }else{
                            printSlow("This item is not in the room.");
                        }
                        
                    } catch(Exception e){
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
