package belven.games;

import java.util.ArrayList;

public class Inventory {

	private ArrayList<InventoryItem> items = new ArrayList<>();
	private Player player;
	private int weight_limit = 100;
	private int current_weight = 0;

	public Inventory(int weight_limit) {
		this.weight_limit = weight_limit;
	}

	public int getWeightLimit() {
		return weight_limit;
	}

	public void AddItem(InventoryItem new_item) {
		// Check item weights, stack size etc.

		// Item i = SurvivalGame.GetItem(item.getItem().getName());
		Item i = new_item.getItem();

		for (InventoryItem exsiting_item : items) {
			if (exsiting_item.getItem() == i && exsiting_item.HasSpace()) {
				SurvivalGame.PrintLn("Existing Item Found", LogCategory.Info);
				exsiting_item.TakeFrom(new_item, GetWeightAvaiable());
				CalculateWeight();
			}

			if (new_item.IsEmpty()) {
				break;
			}
		}

		if (!new_item.IsEmpty()) {
			int item_weight = new_item.GetWeight();
			if (!IsOverweight()) {

				if (GetWeightAvaiable() >= item_weight) {
					items.add(new_item);
				} else {
					InventoryItem temp_item = new InventoryItem(0, new_item.getItem());
					temp_item.TakeFrom(new_item, GetWeightAvaiable());
					items.add(temp_item);
				}
				SurvivalGame.PrintLn("Item Added", LogCategory.Info);

				CalculateWeight();
			}

			if (!new_item.IsEmpty()) {
				SurvivalGame.PrintLn("Inventory full, no space left for item " + i.getName(), LogCategory.Output);
			}
		}
	}

	public boolean IsOverweight() {
		return current_weight >= weight_limit;
	}

	public int GetWeight() {
		return current_weight;
	}

	public int GetWeightAvaiable() {
		return weight_limit - current_weight;
	}

	public void CalculateWeight() {
		current_weight = 0;
		for (InventoryItem ii : items) {
			current_weight += ii.GetWeight();
		}
	}

	protected ArrayList<InventoryItem> getItems() {
		return items;
	}

	public Player getPlayer() {
		return player;
	}

}