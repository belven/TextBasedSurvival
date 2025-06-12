package belven.games;

import java.util.ArrayList;

public class Building {

	private String name;
	private ArrayList<Room> rooms = new ArrayList<>();
	private ArrayList<Room> entrance_rooms = new ArrayList<>();

	public String getName() {
		return name;
	}

	public Building(String name) {
		this.name = name;
	}

	public ArrayList<Room> getRooms() {
		return rooms;
	}

	public ArrayList<Room> getEntranceRooms() {
		return entrance_rooms;
	}
}
