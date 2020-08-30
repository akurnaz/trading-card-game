package io.github.akurnaz.tradingcardgame.collection.deck;

import io.github.akurnaz.tradingcardgame.collection.Card;

public interface Deck {

	Card draw() throws EmptyDeckException;

	int size();

}
