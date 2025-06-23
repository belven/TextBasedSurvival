package belven.games;

import java.util.ArrayList;

public class ExploreChoice extends Choice {
	ArrayList<Location> locations;

	public ExploreChoice(String text, String alias, String response, ArrayList<Location> locations) {
		super(text, alias, response);
		this.locations = locations;
	}

	@Override
	public void performChoice(Choice previous_choice) {
		super.performChoice(previous_choice);

		for (Location l : locations) {
			AddChoice(new LocationChoice(l));
		}
	}

}
