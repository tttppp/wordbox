package com.github.tttppp.wordbox.letters;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AlphabetGenerator implements GridGenerator {
	private String alphabet;

	public AlphabetGenerator(String alphabet) {
		this.alphabet = alphabet;
	}

	public List<List<String>> generate(int gridSize) {
		List<List<String>> grid = new ArrayList<List<String>>();

		for (int i = 0; i < gridSize; i++) {
			grid.add(new ArrayList<String>());
			for (int j = 0; j < gridSize; j++) {
				grid.get(i).add(randomLetter());
			}
		}

		return grid;
	}

	private String randomLetter() {
		Random rand = new Random();
		int i = rand.nextInt(alphabet.length());
		return alphabet.substring(i, i + 1);
	}
}
