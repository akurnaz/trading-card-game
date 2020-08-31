package io.github.akurnaz.tradingcardgame.console;

import io.github.akurnaz.tradingcardgame.collection.deck.EmptyDeckException;
import io.github.akurnaz.tradingcardgame.game.GameCouldNotStartException;

public class ConsoleManager {
	private Console activeConsole;
	private Console opponentConsole;
	private int turnNumber;

	public ConsoleManager(Console activeConsole, Console opponentConsole) {
		this.activeConsole = activeConsole;
		this.opponentConsole = opponentConsole;
		this.turnNumber = 0;
		this.activeConsole.setOpponentConsoleCommand(opponentConsole);
		this.opponentConsole.setOpponentConsoleCommand(activeConsole);
	}

	public void init() {
		try {
			this.activeConsole.init();
			this.opponentConsole.init();
		} catch (EmptyDeckException e) {
			throw new GameCouldNotStartException("Game initialization failed.", e);
		}
	}

	public void nextTurn() {
		turnNumber++;
		swap();
		int roundNumber = (turnNumber + 1) / 2;
		activeConsole.play(roundNumber);
	}

	private void swap() {
		Console temp = activeConsole;
		activeConsole = opponentConsole;
		opponentConsole = temp;
	}

	public Console getWinner(Console loser) {
		return loser == activeConsole ? opponentConsole : activeConsole;
	}

}
