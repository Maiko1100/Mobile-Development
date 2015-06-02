package com.testapplication.wfcmainpage.models;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.View;

import com.testapplication.wfcmainpage.R;

public class CustomView extends View {

	public Bitmap mybitmap;
	Context context;
	Paint paint;

	public CustomView(Context c) {
		super(c);
		context = c;
		paint = new Paint();
		Typeface typeface = Typeface.createFromAsset(context.getAssets(), "fonts/SinkinSans.otf");
		paint.setTypeface(typeface);
	}

	@Override
	public void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		mybitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bluedot);
		canvas.drawBitmap(mybitmap, 0, 0, null);
	}
}
