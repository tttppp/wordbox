package com.github.tttppp.wordbox.letters;

public class GridGeneratorFactory {
	public static GridGenerator getGridGenerator(GridGeneratorType type) {
		switch (type) {
		case ALPHABET:
			return new AlphabetGenerator("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
		case OLD_DISTRIBUTION:
			return new DiceGridGenerator(LetterDistributions.oldSet);
		case NEW_DISTRIBUTION:
			return new DiceGridGenerator(LetterDistributions.newSet);
		default:
			return null;
		}
	}
}
