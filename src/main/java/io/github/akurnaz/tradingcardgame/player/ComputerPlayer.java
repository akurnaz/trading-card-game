package io.github.akurnaz.tradingcardgame.player;

import java.util.List;
import java.util.function.Consumer;

import io.github.akurnaz.tradingcardgame.collection.Card;
import io.github.akurnaz.tradingcardgame.collection.hand.IncorrectRangeException;
import io.github.akurnaz.tradingcardgame.console.ActiveConsoleCommand;
import io.github.akurnaz.tradingcardgame.console.InsufficientManaException;

public class ComputerPlayer implements Playable {
	private static int numberOfInstance = 0;
	private Consumer<String> output;
	private String name;

	public ComputerPlayer(Consumer<String> output) {
		this.output = output;
		this.name = "Computer" + ++numberOfInstance;
	}

	public ComputerPlayer() {
		this(null);
	}

	@Override
	public void play(ActiveConsoleCommand activeConsoleCommand, int mana, List<Card> cardsInHand) {
		print(activeConsoleCommand);
		int selectedMana = -1;
		int index = -1;
		for (int i = 0; i < cardsInHand.size(); i++) {
			Card card = cardsInHand.get(i);
			if (card.getMana() <= mana && card.getMana() > selectedMana) {
				selectedMana = card.getMana();
				index = i;
			}
		}
		if (index != -1) {
			try {
				activeConsoleCommand.insertToTable(index);
			} catch (InsufficientManaException | IncorrectRangeException e) {
				throw new RuntimeException("Something is wrong in ComputerPlayer implementation");
			}
		}
	}

	@Override
	public String getName() {
		return name;
	}

	private void print(ActiveConsoleCommand activeConsoleCommand) {
		if (output == null) {
			return;
		}
		StringBuilder sb = new StringBuilder(activeConsoleCommand.toString());
		sb.append("\n0: Next Turn");
		sb.append("\nChoose a card index in your hand: ");
		output.accept(sb.toString());
	}

}
