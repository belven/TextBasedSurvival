package belven.games;

import java.util.ArrayList;

public class LocationChoice extends Choice {
	private Reward[] rewards;
	private Location location;

	public LocationChoice(String text, String alias, String response, ArrayList<Choice> choices, Reward... rewards) {
		super(text, alias, response, choices);
		this.rewards = rewards;
	}

	public LocationChoice(String text, String alias, String response, Reward... rewards) {
		super(text, alias, response);
		this.rewards = rewards;
	}

	public LocationChoice(Location l, Reward... reward) {
		this(l.getName(), "", "You went to location " + l.getName(), reward);
		this.location = l;

		for (Building b : l.getBuildings()) {
			AddChoice(new BuildingChoice(b));
		}
	}

	@Override
	public void performChoice(Choice previous_choice) {
		super.performChoice(previous_choice);

//		for (Reward r : rewards) {
//			System.out.println(String.valueOf(r.getAmount()) + " x " + r.getName());
//			SurvivalGame.GetPlayer().getRewards().add(r);
//		}
	}

	public Reward[] getRewards() {
		return rewards;
	}

	public Location getLocation() {
		return location;
	}

}
