package belven.games;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Scanner;

public class SurvivalGame {
	private static Choice main_menu = new Choice("Main Menu", "main", "Main Menu - Select an option");

	private static ArrayList<Location> locations = new ArrayList<>();
	private static boolean show_alias = true;
	private static boolean show_id = true;

	private static Player player = new Player();
	private static Choice current_choice = main_menu;

	private static String go_back = "Go Back to ";

	private static boolean show_info = true;

	private static Scanner input = new Scanner(System.in);
	private static HashMap<String, Item> item_data = new HashMap<>();

	static {
		main_menu.getChoices().add(new InventoryChoice("Check Inventory", "inv", "Current Inventory"));
		AddItem(new Item("Medkit", 1, 3, ItemType.Medical));
		AddItem(new Item("Stone", 3, 5, ItemType.Stone));
		AddItem(new Item("Wood", 10, 3, ItemType.Wood));
	}

	public static void AddItem(Item i) {
		item_data.put(i.getName(), i);
	}

	static {
		Room roomA = new Room("A");
		Room roomB = new Room("B");
		Room roomC = new Room("C");
		Room roomD = new Room("D");
		Room roomE = new Room("E");
		Room roomF = new Room("F");
		Room roomG = new Room("G");
		Room roomH = new Room("H");

		roomA.AddRoom(roomB);

		roomB.AddRoom(roomA);
		roomB.AddRoom(roomC);

		roomC.AddRoom(roomB);
		roomC.AddRoom(roomD);

		roomD.AddRoom(roomC);
		roomD.AddRoom(roomE);
		roomD.AddRoom(roomF);
		roomD.AddRoom(roomG);

		roomE.AddRoom(roomD);

		roomF.AddRoom(roomD);

		roomG.AddRoom(roomD);
		roomG.AddRoom(roomH);

		roomH.AddRoom(roomG);

		Building main_building = new Building("Main Building");
		main_building.getRooms().add(roomA);
		main_building.getRooms().add(roomA);
		main_building.getRooms().add(roomB);
		main_building.getRooms().add(roomC);
		main_building.getRooms().add(roomD);
		main_building.getRooms().add(roomE);
		main_building.getRooms().add(roomF);
		main_building.getRooms().add(roomG);
		main_building.getRooms().add(roomH);

		main_building.getEntranceRooms().add(roomA);
		main_building.getEntranceRooms().add(roomH);

		locations.add(new Location("Main Location", new ArrayList<>(Arrays.asList(main_building)), ItemType.Wood, ItemType.Medical));

		locations.trimToSize();
		main_menu.getChoices().add(new ExploreChoice("Explore", "exp", "Pick a location", locations));
	}

	public static Collection<Item> getAllItems() {
		return item_data.values();
	}

	public static Item GetItem(String name) {
		return item_data.get(name);
	}

	public static Choice getMainMenuChoice() {
		return main_menu;
	}

	public static Player GetPlayer() {
		return player;
	}

	public static void main(String[] args) {
		PerformChoice(main_menu);
	}

	public static void PrintLn(String text, LogCategory cat) {
		if (cat == LogCategory.Info && !show_info) {
			return;
		} else {
			String category_text = cat != LogCategory.Output ? cat.toString() + ": " : "";
			System.out.println(category_text + text);
		}

	}

	public static void CheckInput(String input, Choice current_choice) {
		boolean choice_selected = false;

		try {
			int selection = Integer.parseInt(input);

			int choices_amount = current_choice.getChoices().size();

			if (selection <= choices_amount && selection > 0) {
				PerformChoice(current_choice.getChoices().get(selection - 1));
			} else if (current_choice != null && selection == choices_amount + 1) {
				PerformChoice(current_choice.getPreviousChoice());
				choice_selected = true;
			}

		} catch (NumberFormatException e) {
			for (Choice a : current_choice.getChoices()) {
				if (a.getAlias().equals(input)) {
					PerformChoice(a);
					choice_selected = true;
					break;
				}
			}
		}

		if (!choice_selected) {
			ReadPlayerInput();
		}
	}

	public static void PerformChoice(Choice a) {
		Choice previous_choice = current_choice;
		current_choice = a;

		PrintLn("Player selected " + a.getText(), LogCategory.Info);
		PrintLn(current_choice.getResponse(), LogCategory.Output);

		a.performChoice(previous_choice);

		PrintChoices(current_choice.getChoices());
		CheckInput(ReadPlayerInput(), current_choice);
	}

	public static void PrintChoices(ArrayList<Choice> choices) {
		int i = 1;

		for (Choice a : choices) {
			if (a != null)
				PrintLn(a.ToString(i++, show_id, show_alias), LogCategory.Output);
		}

		if (current_choice != main_menu) {
			// This is used to prevent Room Choices having both, Room A and Go Back to Room
			// A as choices
			if (!choices.contains(current_choice.getPreviousChoice())) {
				String text = go_back + current_choice.getPreviousChoice().getText();
				String output = show_id ? String.valueOf(i) + ": " + text : text;
				PrintLn(output, LogCategory.Output);
			}
		}
	}

	public static String ReadPlayerInput() {
		System.out.println("");
		return input.nextLine();
	}
}
