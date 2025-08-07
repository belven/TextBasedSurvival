package belven.games;

public class InventoryChoice extends Choice {

	public InventoryChoice(String text, String alias, String response) {
		super(text, alias, response);
	}

	@Override
	public void performChoice(Choice previous_choice) {
		super.performChoice(previous_choice);

		Player p = SurvivalGame.GetPlayer();

		SurvivalGame.PrintLn("Current Weight: " + String.valueOf(p.getInventory().GetWeight()), LogCategory.Output);
		SurvivalGame.PrintLn("Max Weight: " + String.valueOf(p.getInventory().getWeightLimit()), LogCategory.Output);

		for (InventoryItem ii : p.getInventory().getItems()) {
			String item_text = String.valueOf(ii.getAmount()) + " x " + ii.getItem().getName();
			item_text += " Weight: " + ii.GetWeight();
			SurvivalGame.PrintLn(item_text, LogCategory.Output);
		}
	}

}
