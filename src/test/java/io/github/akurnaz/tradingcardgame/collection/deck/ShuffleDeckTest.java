package io.github.akurnaz.tradingcardgame.collection.deck;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class ShuffleDeckTest {

	@Test
	void draw_givenNotEmptyDeck_thenReturnNotNull() throws EmptyDeckException {
		// given
		int[] initialDeck = { 0, 0, 1, 2, 3, 3, 3 };
		Deck deck = new ShuffleDeck(initialDeck);

		// then
		assertNotNull(deck.draw());
	}

	@Test
	void draw_givenEmptyDeck_thenThrowEmptyDeckException() {
		// given
		int[] initialDeck = {};
		Deck deck = new ShuffleDeck(initialDeck);

		// then
		assertThrows(EmptyDeckException.class, () -> deck.draw());
	}

	@Test
	void size_givenNotEmptyDeck_thenReturnEqualSize() {
		// given
		int[] initialDeck = { 0, 0, 1, 2, 3, 3, 3 };
		Deck deck = new ShuffleDeck(initialDeck);

		// then
		assertEquals(initialDeck.length, deck.size());
	}

	@Test
	void size_givenDrawedDeck_thenReturnOneLessSize() throws EmptyDeckException {
		// given
		int[] initialDeck = { 0, 0, 1, 2, 3, 3, 3 };
		Deck deck = new ShuffleDeck(initialDeck);
		deck.draw();

		// then
		assertEquals(initialDeck.length - 1, deck.size());
	}

	@Test
	void toString_givenNotEmptyHand_thenReturnEquals() {
		// given
		int[] initialDeck = { 0 };
		Deck deck = new ShuffleDeck(initialDeck);

		// then
		assertEquals("[0]", deck.toString());
	}

}
