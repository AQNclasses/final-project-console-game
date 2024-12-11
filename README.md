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
   * <em>I added added 8 rooms to the rooms.yaml. I also added a floor property for every room, and this required Room.java and LoadYAML.java to be updated to accept the new property.</em>

2. Add an ending related to at least one of your new rooms. Endings are
implemented in `GameState.java`.
   * <em>I added 3 endings, none of them rely directly on a room, but they all require moving between rooms to get items. One of the added endings required the garage to be unlocked and the car to be interacted with. All the endings were added to GameState.java</em>

3. Implement a locked door that can only be opened if a Key is in your inventory.
   * <em>The garage is locked and must be unlocked by using a key in an adjacent room. To get this to work I had to modify LoadYAML.java, Game.java, Room.java, and add it Key.java. My initial implementation would have allowed for one way doors, but since it was an unintended complex feature it was removed.</em>

4. Finish implementing subclasses for Items (Animal, Weapon, etc) instead of reading all
items in as Items.
   * <em>Almost every item now has it's own subclass. This required a switch statement in LoadYAML.java and also adding all of the additional classes.</em>

   - Add and implement actions for all the items. All actions should modify GameState.
      * <em>Most items will interact with game state in some way. On the most basic level some items have the action to be dropped, moving the item from inventory to room. I did not integrate the drop action into the item class, it is still in Game.java.</em>

5. Add a new subclass of items and at least three corresponding entries in `items.yaml`.
   * <em>I added the Potion.java class and three potions. These are powerful potions placed around the world. One potion teleports back to the Starting Room, one potion clears the inventory of everything except itself, and the last potion clears all items. I had a bug with the green potion at one point that resulted in a null item in the inventory causing problems, but I haven't been able to recreate the issue.</em>

6. Implement two new tests in `Game/src/test/java/GameTest.java`.
   * <em>I added two new tests. One test will traverse all the rooms to make sure that all the rooms have at least one door to them. The other test will also traverse all rooms, but it checks to make sure that all the doors are connected in both direction, to prevent trapping the player.</em>

## Other notes

- Fixed a bug in Game.java that caused the game to crash when selecting an invalid item from the inventory.
- Fixed a bug in Game.java that caused the game to crash when entering string instead of a int on the option selection screen.
- Added a quit option to gracefully exit the game.
- Added an option to inspect an item in the inventory.
- Added the name of the room to the read out when "Looking around the room."

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
