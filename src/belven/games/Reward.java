package belven.games;

public class Reward {
	private String name = "";
	private int amount = 1;

	public Reward(String name, int amount) {
		this(name);
		this.amount = amount;
	}

	public String getName() {
		return name;
	}

	public int getAmount() {
		return amount;
	}

	public Reward(String name) {
		this.name = name;
	}
}
