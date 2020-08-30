package io.github.akurnaz.tradingcardgame.collection;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class CardTest {

	@Test
	void getMana_givenMana_thenReturnEqualsGivenMana() {
		// given
		int mana = 3;
		Card card = new Card(mana);

		// then
		assertEquals(mana, card.getMana());
	}
}
