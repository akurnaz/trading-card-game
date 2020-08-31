package io.github.akurnaz.tradingcardgame.console;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import io.github.akurnaz.tradingcardgame.collection.Card;
import io.github.akurnaz.tradingcardgame.collection.deck.Deck;
import io.github.akurnaz.tradingcardgame.collection.deck.EmptyDeckException;
import io.github.akurnaz.tradingcardgame.collection.deck.ShuffleDeck;
import io.github.akurnaz.tradingcardgame.collection.hand.Hand;
import io.github.akurnaz.tradingcardgame.collection.hand.IncorrectRangeException;
import io.github.akurnaz.tradingcardgame.collection.hand.SimpleHand;
import io.github.akurnaz.tradingcardgame.collection.table.SimpleTable;
import io.github.akurnaz.tradingcardgame.collection.table.Table;
import io.github.akurnaz.tradingcardgame.game.GameOverException;
import io.github.akurnaz.tradingcardgame.game.Settings;
import io.github.akurnaz.tradingcardgame.player.Playable;

public class Console implements ActiveConsoleCommand, OpponentConsoleCommand {
	private Playable playable;
	private Settings settings;
	private int health;
	private int mana;
	private Deck deck;
	private Hand hand;
	private Table table;
	private OpponentConsoleCommand opponentConsoleCommand;

	public Console(Playable playable, Deck deck, Hand hand, Table table, Settings settings) {
		this.playable = playable;
		this.settings = settings;
		this.health = settings.getInitialHealth();
		this.mana = settings.getInitialMana();
		this.deck = deck;
		this.hand = hand;
		this.table = table;
	}

	public static Console of(Playable playable, Settings settings) {
		return new Console(playable, new ShuffleDeck(settings.getInitialDeck()),
				new SimpleHand(settings.getMaxCardInHand()), new SimpleTable(), settings);
	}

	public void setOpponentConsoleCommand(OpponentConsoleCommand opponentConsoleCommand) {
		this.opponentConsoleCommand = opponentConsoleCommand;
	}

	public Playable getPlayable() {
		return playable;
	}

	public void init() throws EmptyDeckException {
		for (int i = 0; i < 3; i++) {
			hand.insert(deck.draw());
		}
	}

	public void play(int roundNumber) {
		mana = Math.min(settings.getMaxMana(), roundNumber);
		try {
			hand.insert(deck.draw());
		} catch (EmptyDeckException e) {
			damage(1);
		}

		playable.play(this, roundNumber, hand.unmodifiableList());
	}

	@Override
	public void insertToTable(int index) throws InsufficientManaException, IncorrectRangeException {
		Card card = hand.draw(index);
		int remainingMana = mana - card.getMana();
		if (remainingMana < 0) {
			hand.insert(card);
			throw new InsufficientManaException("Insufficient mana.");
		}
		mana = remainingMana;
		table.insert(card);

		opponentConsoleCommand.damage(card.getMana());
	}

	@Override
	public void damage(int amount) {
		health -= amount;
		if (health <= 0) {
			throw new GameOverException(this);
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(opponentConsoleCommand.toStringOpponent());
		sb.append("\n|-----------------------------------------------------");
		sb.append("\n|\t\tTable" + table.toString());
		sb.append("\n|\tHealth: " + health + "\tMana: " + mana);
		sb.append("\n|\t\tHand" + hand.toString());
		sb.append("\n|\t\t" + playable.getName() + "\tDeck["
				+ Stream.generate(() -> "*").limit(deck.size()).collect(Collectors.joining("")) + "]");
		sb.append("\n|-----------------------------------------------------");
		return sb.toString();
	}

	@Override
	public String toStringOpponent() {
		StringBuilder sb = new StringBuilder();
		sb.append("\n|-----------------------------------------------------");
		sb.append("\n|\t\t" + playable.getName() + "\tDeck[-]");
		sb.append("\n|\t\tHand[-]");
		sb.append("\n|\tHealth: " + health + "\tMana: " + mana);
		sb.append("\n|\t\tTable" + table.toString());
		return sb.toString();
	}

}
