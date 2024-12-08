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
            System.out.println("[6]: Drive a vehicle in the room.");
            System.out.println("[7]: Attack Enemy");
            choice = myObj.nextInt();
            myObj.nextLine(); // consume newline from above

            switch (choice) {
                case 1:
                    printSlow("You can see the following items:");
                    for (Item c : state.room.contents) printSlow(c.name);
                        if(state.room.doors.containsKey("brown drawer")){
                            printSlow("You also can see there is room has drawers:");
                        }
                    for (String c : state.room.doors.keySet()){
                        if(c.equals("brown drawer")){
                            printSlow(c);
                        }
                    }
                    printSlow("You also notice that this room has doors:");
                    for (String c : state.room.doors.keySet()) 
                        if(!c.equals("brown drawer")){
                            printSlow(c);
                    }
                    break;
                case 2:
                    printSlow("Which door?");
                    String door = myObj.nextLine();
                    try {
                        if(!door.equals("brown drawer")){
                            String rtemp = state.room.doors.get(door);
                            state.room = state.rooms.get(rtemp);
                            printSlow("You step through the " + door + ". You realize this room is the " + state.room.name + ".");
                        }
                        else{
                            String rtemp = state.room.doors.get(door);
                            state.room = state.rooms.get(rtemp);
                            printSlow("You open the " + door + ".");
                            } 
                        }   
                    catch (Exception e) {
                        printSlow("Unknown door.");
                        }
                    break;
                case 3:
                    printSlow("Which item?");
                    itemp = myObj.nextLine();
                    try {
                        Item item = state.items.get(itemp);
                        if(!itemp.equals("lamborghini") && !itemp.equals("motorcycle")){
                            state.room.contents.remove(item);
                            state.rooms.put(state.room.name, state.room);
                            state.inventory.add(item);
                            printSlow("You pick up the " + item.name + ". " + item.desc + ".");
                        }
                        else{
                            printSlow("This " + item.name + " is too heavy to be picked up.");
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
                    boolean noVehicle = true;
                    for (Item c : state.room.contents){ 
                        if(c.types.contains(ItemType.Vehicle)== true){
                            noVehicle = false;
                        }    
                    }
                    try{
                    if(noVehicle == false){
                        printSlow("Which vehicle?");
                        itemp = myObj.nextLine();
                        try {
                            Item item = state.items.get(itemp);
                            if(state.room.contents.contains(item) && !itemp.equals("motorcycle")) {
                                if(state.inventory.contains(state.items.get("car keys"))){
                                    item.use();
                                    printSlow("You get in the " + item.name + ". " + item.desc + ".");
                                }   
                                else{
                                    printSlow("You dont have the keys for the " + item.name + ". ");
                                }
                            }
                            else{
                                item.use();
                                printSlow("You get in the " + item.name + ". " + item.desc + ".");
                                state.room.contents.remove(item);
                            }
                        } 
                        catch (Exception e) {
                            printSlow("Unknown item.");
                        }
                    }
                    else{
                        printSlow("There are no vehicles in this room.");
                    }
                }
                catch(Exception e){
                    printSlow("Invalid input.");
                }
                    break;
                case 7:
                    boolean noEnemies = true;
                    String enemy = "";
                    for (Item c : state.room.contents){ 
                        if(c.types.contains(ItemType.Enemy)== true){
                            enemy = c.name;
                            noEnemies = false;
                        }    
                    }
                    if(noEnemies == false ){
                        printSlow("What item would you like to attack with?");
                        itemp = myObj.nextLine();
                        try {
                            Item item = state.items.get(itemp);
                            if(item.types.contains(ItemType.Weapon)){
                                state.items.get(name);
                                printSlow("How many times do you want to hit " + enemy + "?");
                                System.out.println("[1]");
                                System.out.println("[2]");
                                System.out.println("[3]");
                                int attack = myObj.nextInt();
                                myObj.nextLine();
                                switch(attack){
                                    case 1:
                                        printSlow(enemy + " beat the living crap outta you.");
                                        state.playerDead = true;
                                        break;
                                    case 2:
                                        state.dead = true;
                                        break;
                                    case 3:
                                        printSlow(enemy + " beat the living crap outta you.");
                                        state.playerDead = true;
                                        break;
                                    default:
                                        printSlow(enemy + " beat the living crap outta you.");
                                        state.playerDead = true;
                                        break;
                                }
                            }
                        } catch (Exception e) {
                            printSlow("Unknown item.");
                        }
                    }
                    break;
                default:
                    printSlow("Unidentified input, try again?");
                    break;
            }
            String update = state.update();
            printSlow(update);
        }
        printSlow("You win!");
    }
}

