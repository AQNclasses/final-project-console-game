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


I added 3 new rooms, the storage room, the display case, and the mysterious room. Which with the combination of those and the ones already added, I added the new ending as well.
The storage room is the first room you go into and then I like to treat it as a 'hub' because the rest of the rooms are connected to this one.

2. Add an ending related to at least one of your new rooms. Endings are
implemented in `GameState.java`.


I added an ending where you unlock the mysterious room and find the mysterious sword in there which queues the ending. It connects a lot of things together because you need a key in order to get in that room to unlock that new ending.

3. Implement a locked door that can only be opened if a Key is in your inventory.


I was very happy that I was able to figure this one out because for some reason it took me a lot of time to figure out. But as mentioned above, to get to the new ending I added you NEED the key to unlock the new door, and if you try to enter without the key, a print statement appears and says it's locked and that you need the key. Once you find the key (located in the closet), then if you try the door again, it will unlock.

4. Finish implementing subclasses for Items (Animal, Weapon, etc) instead of reading all
items in as Items.
   - Add and implement actions for all the items. All actions should modify GameState.


I believe that I finished subclasses for animal and weapon within their code and added an action for each item, a basic one but it should check off this requirement. 

5. Add a new subclass of items and at least three corresponding entries in `items.yaml`.


My new item subclass was for Plants. I created three new items for this, a mushroom, cactus, and sunflower. They've all been put into the rooms somewhere and you're able to interact with it a little bit, I would've added more to do with it if I had extra time but was just trying to make sure I reached the requirements before the due date instead of doing extra and missing the deadline.

6. Implement two new tests in `Game/src/test/java/GameTest.java`.


Added two more new tests in that file. One test is to test the usage of items so what I did was tested the excalibur by seeing if something would pop up wrongfully in the code by using that item purposefully within the test. The second test is to test if the player picks up an item, to make sure the item actually gets picked up and is in their inventory.


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
