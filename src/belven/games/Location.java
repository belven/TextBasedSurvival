package belven.games;

import java.util.ArrayList;

public class Location {

	private String name;

	private ArrayList<Building> buildings = new ArrayList<>();

	public Location(String name, ArrayList<Building> buildings) {
		this.name = name;
		this.buildings = buildings;
	}

	public Location() {
	}

	public ArrayList<Building> getBuildings() {
		return buildings;
	}

	public String getName() {
		return name;
	}

}
