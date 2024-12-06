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
	
	private static void unlockDoor(Key key, GameState state, String doorColor) {
		String rtemp = state.room.doors.get(doorColor);
		if(rtemp != null) {
			state.rooms.get(rtemp).locked = false;
			printSlow(key.use);
			printSlow("Try to walk through the door.");
		}
		else {
			printSlow("You are using the wrong key.");
		}
	}

	public static void main(String[] args) {

		// only instantiate once
		Scanner myObj = new Scanner(System.in);

		System.out.println("What is your name?");
		name = myObj.nextLine();
		// init game state
		GameState state = new GameState(name);
		
		printSlow("welcome, " +name+ ".");
		System.out.println();
		printSlow("You've been hired to restore an old house.");
		System.out.println();
		printSlow("When you walk in, you didn't realize just how dirty the place was going to be.");
		System.out.println();
		printSlow("You might want to look around and see if there are any cleaning supplies so you can get started.");
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
					boolean locked = state.rooms.get(rtemp).locked;
					Item key = state.items.get("key");
					if(locked){
						printSlow("You're trying to get through the " + door + " door. You realize the door is locked.");
						if(state.inventory.contains(key)) {
							printSlow("You have a " + key + " that you can use to unlock the door.");
						}
					}
					else {
						state.room = state.rooms.get(rtemp);
						printSlow("You step through the " + door + " door. You realize this room is the " + state.room.name + ".");
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
						item.use();
						if (item.action.equals("drop")) {
							printSlow(item.use);
							state.inventory.remove(item);
							state.room.contents.add(item);
							state.rooms.put(state.room.name, state.room);
						}
						if(item.action.equals("unlock") && (item instanceof Key)) {
							Key k = (Key) item;
							switch(k.color) {
							case "gold":
								unlockDoor(k, state, "blue");
								break;
							case "silver":
								unlockDoor(k, state, "red");
								break;
							}	
						}
						if(item.action.equals("sweep") && item.name.equals("broom")) {
							printSlow(item.use);
							state.swept.put(state.room, true);
						}
						if(item.action.equals("mop") && item.name.equals("mop")) {
							printSlow(item.use);
							state.mopped.put(state.room, true);
						}
					}
					else {
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
