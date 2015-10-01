package com.muneebahmad.moneyman;

/**
 * ***************************************************************************************
 * ***************************************************************************************
 * ******************                 @author:MuneebAhmad                *****************
 * ******************            package: com.muneebahmad.moneyman       *****************
 * ******************      Identifier: com.muneebahmad.moneyman.BUDGET   *****************
 * ****************** file: com.muneebahmad.moneyman.BudgetActivity.java *****************
 * ******************           @layout: res/layout/budget.xml           *****************
 * ***************************************************************************************
 * ***************************************************************************************
 * */

import java.util.ArrayList;

import org.muneebahmad.jlib.BudgetDB;
import org.muneebahmad.jlib.DBAdapter;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SlidingDrawer;
import android.widget.TextView;
import android.widget.Toast;

public class BudgetActivity extends Activity implements OnClickListener {
	private MediaPlayer buttonMusic;
	private ImageButton right, balance, budget, graph, list, appHome, home,
			menu, credits, help, notes, balanceSheet, settings, back;
	@SuppressWarnings("deprecation")
	private SlidingDrawer mSlidingDrawer;
	private Intent intent;
	private Toast toast;
	private ArrayList<Double> exp;
	private ArrayList<Double> inc;
	private double expSum = 0.0;
	private double incSum = 0.0;
	private int comSum = 0;
	private Button createButton;
	private TextView budgetView, budgetView2, budgetView3, budgetView4, budgetView5;
	private BudgetDB bDb = new BudgetDB(this);
	LinearLayout mLayout, nLayout, lay;
	ImageView iView1;
	ImageView iView2;
	ImageView iView3, iView4, iView5;
	private Cursor c;
	private String bud, name, currency, curr;
	int total, income, expense;

	// -- instantiating DBADapter class
	private DBAdapter db = new DBAdapter(this);
	int col;
	double mDouble;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		overridePendingTransition(R.anim.pull_in_from_left,
				R.anim.pull_out_to_left);
		setContentView(R.layout.budget);

		buttonMusic = MediaPlayer.create(this, R.raw.buttclick);

		intent = new Intent(Intent.ACTION_MAIN);
		intent.addCategory(Intent.CATEGORY_HOME);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

		/**
		 * *********************************************************************
		 * *************** ************* <--- casting View Objects --->
		 * ************
		 * *********************************************************
		 * ***************************
		 * */

		mSlidingDrawer = (SlidingDrawer) findViewById(R.id.slidingDrawer1);

		iView1 = (ImageView) findViewById(R.id.iView1);
		iView2 = (ImageView) findViewById(R.id.iView2);
		iView3 = (ImageView) findViewById(R.id.iView3);
		iView4 = (ImageView) findViewById(R.id.iView4);
		iView5 = (ImageView) findViewById(R.id.iView5);

		createButton = (Button) findViewById(R.id.create_budget);

		settings = (ImageButton) findViewById(R.id.settings);
		back = (ImageButton) findViewById(R.id.back);
		home = (ImageButton) findViewById(R.id.home);
		menu = (ImageButton) findViewById(R.id.menu);
		right = (ImageButton) findViewById(R.id.right);
		appHome = (ImageButton) findViewById(R.id.appHome);
		balance = (ImageButton) findViewById(R.id.balance);
		budget = (ImageButton) findViewById(R.id.budget);
		graph = (ImageButton) findViewById(R.id.graph);
		list = (ImageButton) findViewById(R.id.list);
		credits = (ImageButton) findViewById(R.id.credits);
		help = (ImageButton) findViewById(R.id.help);
		notes = (ImageButton) findViewById(R.id.notes);
		balanceSheet = (ImageButton) findViewById(R.id.balancesheet);

		budgetView = (TextView) findViewById(R.id.budget_view);
		budgetView2 = (TextView) findViewById(R.id.budget_view2);
		budgetView3 = (TextView) findViewById(R.id.budget_view3);
		budgetView4= (TextView) findViewById(R.id.budget_view4);
		budgetView5 = (TextView) findViewById(R.id.budget_view5);

		mLayout = (LinearLayout) findViewById(R.id.budget_touch);
		lay = (LinearLayout) findViewById(R.id.lay_id);
		// <-- Registration -->
		// ImageView

		settings.setOnClickListener(this);
		back.setOnClickListener(this);
		home.setOnClickListener(this);

		menu.setOnClickListener(this);

		// ImageButton
		right.setOnClickListener(this);
		appHome.setOnClickListener(this);
		balance.setOnClickListener(this);
		budget.setOnClickListener(this);
		graph.setOnClickListener(this);
		list.setOnClickListener(this);
		credits.setOnClickListener(this);
		createButton.setOnClickListener(this);
		

		// <-- calling DBAdapter methods -->
		String query = "Expense";
		exp = new ArrayList<Double>();
		db.open();
		int col;
		c = db.getTransRecord(query);
		col = c.getCount();
		if (c.moveToFirst()) {
			do {
				try {
					exp.add(Double.valueOf(c.getString(0)));
				} catch (NumberFormatException e) {
					e.printStackTrace();
				} catch (IndexOutOfBoundsException ex) {
					ex.printStackTrace();
				}
			} while (c.moveToNext());
		}
		if (exp != null) {
			for (int i = 0; i < col; i++) {
				try {
					expSum += exp.get(i);
				} catch (IndexOutOfBoundsException ex) {
					ex.printStackTrace();
				}
			}// end for
		}// end if
		db.close();

		String que = "Income";
		inc = new ArrayList<Double>();
		db.open();
		int rw;
		Cursor mCursor = db.getTransRecord(que);
		rw = mCursor.getCount();
		if (mCursor.moveToFirst()) {
			do {
				try {
					inc.add(Double.valueOf(mCursor.getString(0)));
				} catch (NumberFormatException e) {
					e.printStackTrace();
				} catch (IndexOutOfBoundsException ex) {
					ex.printStackTrace();
				}
			} while (mCursor.moveToNext());
		}
		if (inc != null) {
			for (int i = 0; i < rw; i++) {
				try {
					incSum += inc.get(i);
				} catch (IndexOutOfBoundsException e) {
					e.printStackTrace();
				}

			}// end for
		}// end if
		db.close();

		bDb.open();
		c = bDb.getAllRecords();
		col = c.getCount();
		if (c.moveToFirst()) {
			do {
				name = c.getString(1);
				bud = c.getString(3);
				currency = c.getString(2);
			} while (c.moveToNext());
		}
		bDb.close();
		try {
			total = Integer.parseInt(bud);
		} catch (NumberFormatException ex) {
			ex.printStackTrace();
		}
		income = (int) incSum;
		expense = (int) expSum;
		comSum = income - expense;
		if (total > 0 && incSum > 0) {
			if (total >= 1000) {
				iView1.setMinimumWidth(income / 10);
			}
			if (total >= 10000) {
				iView1.setMinimumWidth(income / 100);
			}
			if (total >= 100000) {
				iView1.setMinimumWidth(income / 1000);
			}
			if (total >= 1000000) {
				iView1.setMinimumWidth(income / 10000);
			}

		} else {
			budgetView3.setText("Budget[" + bud + "]");
		}
		
		if (total > 0 && expSum > 0) {
			if (total >= 1000) {
				iView2.setMinimumWidth(expense / 10);
			}
			if (total >= 10000) {
				iView2.setMinimumWidth(expense / 100);
			}
			if (total >= 100000) {
				iView2.setMinimumWidth(expense / 1000);
			}
			if (total >= 1000000) {
				iView2.setMinimumWidth(expense / 10000);
			}

		} else {
			budgetView3.setText("Budget[" + bud + "]");
		}
		
		if (total > 0 ) {
			//-----------------------
			LayoutInflater mInflater = getLayoutInflater();
			View layout = mInflater.inflate(R.layout.toast2,
					(ViewGroup) findViewById(R.id.custom_toast2));
			TextView text = (TextView) layout.findViewById(R.id.toast_text2);
			text.setText("<-- Swipe -->");
			toast = new Toast(getApplicationContext());
			toast.setDuration(Toast.LENGTH_LONG);
			toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
			toast.setView(layout);
			toast.show();
			//--------------------------
			
			if (total >= 1000) {
				iView3.setMinimumWidth(total / 10);
			}
			if (total >= 10000) {
				iView3.setMinimumWidth(total / 100);
			}
			if (total >= 100000) {
				iView3.setMinimumWidth(total / 1000);
			}
			if (total >= 1000000) {
				iView3.setMinimumWidth(total/ 10000);
			}

		} else {
			budgetView3.setText("Budget[" + bud + "]");
		}
		bDb.open();
		c = bDb.getAllRecords();
		int r = c.getCount();
		bDb.close();
		int sum = total - (int) expSum;
		if (r < 1) {
			budgetView.setText("");
			budgetView2.setText("");
			budgetView3.setText("No BudgetFound!!\n"
					+ "Please Create a New Budget!!");
		} else {
			budgetView.setText("Expenses " + currency + " " + expSum + "");
			budgetView2.setText("Income " + currency + " " + incSum + "");
			budgetView3.setText("Budget: "+name + " [" + currency + " " + bud + "]");
			budgetView4.setText("Budget Deficit ["+currency+" "+sum+"]");
			budgetView5.setText("Cash in hand ["+currency+" "+comSum+"]");
		}

		if (total < expSum) {
			budgetView4.setTextColor(Color.RED);
			budgetView4.setText("Expenses have crossed Budget Limits in Budget: ["
					+ name + "] of [" + currency + " " + bud + "]"
					+ "\n Your Money in hand is [" + sum
					+ "] \nConsider reduction in spendings!!");
		}
		
		if(sum < 0){
			iView4.setBackgroundResource(R.drawable.exp_bck);
		}
		
		if (total > 0 ) {
			if (total >= 1000) {
				iView4.setMinimumWidth(sum / 10);
			}
			if (total >= 10000) {
				iView4.setMinimumWidth(sum / 100);
			}
			if (total >= 100000) {
				iView4.setMinimumWidth(sum / 1000);
			}
			if (total >= 1000000) {
				iView4.setMinimumWidth(sum/ 10000);
			}

		} else {
			budgetView3.setText("Budget[" + bud + "]");
		}
		
		if(comSum < 0){
			iView5.setBackgroundResource(R.drawable.exp_bck);
		}
		
		if (total > 0 ) {
			if (total >= 1000) {
				iView5.setMinimumWidth(comSum / 10);
			}
			if (total >= 10000) {
				iView5.setMinimumWidth(comSum / 100);
			}
			if (total >= 100000) {
				iView5.setMinimumWidth(comSum / 1000);
			}
			if (total >= 1000000) {
				iView5.setMinimumWidth(comSum / 10000);
			}

		} else {
			budgetView3.setText("Budget[" + bud + "]");
		}
		/**
		 * *********************************************************************
		 * **************** ****************************** <-- View Animations
		 * --> ******************************
		 * ***********************************
		 * **************************************************
		 * */
		Animation animRotate = AnimationUtils
				.loadAnimation(this, R.anim.rotate);
		Animation rotateTwo = AnimationUtils
				.loadAnimation(this, R.anim.rotate2);
		Animation navOne = AnimationUtils.loadAnimation(this, R.anim.anim_nav);
		Animation navTwo = AnimationUtils.loadAnimation(this, R.anim.anim_nav2);
		Animation animList = AnimationUtils.loadAnimation(this, R.anim.right);
		Animation animTwo = AnimationUtils
				.loadAnimation(this, R.anim.baranim);
		Animation animOne = AnimationUtils
				.loadAnimation(this, R.anim.anim_home);
		Animation animFour = AnimationUtils.loadAnimation(this,
				R.anim.anim_list_spinner);

		createButton.setAnimation(animOne);
		iView1.setAnimation(animTwo);
		iView2.setAnimation(animTwo);
		iView3.setAnimation(animTwo);
		iView4.setAnimation(animTwo);
		iView5.setAnimation(animTwo);

		budgetView.setAnimation(animList);
		budgetView2.setAnimation(animList);
		budgetView3.setAnimation(animList);
		budgetView4.setAnimation(animList);
		budgetView5.setAnimation(animList);

		mSlidingDrawer.setAnimation(navTwo);

		appHome.setAnimation(navOne);
		list.setAnimation(navOne);
		budget.setAnimation(navOne);
		graph.setAnimation(navOne);
		balance.setAnimation(navOne);

		back.setAnimation(animRotate);
		home.setAnimation(rotateTwo);
		menu.setAnimation(animRotate);
		right.setAnimation(rotateTwo);
		help.setAnimation(animRotate);
		credits.setAnimation(rotateTwo);
		settings.setAnimation(animRotate);
		notes.setAnimation(rotateTwo);
		balanceSheet.setAnimation(animRotate);

		// --------------------------------
		final Toast mToast = Toast.makeText(this, "Going Right!",
				Toast.LENGTH_LONG);
		mToast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
		final Toast nToast = Toast.makeText(this, "Going Left!",
				Toast.LENGTH_LONG);
		nToast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
		final Intent hIntent = new Intent(this, GraphActivity.class);
		final Intent cIntent = new Intent(this, ListActivity.class);
		mLayout.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getX() > mLayout.getWidth() / 2) {
					buttonMusic.start();
					startActivity(hIntent);
					BudgetActivity.this.finish();
				}
				if (event.getX() < mLayout.getWidth() / 2) {
					buttonMusic.start();
					startActivity(cIntent);
					BudgetActivity.this.finish();
				}
				return false;
			}
		});

	}

	/**
	 ********************************************************************************************* 
	 ******************************* --> end onCreate(Bundle) <-- ********************************
	 ********************************************************************************************* 
	 **/

	/**
	 * *************************************************************************
	 * ***************** *********************** <--- Overriding onClick(View)
	 * ---> ***********************
	 * *********************************************
	 * *********************************************
	 * */
	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		if (view == appHome) {
			buttonMusic.start();
			Intent intent = new Intent("com.muneebahmad.moneyman.HOME");
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			overridePendingTransition(R.anim.pull_in_from_left, R.anim.hold);
			startActivity(intent);
			finish();
		}
		if (view == list || view == back) {
			buttonMusic.start();
			intent = new Intent("com.muneebahmad.moneyman.LIST");
			overridePendingTransition(R.anim.pull_in_from_left, R.anim.hold);
			startActivity(intent);
			finish();
		}
		if (view == home) {
			buttonMusic.start();
			LayoutInflater mInflater = getLayoutInflater();
			View layout = mInflater.inflate(R.layout.toast2,
					(ViewGroup) findViewById(R.id.custom_toast2));
			TextView text = (TextView) layout.findViewById(R.id.toast_text2);
			text.setText("Budgetpage minimized!!");
			toast = new Toast(getApplicationContext());
			toast.setDuration(Toast.LENGTH_LONG);
			toast.setView(layout);
			toast.show();
			startActivity(intent);
		}
		if (view == graph || view == right) {
			buttonMusic.start();
			Toast lst = Toast.makeText(this, "In Graph Activity",
					Toast.LENGTH_SHORT);
			Intent intent = new Intent("com.muneebahmad.moneyman.GRAPH");
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			overridePendingTransition(R.anim.pull_in_from_left, R.anim.hold);
			startActivity(intent);
			finish();
		}
		if (view == balance) {
			buttonMusic.start();
			Toast lst = Toast.makeText(this, "In Balance Activity",
					Toast.LENGTH_SHORT);
			Intent intent = new Intent("com.muneebahmad.moneyman.BALANCE");
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			overridePendingTransition(R.anim.pull_in_from_left, R.anim.hold);
			startActivity(intent);
			finish();
		}

		if (view == credits) {
			intent = new Intent(this, CreditsDialog.class);
			startActivity(intent);
		}
		if (view == createButton) {
			intent = new Intent("com.muneebahmad.moneyman.CREATE");
			startActivity(intent);
			finish();
		}
	}// end onClick()

	@Override
	protected void onPause() {
		super.onPause();

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		buttonMusic.release();
	}

	@Override
	public void onBackPressed() {
		buttonMusic.start();
		intent = new Intent(this, ListActivity.class);
		startActivity(intent);
		finish();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		// -- Inflate the menu; this adds items to the action bar if it is
		// present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}// end onCreateOptonsMenu()

	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_settings:
			intent = new Intent(this, CreditsDialog.class);
			startActivity(intent);
			return true;

		case R.id.help:
			buttonMusic.start();
			return true;

		}

		return false;

	}// end onOptionsItemSelected()

}// end class
