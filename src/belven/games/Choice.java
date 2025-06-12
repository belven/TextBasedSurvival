package belven.games;

import java.util.ArrayList;

public class Choice {
	private String text = "";
	private String alias = "";
	private String response = "";
	private ArrayList<Choice> choices = new ArrayList<>();
	protected Choice previous_choice;

	public Choice(String text, String alias, String response) {
		this.text = text;
		this.alias = alias;
		this.response = response;
	}

	public void AddChoice(Choice c) {
		if (!choices.contains(c)) {
			choices.add(c);
		}
	}

	public String ToString(int position, boolean show_id, boolean show_alias) {
		String output = show_id ? String.valueOf(position) + ": " + getText() : getText();
		output = show_alias ? output + " (" + getAlias() + ")" : output;
		return output;
	}

	public Choice(String text, String alias, String response, ArrayList<Choice> choices) {
		this(text, alias, response);

		this.choices = choices;
	}

	public String getText() {
		return text;
	}

	public String getAlias() {
		return alias;
	}

	public ArrayList<Choice> getChoices() {
		return choices;
	}

	public String getResponse() {
		return response;
	}

	public void performChoice(Choice previous_choice) {
		if (this.previous_choice == null) {
			this.previous_choice = previous_choice;
		}
	}

	public Choice getPreviousChoice() {
		return previous_choice;
	}
}
