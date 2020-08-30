package io.github.akurnaz.tradingcardgame.collection.hand;

import java.util.ArrayList;
import java.util.List;

import io.github.akurnaz.tradingcardgame.collection.Card;

public class SimpleHand implements Hand {
	private List<Card> cards;
	private int maxSize;

	public SimpleHand(int maxSize) {
		this.maxSize = maxSize;
		this.cards = new ArrayList<>(maxSize);
	}

	@Override
	public boolean insert(Card card) {
		if (cards.size() < maxSize) {
			return this.cards.add(card);
		}

		return false;
	}

	@Override
	public Card draw(int index) {
		return cards.remove(index);
	}

	@Override
	public String toString() {
		return cards.toString();
	}

}
