import java.util.List;
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
        printSlow("Hello " + name);

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
            System.out.println("What do you want to do next? \n" + name +":   health: " + state.health );
            System.out.println("[1]: Look around the room.");
            System.out.println("[2]: Move to a new room.");
            System.out.println("[3]: Interact with something in the room.");
            System.out.println("[4]: Examine my inventory.");
            System.out.println("[5]: Use an object from my inventory.");

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
    
                    //checking if the next door is Heavy our locked 
                    if (state.room.doors.containsKey(door)) {
                        if("heavy".equals(door)){
                            if(state.inventory.contains(state.items.get("Filled Sacred Container")) && state.items.get("Filled Sacred Container").isUsed()){
                                String nextRoomName = state.room.doors.get(door);
                                state.room = state.rooms.get(nextRoomName);
                                printSlow("You step through the " + door + " door and enter the " + state.room.name + ".");
                                break;
                                }
                            
                            
                            //if its heavy door its locked unless Players inventory cains Key
                            printSlow("this door is locked");
                            printSlow("you can't get in there");
                            break;
                        } 
                            String nextRoomName = state.room.doors.get(door);
                            state.room = state.rooms.get(nextRoomName);
                            printSlow("You step through the " + door + " door and enter the " + state.room.name + ".");
                        }  else{
                            printSlow("Thats not a door");

                        
                            }
                    
                    
                    break;
                case 3:
                    printSlow("Which item?");
                    itemp = myObj.nextLine();
                    
                    
            //types to name
                    
                    try {
                        Item item = state.items.get(itemp);
                        List<ItemType> types = item.getType();
                        if(types.contains(ItemType.Table) || types.contains(ItemType.NPC)){
                            printSlow(item.desc);
                            printSlow("You cant pick " + item.name + " up");
                            if(types.contains(ItemType.Table )){
                                printSlow(item.use);

                            }else
                            
                            printSlow(item.use);
                            break;


                        }

                        
                        
                        state.rooms.put(state.room.name, state.room);


                        //we could infinetely pick up items this is an if else statement to fix that
                        if(state.room.contents.contains(item)){
                        state.inventory.add(item);
                        state.room.contents.remove(item);
                        printSlow("You pick up the " + item.name + ". " + item.desc + ".");
                        }else{
                            printSlow("Thats not in this ");
                        }
                       
                       
                    } catch (Exception e) {
                        printSlow("Unknown item.");
                    }
                    break;
                case 4:
                    printSlow("Your inventory:");
                    printSlow(state.inventory.toString());
                    break;







                    ///you have to action 
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
                            //if you are using an healing item it goes to the healing class
                            if(item.action.equals("heal")){
                                state.inventory.remove(item);
                                state.health += Healing.healthEffect;
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
            }

            String update = state.update();
            printSlow(update);
        }
        if(state.health <= 0){
            printSlow("You Lose");
        }
        printSlow("You win!");
        }
        
    }

