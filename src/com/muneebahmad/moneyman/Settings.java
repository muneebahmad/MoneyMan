package com.muneebahmad.moneyman;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class Settings extends Activity implements OnClickListener{

	private Button close, pwd, sound;
	private ImageView mImage, nImage;
	private Intent intent;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		overridePendingTransition(R.anim.pull_in_from_left, R.anim.pull_out_to_left);
		setContentView(R.layout.settings);
		
		close = (Button) findViewById(R.id.settings_cl);
		pwd = (Button) findViewById(R.id.settings_password);
		sound = (Button) findViewById(R.id.settings_volume);
		mImage = (ImageView) findViewById(R.id.sett_image1);
		nImage = (ImageView) findViewById(R.id.sett_image2);
		
		close.setOnClickListener(this);
		pwd.setOnClickListener(this);
		sound.setOnClickListener(this);
		
		Animation animOne = AnimationUtils.loadAnimation(this, R.anim.sett_anim);
		Animation animTwo = AnimationUtils.loadAnimation(this, R.anim.sett_anim2);
		Animation animList1 = AnimationUtils.loadAnimation(this, R.anim.regs_list);
		Animation animList2 = AnimationUtils.loadAnimation(this, R.anim.regs_list2);
		mImage.setAnimation(animOne);
		nImage.setAnimation(animTwo);
		pwd.setAnimation(animList1);
		sound.setAnimation(animList2);
	}//end onCreate()

	@Override
	public void onClick(View arg0) {
		if(arg0 == close){
			finish();
		}
		if(arg0 == pwd){
			intent = new Intent("com.muneebahmad.moneyman.USER");
			startActivity(intent);
			finish();
		}
		if(arg0 == sound){
			
		}
		
	}//end onClick()
	
	

}//end class
