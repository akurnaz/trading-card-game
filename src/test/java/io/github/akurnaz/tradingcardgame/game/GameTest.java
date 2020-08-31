package io.github.akurnaz.tradingcardgame.game;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import io.github.akurnaz.tradingcardgame.console.ConsoleManager;

@ExtendWith(MockitoExtension.class)
class GameTest {

	@Mock
	private ConsoleManager consoleManager;

	private Game game;

	@BeforeEach
	void beforeEach() {
		game = new Game(consoleManager);
	}

	@Test
	void start_given_thenSuccessed() {
		// given
		Mockito.doNothing().doThrow(GameOverException.class).when(consoleManager).nextTurn();

		// then
		game.start();
		Mockito.verify(consoleManager).init();
		Mockito.verify(consoleManager, Mockito.atLeastOnce()).nextTurn();
		Mockito.verify(consoleManager).getWinner(Mockito.any());
	}

	@Test
	void start_givenAlreadyStarted_thenThrowGameCouldNotStartException() {
		// given
		Mockito.doThrow(GameOverException.class).when(consoleManager).nextTurn();
		game.start();
		// then
		Assertions.assertThrows(GameCouldNotStartException.class, () -> game.start());
	}

	@Test
	void toString_given_thenReturnNotNull() {
		// given

		// then
		Assertions.assertNotNull(game.toString());
	}

}
