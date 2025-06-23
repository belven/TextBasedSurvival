package belven.games;

import java.util.ArrayList;

public class Location {

	private String name;

	private ArrayList<Building> buildings = new ArrayList<>();
	private ItemType[] loot_types = new ItemType[5];

	public Location(String name, ArrayList<Building> buildings, ItemType... loot_types) {
		this.name = name;
		this.buildings = buildings;
		this.loot_types = loot_types;
	}

	public Location() {
	}

	public ArrayList<Building> getBuildings() {
		return buildings;
	}

	public String getName() {
		return name;
	}

	public ItemType[] getItemTypes() {
		return loot_types;
	}

}
