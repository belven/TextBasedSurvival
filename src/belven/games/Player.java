package belven.games;

import java.util.ArrayList;

public class Player {

	private ArrayList<Reward> rewards = new ArrayList<>();
	private Inventory inventory;

	public ArrayList<Reward> getRewards() {
		return rewards;
	}

	public Inventory getInventory() {
		return inventory;
	}
}
