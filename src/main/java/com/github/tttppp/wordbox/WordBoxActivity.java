package com.github.tttppp.wordbox;

import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class WordBoxActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		TableLayout tableLayout = (TableLayout) findViewById(R.id.wordgrid);
		for (int i = 0; i < 4; i++) {
			TableRow row = new TableRow(this);
			TableRow.LayoutParams lp = new TableRow.LayoutParams(
			                                                     TableRow.LayoutParams.WRAP_CONTENT);
			for (int j = 0; j < 4; j++) {
				TextView box = new TextView(this);
				box.setText(randomLetter());
				row.addView(box);
			}
			row.setLayoutParams(lp);
			tableLayout.addView(row);
		}
	}

	private String randomLetter() {
		Random rand = new Random();
		int i = rand.nextInt(26);
		return "ABCDEFGHIJKLMNOPQRSTUVWXYZ".substring(i, i + 1);
	}
}
