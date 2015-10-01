package com.muneebahmad.moneyman;

/**
 * ***************************************************************************************
 * ***************************************************************************************
 * ******************                 @author:MuneebAhmad                 ****************
 * ******************            package: com.muneebahmad.moneyman        ****************
 * ******************      Identifier: com.muneebahmad.moneyman.BALANCE   ****************
 * ****************** file: com.muneebahmad.moneyman.BalanceActivity.java ****************
 * ******************           @layout: res/layout/balance.xml           ****************
 * ***************************************************************************************
 * ***************************************************************************************
 * */

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
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
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.SlidingDrawer;
import android.widget.TextView;
import android.widget.Toast;

public class BalanceActivity extends Activity implements OnClickListener{
	private MediaPlayer buttonMusic;
	private ImageButton balance, budget, graph, list, appHome,
	home, menu, credits, help, notes, balanceSheet, settings, back;
	@SuppressWarnings("deprecation")
	private SlidingDrawer mSlidingDrawer;
	Intent intent;
	private Toast toast;
	LinearLayout mLayout;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		overridePendingTransition(R.anim.pull_in_from_left, R.anim.pull_out_to_left);
		setContentView(R.layout.balance);
		
		
		buttonMusic=MediaPlayer.create(this, R.raw.buttclick);
		
		intent = new Intent(Intent.ACTION_MAIN);
	    intent.addCategory(Intent.CATEGORY_HOME);
	    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	    
	    mSlidingDrawer = (SlidingDrawer) findViewById(R.id.slidingDrawer1);
	    mLayout = (LinearLayout) findViewById(R.id.balance_touch);
		
	    //ImageButton
	    
		settings=(ImageButton)findViewById(R.id.settings);
		back=(ImageButton)findViewById(R.id.back);
		home=(ImageButton)findViewById(R.id.home);
		menu=(ImageButton)findViewById(R.id.menu);
		appHome=(ImageButton)findViewById(R.id.appHome);
		balance=(ImageButton)findViewById(R.id.balance);
		budget=(ImageButton)findViewById(R.id.budget);
		graph=(ImageButton)findViewById(R.id.graph);
		list=(ImageButton)findViewById(R.id.list);
		credits = (ImageButton) findViewById(R.id.credits);
		help = (ImageButton) findViewById(R.id.help);
		notes = (ImageButton) findViewById(R.id.notes);
		balanceSheet = (ImageButton) findViewById(R.id.balancesheet);
	
	//                      <---  Registration --->
		
		//ImageButton
	settings.setOnClickListener(this);
	back.setOnClickListener(this);
	home.setOnClickListener(this);
	menu.setOnClickListener(this);
	balance.setOnClickListener(this);
	budget.setOnClickListener(this);
	graph.setOnClickListener(this);
	list.setOnClickListener(this);
	appHome.setOnClickListener(this);
	credits.setOnClickListener(this);
	
	/**
	 * *************************************************************************************
	 * ****************************** <-- View Animations --> ******************************
	 * *************************************************************************************
	 * */
	Animation animRotate = AnimationUtils.loadAnimation(this, R.anim.rotate);
	Animation rotateTwo = AnimationUtils.loadAnimation(this, R.anim.rotate2);
	Animation navOne = AnimationUtils.loadAnimation(this, R.anim.anim_nav);
	Animation navTwo = AnimationUtils.loadAnimation(this, R.anim.anim_nav2);
	
	mSlidingDrawer.setAnimation(navTwo);
	
	appHome.setAnimation(navOne);
	list.setAnimation(navOne);
	budget.setAnimation(navOne);
	graph.setAnimation(navOne);
	balance.setAnimation(navOne);
	
	back.setAnimation(animRotate);
	home.setAnimation(rotateTwo);
	menu.setAnimation(animRotate);
	help.setAnimation(animRotate);
	credits.setAnimation(rotateTwo);
	settings.setAnimation(animRotate);
	notes.setAnimation(rotateTwo);
	balanceSheet.setAnimation(animRotate);
	
	toast = Toast.makeText(this, "Under Construction!!", Toast.LENGTH_LONG);
	toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
	toast.show();
	
	//--------------------------------
			final Toast mToast = Toast.makeText(this, "Going Right!", Toast.LENGTH_LONG);
			mToast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
			final Toast nToast = Toast.makeText(this, "Going Left!", Toast.LENGTH_LONG);
			nToast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
			final Intent hIntent = new Intent(this, HomeActivity.class);
			final Intent cIntent = new Intent(this, GraphActivity.class);
			mLayout.setOnTouchListener(new OnTouchListener(){

				@Override
				public boolean onTouch(View v, MotionEvent event) {
					if(event.getX() > mLayout.getWidth()/2){
						buttonMusic.start();
					startActivity(hIntent);
					BalanceActivity.this.finish();
					}
					if(event.getX() < mLayout.getWidth()/2){
						buttonMusic.start();
						startActivity(cIntent);
						BalanceActivity.this.finish();
					}
					return false;
				}
			});
	
	}/**
	*********************************************************************************************
	******************************* --> end onCreate(Bundle) <-- ********************************
	*********************************************************************************************
	**/
	
	/**
	 * ******************************************************************************************
	 * ***********************     <--- Overriding onClick(View) --->     ***********************
	 * ******************************************************************************************
	 * */
	
	@Override
	public void onClick(View view) {
		
		if(view == appHome){
			buttonMusic.start();
			Intent intent=new Intent("com.muneebahmad.moneyman.HOME");
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			overridePendingTransition(R.anim.pull_in_from_left, R.anim.hold);
			startActivity(intent);
			finish();
		}

		if(view == list){
			buttonMusic.start();
			Toast lst=Toast.makeText(this,"In list Activity",Toast.LENGTH_SHORT);
			Intent intent=new Intent("com.muneebahmad.moneyman.LIST");
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			overridePendingTransition(R.anim.pull_in_from_left, R.anim.hold);
			startActivity(intent);
			finish();
		}
			if(view == home){
				buttonMusic.start();
				LayoutInflater mInflater = getLayoutInflater();
				View layout = mInflater.inflate(R.layout.toast2,
						(ViewGroup) findViewById(R.id.custom_toast2));
				TextView text = (TextView) layout.findViewById(R.id.toast_text2);
				text.setText("Balancepage minimized!!");
				toast = new Toast(getApplicationContext());
				toast.setDuration(Toast.LENGTH_LONG);
				toast.setView(layout);
				toast.show();
		    startActivity(intent);
		}
			if(view == back || view == graph){
				buttonMusic.start();
				Toast bud=Toast.makeText(this,"In Graph Activity",Toast.LENGTH_SHORT);
				Intent intent=new Intent("com.muneebahmad.moneyman.GRAPH");
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				overridePendingTransition(R.anim.pull_in_from_left, R.anim.hold);
				startActivity(intent);
				finish();
			}
			if(view == budget){
				buttonMusic.start();
				Toast lst=Toast.makeText(this,"In budget Activity",Toast.LENGTH_SHORT);
				Intent intent=new Intent("com.muneebahmad.moneyman.BUDGET");
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
	protected void onPause(){
		super.onPause();
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		buttonMusic.release();
	}
	
	@Override
	public void onBackPressed(){
		buttonMusic.start();
		intent = new Intent(this, GraphActivity.class);
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
	public boolean onOptionsItemSelected(MenuItem item){
		switch (item.getItemId()){
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

}/**
=================================================================================================
=======================                       end class                      ====================
=================================================================================================
 */
