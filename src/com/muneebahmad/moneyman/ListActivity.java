package com.muneebahmad.moneyman;

/**
 * ***************************************************************************************
 * ***************************************************************************************
 * *******************                @author:MuneebAhmad               ******************
 * *******************           package: com.muneebahmad.moneyman      ******************
 * *******************      Identifier: com.muneebahmad.moneyman.LIST   ******************
 * ******************* file: com.muneebahmad.moneyman.ListActivity.java ******************
 * *******************           @layout: res/layout/list.xml           ******************
 * ***************************************************************************************
 * ***************************************************************************************
 * */
import java.util.ArrayList;

import org.muneebahmad.jlib.DBAdapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SlidingDrawer;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ListActivity extends Activity implements OnClickListener {
	private MediaPlayer buttonMusic;
	private ImageButton right, balance, budget, graph, list, appHome, home,
			menu, credits, help, notes, balanceSheet, settings, back;
	@SuppressWarnings("deprecation")
	private SlidingDrawer mSlidingDrawer;
	private Intent intent;
	private ArrayList<String> id;
	private ArrayList<String> date;
	private ArrayList<String> day;
	private ArrayList<String> names;
	private ArrayList<String> desc;
	private ArrayList<String> transaction;
	private ArrayList<String> tags;
	private ArrayList<String> category;
	private ArrayList<String> currency;
	private ArrayList<String> amount;
	Toast toast, delete, nDelete;
	private ExpandableListView mExpandableList;
	private Spinner file, filter;
	private TextView mList;
	int col;
	private Button button;
	String mFile, mFilter, fileValue;
	LinearLayout mLayout;
	// -- instantiating DBAdapter.java class
	DBAdapter db = new DBAdapter(this);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		overridePendingTransition(R.anim.pull_in_from_left,
				R.anim.pull_out_to_left);
		setContentView(R.layout.list);
		buttonMusic = MediaPlayer.create(this, R.raw.buttclick);
		final AlertDialog.Builder builder = new AlertDialog.Builder(this);
		intent = new Intent(Intent.ACTION_MAIN);
		intent.addCategory(Intent.CATEGORY_HOME);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

		/**
		 * *********************************************************************
		 * ************** ***************************** <- casting View Objects
		 * -> **************************
		 * ****************************************
		 * *******************************************
		 * */

		mLayout = (LinearLayout) findViewById(R.id.list_touch);
		mSlidingDrawer = (SlidingDrawer) findViewById(R.id.slidingDrawer1);

		settings = (ImageButton) findViewById(R.id.settings);
		back = (ImageButton) findViewById(R.id.back);
		home = (ImageButton) findViewById(R.id.home);
		menu = (ImageButton) findViewById(R.id.menu);

		mList = (TextView) findViewById(R.id.homeTxt);

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
		// ExpandableListView caste
		mExpandableList = (ExpandableListView) findViewById(R.id.explist);
		mExpandableList.setAnimationCacheEnabled(false);

		file = (Spinner) findViewById(R.id.list_spinner_file);
		filter = (Spinner) findViewById(R.id.list_spinner_filter);
		button = (Button) findViewById(R.id.button1);
		button.setOnClickListener(this);
		// TextView caste

		// --> Registration <--
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

		file.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int pos, long id) {

				Object item = parent.getItemAtPosition(pos);
				mFile = item.toString();
				if (mFile.equals("Weekly")) {
					fileValue = "w";
				} else if (mFile.equals("Monthly")) {
					fileValue = "m";
				} else if (mFile.equals("Yearly")) {
					fileValue = "y";
				} else {
					fileValue = "d";
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {

			}

		});// end onItemSelectedListener()

		id = new ArrayList<String>();
		date = new ArrayList<String>();
		day = new ArrayList<String>();
		names = new ArrayList<String>();
		desc = new ArrayList<String>();
		transaction = new ArrayList<String>();
		tags = new ArrayList<String>();
		category = new ArrayList<String>();
		currency = new ArrayList<String>();
		amount = new ArrayList<String>();

		db.open();
		Cursor c = db.getAllRecords();
		col = c.getCount();
		if (c.moveToFirst()) {
			do {
				id.add("" + c.getString(0));
				date.add(" " + c.getString(1));
				day.add("" + c.getString(2));
				names.add(" " + c.getString(3));
				desc.add(" " + c.getString(4));
				transaction.add(" " + c.getString(5));
				tags.add(" " + c.getString(6));
				category.add(" " + c.getString(7));
				currency.add(" " + c.getString(8));
				amount.add(" " + c.getString(9));

				// -- populating expandable list
				ArrayList<Parent> arrayParent = new ArrayList<Parent>();
				ArrayList<String> arrayChildren = new ArrayList<String>();

				// setting the parents and children
				for (int i = 0; i <= col; i++) {
					// for each i create a new parent
					Parent parent = new Parent();
					try {
						parent.setTitle(names.get(i) + " @ " + date.get(i));
						arrayChildren = new ArrayList<String>();

						arrayChildren.add(id.get(i) + " ~ " + date.get(i) + ","
								+ "\n" + transaction.get(i) + " "
								+ currency.get(i) + "-" + amount.get(i) + ".\n"
								+ " Category: " + category.get(i) + ".\n"
								+ " Tags: " + tags.get(i) + ".\n"
								+ " Description: " + desc.get(i) + ".");

						parent.setArrayChildren(arrayChildren);
						// in this array we add the parent object
						arrayParent.add(parent);

					} catch (IndexOutOfBoundsException e) {
						toast = Toast
								.makeText(
										this,
										"MuneebAhmad Debugger\n "
												+ "java.lang.NullPointerException @\n "
												+ "java.util.ArrayList.IndexOutOfBoundsException @\n "
												+ "com.muneebahmad.moneyman.ListActivity.java @ ~ "
												+ e.getMessage(),
										Toast.LENGTH_LONG);
						toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);

						e.printStackTrace();
					}// end catch

				}// end for
					// sets the adapter that provides data to the list
				mExpandableList.setAdapter(new MyCustomAdapter(
						ListActivity.this, arrayParent));

			} while (c.moveToNext());
		}// end if
		db.close();

		delete = Toast.makeText(this, "The Record has been deleted",
				Toast.LENGTH_SHORT);

		mExpandableList.setOnChildClickListener(new OnChildClickListener() {

			@Override
			public boolean onChildClick(ExpandableListView list, View view,
					int child, int papa, long ids) {
				Object item = list.getItemAtPosition(child);
				final int mChild = child;
				Intent intent = new Intent("com.muneebahmad.moneyman.DELETELISTONE");
				intent.putExtra("delete", mChild+"");
				startActivity(intent);
				finish();
				return false;
			}

		});// end setOnChildClickListener()

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
		Animation animOne = AnimationUtils
				.loadAnimation(this, R.anim.anim_home);
		Animation animTwo = AnimationUtils
				.loadAnimation(this, R.anim.anim_date);
		Animation navOne = AnimationUtils.loadAnimation(this, R.anim.anim_nav);
		Animation navTwo = AnimationUtils.loadAnimation(this, R.anim.anim_nav2);
		Animation animSpinner = AnimationUtils.loadAnimation(this,
				R.anim.anim_list_spinner);
		Animation animList = AnimationUtils.loadAnimation(this,
				R.anim.anim_list);

		mSlidingDrawer.setAnimation(navTwo);

		appHome.setAnimation(navOne);
		list.setAnimation(navOne);
		budget.setAnimation(navOne);
		graph.setAnimation(navOne);
		balance.setAnimation(navOne);

		button.setAnimation(animSpinner);

		back.setAnimation(animRotate);
		home.setAnimation(rotateTwo);
		menu.setAnimation(animRotate);
		right.setAnimation(rotateTwo);
		help.setAnimation(animRotate);
		credits.setAnimation(rotateTwo);
		settings.setAnimation(animRotate);
		notes.setAnimation(rotateTwo);
		balanceSheet.setAnimation(animRotate);

		mExpandableList.setAnimation(animList);
		getWindow().setWindowAnimations(R.anim.anim_list);

		file.setAnimation(animSpinner);
		filter.setAnimation(animSpinner);

		LayoutInflater mInflater = getLayoutInflater();
		View layout = mInflater.inflate(R.layout.toast1,
				(ViewGroup) findViewById(R.id.custom_toast1));
		TextView text = (TextView) layout.findViewById(R.id.text);
		text.setText("Please Wait! Loading Data!");
		toast = new Toast(getApplicationContext());
		toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
		toast.setDuration(Toast.LENGTH_LONG);
		toast.setView(layout);
		toast.show();
		
		//----------------------------------
			if(col < 1){
				button.setClickable(false);
				button.setBackgroundResource(R.drawable.keypad1);
			}
		// --------------------------
		final Toast aToast = Toast.makeText(this, "Going Right!",
				Toast.LENGTH_LONG);
		aToast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
		final Toast bToast = Toast.makeText(this, "Going Left!",
				Toast.LENGTH_LONG);
		bToast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
		final Intent aIntent = new Intent(this, BudgetActivity.class);
		final Intent bIntent = new Intent(this, HomeActivity.class);

		mLayout.setOnTouchListener(new View.OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getX() > mLayout.getWidth() / 2) {
					buttonMusic.start();
					startActivity(aIntent);
					ListActivity.this.finish();
				}
				if (event.getX() < mLayout.getWidth() / 2) {
					buttonMusic.start();
					startActivity(bIntent);
					ListActivity.this.finish();
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

	@Override
	public void onClick(View view) {
		if (view == appHome) {
			buttonMusic.start();
			Intent intent = new Intent("com.muneebahmad.moneyman.HOME");
			overridePendingTransition(R.anim.pull_in_from_left, R.anim.hold);
			finish();
			startActivity(intent);
		}

		if (view == budget || view == right) {
			buttonMusic.start();
			Intent intent = new Intent("com.muneebahmad.moneyman.BUDGET");
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
			finish();
		}
		if (view == home) {
			buttonMusic.start();
			LayoutInflater mInflater = getLayoutInflater();
			View layout = mInflater.inflate(R.layout.toast2,
					(ViewGroup) findViewById(R.id.custom_toast2));
			TextView text = (TextView) layout.findViewById(R.id.toast_text2);
			text.setText("Listpage minimized!!");
			toast = new Toast(getApplicationContext());
			toast.setDuration(Toast.LENGTH_LONG);
			toast.setView(layout);
			toast.show();
			startActivity(intent);
		}
		if (view == back) {
			buttonMusic.start();
			Toast bud = Toast.makeText(this, "In Home Activity",
					Toast.LENGTH_SHORT);
			Intent intent = new Intent("com.muneebahmad.moneyman.HOME");
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			overridePendingTransition(R.anim.pull_in_from_left, R.anim.hold);
			finish();
			startActivity(intent);
		}
		if (view == graph) {
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
			startActivity(intent);
			finish();
		}
		if (view == button) {
			buttonMusic.start();
			intent = new Intent("com.muneebahmad.moneyman.DELETE");
			startActivity(intent);
			ListActivity.this.finish();
		}

		if (view == credits) {
			intent = new Intent(this, CreditsDialog.class);
			startActivity(intent);
		}
	}// end onClick()

	@Override
	public void onBackPressed() {
		buttonMusic.start();
		intent = new Intent(this, HomeActivity.class);
		startActivity(intent);
		finish();
	}
	@Override
	protected void onPause(){
		super.onPause();
		
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		buttonMusic.release();
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
}
/*
 * ==============================================================================
 * ================= ============================================ end class
 * ==========================================
 * ====================================
 * ==============================================================
 */
