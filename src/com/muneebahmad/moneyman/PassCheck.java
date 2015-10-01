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

public class PassCheck extends Activity implements OnClickListener {

	private Button close, login, forget;
	private EditText uName, pass;
	String name, pwd, rName, rPass;
	private Intent intent;
	private Toast toast;
	private TextView tView;
	PwdDb db = new PwdDb(this);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		overridePendingTransition(R.anim.pull_in_from_left, R.anim.pull_out_to_left);
		setContentView(R.layout.pass);

		close = (Button) findViewById(R.id.pass_cl);
		login = (Button) findViewById(R.id.pass_yes);
		forget = (Button) findViewById(R.id.pass_no);
		uName = (EditText) findViewById(R.id.pass_username);
		pass = (EditText) findViewById(R.id.pass_pwd);
		tView = (TextView) findViewById(R.id.pass_title);

		close.setOnClickListener(this);
		login.setOnClickListener(this);
		forget.setOnClickListener(this);

		db.open();
		Cursor mCursor = db.getAllRecords();
		mCursor.moveToFirst();
		rName = mCursor.getString(1);
		if(mCursor.getCount() > 0){
			uName.setClickable(false);
			uName.setBackgroundResource(R.drawable.keypad1);
		}
		db.close();
		tView.setText(rName + " @ MoneyMan");
		uName.setText(rName);
	}// end onCreate()

	@Override
	public void onClick(View v) {
		if (v == close) {
			overridePendingTransition(R.anim.pull_in_from_left, R.anim.hold);
			finish();
		}
		if (v == login) {
			name = uName.getText().toString();
			pwd = pass.getText().toString();
			db.open();
			Cursor mCursor = db.getAllRecords();
			mCursor.moveToFirst();
			rName = mCursor.getString(1);
			rPass = mCursor.getString(2);
			db.close();
			if (rPass.equals(pwd)) {
				LayoutInflater mInflater = getLayoutInflater();
				View layout = mInflater.inflate(R.layout.toast2,
						(ViewGroup) findViewById(R.id.custom_toast2));
				TextView text = (TextView) layout
						.findViewById(R.id.toast_text2);
				text.setText("Login in progress!!");
				toast = new Toast(getApplicationContext());
				toast.setDuration(Toast.LENGTH_LONG);
				toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
				toast.setView(layout);
				toast.show();
				intent = new Intent("com.muneebahmad.moneyman.HOME");
				overridePendingTransition(R.anim.pull_in_from_left, R.anim.hold);
				startActivity(intent);
				finish();
			} else {
				LayoutInflater mInflater = getLayoutInflater();
				View layout = mInflater.inflate(R.layout.toast2,
						(ViewGroup) findViewById(R.id.custom_toast2));
				TextView text = (TextView) layout
						.findViewById(R.id.toast_text2);
				text.setText("for user: [" + rName
						+ "] Please Supply valid password!");
				toast = new Toast(getApplicationContext());
				toast.setDuration(Toast.LENGTH_LONG);
				toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
				toast.setView(layout);
				toast.show();
			}
		}
		if (v == forget) {
			intent = new Intent("com.muneebahmad.moneyman.FORGET");
			startActivity(intent);
			finish();
		}

	}// end onClick()

}// end class
