package belven.games;

import java.util.Objects;

public class Item {

	private String name;
	private int stack_limit;
	private int weight;

	private ItemType type;

	public String getName() {
		return name;
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		return name.equals(other.name);
	}

	public Item(String name, int stack_limit, int weight, ItemType type) {
		this.name = name;
		this.stack_limit = stack_limit;
		this.weight = weight;
		this.type = type;
	}

	public int getStackLimit() {
		return stack_limit;
	}

	public int getWeight() {
		return weight;
	}

	public ItemType getType() {
		return type;
	}

}