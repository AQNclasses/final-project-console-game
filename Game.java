import java.util.*;

public class Game {

    static String name;
    static int choice;
    static String itemp;

    // helper function for printing
    private static void printSlow(String toPrint) {
        char[] chars = toPrint.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            System.out.print(chars[i]);
            try {
                Thread.sleep(25);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
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
         * printSlow("Welcome, "+name+".");
         * System.out.println("");
         * printSlow("You've been studying in the library for hours and decide to take a
         * break by walking around.");
         * System.out.println("");
         * printSlow("You go downstairs into the basement, find an archive room, and get
         * distracted by an old book describing the first version of Java (\'The Java
         * Tutorial\' by Mary Campione and Kathy Walrath, published in 1997).");
         * System.out.println("");
         * printSlow("After reading for a while, you look up and notice that the room
         * looks... different. The lighting seems a little dimmer, the room smells of
         * cigarettes, and you could have sworn the carpet was a different pattern when
         * you first walked into this room.");
         */
        while (!state.finished) {
            if(state.room == state.rooms.get("Kitchen")/*  && state.visited.get(state.room)*/){
                printSlow("The floor is covered in an incredible amount of dust." + "\n");
            }
            else if(state.room == state.rooms.get("Dining Hall")){
                printSlow("A sad dog sits in a corner of this room. It seems he wants something.");
            }

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
                case 1:
                    printSlow("You can see the following items:");
                    for (Item c : state.room.contents)
                        printSlow(c.name);
                    printSlow("You also notice that this room has doors:");
                    for (String c : state.room.doors.keySet())
                        printSlow(c);
                    break;
                case 2:
                    printSlow("Which door?");
                    // printSlow(state.room.doors);
                    //state.room.doors.forEach((key, value) -> printSlow(key));
                    String door = myObj.nextLine();
                    // System.out.println(state.rooms.get(door).locked == true);
                    try {
                        // System.out.println(state.rooms.get(door).locked == true);
                        String rtemp = state.room.doors.get(door);
                        //System.out.println(state.rooms.get(rtemp).locked == true);
                        if (state.rooms.get(rtemp).locked == true) {
                            printSlow("You jiggle the handle of the " + door + " door. However it appears to be locked.");
                        } else {
                            state.room = state.rooms.get(rtemp);
                            printSlow("You step through the " + door + " door. You realize this room is the " + state.room.name + ".");
                        }

                    } catch (Exception e) {
                        printSlow("Unknown door.");
                    }
                    break;
                case 3:
                    printSlow("Which item?");
                    //state.room.contents.forEach((item) -> printSlow(item.name));
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
                            } else if (item.action.equals("unlock")) {
                                // checks if there is a red door in the room, then unlocks that room
                                // System.out.println("test 1");
                                // Key key = (Key) item;
                                // System.out.println("test 2");
                                String color = item.name.split(" ")[0];
                                if (state.room.doors.get(color) != null) {
                                    String rtemp = state.room.doors.get(color);
                                    // key.unlockDoor(state.rooms.get(rtemp));
                                    state.rooms.get(rtemp).locked = false;
                                    printSlow("You spot a " + color + " door and unlock it.");
                                    // state.room.doors.get("red").locked = false;
                                }
                                else{
                                    printSlow("There doesn't seem to be a door you can unlock in this room.");
                                }
                            } else if(item.action.equals("uncover")){
                                Item whiteKey = state.items.get("white key");
                                if(!state.inventory.contains(whiteKey) && state.room == state.rooms.get("Kitchen")){
                                    printSlow("After the dust is brushed away you uncover a glistening white key.");
                                    state.inventory.add(whiteKey);
                                }
                            }
                        } else {
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
