public class Animal extends Item {
    int health;
    int maxHealth;
    boolean alive = true;

    public Animal(String name, String type, String desc, String use, String act, int health) {
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

    public void heal(int points) {
        health = Math.min(health + points, maxHealth);
    }

    @Override
    public String inspect(){
        String message = super.inspect() + " This animal is currently " + (alive ? "alive." : "dead. ") + " Health is at " + health;
        return message;
    }

}
