package com.github.tttppp.wordbox;

import java.util.Arrays;
import java.util.Random;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.widget.TextView.BufferType;

import com.github.tttppp.wordbox.ui.component.FontFitTextView;

public class WordBoxActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		LinearLayout wordGrid = (LinearLayout) findViewById(R.id.wordgrid);
		for (int i = 0; i < 7; i++) {
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
}
