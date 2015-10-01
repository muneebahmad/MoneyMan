package com.muneebahmad.moneyman;

import java.util.ArrayList;

import org.muneebahmad.jlib.BudgetDB;
import org.muneebahmad.jlib.DBAdapter;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SlidingDrawer;
import android.widget.TextView;
import android.widget.Toast;

/**
 * *****************************************************************************
 * **********
 * *******************************************************************
 * ******************** ******************* @author:MuneebAhmad
 * ****************** ******************* package: com.muneebahmad.moneyman
 * ****************** ******************* Identifier:
 * com.muneebahmad.moneyman.GRAPH ****************** ******************* file:
 * com.muneebahmad.moneyman.GraphActivity.java *****************
 * ******************* @layout: res/layout/graph.xml ******************
 * *********
 * *********************************************************************
 * *********
 * ********************************************************************
 * *******************
 * */
public class GraphActivity extends Activity implements OnClickListener {
	MediaPlayer buttonMusic;
	private ImageButton right, balance, budget, graph, list, appHome, home,
			menu, credits, help, notes, balanceSheet, settings, back;
	@SuppressWarnings("deprecation")
	private SlidingDrawer mSlidingDrawer;
	Intent intent;
	private Toast toast;
	LinearLayout mLayout;
	private ImageView iGraph, eGraph, tGraph, bGraph, yGraph;
	private TextView top, middle;
	private ArrayList<Double> exp;
	private ArrayList<Double> inc;
	private Cursor c;
	private double expSum = 0.0;
	private double incSum = 0.0;
	private int comSum = 0;
	private String bud, name, currency, curr;
	int total, income, expense;

	// ------------------------------------------------
	private DBAdapter db = new DBAdapter(this); //    |
	private BudgetDB bDb = new BudgetDB(this); //     |
	// ------------------------------------------------

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		overridePendingTransition(R.anim.pull_in_from_left,
				R.anim.pull_out_to_left);
		setContentView(R.layout.graph);

		buttonMusic = MediaPlayer.create(this, R.raw.buttclick);

		intent = new Intent(Intent.ACTION_MAIN);
		intent.addCategory(Intent.CATEGORY_HOME);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

		mSlidingDrawer = (SlidingDrawer) findViewById(R.id.slidingDrawer1);

		settings = (ImageButton) findViewById(R.id.settings);
		back = (ImageButton) findViewById(R.id.back);
		home = (ImageButton) findViewById(R.id.home);

		mLayout = (LinearLayout) findViewById(R.id.graph_touch);
		menu = (ImageButton) findViewById(R.id.menu);

		// --------------------------------------------------------

		iGraph = (ImageView) findViewById(R.id.graphimg);
		eGraph = (ImageView) findViewById(R.id.grapexp);
		tGraph = (ImageView) findViewById(R.id.graphtotal);
		bGraph = (ImageView) findViewById(R.id.graphbudget);
		yGraph = (ImageView) findViewById(R.id.graphY);

		top = (TextView) findViewById(R.id.texttop);
		middle = (TextView) findViewById(R.id.textmed);
		top.setText("450<");
		middle.setText("225<");

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

		if (total > 0) {
			if (total >= 1000) {
				yGraph.setMinimumHeight((total / 10) - 6);
			}
			if (total >= 10000) {
				yGraph.setMinimumHeight((total / 100) - 6);
			}
			if (total >= 100000) {
				yGraph.setMinimumHeight((total / 1000) - 6);
			}
			if (total >= 1000000) {
				yGraph.setMinimumHeight((total / 10000) - 6);
			}
			top.setText(currency + "" + total + "<");
			middle.setText(currency + "" + total / 2 + "<");
		} else {
			top.setText("You need to create Budget First!!!");
			middle.setText("You need to create Budget First!!!");
		}

		if (total > 0 && incSum > 0) {
			if (total >= 1000) {
				iGraph.setMinimumHeight((income / 10) - 6);

			}
			if (total >= 10000) {
				iGraph.setMinimumHeight((income / 100) - 6);

			}
			if (total >= 100000) {
				iGraph.setMinimumHeight((income / 1000) - 6);

			}
			if (total >= 1000000) {
				iGraph.setMinimumHeight((income / 10000) - 6);

			}
		}

		if (total > 0 && expSum > 0) {
			if (total >= 1000) {
				eGraph.setMinimumHeight((expense / 10) - 6);

			}
			if (total >= 10000) {
				eGraph.setMinimumHeight((expense / 100) - 6);

			}
			if (total >= 100000) {
				eGraph.setMinimumHeight((expense / 1000) - 6);

			}
			if (total >= 1000000) {
				eGraph.setMinimumHeight((expense / 10000) - 6);

			}
		}

		if (comSum < 0) {
			tGraph.setBackgroundResource(R.drawable.expensegraph);
		}

		if (total > 0) {
			if (total >= 1000) {
				tGraph.setMinimumHeight((comSum / 10) - 6);
			}
			if (total >= 10000) {
				tGraph.setMinimumHeight((comSum / 100) - 6);
			}
			if (total >= 100000) {
				tGraph.setMinimumHeight((comSum / 1000) - 6);
			}
			if (total >= 1000000) {
				tGraph.setMinimumHeight((comSum / 10000) - 6);
			}
		}
		
		if (total >= 1000) {
			bGraph.setMinimumHeight((total / 10) - 6);
		}
		if (total >= 10000) {
			bGraph.setMinimumHeight((total / 100) - 6);
		}
		if (total >= 100000) {
			bGraph.setMinimumHeight((total / 1000) - 6);
		}
		if (total >= 1000000) {
			bGraph.setMinimumHeight((total/ 10000) - 6);
		}

		// --------------------------------------------------------

		// ImageButton
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
		// Registration
		// ImageView

		settings.setOnClickListener(this);
		back.setOnClickListener(this);
		home.setOnClickListener(this);

		menu.setOnClickListener(this);

		// ImageButton
		right.setOnClickListener(this);
		balance.setOnClickListener(this);
		budget.setOnClickListener(this);
		graph.setOnClickListener(this);
		list.setOnClickListener(this);
		appHome.setOnClickListener(this);
		credits.setOnClickListener(this);

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
		Animation animThree = AnimationUtils.loadAnimation(this, R.anim.graphanim);

		iGraph.setAnimation(animThree);
		eGraph.setAnimation(animThree);
		tGraph.setAnimation(animThree);
		bGraph.setAnimation(animThree);
		
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

		toast = Toast.makeText(this, "Under Construction!!", Toast.LENGTH_LONG);
		toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
		toast.show();

		// --------------------------------
		final Toast mToast = Toast.makeText(this, "Going Right!",
				Toast.LENGTH_LONG);
		mToast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
		final Toast nToast = Toast.makeText(this, "Going Left!",
				Toast.LENGTH_LONG);
		nToast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
		final Intent hIntent = new Intent(this, BalanceActivity.class);
		final Intent cIntent = new Intent(this, BudgetActivity.class);
		mLayout.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getX() > mLayout.getWidth() / 2) {
					buttonMusic.start();
					startActivity(hIntent);
					GraphActivity.this.finish();
				}
				if (event.getX() < mLayout.getWidth() / 2) {
					buttonMusic.start();
					startActivity(cIntent);
					GraphActivity.this.finish();
				}
				return false;
			}
		});

	}

	/**
	 ********************************************************************************************* 
	 ***************************** ---> end onCreate(Bundle) <--- ********************************
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

		if (view == appHome) {
			buttonMusic.start();
			Intent intent = new Intent("com.muneebahmad.moneyman.HOME");
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			overridePendingTransition(R.anim.pull_in_from_left, R.anim.hold);
			startActivity(intent);
			finish();
		}

		if (view == list) {
			buttonMusic.start();
			Toast lst = Toast.makeText(this, "In list Activity",
					Toast.LENGTH_SHORT);
			Intent intent = new Intent("com.muneebahmad.moneyman.LIST");
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
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
			text.setText("Graphpage minimized!!");
			toast = new Toast(getApplicationContext());
			toast.setDuration(Toast.LENGTH_LONG);
			toast.setView(layout);
			toast.show();
			startActivity(intent);
		}
		if (view == back || view == budget) {
			buttonMusic.start();
			Toast bud = Toast.makeText(this, "In Budget Activity",
					Toast.LENGTH_SHORT);
			Intent intent = new Intent("com.muneebahmad.moneyman.BUDGET");
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			overridePendingTransition(R.anim.pull_in_from_left, R.anim.hold);
			startActivity(intent);
			finish();
		}
		if (view == balance || view == right) {
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

	}

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
		intent = new Intent(this, BudgetActivity.class);
		startActivity(intent);
		finish();
	}

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
			intent = new Intent(this, CreditsDialog.class);
			startActivity(intent);
			return true;

		case R.id.help:
			buttonMusic.start();
			return true;

		}

		return false;

	}

}// end class
