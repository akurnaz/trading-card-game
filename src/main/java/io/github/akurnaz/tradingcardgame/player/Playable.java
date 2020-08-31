package io.github.akurnaz.tradingcardgame.player;

import java.util.List;

import io.github.akurnaz.tradingcardgame.collection.Card;
import io.github.akurnaz.tradingcardgame.console.ActiveConsoleCommand;

public interface Playable {

	void play(ActiveConsoleCommand activeConsoleCommand, int mana, List<Card> cardsInHand);

	String getName();

}
