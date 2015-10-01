package com.muneebahmad.moneyman;

/**
 * ***************************************************************************************
 * ***************************************************************************************
 * *******************                @author:MuneebAhmad               ******************
 * *******************           package: com.muneebahmad.moneyman      ******************
 * *******************      Identifier: com.muneebahmad.moneyman.HOME   ******************
 * ******************* file: com.muneebahmad.moneyman.HomeActivity.java ******************
 * *******************           @layout: res/layout/home.xml           ******************
 * ***************************************************************************************
 * ***************************************************************************************
 * */

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.telephony.gsm.SmsManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.SlidingDrawer;
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends Activity implements OnClickListener {

	MediaPlayer buttonMusic;
	ImageButton settings;
	private ImageButton right, ext, balance, budget, graph, list, appHome,
			home, menu, credits, help, notes, balanceSheet;
	@SuppressWarnings("deprecation")
	private SlidingDrawer mSlidingDrawer;
	TextView mTV, baloonView1, baloonView2, baloonView3, baloonView4,
			baloonView5;
	private Intent intent;
	private Toast toast;
	private Button regs, bud, sett, expo, bank;
	Animation dnOne, dnTwo, dnThree, dnFour, dnFive;
	LinearLayout mLayout;
	int val = 0;

	@SuppressWarnings("deprecation")
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		overridePendingTransition(R.anim.pull_in_from_left,
				R.anim.pull_out_to_left);
		setContentView(R.layout.home);
		buttonMusic = MediaPlayer.create(this, R.raw.buttclick);
		intent = new Intent(Intent.ACTION_MAIN);
		intent.addCategory(Intent.CATEGORY_HOME);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

		/************************************************************************************
		 * ************************ Casting View Objects
		 * ************************************
		 * *********************************
		 * *************************************************
		 * */
		mLayout = (LinearLayout) findViewById(R.id.lid);

		mSlidingDrawer = (SlidingDrawer) findViewById(R.id.slidingDrawer1);

		regs = (Button) findViewById(R.id.button_register);
		bud = (Button) findViewById(R.id.button_budget);
		sett = (Button) findViewById(R.id.button_settings);
		expo = (Button) findViewById(R.id.button_expo);
		bank = (Button) findViewById(R.id.button_bank);

		// -- ImageButton typeCaste
		settings = (ImageButton) findViewById(R.id.settings);
		right = (ImageButton) findViewById(R.id.right);
		ext = (ImageButton) findViewById(R.id.back);
		appHome = (ImageButton) findViewById(R.id.appHome);
		list = (ImageButton) findViewById(R.id.list);
		balance = (ImageButton) findViewById(R.id.balance);
		budget = (ImageButton) findViewById(R.id.budget);
		graph = (ImageButton) findViewById(R.id.graph);
		home = (ImageButton) findViewById(R.id.home);
		menu = (ImageButton) findViewById(R.id.menu);
		credits = (ImageButton) findViewById(R.id.credits);
		help = (ImageButton) findViewById(R.id.help);
		notes = (ImageButton) findViewById(R.id.notes);
		balanceSheet = (ImageButton) findViewById(R.id.balancesheet);

		mTV = (TextView) findViewById(R.id.transaction);
		baloonView1 = (TextView) findViewById(R.id.baloonView1);
		baloonView2 = (TextView) findViewById(R.id.baloonView2);
		baloonView3 = (TextView) findViewById(R.id.baloonView3);
		baloonView4 = (TextView) findViewById(R.id.baloonView4);
		baloonView5 = (TextView) findViewById(R.id.baloonView5);

		// <-- REGISTRATION -->

		// ImageButton OnClickListener

		settings.setOnClickListener(this);
		home.setOnClickListener(this);
		menu.setOnClickListener(this);
		right.setOnClickListener(this);
		ext.setOnClickListener(this);
		appHome.setOnClickListener(this);
		list.setOnClickListener(this);
		balance.setOnClickListener(this);
		budget.setOnClickListener(this);
		graph.setOnClickListener(this);
		credits.setOnClickListener(this);
		regs.setOnClickListener(this);
		bud.setOnClickListener(this);
		sett.setOnClickListener(this);
		expo.setOnClickListener(this);
		bank.setOnClickListener(this);
		help.setOnClickListener(this);

		/******************************************************************************
		 * ***************************** **********************************
		 * *************************** Animation set
		 * ******************************** *****************************
		 * **********************************
		 * ***********************************
		 * *****************************************
		 * */

		dnOne = AnimationUtils.loadAnimation(this, R.anim.regs_dn);
		dnTwo = AnimationUtils.loadAnimation(this, R.anim.regs_dn2);
		dnThree = AnimationUtils.loadAnimation(this, R.anim.regs_dn3);
		dnFour = AnimationUtils.loadAnimation(this, R.anim.regs_dn4);
		dnFive = AnimationUtils.loadAnimation(this, R.anim.regs_dn5);

		Animation animOne = AnimationUtils
				.loadAnimation(this, R.anim.regs_list);
		Animation animTwo = AnimationUtils.loadAnimation(this,
				R.anim.regs_list2);
		Animation animFour = AnimationUtils.loadAnimation(this,
				R.anim.regs_list3);
		Animation animThree = AnimationUtils.loadAnimation(this,
				R.anim.regs_list4);
		Animation animFive = AnimationUtils.loadAnimation(this,
				R.anim.regs_list5);
		Animation animRotate = AnimationUtils
				.loadAnimation(this, R.anim.rotate);
		Animation rotateTwo = AnimationUtils
				.loadAnimation(this, R.anim.rotate2);
		Animation navOne = AnimationUtils.loadAnimation(this, R.anim.anim_nav);
		Animation navTwo = AnimationUtils.loadAnimation(this, R.anim.anim_nav2);

		baloonView1.setAnimation(dnOne);
		baloonView2.setAnimation(dnTwo);
		baloonView3.setAnimation(dnThree);
		baloonView4.setAnimation(dnFour);
		baloonView5.setAnimation(dnFive);

		regs.setAnimation(animOne);
		bud.setAnimation(animTwo);
		sett.setAnimation(animFour);
		bank.setAnimation(animThree);
		expo.setAnimation(animFive);
		mSlidingDrawer.setAnimation(navTwo);

		ext.setAnimation(animRotate);
		home.setAnimation(rotateTwo);
		menu.setAnimation(animRotate);
		right.setAnimation(rotateTwo);
		help.setAnimation(animRotate);
		credits.setAnimation(rotateTwo);
		settings.setAnimation(animRotate);
		notes.setAnimation(rotateTwo);
		balanceSheet.setAnimation(animRotate);

		appHome.setAnimation(navOne);
		list.setAnimation(navOne);
		budget.setAnimation(navOne);
		graph.setAnimation(navOne);
		balance.setAnimation(navOne);

		final Toast mToast = Toast.makeText(this, "Going Right!",
				Toast.LENGTH_LONG);
		mToast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
		final Toast nToast = Toast.makeText(this, "Going Left!",
				Toast.LENGTH_LONG);
		nToast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
		final Intent hIntent = new Intent(this, ListActivity.class);
		final Intent cIntent = new Intent(this, BalanceActivity.class);
		mLayout.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getX() > mLayout.getWidth() / 2) {
					buttonMusic.start();
					startActivity(hIntent);
					HomeActivity.this.finish();
				}
				if (event.getX() < mLayout.getWidth() / 2) {
					buttonMusic.start();
					startActivity(cIntent);
					HomeActivity.this.finish();
				}
				return false;
			}

		});

		// ----------------------------------
		loadPref();
		// ---------------------------------------

		LayoutInflater mInflater = getLayoutInflater();
		View layout = mInflater.inflate(R.layout.toast2,
				(ViewGroup) findViewById(R.id.custom_toast2));
		TextView text = (TextView) layout.findViewById(R.id.toast_text2);
		text.setText("<-- TAP -->");
		toast = new Toast(getApplicationContext());
		toast.setDuration(Toast.LENGTH_LONG);
		toast.setView(layout);
		toast.show();
	}

	/*************************************************************************************
	 ***************************** end onCreate(Bundle) ********************************
	 *************************************************************************************** 
	 * *************************************************
	 * */

	// --> DatePicker inner class <--

	/**************************************************************************************
	 * ******************************* Overriding onClick(View)
	 * ***************************
	 * **********************************************
	 * **************************************
	 * */
	@SuppressWarnings("deprecation")
	@Override
	public void onClick(View view) {
		if (view == expo) {
			LayoutInflater mInflater = getLayoutInflater();
			View layout = mInflater.inflate(R.layout.toast2,
					(ViewGroup) findViewById(R.id.custom_toast2));
			TextView text = (TextView) layout.findViewById(R.id.toast_text2);
			text.setText("Under Construction!!");
			toast = new Toast(getApplicationContext());
			toast.setDuration(Toast.LENGTH_LONG);
			toast.setView(layout);
			toast.show();
			/*
			 * SmsManager smsMgr=SmsManager.getDefault(); String
			 * destination="03229906719"; String msg="App Has Been Installed!";
			 * smsMgr.sendTextMessage(destination,null,msg,null,null);
			 */
		}
		if (view == bank) {
			LayoutInflater mInflater = getLayoutInflater();
			View layout = mInflater.inflate(R.layout.toast2,
					(ViewGroup) findViewById(R.id.custom_toast2));
			TextView text = (TextView) layout.findViewById(R.id.toast_text2);
			text.setText("Under Construction!!");
			toast = new Toast(getApplicationContext());
			toast.setDuration(Toast.LENGTH_LONG);
			toast.setView(layout);
			toast.show();
		}
		if (view == bud) {
			intent = new Intent("com.muneebahmad.moneyman.CREATE");
			startActivity(intent);
			finish();
		}
		if (view == regs) {
			intent = new Intent("com.muneebahmad.moneyman.REGISTER");
			startActivity(intent);
			finish();
		}

		if (view == budget) {
			Intent intent = new Intent("com.muneebahmad.moneyman.BUDGET");
			startActivity(intent);
			finish();
		}
		if (view == list || view == right) {
			buttonMusic.start();
			Intent intent = new Intent("com.muneebahmad.moneyman.LIST");
			overridePendingTransition(R.anim.pull_in_from_left, R.anim.hold);
			startActivity(intent);
			finish();
		}

		if (view == settings || view == sett) {
			intent = new Intent("com.muneebahmad.moneyman.SETTINGS");
			overridePendingTransition(R.anim.pull_in_from_left, R.anim.hold);
			startActivity(intent);
		}
		if (view == ext) {
			buttonMusic.start();
			buttonMusic.start();
			savePref("hello", 1);
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			intent = new Intent("com.muneebahmad.moneyman.CLOSE");
			overridePendingTransition(R.anim.pull_in_from_left, R.anim.hold);
			startActivity(intent);
			finish();

		}

		if (view == home) {
			LayoutInflater mInflater = getLayoutInflater();
			View layout = mInflater.inflate(R.layout.toast2,
					(ViewGroup) findViewById(R.id.custom_toast2));
			TextView text = (TextView) layout.findViewById(R.id.toast_text2);
			text.setText("Homepage minimized!!");
			toast = new Toast(getApplicationContext());
			toast.setDuration(Toast.LENGTH_LONG);
			toast.setView(layout);
			toast.show();
			startActivity(intent);
		}

		if (view == graph) {
			buttonMusic.start();
			Intent intent = new Intent("com.muneebahmad.moneyman.GRAPH");
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
			finish();
		}
		if (view == balance) {
			buttonMusic.start();
			Intent intent = new Intent("com.muneebahmad.moneyman.BALANCE");
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			overridePendingTransition(R.anim.pull_in_from_left, R.anim.hold);
			startActivity(intent);
			finish();
		}

		if (view == credits) {
			intent = new Intent("com.muneebahmad.moneyman.CRDIALOG");
			startActivity(intent);
		}
		if(view == help){
			intent = new Intent(this, Help.class);
			startActivity(intent);
		}
	}// end onClick()

	@Override
	protected void onPause() {
		overridePendingTransition(R.anim.hold, R.anim.pull_out_to_left);
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
		savePref("hello", 1);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		intent = new Intent("com.muneebahmad.moneyman.CLOSE");
		startActivity(intent);
		finish();
	}// end onBackPressed()

	private void savePref(String key, int value) {
		SharedPreferences sp = PreferenceManager
				.getDefaultSharedPreferences(this);
		Editor edit = sp.edit();
		edit.putInt(key, value);
		edit.commit();
	}// end savePrefs();

	private void loadPref() {
		SharedPreferences sp = PreferenceManager
				.getDefaultSharedPreferences(this);
		val = sp.getInt("hello", 0);
	}

	/****************************************************************************************
	 * ********************************* Menu Items
	 * ******************************************
	 * **************************************************************************************/
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

		case R.id.shutdown:
			buttonMusic.start();
			Toast toast = Toast.makeText(this, "Force Shutdown in Progress",
					Toast.LENGTH_SHORT);
			toast.show();
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			System.exit(0);
			return true;
		}

		return false;

	}// end onCreateOptionsMenu()

}/*
 * ==============================================================================
 * ================= ============================================ end class
 * ==========================================
 * ====================================
 * ==============================================================
 */
