package io.github.akurnaz.tradingcardgame.game;

public class GameCouldNotStartException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public GameCouldNotStartException(String message) {
		super(message);
	}

	public GameCouldNotStartException(String message, Throwable cause) {
		super(message, cause);
	}

}
