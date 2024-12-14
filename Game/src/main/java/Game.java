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
                    for (String c : state.room.doors.keySet()) printSlow(c);
                    String door = myObj.nextLine();

                    try {

                        //get the name of the room
                        String rtemp = state.room.doors.get(door);

                        //if the list of lockedrooms contains the room you're trying to go into don't go in
                        if(state.lockedRooms.contains(rtemp)){

                            //print the general message for not being able to go through a door
                            printSlow("You Approach the " + door + " door. You attempt to open the door but it won't budge.");

                            //if the door is the door to the bright room
                            if(rtemp.compareTo("Bright Room") == 0){

                                printSlow("Upon closer inspection of the ornate door you notice it is decorated with a large white gem and three smaller gems.\nBelow the large gem there are three smaller gems with three keyholes, There is a "
                                + "\u001B[31m" + "Red Gem " + "\u001B[0m" + "a " + "\u001B[32m" + "Green Gem " + "\u001B[0m" + "and a " + "\u001B[34m" + "Blue Gem" + "\u001B[0m" + ".");

                            }

                            break;

                        } else {

                            state.room = state.rooms.get(rtemp);


                            printSlow("You step through the " + door + " door. You realize this room is the " + state.room.name + ".");

                        }


                    } catch (Exception e) {

                        printSlow("Unknown door.");

                    }

                    break;

                //pick up an object in the room
                case 3:

                    printSlow("Which item?");
                    //for (Item c : state.room.contents) printSlow(c.name);
                    itemp = myObj.nextLine();

                    try {

                        Item item = state.items.get(itemp);

                        //this is a fix to the original repo, you could just add grab items infinitly, by checking to see if it's in the rooms contents, (which were being updated correctly)
                        //you can have it so you don't just pick it up infinitly
                        if(state.room.contents.contains(item)){

                            if(state.canGrab(item.name)){

                                state.room.contents.remove(item);
                                state.rooms.put(state.room.name, state.room);
                                state.inventory.add(item);
                                printSlow("You pick up the " + item.name + ". " + item.desc + ".");

                            } else {
                                
                                switch(item.name){

                                    case "red key":

                                        printSlow("The red key sits at the bottom of a forge in the center of the room, a sword lies next to it in the forge.");
                                        break;

                                    case "green key":

                                        printSlow("You approach the \u001B[32mgreen key\u001B[0m but you find that it is insnared by vines and held against the wall.\nYou try to grab it but the vine's grip is too strong and you are unable.");
                                        break;

                                    case "blue key":

                                        printSlow("The \u001B[34mblue key\u001B[0m lies at the bottom of the deep pond, you enter the water and try to swim to it.\nBut the water is much too deep and you are forced to return to the surface, and back to the shore.");
                                        break;

                                    case "copper sword":

                                        printSlow("The copper sword is laying in the middle of a glowing forge, to grab it with your hands in it's current state would burn your hand.");
                                        break;

                                    case "vines":
                                        printSlow(item.desc);
                                        printSlow("To touch them would be foolish.");
                                        break;
                                }


                            }

                        } else {

                            printSlow("That item is not in this room, maybe it's in your inventory.");
                            break;
                        }
                        

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
                            //printSlow(item.use);

                            if (item.action.equals("drop")) {

                                if (item.toString().compareTo("poison frog") == 0 && state.room.toString().compareTo("Pond") == 0){

                                    printSlow("You set the frog down by the pond, it swims over to the blue key and grabs it.\nIt swims back to shore and drops the blue key at your feet.");
                                    state.barredItems.remove("blue key");

                                } else {

                                    printSlow(item.use);

                                    state.inventory.remove(item);
                                    state.room.contents.add(item);
                                    state.rooms.put(state.room.name, state.room);

                                }

                            }

                            if (item.action.equals("fill")) {

                                if(state.room.toString().compareTo("Pond") != 0){

                                    printSlow("There is nothing to fill the bucket with in this room.");

                                } else {

                                    printSlow(item.use);
                                    printSlow("[full bucket added]");

                                    state.inventory.remove(item);
                                    state.inventory.add(state.items.get("full bucket"));

                                }

                            }

                            if (item.action.equals("splash")) {

                                if(state.room.toString().compareTo("Forge") != 0){

                                    printSlow("You empty the bucket onto the floor of the room, nothing happens.");
                                    printSlow(item.name + " added.");

                                } else {

                                    printSlow("You empty the bucket into the glowing forge extinguishing the flames.");
                                    printSlow("[empty bucket added]");
                                    state.barredItems.remove("copper sword");
                                    state.barredItems.remove("red key");

                                }

                                state.inventory.remove(item);
                                state.inventory.add(state.items.get("empty bucket"));

                            }

                            if (item.action.equals("unlock")) {

                                if(state.room.toString().compareTo("Starting Room") != 0){

                                    printSlow("There are no doors that his key will unlock in this room.");

                                } else {

                                    printSlow(item.use);
                                    state.usedKeys.add(item);
                                    state.inventory.remove(item);

                                    if(state.usedKeys.size() == 3){

                                        state.lockedRooms.remove("Bright Room");

                                    }
                                    
                                }

                            }

                            if (item.action.equals("slash")) {

                                if(state.room.toString().compareTo("Garden") != 0){

                                    printSlow("There is nothing to attack in this room.");

                                } else {

                                    printSlow(item.use);
                                   

                                    printSlow("The vines fall apart, dropping the green key on the ground.");
                                    state.barredItems.remove("green key");

                                    state.room.contents.remove(state.items.get("vines"));
                                    state.room.contents.add(state.items.get("herb"));
                                    
                                }

                            }

                            if (item.action.equals("eat")) {

                                printSlow(item.use);

                                state.inventory.remove(state.items.get("herb"));
                                    
                                

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
