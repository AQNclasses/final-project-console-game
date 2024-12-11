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
			key.use();
			printSlow(key.use);
			printSlow("Try to walk through the door.");
		}
		else {
			printSlow("You are using the wrong key.");
		}
		allKeys(state);
	}
	
	private static void allKeys(GameState state) {
		if(state.items.get("silver key").used && state.items.get("gold key").used) {
			state.allKeys = true;
		}
			
	}

	public static void main(String[] args) {

		// only instantiate once
		Scanner myObj = new Scanner(System.in);

		System.out.println("What is your name?");
		name = myObj.nextLine();
		// init game state
		GameState state = new GameState(name);
		
		printSlow("Welcome, " +name+ ".");
		System.out.println();
		printSlow("You recently inherited an estate from your estranged Uncle.");
		printSlow("He was rumored to be a strange man, convinced that magic and wizards were real.");
		printSlow("When you arrive at the estate, you get a strange feeling. As you approach the door, the sun all of a sudden disappears and the sky gets dark. A cold wind picks up.");
		printSlow("You step through the door with only a suitcase of clothes and an old book gifted to you by your uncle. The place seems like it hasn't been cleaned in years, but you don't have anything with you to start cleaning.");
		printSlow("Maybe you should look to see if he left any cleaning supplies behind.");

	
		while (!state.finished) {
			System.out.println("");
			System.out.println("What do you want to do next?");
			System.out.println("[1]: Look around the room.");
			System.out.println("[2]: Move to a new room.");
			System.out.println("[3]: Pick up an object from the room.");
			System.out.println("[4]: Examine my inventory.");
			System.out.println("[5]: Use an object from my inventory.");
			System.out.println("[6]: Use a weapon from my inventory.");

			choice = myObj.nextInt();
			myObj.nextLine(); // consume newline from above

			switch (choice) {
			case 1:
				printSlow("You can see the following items:");
				for (Item c : state.room.contents) printSlow(c.name);
				printSlow("You also notice that this room has doors:");
				for (String c : state.room.doors.keySet()) printSlow(c);
				
				if(state.room.enemies.size() > 0) {
					printSlow("Out of the corner of your eye you see something moving.");
					for (Enemy c : state.room.enemies) printSlow("Oh, no! You are faced with a " + c.name + ". " + c.description);
				}
				
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
						if(item.action.equals("place") && item.name.equals("poster")) {
							printSlow(item.use);
							state.inventory.remove(item);
							state.room.contents.add(item);
							state.rooms.put(state.room.name, state.room);
							state.posterHint = true;
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
				printSlow("Which weapon?");
				itemp = myObj.nextLine();
				try {
					Enemy enemy = state.room.enemies.get(0);
					Weapon weapon = state.weapons.get(itemp);
					Item item = state.items.get(itemp);
					if (state.inventory.contains(item)) {
						Weapon w = (Weapon) weapon;
						printSlow(w.use);
						if(item.name.equals("poison frog")) {
							printSlow("The frog sticks out its tongue.");
						}
						if (item.action.equals("drop")) {
							state.inventory.remove(item);
							state.room.contents.add(item);
							state.rooms.put(state.room.name, state.room);
						}
						if(enemy != null) {
							Integer attack = weapon.attack();
							enemy.health = enemy.health - attack;
							printSlow("You strike the " + enemy.name + " with the power of your " + weapon.name + ".");
							for (int i=0; i<attack; i++) {
								System.out.print("\007");
							}
							if (enemy.health > 0){
								state.health = state.health - enemy.health;
								printSlow("The " + enemy.name + " is wounded but survives. You'll have to pick up your weapon and try again.");
								printSlow("Your health: " + state.health + ", " + enemy.name + " health: " + enemy.health);
							} else {
								enemy.defeated = true;
								printSlow("The " + enemy.name + " is done for. Bye bye!");
								printSlow("Your health: " + state.health);
								state.room.enemies.remove(enemy);
							}
						} 
					}
					else {
						printSlow("Unknown weapon.");
					}
				} catch (Exception e) {
					printSlow("Unknown weapon.");
				}
				break;
			default:
				printSlow("Unidentified input, try again?");
			}

			String update = state.update();
			printSlow(update);
		}
		if(state.win) {
			printSlow("You win!");
		} 

	}
}
