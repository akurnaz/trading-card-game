package io.github.akurnaz.tradingcardgame.player;

import java.util.List;

import io.github.akurnaz.tradingcardgame.collection.Card;
import io.github.akurnaz.tradingcardgame.collection.hand.IncorrectRangeException;
import io.github.akurnaz.tradingcardgame.console.ActiveConsoleCommand;
import io.github.akurnaz.tradingcardgame.console.InsufficientManaException;

public class ComputerPlayer implements Playable {

	@Override
	public void play(ActiveConsoleCommand activeConsoleCommand, int mana, List<Card> cardsInHand) {
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
		return "Computer";
	}

}
