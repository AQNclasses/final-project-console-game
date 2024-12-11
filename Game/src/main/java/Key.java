import java.util.List;
import java.util.Random;

public class Key extends Item{
	int min;
	int max;
	public String color;
	private Random rn;

	Key(String name, List<String> types, String desc, String use, String act, String keyColor) {
		super(name, types, desc, use, act);
		color = keyColor;
		rn = new Random();
	}

	public int attack() {
		int var = min + rn.nextInt((max-min) + 1);
		return var;
	}

}
