package io.github.akurnaz.tradingcardgame.game;

import io.github.akurnaz.tradingcardgame.console.Console;

public class GameOverException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private final Console loser;

	public GameOverException(Console loser) {
		this.loser = loser;
	}

	public Console getLoser() {
		return loser;
	}

}
