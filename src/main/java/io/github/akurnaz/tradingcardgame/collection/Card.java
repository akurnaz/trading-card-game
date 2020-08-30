package io.github.akurnaz.tradingcardgame.collection;

public class Card {
	private int mana;

	public Card(int mana) {
		this.mana = mana;
	}

	public int getMana() {
		return mana;
	}

	@Override
	public String toString() {
		return "" + mana;
	}

}
