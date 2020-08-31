package io.github.akurnaz.tradingcardgame.console;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import io.github.akurnaz.tradingcardgame.collection.deck.EmptyDeckException;
import io.github.akurnaz.tradingcardgame.game.GameCouldNotStartException;

@ExtendWith(MockitoExtension.class)
class ConsoleManagerTest {

	@Mock
	private Console activeConsole;

	@Mock
	private Console opponentConsole;

	private ConsoleManager consoleManager;

	@BeforeEach
	void beforeEach() {
		consoleManager = new ConsoleManager(activeConsole, opponentConsole);
	}

	@Test
	void init_given_thenSuccessed() throws EmptyDeckException {
		// given

		// then
		Assertions.assertDoesNotThrow(() -> consoleManager.init());
		Mockito.verify(activeConsole).init();
		Mockito.verify(opponentConsole).init();
	}

	@Test
	void init_given_thenThrowGameCouldNotStartException() throws EmptyDeckException {
		// given
		Mockito.doThrow(EmptyDeckException.class).when(activeConsole).init();

		// then
		Assertions.assertThrows(GameCouldNotStartException.class, () -> consoleManager.init());
	}

	@Test
	void nextTurn_given_thenSuccessed() {
		// given

		// then
		consoleManager.nextTurn();
		Mockito.verify(opponentConsole).play(Mockito.eq(1));
	}

	@Test
	void getWinner_given_then() {
		// given

		// then
		Assertions.assertEquals(opponentConsole, consoleManager.getWinner(activeConsole));
		Assertions.assertEquals(activeConsole, consoleManager.getWinner(opponentConsole));

	}
}
