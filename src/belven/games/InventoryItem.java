package belven.games;

import java.util.Objects;

public class InventoryItem {

	private int amount;
	private Item item;

	public InventoryItem(int amount, Item item) {
		this.amount = amount;
		this.item = item;
	}

	@Override
	public int hashCode() {
		return Objects.hash(amount, item);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InventoryItem other = (InventoryItem) obj;
		return Objects.equals(item, other.item);
	}

	public Item getItem() {
		return item;
	}

	public int getAmount() {
		return amount;
	}

}