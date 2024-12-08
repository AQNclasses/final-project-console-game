public class Plant extends Item {
    int health;
    int maxHealth;
    boolean alive = true;

    public Plant(String name, String type, String desc, String use, String act, int health) {
        super(name, type, desc, use, act);
        this.health = health;
        maxHealth = health;
    }

    // Dead or alive after taking damage
    public boolean attack(int damage) {
        health -= damage;
        alive = health > 0;
        return alive;
    }

    @Override
    public String inspect(){
        String message = super.inspect() + ". Health: " + health;
        return message;
    }

}
