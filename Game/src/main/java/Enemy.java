
public class Enemy {
	public String name;
	String description;
	public boolean defeated;
	public int health;
	
	public Enemy(String name, String description, int health) {
		this.name = name;
		this.description = description;
		this.defeated = false;
		this.health = health;
	}
}
