package belven.games;

import java.util.ArrayList;

public class LocationChoice extends Choice {
	private Location location;

	public LocationChoice(String text, String alias, String response, ArrayList<Choice> choices) {
		super(text, alias, response, choices);
	}

	public LocationChoice(String text, String alias, String response) {
		super(text, alias, response);
	}

	public LocationChoice(Location l) {
		this(l.getName(), "", "You went to location " + l.getName());
		this.location = l;

		for (Building b : l.getBuildings()) {
			AddChoice(new BuildingChoice(b, l.getItemTypes()));
		}
	}

	@Override
	public void performChoice(Choice previous_choice) {
		super.performChoice(previous_choice);

	}

	public Location getLocation() {
		return location;
	}

}
