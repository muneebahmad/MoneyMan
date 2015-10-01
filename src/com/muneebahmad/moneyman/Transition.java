package com.muneebahmad.moneyman;

/*
 * @author:MuneebAhmad
 * */

import org.muneebahmad.jlib.PwdDb;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Window;

public class Transition extends Activity {

	private Intent intent;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		overridePendingTransition(R.anim.pull_in_from_left,
				R.anim.pull_out_to_left);
		setContentView(R.layout.transition);
		
		//calling check() method
		check();
	}//end onCreate()


	protected void check(){
		PwdDb db = new PwdDb(this);
		db.open();
		Cursor c = db.getAllRecords();
		if(c.getCount() > 0){
			intent = new Intent("com.muneebahmad.moneyman.PASSCHECK");
			finish();
			startActivity(intent);
		}else{
			intent = new Intent("com.muneebahmad.moneyman.HOME");
			finish();
			startActivity(intent);
		}
		db.close();
	}//end check()

}//end class
