package io.github.akurnaz.tradingcardgame.collection.deck;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import io.github.akurnaz.tradingcardgame.collection.Card;

public class ShuffleDeck implements Deck {
	private Queue<Card> cards;

	public ShuffleDeck(int[] initialDeck) {
		this.cards = new LinkedList<>();
		Arrays.stream(initialDeck).forEach(mana -> cards.add(new Card(mana)));
		Collections.shuffle((List<?>) cards);
	}

	@Override
	public Card draw() throws EmptyDeckException {
		Card card = cards.poll();
		if (card == null) {
			throw new EmptyDeckException("No cards in the deck.");
		}
		return card;
	}

	@Override
	public long size() {
		return cards.size();
	}

	@Override
	public String toString() {
		return cards.toString();
	}

}
