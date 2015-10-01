package com.muneebahmad.java.chart;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class PieView extends View {

	private Paint p;
	private int startX;
	private int startY;
	private int radius;
	private ArrayList<Integer> colors;
	private ArrayList<Integer> values;
	private RectF rectF;

	// ------------------------------------------

	public PieView(Context context) {
		super(context);
		p = new Paint();
		p.setColor(Color.BLUE);
		p.setAntiAlias(true);
		
		rectF = new RectF();

		colors = new ArrayList<Integer>();
		values = new ArrayList<Integer>();

		startX = 320 / 4;
		startY = 480 / 8;
		radius = 320 / 2;

		colors.add(Color.GREEN);
		colors.add(Color.CYAN);
		colors.add(Color.MAGENTA);
		colors.add(Color.BLUE);
		colors.add(Color.RED);

		values.add(0);
		values.add(1);
		values.add(3);
		values.add(0);
		values.add(2);

	}// end constructor

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		Log.e("", "onDraw() is called... ");

		float offset = 0;
		float sum = 0;
		for (int i = 0; i < values.size(); i++) {
			sum += values.get(i);
		}// end for

		float angle = (float) (360 / sum);

		Log.e("angle", "" + angle);

		
		rectF.set(10, 18, 10, 10);
		
		for (int i = 0; i < values.size(); i++){
			p.setColor(colors.get(i));
			
			if(i == 0){
				canvas.drawArc(rectF, 0, values.get(i) * angle, true, p);
				}else{
					canvas.drawArc(rectF, offset, values.get(i) * angle, true, p);
				}//end else
			
			offset += (values.get(i) * angle);
		}//end loop
		
		canvas.save();
		
	}// end onDraw()
	
	public int getStartX(){
		return startX;
	}
	
	public void setStartX(int startX){
		this.startX = startX;
	}
	
	public int getStartY(){
		return startY;
	}
	
	public void setStartY(int startY){
		this.startY = startY;
	}
	
	public int getRadius(){
		return radius;
	}
	
	public void setRadius(int radius){
		this.radius = radius;
	}
	
	public ArrayList<Integer> getColors(){
		return colors;
	}
	
	public void setColors(ArrayList<Integer> colors){
		this.colors = colors;
	}
	
	public ArrayList<Integer> getValues(){
		return values;
	}
	
	public void setValues(ArrayList<Integer> values){
		this.values = values; 
	}
}// end class
