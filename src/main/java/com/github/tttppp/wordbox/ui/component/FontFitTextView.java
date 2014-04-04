package com.github.tttppp.wordbox.ui.component;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.TextView;

/**
 * Auto resizing TextView (as found on StackOverflow).
 */
public class FontFitTextView extends TextView {
	/** Object used to test the bounds of the text. */
	private Paint testPaint;

	public FontFitTextView(Context context) {
		super(context);
		initialise();
	}

	public FontFitTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initialise();
	}

	private void initialise() {
		testPaint = new Paint();
		testPaint.set(this.getPaint());
		// max size defaults to the initially specified text size unless it is
		// too small
	}

	/**
	 * Re size the font so the specified text fits in the text box assuming the
	 * text box is the specified width and height.
	 */
	private void refitText(String text, int textWidth, int textHeight) {
		if (textWidth <= 0)
			return;
		int targetWidth = textWidth - this.getPaddingLeft()
		    - this.getPaddingRight();
		int targetHeight = textHeight - this.getPaddingTop()
		    - this.getPaddingBottom();
		float hi = 100;
		float lo = 2;
		final float threshold = 0.5f; // How close we have to be

		testPaint.set(this.getPaint());

		while ((hi - lo) > threshold) {
			float size = (hi + lo) / 2;
			testPaint.setTextSize(size);
			Rect bounds = new Rect();
			testPaint.getTextBounds(text, 0, text.length(), bounds);
			if (bounds.width() >= targetWidth
			    || Math.abs(bounds.height()) >= Math.abs(targetHeight)) {
				hi = size; // too big
			} else {
				lo = size; // too small
			}
		}
		// Use lo so that we undershoot rather than overshoot
		this.setTextSize(TypedValue.COMPLEX_UNIT_PX, lo);
		this.scrollTo(0, Math.round(lo / 6));
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		int parentWidth = MeasureSpec.getSize(widthMeasureSpec);
		int parentHeight = MeasureSpec.getSize(heightMeasureSpec);
		int height = getMeasuredHeight();
		refitText(this.getText().toString(), parentWidth, parentHeight);
		this.setMeasuredDimension(parentWidth, height);
	}

	@Override
	protected void onTextChanged(final CharSequence text, final int start,
	                             final int before, final int after) {
		refitText(text.toString(), this.getWidth(), this.getHeight());
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		if (w != oldw) {
			refitText(this.getText().toString(), w, h);
		}
	}
}