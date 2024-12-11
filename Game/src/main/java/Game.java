import java.util.*;

public class Game {

    static String name;
    static int choice;
    static String itemp;
    static int playerHealth = 100;
    static int enemyHealth = 0;
    static boolean enemyPresent = false;

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


        printSlow("Welcome, " + name + ". Be careful - there are monsters here!");
        printSlow("Your health: " + playerHealth);
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
            if (enemyPresent) {
                System.out.println("[6]: Fight the enemy!");
            }

            choice = myObj.nextInt();
            myObj.nextLine();

            if (!enemyPresent && Math.random() < 0.2) {
                enemyPresent = true;
                enemyHealth = 50;
                printSlow("\nA monster appears! It looks hostile!");
            }

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
                        if (!state.canOpenDoor(door, state.room)) {
                            printSlow("This door appears to be locked. You need a key to open it.");
                            break;
                        }
                        String rtemp = state.room.doors.get(door);
                        state.room = state.rooms.get(rtemp);
                        if (door.equals("iron") && !state.unlockedDoors.getOrDefault("iron", false)) {
                            printSlow("You use the magic key to unlock the door.");
                            state.unlockDoor("iron");
                        }
                        printSlow("You step through the " + door + " door. You realize this room is the " + state.room.name + ".");
                        if (enemyPresent) {
                            printSlow("You managed to escape from the monster!");
                            enemyPresent = false;
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
                            if (item.types.contains(ItemType.Healing)) {
                                playerHealth = Math.min(100, playerHealth + 20);
                                printSlow("You used a healing item! Health: " + playerHealth);
                                state.inventory.remove(item);
                            } else if (item.types.contains(ItemType.Weapon) && enemyPresent) {
                                int damage = 20 + (int)(Math.random() * 10);
                                enemyHealth -= damage;
                                printSlow("You attack the monster for " + damage + " damage!");
                                if (enemyHealth <= 0) {
                                    printSlow("You defeated the monster!");
                                    enemyPresent = false;
                                }
                            } else {
                                item.use();
                                printSlow(item.use);
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
                    if (enemyPresent) {
                        int damage = 10 + (int)(Math.random() * 10);
                        playerHealth -= damage;
                        printSlow("The monster attacks you for " + damage + " damage!");
                        printSlow("Your health: " + playerHealth);

                        int counterDamage = 5 + (int)(Math.random() * 5);
                        enemyHealth -= counterDamage;
                        printSlow("You punch the monster for " + counterDamage + " damage!");

                        if (enemyHealth <= 0) {
                            printSlow("You defeated the monster!");
                            enemyPresent = false;
                        }
                    }
                    break;

            default:
                    printSlow("Unidentified input, try again?");
            }
            if (enemyPresent && choice != 6) {
                int damage = 10 + (int)(Math.random() * 10);
                playerHealth -= damage;
                printSlow("\nThe monster attacks you for " + damage + " damage!");
                printSlow("Your health: " + playerHealth);
                if (playerHealth <= 0) {
                    printSlow("You have succumbed to your injuries. Game Over.");
                    break;
                }
            }

            if (playerHealth <= 0) {
                printSlow("You have succumbed to your injuries. Game Over.");
                break;
            }
            String update = state.update();
            printSlow(update);

        }
        printSlow("You win!");
    }
}
