package belven.games;

import java.util.ArrayList;

public class Room {

	private String name;
	protected ArrayList<Room> rooms = new ArrayList<>();

	public Room(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public boolean equals(Object obj) {
		boolean result = false;

		if (obj instanceof Room) {
			Room r = (Room) obj;
			result = r.getName().equals(this.getName());
		}

		return result;
	}

	public void AddRoom(Room r) {
		if (!rooms.contains(r) && r != this) {
			rooms.add(r);
		}
	}
}