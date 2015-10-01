package com.muneebahmad.moneyman;

/**
 * ***************************************************************************************
 * ***************************************************************************************
 * ***************                    @author:MuneebAhmad                 ****************
 * ***************               package: com.muneebahmad.moneyman        ****************
 * ***************      Identifier: com.muneebahmad.moneyman.CALCULATOR   ****************
 * *************** file: com.muneebahmad.moneyman.CalculatorActivity.java ****************
 * ***************            @layout: res/layout/calculator.xml          ****************
 * ***************************************************************************************
 * ***************************************************************************************
 * */

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class CalculatorActivity extends Activity implements OnClickListener {
	MediaPlayer buttonMusic, musicExit, musicCalc;
	Button close, min;
	ImageButton one, two, three, four, five, six, seven, eight, nine, zero, c,
			divide, multiply, minus, plus, decimal, equal, use, m, bck;
	TextView calculatorView, ansView;
	int dec, count, disp = 0;
	double number1, number2, result;
	int a, s, o, d, clear = 0;
	Intent intent;
	Bitmap div, mul, ads, subs, dash, ans, mem;
	Toast toast;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		overridePendingTransition(R.anim.pull_in_from_left,
				R.anim.pull_out_to_left);
		setContentView(R.layout.calculator);

		intent = new Intent(Intent.ACTION_MAIN);
		intent.addCategory(Intent.CATEGORY_HOME);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		musicExit = MediaPlayer.create(this, R.raw.music_exit);
		musicCalc = MediaPlayer.create(this, R.raw.music_calc);

		div = BitmapFactory.decodeResource(getResources(), R.drawable.div);
		mul = BitmapFactory.decodeResource(getResources(), R.drawable.mul);
		ads = BitmapFactory.decodeResource(getResources(), R.drawable.ads);
		subs = BitmapFactory.decodeResource(getResources(), R.drawable.subs);
		dash = BitmapFactory.decodeResource(getResources(), R.drawable.dash);
		ans = BitmapFactory.decodeResource(getResources(), R.drawable.ans);
		mem = BitmapFactory.decodeResource(getResources(), R.drawable.mem);

		// TextView caste
		calculatorView = (TextView) findViewById(R.id.calculatorView);
		ansView = (TextView) findViewById(R.id.num1);
		// Button caste
		close = (Button) findViewById(R.id.close);
		min = (Button) findViewById(R.id.min);

		// ImageButton caste
		zero = (ImageButton) findViewById(R.id.zero);
		one = (ImageButton) findViewById(R.id.one);
		two = (ImageButton) findViewById(R.id.two);
		three = (ImageButton) findViewById(R.id.three);
		four = (ImageButton) findViewById(R.id.four);
		five = (ImageButton) findViewById(R.id.five);
		six = (ImageButton) findViewById(R.id.six);
		seven = (ImageButton) findViewById(R.id.seven);
		eight = (ImageButton) findViewById(R.id.eight);
		nine = (ImageButton) findViewById(R.id.nine);
		divide = (ImageButton) findViewById(R.id.divide);
		multiply = (ImageButton) findViewById(R.id.multiply);
		plus = (ImageButton) findViewById(R.id.plus);
		minus = (ImageButton) findViewById(R.id.minus);
		c = (ImageButton) findViewById(R.id.c);
		decimal = (ImageButton) findViewById(R.id.decimal);
		equal = (ImageButton) findViewById(R.id.equal);
		use = (ImageButton) findViewById(R.id.use);
		m = (ImageButton) findViewById(R.id.m);
		bck = (ImageButton) findViewById(R.id.bck);

		// <-- Registration -->
		// <-- Button -->
		close.setOnClickListener(this);
		min.setOnClickListener(this);
		// <-- ImageButton -->
		zero.setOnClickListener(this);
		one.setOnClickListener(this);
		two.setOnClickListener(this);
		three.setOnClickListener(this);
		four.setOnClickListener(this);
		five.setOnClickListener(this);
		six.setOnClickListener(this);
		seven.setOnClickListener(this);
		eight.setOnClickListener(this);
		nine.setOnClickListener(this);
		divide.setOnClickListener(this);
		multiply.setOnClickListener(this);
		plus.setOnClickListener(this);
		minus.setOnClickListener(this);
		c.setOnClickListener(this);
		decimal.setOnClickListener(this);
		equal.setOnClickListener(this);
		use.setOnClickListener(this);
		m.setOnClickListener(this);
		bck.setOnClickListener(this);
		
		/**
		 * *************************************************************************************
		 * ****************************** <-- View Animations --> ******************************
		 * *************************************************************************************
		 * */
		Animation animRotate = AnimationUtils.loadAnimation(this, R.anim.rotate);
		Animation rotateTwo = AnimationUtils.loadAnimation(this, R.anim.rotate2);
		
		close.setAnimation(animRotate);
		min.setAnimation(rotateTwo);

	}// end onCreate()

	@Override
	public void onClick(View view) {

		count = calculatorView.length() - 1;
		// TODO Auto-generated method stub

		if (view == m) {
			musicExit.start();
			bck.setImageBitmap(mem);
			calculatorView.setText(Double.toString(result));
		}
		if (view == min) {
			musicExit.start();
			LayoutInflater mInflater = getLayoutInflater();
			View layout = mInflater.inflate(R.layout.toast2,
					(ViewGroup) findViewById(R.id.custom_toast2));
			TextView text = (TextView) layout.findViewById(R.id.toast_text2);
			text.setText("Calculator minimized!!");
			toast = new Toast(getApplicationContext());
			toast.setDuration(Toast.LENGTH_LONG);
			toast.setView(layout);
			toast.show();
			startActivity(intent);
		}
		if (view == close) {
			musicExit.start();
			String resi = "";
			intent = getIntent();
			String msgi = intent.getStringExtra("money");
			if (msgi.contentEquals("amount")) {
				intent.putExtra("moneyInfo", resi);
				setResult(RESULT_OK, intent);
				finish();
			}
		}
		if (view == zero) {
			bck.setImageBitmap(dash);
			ansView.setText("");
			if (clear > 0) {
				calculatorView.setText("");
				clear = 0;
			}
			calculatorView.append("0");
		}
		if (view == one) {
			bck.setImageBitmap(dash);
			ansView.setText("");
			if (clear > 0) {
				calculatorView.setText("");
				clear = 0;
			}
			calculatorView.append("1");
		}
		if (view == two) {
			bck.setImageBitmap(dash);
			ansView.setText("");
			if (clear > 0) {
				calculatorView.setText("");
				clear = 0;
			}
			calculatorView.append("2");
		}
		if (view == three) {
			bck.setImageBitmap(dash);
			ansView.setText("");
			if (clear > 0) {
				calculatorView.setText("");
				clear = 0;
			}
			calculatorView.append("3");
		}
		if (view == four) {
			bck.setImageBitmap(dash);
			ansView.setText("");
			if (clear > 0) {
				calculatorView.setText("");
				clear = 0;
			}
			calculatorView.append("4");
		}
		if (view == five) {
			bck.setImageBitmap(dash);
			ansView.setText("");
			if (clear > 0) {
				calculatorView.setText("");
				clear = 0;
			}
			calculatorView.append("5");
		}
		if (view == six) {
			bck.setImageBitmap(dash);
			ansView.setText("");
			if (clear > 0) {
				calculatorView.setText("");
				clear = 0;
			}
			calculatorView.append("6");
		}
		if (view == seven) {
			bck.setImageBitmap(dash);
			ansView.setText("");
			if (clear > 0) {
				calculatorView.setText("");
				clear = 0;
			}
			calculatorView.append("7");
		}
		if (view == eight) {
			bck.setImageBitmap(dash);
			ansView.setText("");
			if (clear > 0) {
				calculatorView.setText("");
				clear = 0;
			}
			calculatorView.append("8");
		}
		if (view == nine) {
			bck.setImageBitmap(dash);
			ansView.setText("");
			if (clear > 0) {
				calculatorView.setText("");
				clear = 0;
			}
			calculatorView.append("9");
		}
		if (view == c) {
			bck.setImageBitmap(dash);
			calculatorView.setText("");
			ansView.setText("");
			dec = 0;

		}
		if (view == divide) {
			number1 = number_reader();
			calculatorView.setText("");
			bck.setImageBitmap(div);
			ansView.setTextColor(Color.RED);
			ansView.setText(Double.toString(number1));
			dec = 0;
			a = 0;
			s = 0;
			o = 0;
			d = 1;
		}
		if (view == multiply) {
			number1 = number_reader();
			calculatorView.setText("");
			bck.setImageBitmap(mul);
			ansView.setTextColor(Color.RED);
			ansView.setText(Double.toString(number1));
			dec = 0;
			a = 0;
			s = 0;
			o = 1;
			d = 0;
		}
		if (view == minus) {
			number1 = number_reader();
			calculatorView.setText("");
			bck.setImageBitmap(subs);
			ansView.setTextColor(Color.RED);
			ansView.setText(Double.toString(number1));
			dec = 0;
			a = 0;
			s = 1;
			o = 0;
			d = 0;
		}
		if (view == plus) {
			number1 = number_reader();
			calculatorView.setText("");
			bck.setImageBitmap(ads);
			ansView.setTextColor(Color.RED);
			ansView.setText(Double.toString(number1));
			dec = 0;
			a = 1;
			s = 0;
			o = 0;
			d = 0;
		}
		if (view == decimal) {
			if (dec < 1) {
				dec = 1;
				calculatorView.append(".");
			}
		}
		if (view == equal) {
			number2 = number_reader();
			bck.setImageBitmap(ans);
			if (a > 0) {
				result = number1 + number2;
				calculatorView.setText(Double.toString(result));
				dec = 0;
				clear = 1;
			}
			if (s > 0) {
				result = number1 - number2;
				calculatorView.setText(Double.toString(result));
				dec = 0;
				clear = 1;
			}
			if (d > 0) {
				result = number1 / number2;
				calculatorView.setText(Double.toString(result));
				dec = 0;
				clear = 1;
			}
			if (o > 0) {
				result = number1 * number2;
				calculatorView.setText(Double.toString(result));
				dec = 0;
				clear = 1;
			}

		}
		if (view == use) {
			String res = calculatorView.getText().toString();
			intent = getIntent();
			String msg = intent.getStringExtra("money");
			if (msg.contentEquals("amount")) {
				intent.putExtra("moneyInfo", res);
				setResult(RESULT_OK, intent);
				finish();
			}
		}

	}// end onClick()

	public double number_reader() {
		String read;
		double num = 0;
		read = calculatorView.getText().toString();
		if (read == null || read == "" || read.contentEquals("0")
				|| read.contentEquals("")) {
			calculatorView.setHint("0");
			equal.setClickable(false);
			equal.setBackgroundResource(R.drawable.keypad1);
		} else {
			num = Double.parseDouble(read);
			equal.setClickable(true);
			equal.setBackgroundResource(R.drawable.calc_bck);
		}
		return num;
	}

	@Override
	public void onBackPressed() {
		Toast toast = Toast
				.makeText(
						this,
						"This Button has been Disabled! Please use the close or minimize button to exit or minimize",
						Toast.LENGTH_LONG);
		toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
		toast.show();
	}//end onBackPressed()
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_settings:
			buttonMusic.start();
			return true;

		case R.id.help:
			buttonMusic.start();
			return true;

		}

		return false;

	}

}// end class
