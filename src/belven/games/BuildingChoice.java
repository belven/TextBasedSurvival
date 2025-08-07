package belven.games;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

public class BuildingChoice extends Choice {
	private Building building;
	private Random r = new Random();

	private ArrayList<ItemType> loot_types = new ArrayList<>();
	private HashMap<Room, ArrayList<Reward>> room_rewards = new HashMap<>();

	private ArrayList<Item> filtered_items = new ArrayList<>();

	public BuildingChoice(Building b, ItemType[] lootTypes) {
		super(b.getName(), "", "You went to Building " + b.getName());
		this.building = b;
		this.loot_types = new ArrayList<>(Arrays.asList(lootTypes));

		SetupLoot();
		SetupEntrances();
	}

	private void SetupEntrances() {
		for (Room r : building.getEntranceRooms()) {
			RoomChoice room_choice = new RoomChoice(r, this);
			room_choice.previous_choice = this;
			AddChoice(room_choice);
		}
	}

	private void SetupLoot() {
		for (Item i : SurvivalGame.getAllItems()) {
			if (loot_types.contains(i.getType())) {
				filtered_items.add(i);
			}
		}

		ArrayList<Reward> rewards = new ArrayList<>();

		for (Item i : filtered_items) {
			rewards.add(new Reward(randomBetween(10, 30), i));
		}

		ArrayList<Room> shuffled_rooms = building.getRooms();

		Collections.shuffle(shuffled_rooms);

		// Keep going until all rewards have been assigned to a room

		while (!rewards.isEmpty()) {

			SurvivalGame.PrintLn("Adding Rewards to Rooms, Rewards left = " + String.valueOf(rewards.size()), LogCategory.Info);
			// Loop through all rooms
			for (Room r : shuffled_rooms) {

				SurvivalGame.PrintLn("Attepmting to add rewards to room " + r.getName(), LogCategory.Info);

				boolean one_reward_left = rewards.size() == 1;

				// Get a random amount of rewards
				int i = !one_reward_left ? randomBetween(1, rewards.size()) : 1;

				SurvivalGame.PrintLn("Attepmting to add " + String.valueOf(i) + " rewards to room " + r.getName(), LogCategory.Info);

				// Loop through the amount of possible rewards for the room, until we reach 0
				for (; i > 0 && !rewards.isEmpty(); i--) {

					// Get a random reward index
					int reward_index = !one_reward_left ? randomBetween(0, rewards.size() - 1) : 0;

					// Get the reward
					Reward reward = rewards.get(reward_index);
					SurvivalGame.PrintLn("Adding " + reward.getItem().getName() + " to room " + r.getName(), LogCategory.Info);

					// Add the reward to the list for that room
					if (room_rewards.containsKey(r)) {
						ArrayList<Reward> local_room_rewards = room_rewards.get(r);
						local_room_rewards.add(reward);
						room_rewards.put(r, local_room_rewards);

					} else {
						room_rewards.put(r, new ArrayList<>(Arrays.asList(reward)));
					}

					// Remove the reward from the list of available rewards
					rewards.remove(reward_index);
					SurvivalGame.PrintLn("Rewards left = " + String.valueOf(rewards.size()), LogCategory.Info);

					if (rewards.isEmpty()) {
						break;
					}
				}

				if (rewards.isEmpty()) {
					break;
				}
			}
		}

	}

	public int randomBetween(int low, int high) {
		return r.nextInt(high - low) + low;

	}

	public Building getBuilding() {
		return building;
	}

	public HashMap<Room, ArrayList<Reward>> getRoomRewards() {
		return room_rewards;
	}

}
