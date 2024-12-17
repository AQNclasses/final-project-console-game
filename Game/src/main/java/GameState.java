import java.util.*;

import javax.naming.ldap.SortControl;

// Tracks global game state
// Useful for implementing state-based behavior (ex: see something new on second visit to room)

public class GameState {
    HashMap<Room, Boolean> visited = new HashMap<Room, Boolean>();
    ArrayList<Room> storyTold = new ArrayList<>();
    String name;
    boolean finished;
    boolean quitProgram;
    boolean closetUpdate = false;
    Room room;
    List<Item> inventory = new ArrayList<Item>();
    Map<String, Room> rooms; // global list of rooms
    Map<String, Item> items; // global list of known items

    // update state and check for winning condition
    public String update() {
        if (quitProgram) {
            finished = true;
            return "";
        }
        if(!storyTold.contains(room) ) {
            String storytext = ""; 
            if (room.equals(rooms.get("closet"))) { 
                storytext = """
                    You nervously turn the knob on the closet door. Upon opening, you realize there's a large human
                    sized hole in the closet wall leading to what looks like a cave with two tunnels. As you walk into 
                    the closet to take a peek inside the cave, the closet door slams shut behind you, a click sound 
                    can be heard coming from the doorknob.
                    """;
            }
            if (room.equals(rooms.get("left tunnel"))) {
                 storytext = """
                    You shake as you creep further into the tunnel, hearing the groaning become louder each step
                    closer you get to this... thing. As you turn the corner you make a gruesome discovery. You 
                    discover a human skeleton at the end of the tunnel. To the side of the skeleton lies a sword on the floor.
                    To the right, a 7 foot large vault door is on the tunnel wall. The groans are loud now.
                    """;
            }  
            if (room.equals(rooms.get("skeleton"))) {
                storytext = """
                   You are infront of the skeleton now. Upon closer examination, you see something faintly glowing red under 
                   the skeleton's right hand. As you lift the hand up, your eyes light up in excitement!
                   """;
           }           
            if (room.equals(rooms.get("right tunnel"))) {
                storytext = """
                    As you walk through the tunnel, a golden hue of light becomes brighter. You are curious, yet terrified.
                    At the end of the tunnel you discover a golden door, a bright light is shining through the sides of the 
                    closed door.
                    """;
            }           
            if (room.equals(rooms.get("vault"))) {
                storytext = """
                     You grab ahold of the vault door handles and use all your strength to unlock the large vault. White smoke 
                     comes flooding out of the vault when you pull the door open. When the smoke clears, you find yourself face to face
                     with a large green oger! Behind the oger is a glowing green key hovering in the air. The oger screams: 
                     \" AAAAAURRRRGHHH \". You need that key, you have no other choice than to fight this beast.
                     
                     Oger Health: 15
                    """; 
            }
            if (room.equals(rooms.get("gold door"))) { 
                storytext = """
                    You are able to see the gold door clearly now. Above the doorknob of the gold door you see 3 locks, first lock
                    with the color red, second lock with the color green, and third lock with the color gold. You need 3 keys.

                    Locks Unlocked: 0/3
                    """;
            }      
        if (room.equals(rooms.get("light"))){
            finished = true;
            String finaltext =  """
                                The door is finally unlocked. When you turn the doorknob and open the door a bright glowing light 
                                fills your eyes, You reach into the light, trying to grab it in your hands. Suddenly, you start to
                                feel drowsy once again. You bink once more and awaken back on the table you rested your
                                head on. You lift your head off the table and realize you've been dreaming this 
                                whole time. As you get up to return the book to its shelf, you take a final glance 
                                at the front cover. Your eyes widen as you read the title...
                                """;
            String finaltext2 ="\'" + name + "'s Lost Treasure\', Written by " + name;
             storytext = finaltext + finaltext2;
        }            
            storyTold.add(room);
            visited.put(room, true);
            return storytext;
        }
        
        if (!closetUpdate && room.equals(rooms.get("closet")) && inventory.contains(items.get("greenkey")) && inventory.contains(items.get("redkey"))) {
            String updatetext = """
                    On second look inside the closet, you see a faint glowing inside what looks like a mouse hole at the bottom corner
                    of the room. You try your best to stick your hand inside the hole to grab what looks like a glowing gold key but the 
                    hole is simply too small. You need something very little to crawl into this hole and retrieve this key.
                    """;
            closetUpdate = true;
            return updatetext;
        }
        // finish 

        return "";
    }

    public GameState(String name) {
        this.name = name;
        finished = false;
        quitProgram = false;
        LoadYAML yl = new LoadYAML();
        rooms = yl.rooms;
        items = yl.items;
        storyTold.add(room);
        room = rooms.get("library");
        visited.put(room, true);
        //inventory.add(items.get("book"));
    }
}
