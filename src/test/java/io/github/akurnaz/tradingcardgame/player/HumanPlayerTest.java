package io.github.akurnaz.tradingcardgame.player;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.IntSupplier;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import io.github.akurnaz.tradingcardgame.collection.Card;
import io.github.akurnaz.tradingcardgame.collection.hand.IncorrectRangeException;
import io.github.akurnaz.tradingcardgame.console.ActiveConsoleCommand;
import io.github.akurnaz.tradingcardgame.console.InsufficientManaException;

@ExtendWith(MockitoExtension.class)
class HumanPlayerTest {

	@Mock
	private ActiveConsoleCommand activeConsoleCommand;

	@Mock
	private IntSupplier input;

	@Mock
	private Consumer<String> output;

	private Playable playable;

	private List<Card> cardsInHand;

	private String name;

	private int mana;

	@BeforeEach
	void beforeEach() {
		name = "Human";
		playable = new HumanPlayer(name, input, output);
		cardsInHand = Arrays.asList(new Card(8), new Card(3), new Card(6), new Card(4), new Card(2));
		mana = 5;
	}

	@Test
	void play_given3AndExitIndex_thenInvokeOneTime() {
		// given
		int expectedIndex = 3;
		when(input.getAsInt()).thenReturn(expectedIndex).thenReturn(-1);

		// then
		playable.play(activeConsoleCommand, mana, cardsInHand);
		assertDoesNotThrow(() -> verify(activeConsoleCommand, times(1)).insertToTable(eq(expectedIndex)));
	}

	@Test
	void play_givenExitIndex_thenInvokeNever() throws InsufficientManaException, IncorrectRangeException {
		// given
		when(input.getAsInt()).thenReturn(-1);

		// then
		playable.play(activeConsoleCommand, mana, cardsInHand);
		verify(activeConsoleCommand, never()).insertToTable(anyInt());
	}

	@Test
	void play_given3AndExitIndex_thenInvoke3Times() throws InsufficientManaException, IncorrectRangeException {
		// given
		doThrow(new InsufficientManaException("")).when(activeConsoleCommand).insertToTable(anyInt());
		when(input.getAsInt()).thenReturn(3).thenReturn(-1);

		// then
		playable.play(activeConsoleCommand, mana, cardsInHand);
		verify(output, times(3)).accept(any());
	}

	@Test
	void getName_givenName_thenReturnEqualsName() {
		// given
		String expectedName = name;

		// then
		assertEquals(expectedName, playable.getName());
	}
}
