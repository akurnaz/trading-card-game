package io.github.akurnaz.tradingcardgame.collection;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CardTest {
	private int mana;
	private Card card;

	@BeforeEach
	void BeforeEach() {
		mana = 3;
		card = new Card(mana);
	}

	@Test
	void getMana_givenMana_thenReturnEqualsGivenMana() {
		// then
		assertEquals(mana, card.getMana());
	}

	@Test
	void toString_givenMana_thenReturnEquals() {
		// then
		assertEquals(String.valueOf(mana), card.toString());

	}
}
