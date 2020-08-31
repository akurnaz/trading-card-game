package io.github.akurnaz.tradingcardgame.console;

public class InsufficientManaException extends Exception {
	private static final long serialVersionUID = 1L;

	public InsufficientManaException(String message) {
		super(message);
	}
}
