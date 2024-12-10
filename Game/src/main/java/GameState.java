import java.util.*;

public class GameState {
    HashMap<Room, Boolean> visited = new HashMap<>();
    String name;
    boolean finished;
    Room room;
    List<Item> inventory = new ArrayList<>();
    Map<String, Room> rooms; 
    Map<String, Item> items;
    public int health;
    public String endMessage = "";
    Map<String, Boolean> itemStates = new HashMap<>();

    private static final String BRAZIER_LIT = "brazier_lit";
    private static final String KEY_GIVEN = "key_given";
    private static final String DOOR_UNLOCKED = "door_unlocked";
    private static final String SCROLL_READ = "scroll_read";
    private static final String SYMBOLS_REVEALED = "symbols_revealed";
    private static final String TORCH_EXTINGUISHED = "torch_extinguished";
    private static final String VEILED_PASSAGE_REVEALED = "veiled_passage_revealed";
    private static final String BRAZIER_FED = "brazier_fed";
    

    public GameState(String name) {
        this.name = name;
        this.health = 100;
        finished = false;
        LoadYAML yl = new LoadYAML();
        rooms = yl.rooms;
        items = yl.items;
        room = rooms.get("Unending Desert");
        
        
        

        // Initialize states
        itemStates.put(BRAZIER_LIT, false);
        itemStates.put(KEY_GIVEN, false);
        itemStates.put(DOOR_UNLOCKED, false);
        itemStates.put(SCROLL_READ, false);
        itemStates.put(SYMBOLS_REVEALED, false);
        itemStates.put(TORCH_EXTINGUISHED, false);
        itemStates.put(VEILED_PASSAGE_REVEALED, false);
        itemStates.put(BRAZIER_FED, false);

            Item rustySword = items.get("rusty sword"); // start game with rusty sword
            Item hatchet = items.get("hatchet"); // and hatchet
            inventory.add(hatchet);
            inventory.add(rustySword);
            

        
    }

    public String update() {
        if (health <= 0) {
            finished = true; // if you die game ends
            return "You have succumbed to your injuries and died.";
        } 

        if (room.name.equals("Veiled Passage")) {
            finished = true;
            return "You find yourself before an ancient shrine, its silhouette a mirror of the stone statue you encountered before. Warmth fills the air, and from the shadows, a distant figure approaches. It speaks, 'I've been summoned to escort you the rest of the way'.";
        }

        return "";
    }

    public String useItem(String itemName) {
        Item item = items.get(itemName);
        if (item == null) 
        return "Unknown item.";
        if (!inventory.contains(item)) 
        return "That item is not in your inventory.";
        
        

           // check if player used rusty sword or venomous sword
           if (item.name.equals("rusty sword") || item.name.equals("venomous sword")) {
            
            for (Item roomItem : room.contents) { // iterate over each roomItem in room.contents
                if (roomItem.types.contains(ItemType.Enemy)) { // check if roomItem contains enemy
                    Enemy currentEnemy = (Enemy) roomItem; // cast the roomItem to Enemy
                    int damage = ((Weapons) item).attack(); // treat item as weapon object and call attack
                    printSlow("You attack the " + currentEnemy.name + " with your " + item.name + "."); // print attack info
                    boolean defeated = currentEnemy.takeDamage(damage, this); // apply damage to enemy 
        
                    if (defeated) { // if defeated print out defeat message 
                        printSlow("With a final blow, you have defeated the " + currentEnemy.name + "!");
                        room.contents.remove(currentEnemy);
                    } else { // if not have enemy hit the player back
                        enemyCounterAttack(currentEnemy);
                    }
                    return "";
                }
            }
        
            // this handles fights with animals
            for (Item roomItem : room.contents) {  // iterate over each roomItem in room.contents
                if (roomItem.types.contains(ItemType.Animal)) {
                    
                    Animal animal = (Animal) roomItem; // cast roomItem to Animal object
                    int damage = ((Weapons) item).attack(); // treat item as weapon object, call attack
                    
                    printSlow("You strike the " + animal.name + " with your " + item.name + ", dealing " + damage + " damage!");
                    boolean defeated = animal.takeDamage(damage, this); // apply damage to
                    if (defeated) {
                        room.contents.remove(animal);
                        // Check if this is a scorpion that might drop venom
                        if (animal.name.equals("scorpion")) {
                            Item venom = items.get("venom");
                            if (venom != null) {
                                room.contents.add(venom);
                                printSlow("Your blow is lethal! The scorpion collapses and leaves behind a vial of [venom].");
                            } else {
                                printSlow("Your blow is lethal! The scorpion collapses, defeated.");
                            }
                        } else {
                            // Other animals if any
                            printSlow("Your blow is lethal! The " + animal.name + " collapses, defeated.");
                        }
                    } 
                    return "";
                }
            }
        
            // If no enemies or animals found, just print a default message or do nothing
            return "You swing your " + item.name + " through the air, but there's nothing to fight here.";
        }


        if (item.name.equals("axe") || item.name.equals("hatchet")) {
            for (Item roomItem : room.contents) {
                
                if (roomItem.types.contains(ItemType.Plant)) {
                    
                    // Restrict hatchet from chopping the Coconut Tree
                    if (item.name.equals("hatchet") && roomItem.name.equals("coconut tree")) {
                        continue; // Skip Coconut Tree if the player is using a hatchet
                    }
                    int damage = ((Utility) item).attack(); // get damage from utility attack
                    Plant plant = (Plant) roomItem; // cast roomItem as plant
                    boolean destroyed = plant.takeDamage(damage, this); // apply damage to plant
        
                    if (destroyed) {
                        // Handle specific item drops for each type of plant
                        if (plant.name.equals("coconut tree")) {
                            Item logs = items.get("logs"); 
                            Item coconut = items.get("coconut");
                            Item stump = items.get("stump");
                            if (logs != null) room.contents.add(logs);
                            if (coconut != null) room.contents.add(coconut);
                            if (stump != null) room.contents.add(stump);
                            return "It crashes to the ground! Only some [logs], a fresh [coconut], and a [stump] remain.";
                        } 
                        else if (plant.name.equals("glowvine")) {
                            Item glowfruit = items.get("glowfruit");
                            if (glowfruit != null) {
                                room.contents.add(glowfruit); 
                            }
                            return "A [glowfruit] falls to the ground.";
                        } 
                        else if (plant.name.equals("cactus")) {
                            Item cactusFruit = items.get("cactus fruit");
                            if (cactusFruit != null) {
                                room.contents.add(cactusFruit); // Drop cactus fruit into the room
                        
                            }
                            return "A [cactus fruit] falls to the ground";
                        }
                    }
        
                    // If the plant isn't destroyed, describe its current state
                    if (plant.health > 6) {
                        return "The " + plant.name + " quivers, but it still stands strong.";
                    } else if (plant.health > 3) {
                        return "The " + plant.name + " sways, its structure visibly weakened.";
                    } else if (plant.health > 0) {
                        return "Cracks form along the " + plant.name + ". It's on the brink of collapse.";
                    }
                }
            }
            return "There's nothing here to chop with the " + item.name + ".";
        } 
        

        if (item.name.equals("venom")) { // venom and rusty sword interaction
            Item rusty = items.get("rusty sword"); 
            if (inventory.contains(rusty)) {
                inventory.remove(rusty);
                inventory.remove(item);
                inventory.add(items.get("venomous sword")); 
                return "You coat the Rusty Sword with venom, transforming it into a deadly Venomous Sword!";
            }
        }
        
    
        
        if (item.name.equals("lighter")) { // lighter and unlit torch interaction
            Item unlitTorch = items.get("unlit torch");
            if (inventory.contains(unlitTorch)) {
                inventory.remove(unlitTorch);
                inventory.remove(item);
                inventory.add(items.get("lit torch"));
                return "You use the lighter to ignite the unlit torch, creating a lit torch.";
            }
        }

        if (item.name.equals("lit torch") && room.contents.contains(items.get("unlit brazier"))) { // lit torch and firee interaction
            if (!itemStates.getOrDefault("brazier_lit", false)) {
                itemStates.put("brazier_lit", true);
                room.contents.remove(items.get("unlit brazier"));
                room.contents.add(items.get("lit brazier"));
                
                
                for (Item roomItem : room.contents) { // check each item in room
                    if (roomItem.name.equals("strange statue")) { // for statue to change it's message after brazier lit
                        roomItem.desc = "A stone statue now engulfed in flames, fire roaring from its mouth and eyes.";
                        break;
                    }
                }
                return "You use the lit torch to ignite the brazier. It flames to life!\nNearby, the strange statue comes to life, fire roaring from its mouth and eyes as it speaks: 'Come forth, bearer of the flame...'";
            } 
        }

        
        if (item.name.equals("lit torch") && room.name.equals("Oasis") && room.contents.contains(items.get("spring"))) {
            if (itemStates.getOrDefault(BRAZIER_FED, false)) {
                itemStates.put(TORCH_EXTINGUISHED, true);
                inventory.remove(item);
                if (!itemStates.getOrDefault(VEILED_PASSAGE_REVEALED, false)) {
                    itemStates.put(VEILED_PASSAGE_REVEALED, true);
                    Room oasisRoom = rooms.get("Oasis");
                    if (oasisRoom != null) {
                        oasisRoom.doors.put("green", "Veiled Passage");
                    }
                    return "You douse the torch in the spring. The flame is extinguished with a soft hiss.\nA veil lifts from the passage, revealing a hidden green door to the north!";
                }
                return "You douse the torch in the spring. The flame is extinguished with a soft hiss.";
            }
        }
        
        if (item.name.equals("key") && room.name.equals("Red Rock Cavern")) { // use key on black door
            itemStates.put(DOOR_UNLOCKED, true);
            inventory.remove(item);
            return "The black door unlocks with a soft click.";
        }

        
        if (item.name.equals("logs") && room.contents.contains(items.get("lit brazier"))) { // logs and brazier interaction
            itemStates.put(BRAZIER_FED, true);  // mark game progression
            room.contents.remove(item); 
            inventory.remove(item); 
            for (Item roomItem : room.contents) { // check each item in room
                if (roomItem.name.equals("strange statue")) { // change statue text
                    roomItem.use = "The statue speaks once more: 'You have fed the sacred flame, now relinquish your own. Quench it in the spring.'";
                    break;
                }
            }
            return "You place the logs into the brazier. The flames grow stronger.\nThe statue speaks once more: 'You have fed the sacred flame, now relinquish your own. Quench it in the spring.'";
        }

       
        if (item.name.equals("ancient scroll")) { // use ancient scroll
            itemStates.put(SCROLL_READ, true); // mark game progression
            inventory.remove(item);
            return item.use;
        }

        
        if (item.name.equals("sand") && room.contents.contains(items.get("unintelligible symbols"))) { // for sand and symbol interaction
            if (!itemStates.getOrDefault(SYMBOLS_REVEALED, false)) {
                itemStates.put(SYMBOLS_REVEALED, true); // mark game progression
                inventory.remove(item); 
                return "The symbols shimmer and reveal a clue: 'Feed the brazier with the fallen tree's logs'";
            } else {
                return "The symbols have already been revealed.";
            }
        }

        
        if (item.types.contains(ItemType.Healing)) { // check if item is healing item
            Healing healingItem = (Healing) item;
        
            if (health >= 100) { // don't consume item if at full health
                return "Your health is already at maximum. You store the " + item.name + " for later.";
            } else {
                int healthBefore = health;
                healingItem.use(this);
                inventory.remove(item);
        
                int healAmount = healingItem.healAmount;
                int healthGained = Math.min(healAmount, 100 - healthBefore);
                health = Math.min(health + healthGained, 100);
        
                return "You recover " + healthGained + " health points and feel rejuvenated! Your health is now " + health + ".";
            }
        }

        item.use(this);
        return "";
    
}
    


// handles the spawning of enemies in rooms, vultures and fire spirits spawn in at a %25 rate, the shadowy
// figure spawns in once, but at a 100% rate when the user enters the hidden chamber
public void spawnEnemyInRoom() {
    double spawnChance = 0.25;


    if (room.name.equals("Unending Desert")) {
        Item vulture = items.get("hungry vulture");
        if (vulture != null && !room.contents.contains(vulture) && Math.random() < spawnChance) {
            room.contents.add(vulture);
            printSlow("A vulture swoops down from the sky!");
            printSlow("You are now in combat! You must defeat the enemy before you can leave the room or pick up items.");
        }
    } else if (room.name.equals("Oasis")) {
        Item flameSpirit = items.get("flame spirit");
        if (flameSpirit != null && !room.contents.contains(flameSpirit) && Math.random() < spawnChance) {
            room.contents.add(flameSpirit);
            printSlow("A flame spirit flickers into existence!");
            printSlow("You are now in combat! You must defeat the enemy before you can leave the room or pick up items.");
        }
    } else if (room.name.equals("Hidden Chamber")) {
        if (!itemStates.getOrDefault("shadow_spawned", false)) {
            Item shadow = items.get("shadowy figure");
            if (shadow != null && !room.contents.contains(shadow)) {
                room.contents.add(shadow);
                itemStates.put("shadow_spawned", true);
                printSlow("The shadowy figure phases through the red rock in front of you! Cloaked in shadows, its angry eyes glowing faintly red.");
                printSlow("You are now in combat! You must defeat the enemy before you can leave the room or pick up items.");
            }
        }
    }
}

    public void enemyCounterAttack(Enemy enemy) {
        enemy.counterAttack(this); // Call the Enemy's counterAttack method
    }

    public boolean isEnemyPresent() { // track if enemy is in the room, gameplay changes
        for (Item item : room.contents) {
            if (item.types.contains(ItemType.Enemy)) {
                return true;
            }
        }
        return false;
    }

    public void unlockDoor() { // locked door method
        itemStates.put(DOOR_UNLOCKED, true);
        
    }
    
    private static void printSlow(String toPrint) { // copied print slow method over
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
    
}

