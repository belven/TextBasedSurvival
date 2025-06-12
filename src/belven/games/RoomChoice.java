package belven.games;

import java.util.HashMap;

public class RoomChoice extends Choice {

	private static HashMap<Room, RoomChoice> room_choices = new HashMap<>();

	private Room room;

	public RoomChoice(Room in_room) {
		super(in_room.getName(), "", "You went to room " + in_room.getName());
		this.room = in_room;
		SetupRoomChoices();

	}

	public void SetupRoomChoices() {
		if (!room_choices.containsKey(room)) {
			room_choices.put(room, this);
		}

		for (Room r : room.rooms) {
			if (r != room) {
				if (!room_choices.containsKey(r)) {
					AddChoice(new RoomChoice(r));
				} else {
					AddChoice(room_choices.get(r));
				}
			} else {
				System.out.println("Room added self");
			}
		}
	}

	public Room getRoom() {
		return room;
	}

}
