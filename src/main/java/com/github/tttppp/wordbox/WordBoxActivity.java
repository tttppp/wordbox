package com.github.tttppp.wordbox;

import java.util.Arrays;
import java.util.Random;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;
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

import com.github.tttppp.wordbox.persistence.PersistanceName;
import com.github.tttppp.wordbox.ui.component.FontFitTextView;

public class WordBoxActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		LinearLayout wordGrid = (LinearLayout) findViewById(R.id.wordgrid);
		for (int i = 0; i < 4; i++) {
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
			for (int j = 0; j < 4; j++) {
				TextView cell = new FontFitTextView(this);
				cell.setLayoutParams(cellLayout);
				cell.setPadding(1, 1, 1, 1);
				cell.setGravity(Gravity.CENTER);
				cell.setText(randomLetter(), BufferType.SPANNABLE);
				cell.setIncludeFontPadding(false);
				row.addView(cell);
			}
			wordGrid.addView(row);
		}
	}

	private String randomLetter() {
		Random rand = new Random();
		int i = rand.nextInt(26);
		return "ABCDEFGHIJKLMNOPQRSTUVWXYZ".substring(i, i + 1);
	}

	private int randomColor() {
		Random rand = new Random();
		int i = rand.nextInt(3);
		return Arrays.asList(Color.RED, Color.CYAN, Color.YELLOW).get(i);
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
			Toast.makeText(WordBoxActivity.this, "New is Selected",
			               Toast.LENGTH_SHORT).show();
			return true;

		case R.id.menu_toggle_timer:
			SharedPreferences preferences = PreferenceManager
			    .getDefaultSharedPreferences(getApplicationContext());
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
