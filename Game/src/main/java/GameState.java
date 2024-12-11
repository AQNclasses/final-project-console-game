import java.util.*;

// Tracks global game state
// Useful for implementing state-based behavior (ex: see something new on second visit to room)

public class GameState {
    HashMap<Room, Boolean> visited = new HashMap<Room, Boolean>();
    String name;
    String BestFriend;
    boolean isDanger;
    boolean finished;
    int hp;
    Room room;
    List<Item> inventory = new ArrayList<Item>();
    List<Enemy> battleField = new ArrayList<>();
    Map<String, Room> rooms; // global list of rooms
    Map<String, Item> items; // global list of known items

    Random dice = new Random();

    // update state and check for winning condition
    public String update() {
        if(room.name.equals("Master Bedroom") && inventory.contains(items.get(BestFriend +"'s Phone"))){
            String phase2Start =  "You hear " + BestFriend + "'s voice coming from her phone\n";
                   phase2Start +=  name.charAt(0) + "- " + name.substring(0,1) + "- ...\n";
                   phase2Start +=  "t-rn -ack...\nBefore you could react the pentagram begins to glow a dark red and you are hit with flash of light\n";
                    phase2Start += "When you come to you are somewhere else...\nYou are now in the Servant's Quarters";
            isDanger = true;
            room = rooms.get("Servant's Quarter");
            return phase2Start;
        }
        if(inventory.contains(items.get("BestFriend"))){
            String win = "You rescued " + BestFriend + "!\n You Win";
            return win;
        }
        return "";
    }

    public GameState(String name) {
        this.name = name;
        BestFriend = "Stella";
        if(name.equals(BestFriend)){
            BestFriend = "Tabby";
        }
	hp = 50;
        finished = false;
        isDanger = false;
        LoadYAML yl = new LoadYAML(BestFriend);
        rooms = yl.rooms;
        items = yl.items;
        room = rooms.get("Master Bedroom");
        visited.put(room, true);
        inventory.add(items.get("Phone"));
        inventory.add(items.get(BestFriend+"'s Phone"));
        inventory.add(items.get("Revolver"));
        // inventory.add(items.get("BestFriend"));
    }

    public void roomCheck(){
        Item sir = items.get("Sir Arthur");
        Item boss = items.get("");
        if(room.contents.contains(sir)){
            battleField.add((Enemy)sir);
        }
        if(room.contents.contains(boss)){
            battleField.add((Enemy)boss);
        }
    }
}
