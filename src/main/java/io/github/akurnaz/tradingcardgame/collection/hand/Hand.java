package io.github.akurnaz.tradingcardgame.collection.hand;

import io.github.akurnaz.tradingcardgame.collection.Card;

public interface Hand {

	boolean insert(Card card);

	Card draw(int index) throws IncorrectRangeException;

}
