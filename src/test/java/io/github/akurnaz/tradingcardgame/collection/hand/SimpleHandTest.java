package io.github.akurnaz.tradingcardgame.collection.hand;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.github.akurnaz.tradingcardgame.collection.Card;

class SimpleHandTest {
	private int maxSize;
	private Hand hand;

	@BeforeEach
	void BeforeEach() {
		maxSize = 5;
		hand = new SimpleHand(maxSize);
	}

	@Test
	void insert_givenEmptyHand_thenReturnTrue() {
		// then
		assertTrue(hand.insert(new Card(3)));
	}

	@Test
	void insert_givenFullHand_thenReturnFalse() {
		// given
		new Random().ints().limit(maxSize).forEach(m -> hand.insert(new Card(m)));

		// then
		assertFalse(hand.insert(new Card(3)));
	}

	@Test
	void draw_givenNotEmptyHand_thenReturnEqualsCard() throws IncorrectRangeException {
		// given
		Card card = new Card(3);
		hand.insert(card);

		// then
		assertEquals(card, hand.draw(0));
	}

	@Test
	void draw_givenIndexOutOfRange_thenThrowIncorrectRangeException() {
		// given
		Card card = new Card(3);
		hand.insert(card);

		// then
		assertThrows(IncorrectRangeException.class, () -> hand.draw(2));
		assertThrows(IncorrectRangeException.class, () -> hand.draw(-1));
	}

	@Test
	void toString_givenNotEmptyHand_thenReturnEquals() {
		// given
		Card card = new Card(3);
		hand.insert(card);
		hand.insert(card);

		// then
		assertEquals("[3, 3]", hand.toString());
	}
}
