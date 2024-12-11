import java.util.*;

public class Game {

	static String name;
	static int choice;
	static String itemp;
	static Random rn;
	static String bestf;

	// helper function for printing
	private static void printSlow(String toPrint) {
		toPrint = toPrint.replace("[BestFriend]", bestf);
		char[] chars = toPrint.toCharArray();
		for (int i=0; i < chars.length; i++) {
			System.out.print(chars[i]);
			try { Thread.sleep(14);} 
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
		rn = new Random();
		name = myObj.nextLine();
		// init game state
		GameState state = new GameState(name);

		bestf = state.BestFriend;
		// beginning flavor text
		/*
		printSlow("Welcome, "+name+".");
		System.out.println("");
		printSlow("Your best friend [BestFriend] is an urban explorer who goes spelunking abandonned buildings");
		System.out.println("");
		printSlow("2 weeks ago, [BestFriend] has gone missing while exploring an ancient clinic at the edge of town");
		System.out.println("");
		printSlow("The police have been useless, so it is up to you to find out what happened to [BestFriend], and bring her home");
		*/
		while (!state.finished) {
			System.out.println("");
			System.out.println("What do you want to do next?");
			System.out.println("[1]: Look around the room.");
			System.out.println("[2]: Move to a new room.");
			System.out.println("[3]: Interact.");
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
					String doorList = "Doors: " + state.room.doors.keySet();
					printSlow(doorList);
					printSlow("Which door?");
					String door = myObj.nextLine();
					Room curr = state.room;
					try {
						String rtemp = state.room.doors.get(door);
						if(rtemp.equals("_Locked")){
							printSlow("The door is locked");
						}else{
							state.room = state.rooms.get(rtemp);
							printSlow("You walk towards the " + state.room.name + ".");
							state.roomCheck();
							int numEnemies = state.isDanger ? rn.nextInt(3) + 1 : 0;
							if(numEnemies > 0){
								for(int i = 1; i < numEnemies; i++){
									Enemy foe = new Enemy( (Enemy) state.items.get("Ghost") );
									foe.name += " " + i;
									state.battleField.add(foe);
								}
							}
							if(!state.battleField.isEmpty()){
								boolean result = battle(state,myObj);

								if(state.hp <= 0){
									printSlow("You Died");
									state.finished = true;
									break;
								}
								if(result == false){
									state.room = curr;
								}
							}
						}
					} catch (Exception e) {
						state.room = curr;
						printSlow("Unknown door.");
					}
					break;


				case 3:
					printSlow("Items:");
					printSlow(state.room.contents.toString());
					printSlow("Which item?");
					itemp = myObj.nextLine();
					try {
						Item item = state.items.get(itemp);
						if(state.room.contents.contains(item)){
							printSlow(item.pickUp(state));
						}else{
							printSlow("Item not found");
						}
						
					} catch (Exception e) {
						printSlow("Unknown item.");
					}
					break;

					
				case 4:
					printSlow("Your inventory:");
					printSlow(state.inventory.toString());

					printSlow("Which item?");
					itemp = myObj.nextLine();
					try {
						Item item = state.items.get(itemp);
						if (state.inventory.contains(item)) {
							printSlow(item.inspect());
						}
						else {
							printSlow("Unknown");
						}
					} catch (Exception e) {
						System.out.println(e);
						printSlow("Unknown item.");
					}
					break;


				case 5:
					printSlow("Your inventory:");
					printSlow(state.inventory.toString());
					printSlow("Which item?");
					itemp = myObj.nextLine();
					try {
						Item item = state.items.get(itemp);
						if (state.inventory.contains(item)) {
							printSlow(item.use(state));

							if (item.action.equals("drop")) {
								state.inventory.remove(item);
								state.room.contents.add(item);
								state.rooms.put(state.room.name, state.room);
							}
						}
						else {
							printSlow("Unknown");
						}
					} catch (Exception e) {
						System.out.println(e);
						printSlow("Unknown item.");
					}
					break;
				default:
					printSlow("Unidentified input, try again?");
			}

			String update = state.update();
			printSlow(update);
		}
		printSlow("Game Over");
	}

	public static boolean battle(GameState state, Scanner myObj){
		Enemy target;
		boolean validChoice = false;
		boolean isBattle = true;
		printSlow("You have been attacked by " + state.battleField.size() + " Enemies");
		while(isBattle){
			System.out.println("");
			System.out.println("What do you want to do next?");
			System.out.println("[1]: Use Item");
			System.out.println("[2]: Run");

			choice = myObj.nextInt();
			myObj.nextLine();
			switch(choice){
				case 1:
					printSlow("Your inventory:");
					printSlow(state.inventory.toString());
					printSlow("Which item?");
					String itemp = myObj.nextLine();
					try {
						Item item = state.items.get(itemp);
						if (state.inventory.contains(item) && (item.action.equals("attack") || item.action.equals("healing")) ) {
							printSlow(item.use(state));
						}
						else {
							printSlow("You can't do that right now");
						}
					validChoice = true;
					} catch (Exception e) {
						System.out.println(e);
						printSlow("Unknown item.");
					}
					break;
				case 2:
					if(rn.nextBoolean()){
						printSlow("You got away safely!");
						return false;
					}else{
						printSlow("You couldn't get away!");
					}
					validChoice = true;
					break;
				default:
					printSlow("Unidentified input, try again?");
			}
		if(validChoice){
			for(Enemy en : state.battleField){
				printSlow(en.attack(state));
			}
			printSlow("You have " + state.hp + "hp remaining");
			validChoice = false;
			if(state.hp <= 0){
				return false;
			}
		}
		if(state.battleField.size() <= 0){
			isBattle = false;
		}
		}
		
		return true;
	}
}
