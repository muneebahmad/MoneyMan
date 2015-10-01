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

public class User extends Activity implements OnClickListener {
	private Button close, create, quit;
	private EditText uName, pwd1, pwd2, secret;
	private Toast toast;
	private Intent intent;
	String name, pass1, pass2, sec;
	PwdDb db = new PwdDb(this);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		overridePendingTransition(R.anim.pull_in_from_left, R.anim.pull_out_to_left);
		setContentView(R.layout.user);

		close = (Button) findViewById(R.id.user_cl);
		create = (Button) findViewById(R.id.user_yes);
		quit = (Button) findViewById(R.id.user_no);

		uName = (EditText) findViewById(R.id.username);
		pwd1 = (EditText) findViewById(R.id.pwd);
		pwd2 = (EditText) findViewById(R.id.confirm_pwd);
		secret = (EditText) findViewById(R.id.secret_word);

		close.setOnClickListener(this);
		create.setOnClickListener(this);
		quit.setOnClickListener(this);

		db.open();
		Cursor c = db.getAllRecords();
		if (c.getCount() > 0) {
			c.moveToFirst();
			create.setClickable(false);
			create.setBackgroundResource(R.drawable.keypad1);
			LayoutInflater mInflater = getLayoutInflater();
			View layout = mInflater.inflate(R.layout.toast2,
					(ViewGroup) findViewById(R.id.custom_toast2));
			TextView text = (TextView) layout
					.findViewById(R.id.toast_text2);
			text.setText("There is already a user created with name ["
					+ c.getString(1)+"]");
			toast = new Toast(getApplicationContext());
			toast.setDuration(Toast.LENGTH_LONG);
			toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
			toast.setView(layout);
			toast.show();
		} else if (c.getCount() < 1) {
			quit.setClickable(false);
			quit.setBackgroundResource(R.drawable.keypad1);
		}
		db.close();

	}// end onCreate()

	@Override
	public void onClick(View view) {
		if (view == close) {
			overridePendingTransition(R.anim.pull_in_from_left, R.anim.hold);
			intent = new Intent(this, Settings.class);
			startActivity(intent);
			finish();
		}
		if (view == quit) {
			intent = new Intent("com.muneebahmad.moneyman.USEREDIT");
			overridePendingTransition(R.anim.pull_in_from_left, R.anim.hold);
			startActivity(intent);
			finish();
		}
		if (view == create) {
			name = uName.getText().toString();
			pass1 = pwd1.getText().toString();
			pass2 = pwd2.getText().toString();
			sec = secret.getText().toString();
			if (!pass1.equals(pass2)) {
				LayoutInflater mInflater = getLayoutInflater();
				View layout = mInflater.inflate(R.layout.toast2,
						(ViewGroup) findViewById(R.id.custom_toast2));
				TextView text = (TextView) layout
						.findViewById(R.id.toast_text2);
				text.setText("User: [" + name
						+ "] cannot be created!! your supplied passwords do not match!");
				toast = new Toast(getApplicationContext());
				toast.setDuration(Toast.LENGTH_LONG);
				toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
				toast.setView(layout);
				toast.show();
			} else if (name.isEmpty() || name.length() < 1) {
				LayoutInflater mInflater = getLayoutInflater();
				View layout = mInflater.inflate(R.layout.toast2,
						(ViewGroup) findViewById(R.id.custom_toast2));
				TextView text = (TextView) layout
						.findViewById(R.id.toast_text2);
				text.setText("User: [~empty]" + name
						+ " cannot be created!! Username cannot go blank!!");
				toast = new Toast(getApplicationContext());
				toast.setDuration(Toast.LENGTH_LONG);
				toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
				toast.setView(layout);
				toast.show();
			} else if (pass1.isEmpty() || pass1.length() < 1) {
				LayoutInflater mInflater = getLayoutInflater();
				View layout = mInflater.inflate(R.layout.toast2,
						(ViewGroup) findViewById(R.id.custom_toast2));
				TextView text = (TextView) layout
						.findViewById(R.id.toast_text2);
				text.setText("User: ["
						+ name
						+ "] cannot be created!! no password!! Are you kidding?");
				toast = new Toast(getApplicationContext());
				toast.setDuration(Toast.LENGTH_LONG);
				toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
				toast.setView(layout);
				toast.show();

			} else if (sec.isEmpty() || sec.length() < 1) {
				LayoutInflater mInflater = getLayoutInflater();
				View layout = mInflater.inflate(R.layout.toast2,
						(ViewGroup) findViewById(R.id.custom_toast2));
				TextView text = (TextView) layout
						.findViewById(R.id.toast_text2);
				text.setText("User: ["
						+ name
						+ "] cannot be created!! Supply Secret Word! It is necessary!!");
				toast = new Toast(getApplicationContext());
				toast.setDuration(Toast.LENGTH_LONG);
				toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
				toast.setView(layout);
				toast.show();
			}else {
				db.open();
				long id = db.insertRecord(name, pass1, sec);
				db.close();
				LayoutInflater mInflater = getLayoutInflater();
				View layout = mInflater.inflate(R.layout.toast2,
						(ViewGroup) findViewById(R.id.custom_toast2));
				TextView text = (TextView) layout
						.findViewById(R.id.toast_text2);
				text.setText("User: " + name
						+ " created Successfully!");
				toast = new Toast(getApplicationContext());
				toast.setDuration(Toast.LENGTH_LONG);
				toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
				toast.setView(layout);
				toast.show();

				intent = new Intent(this, Settings.class);
				startActivity(intent);
				finish();
			}
		}
	}// end onClick()
}// end class
