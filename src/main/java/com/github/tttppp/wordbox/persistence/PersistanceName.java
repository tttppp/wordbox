package com.github.tttppp.wordbox.persistence;

public enum PersistanceName {
	/** Whether the timer is visible or not. */
	TIMER_VISIBLE,
	/** A list of lists of strings. Each string is a box value. */
	GRID_LETTERS,
	/** Width/Height of the grid. */
	GRID_SIZE;

	public String name(int i) {
		return this.name() + String.valueOf(i);
	}
}
