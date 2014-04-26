package com.github.tttppp.wordbox.letters;

public class GridGeneratorFactory {
	public static GridGenerator getGridGenerator(GridGeneratorType type) {
		switch (type) {
		case ALPHABET:
			return new AlphabetGenerator("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
		case FREQUENCY:
			return new AlphabetGenerator(
			                             "EEEEEEEEEEEETTTTTTTTTAAAAAAAAOOOOOOOOIIIIIIINNNNNNNSSSSSSRRRRRRHHHHHHDDDDLLLLUUUCCCMMMFFYYWWGGPPBVKABCDEFGHIJKLMNOPQRSTUVWXYZ");
		case OLD_DISTRIBUTION:
			return new DiceGridGenerator(LetterDistributions.oldSet);
		case NEW_DISTRIBUTION:
			return new DiceGridGenerator(LetterDistributions.newSet);
		case FIVE_BY_FIVE:
			return new DiceGridGenerator(LetterDistributions.fiveByFive);
		default:
			return null;
		}
	}
}
