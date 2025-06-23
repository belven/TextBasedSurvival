package belven.games;

public class Item {

	private String name;
	private int stack_limit;
	private int weight;

	private ItemType type;

	public String getName() {
		return name;
	}

	public Item(String name, int stack_limit, int weight, ItemType type) {
		this.name = name;
		this.stack_limit = stack_limit;
		this.weight = weight;
		this.type = type;
	}

	public int getStack_limit() {
		return stack_limit;
	}

	public int getWeight() {
		return weight;
	}

	public ItemType getType() {
		return type;
	}

}