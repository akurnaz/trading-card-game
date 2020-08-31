package io.github.akurnaz.tradingcardgame.collection.hand;

import java.util.List;

import io.github.akurnaz.tradingcardgame.collection.Card;

public interface Hand {

	boolean insert(Card card);

	Card draw(int index) throws IncorrectRangeException;

	List<Card> unmodifiableList();

}
