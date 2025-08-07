package belven.games;

import java.util.Objects;

public class InventoryItem {

	private int amount;
	private Item item;

	public InventoryItem(int amount, Item item) {
		this.amount = amount;
		this.item = item;

		amount = Math.min(item.getStackLimit(), amount);
	}

	public boolean HasSpace() {
		return amount < item.getStackLimit();
	}

	public boolean IsEmpty() {
		return amount <= 0;
	}

	public int GetWeight() {
		return getAmount() * getItem().getWeight();
	}

	public int GetSpace() {
		return item.getStackLimit() - amount;
	}

	public void TakeFrom(InventoryItem i, int weight_avaiable) {
		if (i.getItem() == getItem()) {

			int amount_by_weight = 0;

			if (i.GetWeight() <= weight_avaiable) {
				amount_by_weight = i.amount;
			} else {
				int temp_weight = 0;

				while (temp_weight + i.getItem().getWeight() <= weight_avaiable) {
					temp_weight += i.getItem().getWeight();
					amount_by_weight++;
				}
			}

			int amount_to_take = Math.min(GetSpace(), amount_by_weight);

			i.amount -= amount_to_take;
			amount += amount_to_take;
			SurvivalGame.PrintLn("Item Added", LogCategory.Info);
		} else {
			SurvivalGame.PrintLn("Different item attempted to take from", LogCategory.Error);
		}
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
		return item == other.item;
	}

	public Item getItem() {
		return item;
	}

	public int getAmount() {
		return amount;
	}

}