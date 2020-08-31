package io.github.akurnaz.tradingcardgame.console;

import io.github.akurnaz.tradingcardgame.collection.hand.IncorrectRangeException;

public interface ActiveConsoleCommand {

	void insertToTable(int index) throws InsufficientManaException, IncorrectRangeException;

}
