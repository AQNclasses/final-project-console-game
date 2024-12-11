import java.util.List;

public class Tool extends Item {
    
    public Tool(String name, List<String> types, String desc, String use, String act) {
        super(name, types, desc, use, act);
    }

    public void clean() { 
        switch (this.name) {
            case "broom": 
                System.out.println("The broom glides across the floor of the room, kicking up some dust and gathering loose bits of debris from the surface into a small pile. You notice that some of the bristles are crooked and snapped off.");
                break;
            case "bucket":
                System.out.println("You splash the murky water from the bucket across the floor, hoping it will make the room look a little cleaner. The resulting state of the room just looks more dingy and gross. The water in the bucket magically refills itself");
                break;
            case "mop":
                System.out.println("You take the mop across the floor, moving back and forth, you see some of the stains being moved as the wet mop digs into them. Having no strainer, the mop is just a little too wet and leaves behind a few puddles as you mop the whole room.");
                break;
        }
        this.used = false;
    }
}    