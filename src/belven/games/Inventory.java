package belven.games;

import java.util.ArrayList;

public class Inventory {

	private ArrayList<InventoryItem> items = new ArrayList<>();
	private Player player;

	public Inventory() {

	}

	public ArrayList<InventoryItem> getItems() {
		return items;
	}

	public Player getPlayer() {
		return player;
	}

}