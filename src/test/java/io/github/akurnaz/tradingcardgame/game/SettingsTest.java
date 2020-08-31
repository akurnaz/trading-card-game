package io.github.akurnaz.tradingcardgame.game;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class SettingsTest {
	
	@Test
	void build_givenSettings_thenEqualsAllField() {
		// given
		int initialHealth = 30;
		int initialMana=0;
		int maxMana = 10;
		int[] initialDeck = new int[] { 0, 0, 1, 1, 2, 2, 2, 3, 3, 3, 3, 4, 4, 4, 5, 5, 6, 6, 7, 8 };
		int maxCardInHand = 5;
		
		Settings settings = Settings.builder()
				.initialHealth(initialHealth)
				.initialMana(0)
				.maxMana(maxMana)
				.initialDeck(initialDeck)
				.maxCardInHand(maxCardInHand)
				.build();
		
		// then
		assertEquals(initialHealth, settings.getInitialHealth());
		assertEquals(initialMana, settings.getInitialMana());
		assertEquals(maxMana, settings.getMaxMana());
		assertEquals(initialDeck, settings.getInitialDeck());
		assertEquals(maxCardInHand, settings.getMaxCardInHand());
	}
}
