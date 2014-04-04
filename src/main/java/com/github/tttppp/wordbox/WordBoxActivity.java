package com.github.tttppp.wordbox;

import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

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
			                                   LayoutParams.WRAP_CONTENT, 1f);
			row.setLayoutParams(lp);
			row.setGravity(Gravity.CENTER);
			LayoutParams cellLayout = new LayoutParams(
			                                           LayoutParams.WRAP_CONTENT,
			                                           LayoutParams.MATCH_PARENT,
			                                           1f);
			for (int j = 0; j < 4; j++) {
				TextView cell = new FontFitTextView(this);
				cell.setLayoutParams(cellLayout);
				cell.setGravity(Gravity.CENTER);
				cell.setText(randomLetter());
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
}
