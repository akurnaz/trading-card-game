package io.github.akurnaz.tradingcardgame.player;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.IntSupplier;

import io.github.akurnaz.tradingcardgame.collection.Card;
import io.github.akurnaz.tradingcardgame.collection.hand.IncorrectRangeException;
import io.github.akurnaz.tradingcardgame.console.ActiveConsoleCommand;
import io.github.akurnaz.tradingcardgame.console.InsufficientManaException;

public class HumanPlayer implements Playable {
	private String name;
	private IntSupplier input;
	private Consumer<String> output;

	public HumanPlayer(String name, IntSupplier input, Consumer<String> output) {
		this.name = name;
		this.input = input;
		this.output = output;
	}

	@Override
	public void play(ActiveConsoleCommand activeConsoleCommand, int mana, List<Card> cardsInHand) {
		int index = getChoice(activeConsoleCommand);
		while (index != -1) {
			try {
				activeConsoleCommand.insertToTable(index);
			} catch (InsufficientManaException | IncorrectRangeException e) {
				output.accept(e.getMessage());
			}
			index = getChoice(activeConsoleCommand);
		}
	}

	@Override
	public String getName() {
		return name;
	}

	private int getChoice(ActiveConsoleCommand activeConsoleCommand) {
		StringBuilder sb = new StringBuilder(activeConsoleCommand.toString());
		sb.append("\n0: Next Turn");
		sb.append("\nChoose a card index in your hand: ");
		output.accept(sb.toString());
		return input.getAsInt();
	}

}
