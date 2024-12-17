    import java.io.FileInputStream;
    import java.io.InputStream;
    import java.lang.reflect.Constructor;
    import java.util.ArrayList;
    import java.util.List;
    import java.util.Map;

    public class Room {
        public String name;
        public List<Item> contents;
        public Map<String,String> doors;
        public boolean locked;
        public String  requires;

        public Room(String name, List<Item> contents, Map<String, String> doors) {
            this.name = name;
            this.contents = contents;
            this.doors = doors;
          
        }

        @Override
    public String toString() {
        return this.name;
    }

    }