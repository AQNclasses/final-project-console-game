
import java.util.Scanner;

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
        // /**
        //  * printSlow("Welcome, "+name+"."); System.out.println("");
        //  * printSlow("You've been studying in the library for hours and decide
        //  * to take a break by walking around."); System.out.println("");
        //  * printSlow("You go downstairs into the basement, find an archive room,
        //  * and get distracted by an old book describing the first version of
        //  * Java (\'The Java Tutorial\' by Mary Campione and Kathy Walrath,
        //  * published in 1997)."); System.out.println(""); printSlow("After
        //  * reading for a while, you look up and notice that the room looks...
        //  * different. The lighting seems a little dimmer, the room smells of
        //  * cigarettes, and you could have sworn the carpet was a different
        //  * pattern when you first walked into this room.");
        //  */
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
                case 1:
                    printSlow("You can see the following items:");
                    for (Item c : state.room.contents) {
                        printSlow(c.name);
                    }
                    printSlow("You also notice that this room has doors:");
                    for (String c : state.room.doors.keySet()) {
                        printSlow(c);
                    }
                    break;
                case 2:
                    printSlow("Which door?");
                    String door = myObj.nextLine();
                    try {

                        // Implementation of locked doors
                        if (state.room.lockedDoors.containsKey(door)) {
                            String requiredKey = state.room.lockedDoors.get(door);
                            boolean hasKey = state.inventory.stream().anyMatch(item -> item.name.equals(requiredKey));
                            if (!hasKey) {
                                printSlow("The door is locked. You need the " + requiredKey + " to open it.");
                                break;
                            }
                        }
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
                            int i = 1;
                            // Another menu for what you want to do with items
                            System.out.println("What would you like to do with the " + item.name + "?");
                            System.out.println("[" + i++ + "]: Inspect the item.");
                            System.out.println("[" + i++ + "]: Use the item.");
                            System.out.println("[" + i++ + "]: Drop the item.");
                            if (item instanceof Weapon || item instanceof Animal) {
                                System.out.println("[" + i++ + "]: Attack with the item.");
                            } 
                            if (item instanceof Book) {
                                System.out.println("[" + i++ + "]: Read the book");
                            }
                            int actionChoice = myObj.nextInt();
                            myObj.nextLine();

                            switch (actionChoice) {
                                case 1: // Inspect
                                    System.out.println(item.inspect());
                                    break;
                                case 2: // Use
                                    item.use();
                                    System.out.println(item.use);
                                    if (item.action.equals("drop")) {
                                        state.inventory.remove(item);
                                        state.room.contents.add(item);
                                        state.rooms.put(state.room.name, state.room);
                                    }
                                    break;
                                case 3: // Drop
                                    state.inventory.remove(item);
                                    state.room.contents.add(item);
                                    state.rooms.put(state.room.name, state.room);
                                    System.out.println("You dropped the " + item.name + ".");
                                    break;
                                case 4: // Attack (if applicable)
                                    if (item instanceof Weapon) {
                                        Weapon weapon = (Weapon) item;
                                        int damage = weapon.attack();
                                        System.out.println("You attack with the " + weapon.name + ", dealing " + damage + " damage!");
                                        break;
                                    } else if (item instanceof Animal) {
                                        Animal animal = (Animal) item;
                                        int damage = animal.attack();
                                        System.out.println("The " + animal.name + " attacks, dealing " + damage + " damage!");
                                        break;
                                    }
                                case 5: // if item is book and weapon
                                    Book book = (Book) item;
                                    String content = book.read();
                                    System.out.println("The book reads: " + content);
                                    break;
                                default:
                                    System.out.println("Invalid action. Try again.");
                            }
                        } else {
                            printSlow("You don't have that item.");
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
