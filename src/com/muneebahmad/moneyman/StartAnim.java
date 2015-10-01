package com.muneebahmad.moneyman;

/*
 * @author:MuneebAhmad
 * */

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

public class StartAnim extends View {
	Bitmap bg, black;
	int y, b, d, f, h, j, l = 0;
	int x = -50;
	int a = 300;
	int k = 400;
	int c = 50;
	int e = 100;
	int g = 150;
	int i = 200;
	Paint paint;

	// constructor arg = context
	public StartAnim(Context context) {
		super(context);
		black = BitmapFactory.decodeResource(getResources(), R.drawable.dollar);
		bg = BitmapFactory.decodeResource(getResources(), R.drawable.bg);
		paint = new Paint();
	}// end constructor

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		if (x < canvas.getWidth()) {
			x -= 80;
		}
		if (c < canvas.getWidth()) {
			c -= 65;
		}
		if (e < canvas.getWidth()) {
			e -= 50;
		}
		if (g < canvas.getWidth()) {
			g -= 35;
		}
		if (i < canvas.getWidth()) {
			i -= 20;
		}
		if(k < canvas.getWidth()){
			k -= 15;
		}
		if (a > 1) {
			a -= 100;
		}
		
		canvas.drawBitmap(bg, a, b, paint);
		canvas.drawBitmap(black, k, l, paint);
		canvas.drawBitmap(black, i, j, paint);
		canvas.drawBitmap(black, g, h, paint);
		canvas.drawBitmap(black, e, f, paint);
		canvas.drawBitmap(black, c, d, paint);
		canvas.drawBitmap(black, x, y, paint);
		invalidate();
	}
}// end class
