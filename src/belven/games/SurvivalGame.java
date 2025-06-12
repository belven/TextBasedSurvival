package belven.games;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class SurvivalGame {
	private static Choice main_menu = new Choice("Main Menu", "main", "Main Menu - Select an option");

	private static ArrayList<Location> locations = new ArrayList<>();
	private static boolean show_alias = true;
	private static boolean show_id = true;

	private static Player player = new Player();
	private static Choice current_choice = main_menu;

	private static String go_back = "Go Back to ";

	private static Scanner input = new Scanner(System.in);

	static {

		main_menu.getChoices().add(new InventoryChoice("Check Inventory", "inv", "Current Inventory"));
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
//		main_building.getRooms().add(roomA);
//		main_building.getRooms().add(roomB);
//		main_building.getRooms().add(roomC);
//		main_building.getRooms().add(roomD);
//		main_building.getRooms().add(roomE);
//		main_building.getRooms().add(roomF);
//		main_building.getRooms().add(roomG);
//		main_building.getRooms().add(roomH);

		main_building.getEntranceRooms().add(roomA);
		main_building.getEntranceRooms().add(roomH);

		locations.add(new Location("Main Location", new ArrayList<>(Arrays.asList(main_building))));

		ArrayList<Choice> location_choices = new ArrayList<>();

		for (Location l : locations) {
			location_choices.add(new LocationChoice(l, new Reward("Test 1")));
		}

		main_menu.getChoices().add(new Choice("Explore", "exp", "Pick a location", location_choices));
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

	public static void CheckInput(String input, Choice current_choice) {
		boolean choice_selected = false;

		try {
			int selection = Integer.parseInt(input);

			if (selection <= current_choice.getChoices().size() && selection > 0) {
				PerformChoice(current_choice.getChoices().get(selection - 1));
			} else if (current_choice != null && selection == current_choice.getChoices().size() + 1) {
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

		a.performChoice(previous_choice);

		System.out.println("Player selected " + a.getText());
		System.out.println(current_choice.getResponse());

		PrintChoices(current_choice.getChoices());
		CheckInput(ReadPlayerInput(), current_choice);
	}

	public static void PrintChoices(ArrayList<Choice> choices) {
		int i = 1;

		for (Choice a : choices) {
			System.out.println(a.ToString(i++, show_id, show_alias));
		}

		if (current_choice != main_menu) {
			String text = go_back + current_choice.getPreviousChoice().getText();
			String output = show_id ? String.valueOf(i) + ": " + text : text;
			System.out.println(output);
		}
	}

	public static String ReadPlayerInput() {
		System.out.println("");
		return input.nextLine();
	}
}
