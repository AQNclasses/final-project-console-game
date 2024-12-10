import java.util.List;
import java.util.ArrayList;

// Define types of items that share actions / properties
enum ItemType {
    Weapon,
    Healing,
    Key,
    Animal,
    Plant,
    Potion,
    Item;

    public static ItemType toType(String s) {
        switch (s) {
            case "Weapon":
                return ItemType.Weapon;
            case "Healing":
                return ItemType.Healing;
            case "Key":
                return ItemType.Key;
            case "Animal":
                return ItemType.Animal;
            case "Plant":
                return ItemType.Plant;
            case "Potion":
                return ItemType.Potion;
            default:
                return ItemType.Item;
        }
    }
}

// Object defining how general items work in your game
// All other item classes should inherit this class
public class Item {
    String name;
    ArrayList<ItemType> types = new ArrayList<ItemType>();
    String desc;
    String use;
    String action;
    Boolean used = false;

    Item(String n, List<String> ts, String d, String u, String a) {
        name = n;
        for (String ty : ts) types.add(ItemType.valueOf(ty));
        desc = d;
        use = u;
        action = a;
    }

    public String inspect() {
        String alltypes = "";
        for (ItemType t: types) alltypes += t.name() + " ";
        String message = "This is a " + this.name + ", a kind of " + alltypes + ". Description: " + this.desc;
        return message;
    }

    public void use() {
        used = true;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public static class Weapon extends Item {
        int minDamage;
        int maxDamage;

        public Weapon(String n, List<String> ts, String d, String u, String a, int min, int max) {
            super(n, ts, d, u, a);
            this.minDamage = min;
            this.maxDamage = max;
        }

        @Override
        public void use() {
            if (GameState.enemyPresent) {
                int damage = (int) (Math.random() * (maxDamage - minDamage + 1)) + minDamage;
                int buffedDamage = (int) (damage * GameState.buffMultiplier);
                GameState.enemyHealth -= buffedDamage;
                Game.printSlow("You swing the " + name + " and deal " + buffedDamage + " damage!");
                if (GameState.enemyHealth <= 0) {
                    GameState.enemyPresent = false;
                    Game.printSlow("The enemy collapses, his golden crown falling from his head. You are victorious!");
                    GameState.room.contents.add(GameState.items.get("crown"));
                } else {
                    Game.printSlow("The enemy has " + GameState.enemyHealth + " health left.");
                }
            } else {
                Game.printSlow("You inspect the " + name + ". " + desc);
            }
        }
    }

    public static class Healing extends Item {
        int healAmount;

        public Healing(String n, List<String> ts, String d, String u, String a, int heal) {
            super(n, ts, d, u, a);
            this.healAmount = heal;
        }

        @Override
        public void use() {
            if (GameState.playerHealth < GameState.maxHealth) {
                GameState.playerHealth = Math.min(GameState.playerHealth + healAmount, GameState.maxHealth);
                Game.printSlow("You feel the gentle warmth of the healing stone. Your health is now " + GameState.playerHealth + ".");
            } else {
                Game.printSlow("Your health is full. The healing stone does nothing.");
            }
        }
    }

    public static class Key extends Item {
        public Key(String n, List<String> ts, String d, String u, String a) {
            super(n, ts, d, u, a);
        }
        @Override
        public void use() {
            Game.printSlow("You hold the key in your hand. Maybe try moving through a locked door.");
        }
    }

    public static class Animal extends Item {
        public Animal(String n, List<String> ts, String d, String u, String a) {
            super(n, ts, d, u, a);
        }

        @Override
        public void use() {
            System.out.println(name + " hops around.");
        }
    }

    public static class Plant extends Item {
        int healthBoost;

        public Plant(String n, List<String> ts, String d, String u, String a, int boost) {
            super(n, ts, d, u, a);
            this.healthBoost = boost;
        }
        @Override
        public void use() {
            System.out.println("You eat the " + name + " and feel your vitality increase!");
            GameState.increaseMaxHealth(healthBoost);
        }
    }

    public static class Potion extends Item {
        String potionEffect;

        public Potion(String n, List<String> ts, String d, String u, String a, String effect) {
            super(n, ts, d, u, a);
            this.potionEffect = effect;
        }

        @Override
        public void use() {
            switch (potionEffect) {
                case "death":
                    Game.printSlow("You drink the ominous black liquid.");
                    Game.printSlow("Probably not the best idea...");
                    GameState.playerHealth = 0;
                    break;
                case "buff":
                    Game.printSlow("You drink the shimmering gold liquid.");
                    Game.printSlow("You feel a surge of power from within! Your attacks will now deal double damage!");
                    GameState.buffMultiplier = 2;
                    break;
                case "damage":
                    Game.printSlow("You drink the oozing purple liquid.");
                    int damNum = 5;
                    GameState.playerHealth -= damNum;
                    Game.printSlow("Your throat burns! You take " + damNum + " damage. Your health is now " + GameState.playerHealth + ".");
                    break;
                default:
                    Game.printSlow("Nothing happens.");
            }
        }
    }
}
