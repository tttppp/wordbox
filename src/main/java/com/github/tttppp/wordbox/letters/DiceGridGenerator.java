package com.github.tttppp.wordbox.letters;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import edu.emory.mathcs.backport.java.util.Collections;

public class DiceGridGenerator implements GridGenerator {
	List<List<String>> dice;

	public DiceGridGenerator(List<List<String>> dice) {
		this.dice = dice;
	}

	public List<List<String>> generate(int gridSize) {
		Random rand = new Random();

		List<List<String>> diceToUse = new ArrayList<List<String>>(dice);

		while (diceToUse.size() < gridSize * gridSize) {
			int i = rand.nextInt(dice.size());
			diceToUse.add(dice.get(i));
		}
		while (diceToUse.size() > gridSize * gridSize) {
			diceToUse.remove(0);
		}

		Collections.shuffle(diceToUse);

		List<List<String>> grid = new ArrayList<List<String>>();
		for (int i = 0; i < gridSize; i++) {
			grid.add(new ArrayList<String>());
			for (int j = 0; j < gridSize; j++) {
				List<String> die = diceToUse.get(i * gridSize + j);
				grid.get(i).add(die.get(rand.nextInt(die.size())));
			}
		}
		return grid;
	}

}
