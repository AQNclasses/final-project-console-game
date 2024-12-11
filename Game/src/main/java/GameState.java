import java.util.*;

// Tracks global game state
// Useful for implementing state-based behavior (ex: see something new on second visit to room)

public class GameState {
    HashMap<Room, Boolean> visited = new HashMap<Room, Boolean>();
    HashMap<Room, Boolean> mopped = new HashMap<Room, Boolean>();
    HashMap<Room, Boolean> swept = new HashMap<Room, Boolean>();
    HashMap<Room, Boolean> cleanRoom = new HashMap<Room, Boolean>();

    boolean cleaned;
    String name;
    boolean finished;
    Room room;
    List<Item> inventory = new ArrayList<Item>();
    Map<String, Room> rooms; // global list of rooms
    Map<String, Enemy> enemies;
    Map<String, ? extends Item> items; // global list of known items
    Map<String, Weapon> weapons;
    int protection;
    boolean posterHint;
    boolean allKeys;
    int health;
    boolean win;
    

    // update state and check for winning condition
    public String update() {
//    	if(!cleaned && mopped.get(room) != null && mopped.get(room) && swept.get(room) != null && swept.get(room)) {
//    		String clean = "You've cleaned the room! Looks a lot better in here.";
//    		if(room.name.equals("Starting Room")) {
//    			room.contents.add(items.get("silver key"));
//    			clean += " Check to see if there are any new items.";
//    		}
//    		cleaned = true;
//    		return clean;
//    	}
        if(!cleaned && room.name.equals("Starting Room") && mopped.get(room) && swept.get(room)) {
        	room.contents.add(items.get("silver key"));
        	String cleanRoom= "Check to see if there are any new items.";
        	cleaned = true;
        	return cleanRoom;
        }
        if(items.get("bucket").used) {
        	protection = 1;
        }
        if(items.get("poster").used && posterHint) {
        	String hint = "After you put up the poster, you realize there's also the image of what looks like the posion frog you've encountered sitting on a book...";
        	posterHint = false;
        	return hint;
        }
        if(allKeys) {
        	String note = "You unlocked all the doors.";
        	allKeys = false;
        	return note;
        }
        if(health < 1) {
        	finished = true;
        	win = false;
        	String finalText = "Your enemies have defeated you. You'll have to try again to figure out the secrets of the estate.";
        	return finalText;
        }
        if (room.contents.contains(items.get("poison frog")) &&
            room.contents.contains(items.get("book")) ){
            finished = true;
            String finaltext =  """
                                The frog hops slowly over to the book and hops on top. Suddenly the book and the
                                frog begin to glow. The glow becomes more intense until there's a flash. Suddenly,
                                the mansion seems to be spotless, looking brand new. As you look through the rooms,
                                you notice that the troll and goblin are no where to be found. You've lifted the curse,
                                and you can live in peace on a now beautiful estate.
                                """;
            return finaltext;
        }
        boolean allEnemiesDefeated = true;
        for (String key : enemies.keySet()) {
            if (!enemies.get(key).defeated) {
                allEnemiesDefeated = false;
                break;
            }
        }
        if (allEnemiesDefeated) {
            finished = true;
            win = true;
            String finaltext =  "You have vanquished all the nasty creatures from this house. You can live here in peace.";
            return finaltext;
        }
        boolean allRoomsCleaned = true;
        for (String key : rooms.keySet()) {
            if (mopped.get(rooms.get(key)) == null || !mopped.get(rooms.get(key)) || swept.get(rooms.get(key)) == null || !swept.get(rooms.get(key))) {
                allRoomsCleaned = false;
                break;
            }
        }
        if (allRoomsCleaned) {
            finished = true;
            win = true;
            String finaltext =  "You have vanquished all the nasty dirt from all of the rooms. You can live here in peace.";
            return finaltext;
        }
        return "";
    }

    public GameState(String name) {
        this.name = name;
        finished = false;
        LoadYAML yl = new LoadYAML();
        rooms = yl.rooms;
        items = yl.items;
        enemies = yl.enemies;
        weapons = yl.weapons;
        room = rooms.get("Starting Room");
        visited.put(room, true);
        mopped.put(room, false);
        swept.put(room, false);
        cleanRoom.put(room, false);
        inventory.add(items.get("book"));
        cleaned = false;
        protection = 0;
        posterHint = false;
        allKeys = false;
        health = 5;
        win = false;
    }
}
