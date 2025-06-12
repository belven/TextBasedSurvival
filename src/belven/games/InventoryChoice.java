package belven.games;

public class InventoryChoice extends Choice {

	public InventoryChoice(String text, String alias, String response) {
		super(text, alias, response);
	}

	@Override
	public void performChoice(Choice previous_choice) {
		super.performChoice(previous_choice);

		Player p = SurvivalGame.GetPlayer();
		for (Reward r : p.getRewards()) {
			System.out.println(String.valueOf(r.getAmount()) + " x " + r.getName());
		}
	}

}
