package belven.games;

public class BuildingChoice extends Choice {
	private Building building;

	public BuildingChoice(Building b) {
		super(b.getName(), "", "You went to Building " + b.getName());
		this.building = b;

		for (Room r : b.getEntranceRooms()) {
			AddChoice(new RoomChoice(r));
		}
	}

	public Building getBuilding() {
		return building;
	}

}
