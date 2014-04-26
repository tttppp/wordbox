package com.github.tttppp.wordbox;

import java.util.List;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.widget.TextView.BufferType;
import android.widget.Toast;

import com.github.tttppp.wordbox.letters.GridGenerator;
import com.github.tttppp.wordbox.letters.GridGeneratorFactory;
import com.github.tttppp.wordbox.letters.GridGeneratorType;
import com.github.tttppp.wordbox.persistence.PersistanceName;
import com.github.tttppp.wordbox.ui.component.FontFitTextView;

public class WordBoxActivity extends Activity {
	private SharedPreferences preferences;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		preferences = PreferenceManager
		    .getDefaultSharedPreferences(getApplicationContext());

		int gridSize = preferences.getInt(PersistanceName.GRID_SIZE.name(), 0);

		if (gridSize == 0) {
			gridSize = 4;
			drawNewGrid(gridSize);
		} else {
			makeGrid(gridSize);
		}
	}

	/**
	 * Generate a new grid of the given size.
	 * 
	 * @param gridSize
	 */
	private void drawNewGrid(int gridSize) {
		String typeName = preferences.getString(PersistanceName.GENERATION_TYPE
		    .name(), GridGeneratorType.NEW_DISTRIBUTION.name());
		GridGeneratorType type = GridGeneratorType.valueOf(typeName);
		GridGenerator gridGenerator = GridGeneratorFactory
		    .getGridGenerator(type);
		List<List<String>> letters = gridGenerator.generate(gridSize);

		Editor editor = preferences.edit();
		editor.putInt(PersistanceName.GRID_SIZE.name(), gridSize);

		for (int i = 0; i < gridSize; i++) {
			for (int j = 0; j < gridSize; j++) {
				String name = PersistanceName.GRID_LETTERS.name(i * gridSize
				    + j);
				editor.putString(name, letters.get(i).get(j));
			}
		}

		editor.commit();

		makeGrid(gridSize);
	}

	/**
	 * Re-draw the grid.
	 * 
	 * @param gridSize
	 */
	private void makeGrid(int gridSize) {
		LinearLayout wordGrid = (LinearLayout) findViewById(R.id.wordgrid);
		wordGrid.removeAllViews();
		for (int i = 0; i < gridSize; i++) {
			LinearLayout row = new LinearLayout(this);
			LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT,
			                                   LayoutParams.MATCH_PARENT, 1f);
			lp.setMargins(0, 0, 0, 0);
			row.setLayoutParams(lp);
			row.setGravity(Gravity.TOP);
			row.setPadding(1, 1, 1, 1);
			LayoutParams cellLayout = new LayoutParams(
			                                           LayoutParams.MATCH_PARENT,
			                                           LayoutParams.MATCH_PARENT,
			                                           1f);
			cellLayout.setMargins(0, 0, 0, 0);
			for (int j = 0; j < gridSize; j++) {
				TextView cell = new FontFitTextView(this);
				cell.setLayoutParams(cellLayout);
				cell.setPadding(1, 1, 1, 1);
				cell.setGravity(Gravity.CENTER);
				String name = PersistanceName.GRID_LETTERS.name(i * gridSize
				    + j);
				String cellStr = preferences.getString(name, "");
				cell.setText(cellStr, BufferType.SPANNABLE);
				cell.setIncludeFontPadding(false);
				row.addView(cell);
			}
			wordGrid.addView(row);
		}
	}

	/** When the menu button is clicked. */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater menuInflater = getMenuInflater();
		menuInflater.inflate(R.layout.main_menu, menu);
		return true;
	}

	/** Event handling for menu items. */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_new:
			int gridSize = preferences.getInt(PersistanceName.GRID_SIZE.name(),
			                                  0);
			drawNewGrid(gridSize);
			return true;

		case R.id.menu_toggle_timer:
			Editor editor = preferences.edit();
			boolean previousValue = preferences
			    .getBoolean(PersistanceName.TIMER_VISIBLE.name(), false);
			editor.putBoolean(PersistanceName.TIMER_VISIBLE.name(),
			                  !previousValue);
			editor.commit();
			if (previousValue) {
				Toast.makeText(WordBoxActivity.this, "Timer is now off",
				               Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(WordBoxActivity.this, "Timer is now on",
				               Toast.LENGTH_SHORT).show();
			}
			return true;

		case R.id.menu_solve:
			Toast.makeText(WordBoxActivity.this, "Solve is Selected",
			               Toast.LENGTH_SHORT).show();
			return true;

		case R.id.menu_settings:
			Toast.makeText(WordBoxActivity.this, "Settings is Selected",
			               Toast.LENGTH_SHORT).show();
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}
	}
}
