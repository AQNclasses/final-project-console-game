# Final Project: Console Game

## Due Tuesday December 10

## Getting Started

How to build and run a gradle project on the command line for Linux and Mac:

```sh
./gradlew build
./gradlew -q run
```

and on Windows:

```sh
./gradlew.bat build
./gradlew.bat -q run
```

You may also use the
![VS Code extension, Grade for
Java](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-gradle),
however the game will likely be easier to interact with in the terminal.

## Directory Structure

Game code is located in `Game/src/main/java/`.

Tests are located in `Game/src/test/java/`.

YAML configuration files are located in `Game/config/`.

You shouldn't have to touch any of the Gradle files.

## Requirements and Deliverables

1. Add at least two new rooms in `rooms.yaml`, connected to the existing rooms.
	Complete: added many rooms to `rooms.yaml`
2. Add an ending related to at least one of your new rooms. Endings are
implemented in `GameState.java`.
	Complete: added an ending involved to `GameState.java` 
3. Implement a locked door that can only be opened if a Key is in your inventory.
	Complete: locked doors point to a "_Locked" Room, a Key Item redirect the door to the correct room when used 
4. Finish implementing subclasses for Items (Animal, Weapon, etc) instead of reading all
items in as Items.
   - Add and implement actions for all the items. All actions should modify GameState.
   Complete: All Items uses a subClass of items.
   - Changed Items to abstract class to make it easier to add new subclasses, and stops need for downcasting
5. Add a new subclass of items and at least three corresponding entries in `items.yaml`.
	- added Enemy, Container, and Note subclass
6. Implement two new tests in `Game/src/test/java/GameTest.java`.
	-Completed: 
		-tests to see if the a variable will change depending on the name inputted
		-tests to see if the Enemy constructor is working correctly

## Extra Credit Opportunities

Up to 2.5% extra credit (toward your overall class grade) is available if you complete one of the following options:

- Create a "game player" object that can play your game (hint: you will have to
modify the game interaction loop in `Game.java` since a virtual player cannot
give input on the command line). Implement a search algorithm for your player to
find and print all endings to the game.
- Make a significant change to the storyline or behaviors of the game (example:
add enemies and battling, including the option to "die" if an enemy does too
much damage to you). Run your idea past Prof. Nilles to guarantee it will earn
extra credit.


Extra credit:
	-Added combat to the game.
		-After Phase 2 begins Ghost will start appearing whenever you move to another room.
		-Selecting a weapon will cause you to attack a random Enemy for a random number of times
		-Defeating an enemy will add an item to your inventory
		-Using a healing item will allow you to heal during the battle
		-if your hp is <= to 0, the game ends
	-