import java.util.List;

public class Note extends Key{
    String clue;
    String altClue;
    String item;
    boolean i;
    boolean r;
    public Note(String name, List<String> types, String desc, String use, String act, String[] s){
        super(name,types,desc,use,act,s);
        clue = s[varNum++];
        item = s[varNum++];
        i = Boolean.valueOf(s[varNum++]);
        r = Boolean.valueOf(s[varNum++]);
    }

    public String showClue(GameState state){
        String txt = clue.replace("[Room]",srcRoom);

        Room src = state.rooms.get(srcRoom);
        Item obj = state.items.get(item);
        if(i){
            txt += "\n" + obj.name +" revealed";
            src.contents.add(obj);
        }
        if(r){
            txt += "\n" + door + " revealed";
            src.doors.put(door,destRoom);
        }
        if(i || r){
            txt += " in " + srcRoom;
        }
        state.rooms.put(srcRoom,src);
        return txt;
    }
@Override
    public String use(GameState s){
        return use() + "\n" + showClue(s);
    }
}