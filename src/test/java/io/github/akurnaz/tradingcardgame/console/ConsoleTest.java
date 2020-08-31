package io.github.akurnaz.tradingcardgame.console;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import io.github.akurnaz.tradingcardgame.collection.Card;
import io.github.akurnaz.tradingcardgame.collection.deck.Deck;
import io.github.akurnaz.tradingcardgame.collection.deck.EmptyDeckException;
import io.github.akurnaz.tradingcardgame.collection.hand.Hand;
import io.github.akurnaz.tradingcardgame.collection.hand.IncorrectRangeException;
import io.github.akurnaz.tradingcardgame.collection.table.Table;
import io.github.akurnaz.tradingcardgame.game.GameOverException;
import io.github.akurnaz.tradingcardgame.game.Settings;
import io.github.akurnaz.tradingcardgame.player.Playable;

@ExtendWith(MockitoExtension.class)
class ConsoleTest {

	@Mock
	private Playable playable;

	@Mock
	private Deck deck;

	@Mock
	private Hand hand;

	@Mock
	private Table table;

	@Mock
	private Settings settings;

	@Mock
	private OpponentConsoleCommand opponentConsoleCommand;

	private Console console;

	@BeforeEach
	void beforeEach() {
		Mockito.when(settings.getInitialHealth()).thenReturn(30);
		Mockito.when(settings.getInitialMana()).thenReturn(8);

		console = new Console(playable, deck, hand, table, settings);
		console.setOpponentConsoleCommand(opponentConsoleCommand);
	}

	@Test
	void of_given_thenReturnNotNull() {
		// given
		Mockito.when(settings.getInitialDeck()).thenReturn(new int[] {});

		// then
		Assertions.assertNotNull(Console.of(playable, settings));
	}

	@Test
	void init_given_thenInvoke3Times() throws EmptyDeckException {
		// given
		Card card = new Card(5);
		Mockito.when(deck.draw()).thenReturn(card);

		// then
		Assertions.assertDoesNotThrow(() -> console.init());
		Assertions.assertDoesNotThrow(() -> Mockito.verify(deck, Mockito.times(3)).draw());
		Mockito.verify(hand, Mockito.times(3)).insert(Mockito.eq(card));
	}

	@Test
	void play_given4RoundNumber_thenSuccessed() throws EmptyDeckException {
		// given
		int roundNumber = 4;
		Card card = new Card(5);
		Mockito.when(deck.draw()).thenReturn(card);

		// then
		console.play(roundNumber);
		Assertions.assertDoesNotThrow(() -> Mockito.verify(deck, Mockito.only()).draw());
		Mockito.verify(hand).insert(Mockito.eq(card));
		Mockito.verify(playable, Mockito.only()).play(Mockito.eq(console), Mockito.eq(roundNumber), Mockito.anyList());
	}

	@Test
	void play_given4RoundNumber_thenDamage() throws EmptyDeckException {
		// given
		console = Mockito.spy(console);
		int roundNumber = 4;
		Mockito.when(deck.draw()).thenThrow(new EmptyDeckException(""));

		// then
		console.play(roundNumber);
		Mockito.verify(console).damage(Mockito.eq(1));
		Mockito.verify(playable, Mockito.only()).play(Mockito.eq(console), Mockito.eq(roundNumber), Mockito.anyList());
	}

	@Test
	void insertToTable_given3Index_thenSuccessed() throws IncorrectRangeException {
		// given
		int index = 3;
		Card card = new Card(5);
		Mockito.when(hand.draw(index)).thenReturn(card);

		// then
		Assertions.assertDoesNotThrow(() -> console.insertToTable(index));
		Mockito.verify(hand).draw(Mockito.eq(index));
		Mockito.verify(table).insert(Mockito.eq(card));
		Mockito.verify(opponentConsoleCommand).damage(Mockito.eq(card.getMana()));
	}

	@Test
	void insertToTable_given3Index_thenThrowInsufficientManaException() throws IncorrectRangeException {
		// given
		int index = 3;
		Card card = new Card(999);
		Mockito.when(hand.draw(index)).thenReturn(card);

		// then
		Assertions.assertThrows(InsufficientManaException.class, () -> console.insertToTable(index));
		Mockito.verify(hand).insert(Mockito.eq(card));
	}

	@Test
	void damage_given1Amount_thenSuccessed() {
		// given
		int amount = 1;

		// then
		Assertions.assertDoesNotThrow(() -> console.damage(amount));
	}

	@Test
	void damage_given999Amount_thenThrowGameOverException() {
		// given
		int amount = 999;

		// then
		Assertions.assertThrows(GameOverException.class, () -> console.damage(amount));
	}

	@Test
	void toString_given_thenReturnNotNull() {
		// given
		Mockito.when(opponentConsoleCommand.toStringOpponent()).thenReturn("");

		// then
		Assertions.assertNotNull(console.toString());
	}

	@Test
	void toStringOpponent_given_thenReturnNotNull() {
		// given

		// then
		Assertions.assertNotNull(console.toStringOpponent());
	}
}
