package io.github.akurnaz.tradingcardgame;

import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.IntSupplier;
import java.util.logging.Level;
import java.util.logging.Logger;

import io.github.akurnaz.tradingcardgame.game.Game;
import io.github.akurnaz.tradingcardgame.game.Settings;

public class TradingCardGameApp {
	private static Logger logger = Logger.getLogger(TradingCardGameApp.class.getName());

	public static void main(String[] args) {
		Settings settings = Settings.builder()
				.initialHealth(30)
				.maxMana(10)
				.initialDeck(new int[] { 0, 0, 1, 1, 2, 2, 2, 3, 3, 3, 3, 4, 4, 4, 5, 5, 6, 6, 7, 8 })
				.maxCardInHand(5)
				.build();
		
		IntSupplier input = () -> {
			Scanner sc = new Scanner(System.in);
			return sc.nextInt() - 1;
		};

		Consumer<String> output = message -> logger.log(Level.INFO, message);

		Game game = Game.humanVsComputer(settings, "alikurnaz", input, output);
		game.start();
		output.accept(game.toString());
	}

}
