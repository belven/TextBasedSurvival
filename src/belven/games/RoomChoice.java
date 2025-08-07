package belven.games;

import java.util.HashMap;

public class RoomChoice extends Choice {

	private static HashMap<Room, RoomChoice> room_choices = new HashMap<>();

	private Room room;
	private BuildingChoice building;

	public RoomChoice(Room in_room, BuildingChoice building) {
		super("Room " + in_room.getName(), "", "You went to room " + in_room.getName());
		this.room = in_room;
		this.building = building;
		SetupRoomChoices();
	}

	@Override
	public void performChoice(Choice previous_choice) {
		super.performChoice(previous_choice);

		if (building.getRoomRewards().containsKey(getRoom())) {

			SurvivalGame.PrintLn("You found the following Items:", LogCategory.Output);

			for (Reward r : building.getRoomRewards().get(getRoom())) {
				String output = "- " + String.valueOf(r.getAmount()) + " x " + r.getItem().getName();
				SurvivalGame.PrintLn(output, LogCategory.Output);
				SurvivalGame.GetPlayer().getInventory().AddItem(r);
			}
		}
	}

	public void SetupRoomChoices() {
		if (!room_choices.containsKey(getRoom())) {
			room_choices.put(getRoom(), this);
		}

		for (Room r : getRoom().rooms) {
			if (r != getRoom()) {
				if (!room_choices.containsKey(r)) {
					AddChoice(new RoomChoice(r, building));
				} else {
					AddChoice(room_choices.get(r));
				}
			} else {
				SurvivalGame.PrintLn("Room " + this.getText() + " added self", LogCategory.Error);
			}
		}
	}

	public Room getRoom() {
		return room;
	}

}
