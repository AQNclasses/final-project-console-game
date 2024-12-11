import java.util.Scanner;

public class Game {

    static String name;
    static int choice;
    static String itemp;
    static int ogerHealth = 15;
    static int locksUnlocked = 0;

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


        // beginning flavor text
        
        printSlow("Welcome, "+name+".");
        System.out.println("");
        printSlow("You've been studying in the library for hours and decide to take a break by walking around.");
        System.out.println("");
        printSlow("You go downstairs into the basement, find an archive room, and get distracted by an old book describing a mysterious door of treasures at the center of the earth, (\'Lost Treasure\', written by UNKNOWN)");
        printSlow("Before you are able to open the book, you start feeling drowsy and rest your head on the table, your blinking slows... Upon blinking once more, you look up and notice that the room looks... different. The lighting seems a little dimmer and you could have sworn the carpet was a different pattern when you first walked into this room.");
        System.out.println("");
        printSlow("You hear an ever so slight noise... what sounds like a groan is coming from the closet...");
            

        GameState state = new GameState(name);
        String ld = "library door", cl = "closet", lt = "left tunnel", rt = "right tunnel", va = "vault", gd = "gold door", sk = "skeleton";
        Room room = state.room;

        while (!state.finished) {
            System.out.println("");
            System.out.println("What do you want to do next?");
            System.out.println("[1]: Look around the area.");
            System.out.println("[2]: Change locations");
            System.out.println("[3]: Pick up an object in the area.");
            System.out.println("[4]: Examine my inventory.");
            System.out.println("[5]: Use an object from my inventory.");

            choice = myObj.nextInt();
            myObj.nextLine(); // consume newline from above
            
            switch (choice) {
                case 1:
                    room = state.room;
                    printSlow("You can see the following items:");
                    for (Item c : state.room.contents) printSlow(c.name);

                    if (room.equals(state.rooms.get(lt)) || room.equals(state.rooms.get(rt))) {
                        printSlow("This is the end of the tunnel. You can walk back/forward to:");
                    }
                    else if (room.equals(state.rooms.get(cl))) {
                        printSlow("You see that this room has doors/paths:");
                    }
                    else if (room.equals(state.rooms.get(ld))) {
                    printSlow("You also notice that this room has doors:");
                    }
                    else if (room.equals(state.rooms.get(gd)) || room.equals(state.rooms.get(sk))) {
                        printSlow("You can step back into:");
                    }
                    else if(room.equals(state.rooms.get(va))) {
                        printSlow("You can step away from " + room + " back into:");
                    }
                    for (String c : state.room.doors.keySet()) printSlow(c);
                    break;
                case 2:
                    printSlow("Which area?");
                    String door = myObj.nextLine();
                    
                    try {
                        if (door.equals(ld) && state.visited.get(state.rooms.get(ld))) {
                            printSlow("You try to open the library door but it appears to be locked.");
                            break;
                        } else if (door.equals(cl) && !state.items.get("book").isPickedUp()) {
                            printSlow("(Hint: take the book before proceeding to the " + door + ")");
                            break;
                        }                     
                        String rtemp = state.room.doors.get(door); // getting room but in string form, checks to see if door is part of rooms doors

                        Room prevroom = state.room;
                        // need an if else catch statement if door is not a door
                        state.room = state.rooms.get(rtemp); // setting room in room form
                        room = state.room;

                        if((door.equals(lt)|| door.equals(rt)) && !prevroom.equals(state.rooms.get(cl))) {
                            printSlow("You begin walking deep into the " + door + ".");
                        }
                        else if (door.equals(cl) && state.visited.containsKey(room) && state.visited.get(room)) {
                            printSlow ("You walk back to the closet. You still need find a way out.");
                        }
                        else if (door.equals(cl)) {
                        printSlow("You get up out of your chair and walk to the " + door + ".");
                        }
                        else if (door.equals("left tunnel")|| door.equals("right tunnel") ) {
                            printSlow("You walk out of the closet, stepping into the " + door + ".");
                        }
                        else if (door.equals(sk)) {
                            printSlow("You walk up to the " + room.name + " and kneel down.");
                        }
                        else if (door.equals(gd) && !state.inventory.contains(state.items.get("key"))) {
                            printSlow("You are infront of the glowing " + room.name + ", it remains locked.");
                        }
                        else if (door.equals(gd) && state.inventory.contains(state.items.get("key"))) {
                            printSlow("You are infront of the glowing " + room.name + ", the only thing left to do is use the keys.");
                        }                        
                        else if (room.equals(state.rooms.get("vault"))) {
                            printSlow("You step into the " + room);
                            if (ogerHealth > 0) {
                                printSlow("Kill oger to collect greenkey.");
                                printSlow("Oger Health: " + ogerHealth);
                            }
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
                        if (item.equals(state.items.get("greenkey")) && ogerHealth > 0) {
                            printSlow("The oger is blocking the greenkey. You must defeat the oger first!");
                            break;
                        }
                        if (item.equals(state.items.get("book"))) {
                            item.pickedUp();
                        }
                        state.room.contents.remove(item);
                        state.rooms.put(state.room.name, state.room);
                        state.inventory.add(item);
                        printSlow("You pick up the " + item.name + ". " + item.desc );
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
                            if (item.action.equals("slip")) {
                                if (item instanceof Plant) {
                                    Plant plant = (Plant)item;
                                    if (!plant.hasDropped()) {
                                        printSlow(plant.notdroppedString());
                                        plant.drop();
                                    } else {
                                        printSlow(plant.droppedString());
                                    }
                                        state.inventory.remove(item);
                                        state.room.contents.add(item);
                                } else {
                                    printSlow("This item can't be used to slip plant");
                                }
                            }
                            else if (item.action.equals("vanish")) {
                                state.inventory.remove(item);
                                printSlow(item.use);
                                state.inventory.add(state.items.get("dagger"));
                            } 
                            else if (item.action.equals("drop")) {
                                if (state.room.equals(state.rooms.get(cl)) && state.inventory.contains(state.items.get("redkey")) && state.inventory.contains(state.items.get("greenkey"))) {
                                    if (item instanceof Animal) {
                                        Animal animal = (Animal)item;
                                        if (!animal.isretrieveKey()) {
                                            state.inventory.remove(item); 
                                            printSlow("The mouse scurries into the hole and retrieves the gold key. The gold key is on the floor now.");
                                            state.room.contents.add(state.items.get("goldkey"));
                                            state.room.contents.add(item);
                                            animal.retrievedKey();
                                        } else {
                                            printSlow("gold key has already been found");
                                        }
                                    } else {
                                        printSlow("This item cant be used to retrieve key");
                                    }
                                    } else {
                                        printSlow("We drop " + item + ".");
                                        state.inventory.remove(item);
                                        state.room.contents.add(item);
                                }
                            }
                            else if (item.action.equals("unlock") && state.room.equals(state.rooms.get(gd))) {
                                if (item instanceof Key) {
                                    Key keykey = (Key)item;
                                    if (!keykey.isUnlock()) {
                                        locksUnlocked +=1;
                                        printSlow(item.use);
                                        keykey.unlock();
                                    } else {
                                        printSlow(item.name + " already used.");
                                    }
                                printSlow("Locks unlocked: " + locksUnlocked + "/3");
                                } else {
                                    printSlow("This item can't be used to unlock.");
                                }
                            } else if (item.action.equals("hit") && state.room.equals(state.rooms.get(va))) {
                                printSlow(item.use);
                                if (!item.name.equals("rock")) {
                                printSlow("The " + item + " snaps in half.");
                                } else {
                                    printSlow("The " + item + " shattters against ogers skin.");
                                }
                                state.inventory.remove(item);
                                
                                // Check if item is an instance of Weapon before casting
                                if (item instanceof Weapon) {
                                    Weapon weapon = (Weapon) item; 
                                    int damaging = weapon.attack(); 
                                    ogerHealth -= damaging;
                                    printSlow("Oger Health: " + ogerHealth);
                                    
                                    if (ogerHealth > 0) {
                                        printSlow("Find another item to attack it with!");
                                    } else {
                                        printSlow("You killed the beast! Go collect your key now.");
                                    }
                                } else {
                                    printSlow("This item can't be used to attack.");
                                }
                            } 
                            else {
                            printSlow("We can't use " + item + " in this room.");
                        }
                    }
                            if (locksUnlocked == 3) {
                                state.room = state.rooms.get("light");
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
