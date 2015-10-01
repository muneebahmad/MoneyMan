package com.muneebahmad.moneyman;

/*
 * @author:MuneebAhmad
 * */

import org.muneebahmad.jlib.PwdDb;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Forget extends Activity implements OnClickListener {

	private Button close, update;
	private EditText user, pwd, secret;
	private PwdDb db = new PwdDb(this);
	private Toast toast;
	private Intent intent;
	private String id;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		overridePendingTransition(R.anim.pull_in_from_left, R.anim.pull_out_to_left);
		setContentView(R.layout.forget);

		close = (Button) findViewById(R.id.forget_user_cl);
		update = (Button) findViewById(R.id.forget_user_yes);

		user = (EditText) findViewById(R.id.forget_username);
		pwd = (EditText) findViewById(R.id.forget_pwd);
		secret = (EditText) findViewById(R.id.forget_secret_word);

		close.setOnClickListener(this);
		update.setOnClickListener(this);

		db.open();
		Cursor c = db.getAllRecords();
		c.moveToFirst();
		if (c.getCount() > 0) {
			user.setText(c.getString(1));
			id = c.getString(0);
		}
		
		db.close();

	}// end onCreate()

	@Override
	public void onClick(View view) {
		if (view == close) {
			overridePendingTransition(R.anim.pull_in_from_left, R.anim.hold);
			finish();
		}
		if (view == update) {
			String newPass, secr;
			db.open();
			Cursor c = db.getAllRecords();
			c.moveToFirst();
			newPass = pwd.getText().toString();
			secr = secret.getText().toString();
			if(!secr.equals(c.getString(3))) {
				LayoutInflater mInflater = getLayoutInflater();
				View layout = mInflater.inflate(R.layout.toast2,
						(ViewGroup) findViewById(R.id.custom_toast2));
				TextView text = (TextView) layout
						.findViewById(R.id.toast_text2);
				text.setText("Your provided secred word is wrong!!");
				toast = new Toast(getApplicationContext());
				toast.setDuration(Toast.LENGTH_LONG);
				toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
				toast.setView(layout);
				toast.show();
			}else if(newPass.isEmpty() || newPass.length() < 1){
				LayoutInflater mInflater = getLayoutInflater();
				View layout = mInflater.inflate(R.layout.toast2,
						(ViewGroup) findViewById(R.id.custom_toast2));
				TextView text = (TextView) layout
						.findViewById(R.id.toast_text2);
				text.setText("Please supply password, Field cannot go empty!!");
				toast = new Toast(getApplicationContext());
				toast.setDuration(Toast.LENGTH_LONG);
				toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
				toast.setView(layout);
				toast.show();
			}else {
				boolean hello = db.updateRecord(Integer.valueOf(id), user.getText().toString(),
						newPass, secr);
				db.close();
				LayoutInflater mInflater = getLayoutInflater();
				View layout = mInflater.inflate(R.layout.toast2,
						(ViewGroup) findViewById(R.id.custom_toast2));
				TextView text = (TextView) layout
						.findViewById(R.id.toast_text2);
				text.setText("User password has been updated successfully!!");
				toast = new Toast(getApplicationContext());
				toast.setDuration(Toast.LENGTH_LONG);
				toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
				toast.setView(layout);
				toast.show();
				intent = new Intent("com.muneebahmad.moneyman.HOME");
				overridePendingTransition(R.anim.pull_in_from_left, R.anim.hold);
				startActivity(intent);
				finish();
			} 
			db.close();
		}

	}// end onClick()

}// end class
