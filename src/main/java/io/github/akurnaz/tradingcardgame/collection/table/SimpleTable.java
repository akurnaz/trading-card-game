package io.github.akurnaz.tradingcardgame.collection.table;

import java.util.LinkedList;
import java.util.List;

import io.github.akurnaz.tradingcardgame.collection.Card;

public class SimpleTable implements Table {
	private List<Card> cards;

	public SimpleTable() {
		this.cards = new LinkedList<>();
	}

	@Override
	public boolean insert(Card card) {
		return this.cards.add(card);
	}

	@Override
	public String toString() {
		return cards.toString();
	}
}
