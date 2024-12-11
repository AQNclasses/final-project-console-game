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
   Successfully completed this requirement, adding two rooms called "Mysterious Cave", and "Chamber of Curses. "Mysterious Cave" is connected to "Starting Room" with a door, and "Chamber of Curses" with a locked door.

2. Add an ending related to at least one of your new rooms. Endings are
implemented in `GameState.java`.
   Successfully completed this requirement, making the winning condition and one game ending related to the successful use of Item "bottomless pit", which is located in one of the new rooms - "Chamber of Curses". Classes changed to complete this req. include GameState.java as well as Trap.java.

3. Implement a locked door that can only be opened if a Key is in your inventory.
   Successfully completed this requirement, creating a locked door between "Mysterious Cave" and "Chamber of Curses". Classes changed to complete this req. include Trap.java and GameState.java.

4. Finish implementing subclasses for Items (Animal, Weapon, etc) instead of reading all
items in as Items.
   - Add and implement actions for all the items. All actions should modify GameState.
   Successfully completed this requirement, changing the default [Item] Items to two different subclasses [Readable] and [Tool]. All subclasses have at least one new action that affects GameState.java.

5. Add a new subclass of items and at least three corresponding entries in `items.yaml`.
   Successfully completed this requirement, added a new subclass called [Trap], with five corresponding entries to the subclass.

6. Implement two new tests in `Game/src/test/java/GameTest.java`.
   Successfully completed this requirement, adding a test that tests that items are loaded correctly, and another that tests that rooms are loaded correctly. 

## Extra Credit Opportunities

Up to 2.5% extra credit (toward your overall class grade) is available if you complete one of the following options:

- Make a significant change to the storyline or behaviors of the game (example:
add enemies and battling, including the option to "die" if an enemy does too
much damage to you). Run your idea past Prof. Nilles to guarantee it will earn
extra credit.

Summary: 
Created a battling feature that allows you to both do damage and take damage from Items in Type [Animal]. This fighting logic begins with introducing the idea of playerHP and animalHP, player health and animal health, that is tracked and incremented down with damage dealt. Increases to player health is also possible through the use of the Item [Healing] item "elixir", and any other items of Type[Healing], that has the chance to either instant kill or set playerHP to full. This logic is implemented in the [Animal] and [Weapon] subclasses and worked out more in GameState.java.
Subsequently, ending conditions in GameState are set to end the game if playerHP equals or is less than zero. Any animals that are killed (animal HP <= 0), are removed from the room that they are within. Some other instances of player death are as detailed above, with the chance of instant death from drinking the "elixir", as well as the chance of instant death in actions such as eating the "bioluminescent ivy", drinking the "poison frog", and when randomly moving to a new room when movement() is called on certain items in Item Type [Trap].

Extra: 

Bug within Game.java:
Original case 3 - add to inventory logic 
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
At no point does the program ascertain whether or not the desired item is included within the current room contents. This allows the player, if given knowledge of a master list of items, or after running through the game loop to have an understanding of the contents of other rooms, to pick up an item no matter the room they are currently in, or what room the item belongs to. 
I changed the logic to require the item to be within the current room in order to be picked up.

Added a new case to Game.java:
   case 6: 
      printSlow("What item would you like to interact with?"); 
      itemp = myObj.nextLine();
      try {
         Item item = state.items.get(itemp);
         if (item != null && (item.types.contains(ItemType.Trap) || item.types.contains(ItemType.Animal))) {
            item.used = true;
         }
      } catch (Exception e) {
         printSlow("Unknown item.");
      }
      break;
   default:
      printSlow("Unidentified input, try again?");
Allows the player to interact and use items from their position in the room without needing to pick up and use the item from their inventory. This is used to implement and add to the game flow for Items of Type Trap, as they are set within the story as fixtures to the room, and should not be picked up. This can also be used on Item Type Animal, to trigger the method sing(), which aligns with the game design.

