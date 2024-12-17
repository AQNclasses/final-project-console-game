public class Tool extends Item {

    String pairName;

    public Tool(String name, String type, String desc, String use, String act, String pair) {
        super(name, type, desc, use, act);
        pairName = pair;
    }

    public String use(GameState state, String item) {
        if (!state.inventory.contains(state.items.get(pairName))) {
            return "You don't have that item.";
        }
        switch (name) {
            case "Plunger":
                if (state.inventory.contains(state.items.get("Toilet"))) {
                    state.inventory.add(state.items.get("Greenhouse Key"));
                    return use;
                } else {
                    return "You try to use the plunger on the " + item + ", but they dont seem to work together.";
                }
            case "Oven":
                if (state.inventory.contains(state.items.get("Frozen Pizza"))) {
                    state.inventory.remove(state.items.get("Frozen Pizza"));
                    state.inventory.add(state.items.get("Veggie Pizza"));
                    return use;
                } else {
                    return "You think about putting the " + item + " in the oven, but decide not to start a fire.";
                }
            

        }

        return null;
    }

}
