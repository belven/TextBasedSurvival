package belven.games;

public class InventoryChoice extends Choice {

	public InventoryChoice(String text, String alias, String response) {
		super(text, alias, response);
	}

	@Override
	public void performChoice(Choice previous_choice) {
		super.performChoice(previous_choice);

		Player p = SurvivalGame.GetPlayer();
		for (InventoryItem ii : p.getInventory().getItems()) {
			System.out.println(String.valueOf(ii.getAmount()) + " x " + ii.getItem().getName());
		}
	}

}
