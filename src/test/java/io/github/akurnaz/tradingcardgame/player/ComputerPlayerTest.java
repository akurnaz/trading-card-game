package io.github.akurnaz.tradingcardgame.player;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import io.github.akurnaz.tradingcardgame.collection.Card;
import io.github.akurnaz.tradingcardgame.collection.hand.IncorrectRangeException;
import io.github.akurnaz.tradingcardgame.console.ActiveConsoleCommand;
import io.github.akurnaz.tradingcardgame.console.InsufficientManaException;

@ExtendWith(MockitoExtension.class)
class ComputerPlayerTest {

	@Mock
	private ActiveConsoleCommand activeConsoleCommand;

	private Playable playable;

	private List<Card> cardsInHand;

	@BeforeEach
	void beforeEach() {
		playable = new ComputerPlayer();
		cardsInHand = Arrays.asList(new Card(8), new Card(3), new Card(6), new Card(4), new Card(2));
	}

	@Test
	void play_given5Mama_thenNotThrowExceptionAndInvokeOneTime() {
		// given
		int mana = 5;
		int expectedIndex = 3;

		// then
		assertDoesNotThrow(() -> playable.play(activeConsoleCommand, mana, cardsInHand));
		assertDoesNotThrow(() -> Mockito.verify(activeConsoleCommand).insertToTable(eq(expectedIndex)));
	}

	@Test
	void play_given1Mana_thenNotThrowExceptionAndInvokeNever()
			throws InsufficientManaException, IncorrectRangeException {
		// given
		int mana = 1;

		// then
		assertDoesNotThrow(() -> playable.play(activeConsoleCommand, mana, cardsInHand));
		Mockito.verify(activeConsoleCommand, Mockito.never()).insertToTable(anyInt());
	}

	@Test
	void play_given5Mama_thenThrowException() throws InsufficientManaException, IncorrectRangeException {
		// given
		int mana = 5;
		Mockito.doThrow(InsufficientManaException.class).when(activeConsoleCommand).insertToTable(anyInt());
		// then
		assertThrows(RuntimeException.class, () -> playable.play(activeConsoleCommand, mana, cardsInHand));
	}

	@Test
	void getName_givenName_thenReturnEqualsName() {
		// given
		String expectedName = "Computer";

		// then
		assertEquals(expectedName, playable.getName());
	}
}
