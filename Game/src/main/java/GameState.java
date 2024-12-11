import java.util.*;

// Tracks global game state
// Useful for implementing state-based behavior (ex: see something new on second visit to room)

public class GameState {
    HashMap<Room, Boolean> visited = new HashMap<Room, Boolean>();
    String name;
    boolean finished;
    Room room;
    List<Item> inventory = new ArrayList<Item>();
    Map<String, Room> rooms; // global list of rooms
    Map<String, Item> items; // global list of known items
    int playerHP = 10;
    Random r = new Random();
    Scanner myObj = new Scanner(System.in);

    // update state and check for winning condition
    public String update() {
        if (playerHP <= 0) {
            finished = true;
            return "You died. Game over.";
        }

        if (items.get("bottomless pit").used == true) { //winning condition.
            finished = true;
            String finaltext =  """
                                The ground trembles, and the pit seems to swallow up the very air around it. Suddenly, the room shifts,
                                and you find yourself standing at the edge of an endless void. A sense of victory floods over you,
                                and you realize that you’ve overcome the final challenge. The darkness below no longer seems so threatening,
                                but more like a gateway to something new. You’ve won.
                                """;
            return finaltext;
        }

        //Fighting logic
        if (items.get("excalibur").used == true) {
            System.out.println("| attack | throw weapon |");
            String userInput = myObj.nextLine(); 
            if (items.get("bloodthirsty wasp").types.contains(ItemType.Animal)) {
                Animal targetAnimal = (Animal) items.get("bloodthirsty wasp"); 
                try {
                    int damage = 0;
                    switch (userInput){
                        case "throw weapon":
                            damage = ((Weapon) items.get("excalibur")).throwWeapon(room, inventory);
                        case "attack":
                            damage = ((Weapon) items.get("excalibur")).attack();

                    }                    
                    targetAnimal.animalHP -= damage; 
                    playerHP -= targetAnimal.attack();                    
                    if (targetAnimal.returnHealth() <= 0){
                        room.contents.remove(targetAnimal);
                    }
                    System.out.println("Health = " + playerHP + ", Animal Health = " + targetAnimal.returnHealth());
                } catch (Exception e) {        
                    System.out.println("That is not a valid action.");
                }
            } 
        }
        if ((items.get("bloodthirsty wasp").used == true) && !(inventory.contains(items.get("bloodthirsty wasp")))){
            if (items.get("bloodthirsty wasp").types.contains(ItemType.Animal)) {
                ((Animal)items.get("bloodthirsty wasp")).sing();
                Animal bloodthirstyWasp = (Animal) items.get("bloodthirsty wasp");
                bloodthirstyWasp.used = false;
            }
        }
        //Update for eating plant:
        if (items.get("bioluminescent ivy").used == true) {
            if (items.get("bioluminescent ivy").types.contains(ItemType.Plant)) {
                playerHP = playerHP - ((Plant) items.get("bioluminescent ivy")).eat(playerHP);
                inventory.remove(items.get("bioluminescent ivy"));
                Plant bioluminescentIvy = (Plant) items.get("bioluminescent ivy");
                bioluminescentIvy.used = false;
            }
        }
        //Drink elixir
        if (items.get("elixir").used == true) {
            if (items.get("elixir").types.contains(ItemType.Healing)) {
                playerHP = playerHP - ((Healing) items.get("elixir")).drink(playerHP);
                inventory.remove(items.get("elixir"));
                Healing elixir = (Healing) items.get("elixir");
                elixir.used = false;
            }
        } 
        //Drink frog
        if (items.get("purple frog").used == true && (inventory.contains(items.get("purple frog")))) {
            if (items.get("purple frog").types.contains(ItemType.Healing)) {
                playerHP = playerHP - ((Healing) items.get("purple frog")).drink(playerHP);
                inventory.remove(items.get("purple frog"));
                Healing purpleFrog = (Healing) items.get("purple frog");
                purpleFrog.used = false;
            }
        }  
        //Read book
        if (items.get("book").used == true) { 
            if (items.get("book").types.contains(ItemType.Readable)) {
                ((Readable)items.get("book")).read();
            }
        }    
        //Read poster
        if (items.get("poster").used == true) {
            if (items.get("poster").types.contains(ItemType.Readable)) {
                ((Readable)items.get("poster")).read();
            }
        }    
        // Clean with bucket
        if (items.get("bucket").used == true) {
            if (items.get("bucket").types.contains(ItemType.Tool)) {
                ((Tool) items.get("bucket")).clean();
            }
        }    
        // Clean with mop
        if (items.get("mop").used == true) {
            if (items.get("mop").types.contains(ItemType.Tool)) {
                ((Tool) items.get("mop")).clean();
            }
        }            
        // Clean with broom
        if (items.get("broom").used == true) {
            if (items.get("broom").types.contains(ItemType.Tool)) {
                ((Tool) items.get("broom")).clean();
            }
        }
        //Push button 
        if (items.get("red button").used == true) {
            if (items.get("red button").types.contains(ItemType.Trap)) {
                Trap redButton = (Trap) items.get("red button");
                String a = ((Trap) items.get("red button")).movement(r.nextInt(3), playerHP);
                room = rooms.get(a);
                redButton.used = false; 
            }
        }
        //Pull lever 
        if (items.get("lever").used == true) {
            if (items.get("lever").types.contains(ItemType.Trap)) {
                room = rooms.get(((Trap) items.get("lever")).movement(r.nextInt(3), playerHP));
                Trap lever = (Trap) items.get("lever");
                lever.used = false;
            }
        }
        //Step in to magic circle 
        if (items.get("magic circle").used == true) {
            if (items.get("magic circle").types.contains(ItemType.Trap)) {
                room = rooms.get(((Trap) items.get("magic circle")).movement(r.nextInt(3), playerHP));
                Trap magicCircle = (Trap) items.get("magic circle");
                magicCircle.used = false;
            }

        }
        //Unlock locked door 
        if ((items.get("key").used == true) && (room.contents.contains(items.get("locked door")))) {   
            if (items.get("key").types.contains(ItemType.Key)) {
                Key key = (Key) items.get("key");
                key.unlock(room, items, rooms, inventory);
            }
        }
        return ""; 
    }

    public GameState(String name) { //starting state of game.
        this.name = name;
        finished = false;
        LoadYAML yl = new LoadYAML();
        rooms = yl.rooms;
        items = yl.items;
        room = rooms.get("Starting Room");
        visited.put(room, true);
    }
}
