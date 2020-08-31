package io.github.akurnaz.tradingcardgame.game;

public class Settings {
	private int initialHealth;
	private int maxMana;
	private int[] initialDeck;
	private int maxCardInHand;

	public Settings(SettingsBuilder builder) {
		this.initialHealth = builder.initialHealth;
		this.maxMana = builder.maxMana;
		this.initialDeck = builder.initialDeck;
		this.maxCardInHand = builder.maxCardInHand;
	}

	public int getInitialHealth() {
		return initialHealth;
	}

	public int getMaxMana() {
		return maxMana;
	}

	public int[] getInitialDeck() {
		return initialDeck;
	}

	public int getMaxCardInHand() {
		return maxCardInHand;
	}

	public static SettingsBuilder builder() {
		return new SettingsBuilder();
	}

	public static class SettingsBuilder {
		private int initialHealth = 30;
		private int maxMana = 10;
		private int[] initialDeck = {};
		private int maxCardInHand = 5;

		public SettingsBuilder initialHealth(int initialHealth) {
			this.initialHealth = initialHealth;
			return this;
		}

		public SettingsBuilder maxMana(int maxMana) {
			this.maxMana = maxMana;
			return this;
		}

		public SettingsBuilder initialDeck(int[] initialDeck) {
			this.initialDeck = initialDeck;
			return this;
		}

		public SettingsBuilder maxCardInHand(int maxCardInHand) {
			this.maxCardInHand = maxCardInHand;
			return this;
		}

		public Settings build() {
			return new Settings(this);
		}

	}

}
