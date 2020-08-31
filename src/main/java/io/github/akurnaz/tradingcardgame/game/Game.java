package io.github.akurnaz.tradingcardgame.game;

import java.util.function.Consumer;
import java.util.function.IntSupplier;

import io.github.akurnaz.tradingcardgame.console.Console;
import io.github.akurnaz.tradingcardgame.console.ConsoleManager;
import io.github.akurnaz.tradingcardgame.player.ComputerPlayer;
import io.github.akurnaz.tradingcardgame.player.HumanPlayer;
import io.github.akurnaz.tradingcardgame.player.Playable;

public class Game {
	private ConsoleManager consoleManager;
	private GameStatus gameStatus;
	private Console winner;

	public Game(ConsoleManager consoleManager) {
		this.consoleManager = consoleManager;
		this.gameStatus = GameStatus.READY;
		this.winner = null;
	}

	public static Game humanVsComputer(Settings settings, String playerName, IntSupplier input,
			Consumer<String> output) {
		Playable playable1 = new ComputerPlayer();
		Playable playable2 = new HumanPlayer(playerName, input, output);

		Console consoleUp = Console.of(playable1, settings);
		Console consoleDown = Console.of(playable2, settings);

		return new Game(new ConsoleManager(consoleUp, consoleDown));
	}

	public static Game humanVsHuman(Settings settings, String player1Name, String player2Name, IntSupplier input,
			Consumer<String> output) {
		Playable playable1 = new HumanPlayer(player1Name, input, output);
		Playable playable2 = new HumanPlayer(player2Name, input, output);

		Console consoleUp = Console.of(playable1, settings);
		Console consoleDown = Console.of(playable2, settings);

		return new Game(new ConsoleManager(consoleUp, consoleDown));
	}

	public static Game computerVsComputer(Settings settings, Consumer<String> output) {
		Playable playable1 = new ComputerPlayer();
		Playable playable2 = new ComputerPlayer(output);

		Console consoleUp = Console.of(playable1, settings);
		Console consoleDown = Console.of(playable2, settings);

		return new Game(new ConsoleManager(consoleUp, consoleDown));
	}

	public void start() {
		synchronized (this) {
			if (gameStatus != GameStatus.READY) {
				throw new GameCouldNotStartException("Game is already started.");
			}
			gameStatus = GameStatus.PLAYING;
		}
		consoleManager.init();
		while (gameStatus == GameStatus.PLAYING) {
			try {
				consoleManager.nextTurn();
			} catch (GameOverException e) {
				this.winner = consoleManager.getWinner(e.getLoser());
				gameStatus = GameStatus.OVER;
			}
		}
	}

	@Override
	public String toString() {
		return "\nGame status: " + gameStatus.toString() + "\nWinner: "
				+ (winner != null ? winner.getPlayable().getName() : "");
	}

}
