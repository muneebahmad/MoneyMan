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

public class UserEdit extends Activity implements OnClickListener {

	private Button close, update, delete;
	private EditText user, pwd, secret;
	private PwdDb db = new PwdDb(this);
	private Toast toast;
	String id;
	private Intent intent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		overridePendingTransition(R.anim.pull_in_from_left,
				R.anim.pull_out_to_left);
		setContentView(R.layout.edit_user);

		close = (Button) findViewById(R.id.edit_user_cl);
		update = (Button) findViewById(R.id.edit_user_yes);
		delete = (Button) findViewById(R.id.edit_user_no);

		user = (EditText) findViewById(R.id.edit_username);
		pwd = (EditText) findViewById(R.id.edit_pwd);
		secret = (EditText) findViewById(R.id.edit_secret_word);

		close.setOnClickListener(this);
		update.setOnClickListener(this);
		delete.setOnClickListener(this);

		db.open();
		Cursor c = db.getAllRecords();
		c.moveToFirst();
		if (c.getCount() > 0) {
			user.setText(c.getString(1));
			pwd.setText(c.getString(2));
			secret.setText(c.getString(3));
			id = c.getString(0);
		}
		db.close();

	}// end onCreate()

	@Override
	public void onClick(View view) {
		if (view == close) {
			overridePendingTransition(R.anim.pull_in_from_left, R.anim.hold);
			intent = new Intent(this, User.class);
			startActivity(intent);
			finish();
		}
		if (view == update) {
			String name, pass, secr;
			name = user.getText().toString();
			pass = pwd.getText().toString();
			secr = secret.getText().toString();
			if (name.isEmpty() || name.length() < 1) {
				LayoutInflater mInflater = getLayoutInflater();
				View layout = mInflater.inflate(R.layout.toast2,
						(ViewGroup) findViewById(R.id.custom_toast2));
				TextView text = (TextView) layout
						.findViewById(R.id.toast_text2);
				text.setText("User Name required..!!");
				toast = new Toast(getApplicationContext());
				toast.setDuration(Toast.LENGTH_LONG);
				toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
				toast.setView(layout);
				toast.show();
			} else if (pass.isEmpty() || pass.length() < 1) {
				LayoutInflater mInflater = getLayoutInflater();
				View layout = mInflater.inflate(R.layout.toast2,
						(ViewGroup) findViewById(R.id.custom_toast2));
				TextView text = (TextView) layout
						.findViewById(R.id.toast_text2);
				text.setText("Supply password, it's necessary!!");
				toast = new Toast(getApplicationContext());
				toast.setDuration(Toast.LENGTH_LONG);
				toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
				toast.setView(layout);
				toast.show();
			} else if (secr.isEmpty() || secr.length() < 1) {
				LayoutInflater mInflater = getLayoutInflater();
				View layout = mInflater.inflate(R.layout.toast2,
						(ViewGroup) findViewById(R.id.custom_toast2));
				TextView text = (TextView) layout
						.findViewById(R.id.toast_text2);
				text.setText("Please Supply a valid Secret Word! that you"
						+ " can remember" + " it's helpful if you"
						+ " forget your password!!");
				toast = new Toast(getApplicationContext());
				toast.setDuration(Toast.LENGTH_LONG);
				toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
				toast.setView(layout);
				toast.show();
			} else {
				db.open();
				boolean hello = db.updateRecord(Integer.valueOf(id), name,
						pass, secr);
				db.close();
				LayoutInflater mInflater = getLayoutInflater();
				View layout = mInflater.inflate(R.layout.toast2,
						(ViewGroup) findViewById(R.id.custom_toast2));
				TextView text = (TextView) layout
						.findViewById(R.id.toast_text2);
				text.setText("User ["+ name + "] Updated Successfully!!");
				toast = new Toast(getApplicationContext());
				toast.setDuration(Toast.LENGTH_LONG);
				toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
				toast.setView(layout);
				toast.show();
				intent = new Intent("com.muneebahmad.moneyman.USER");
				overridePendingTransition(R.anim.pull_in_from_left, R.anim.hold);
				startActivity(intent);
				finish();
			}// end else
		}
		if (view == delete) {
			db.open();
			if (db.deleteRecord(Integer.valueOf(id))) {
				LayoutInflater mInflater = getLayoutInflater();
				View layout = mInflater.inflate(R.layout.toast2,
						(ViewGroup) findViewById(R.id.custom_toast2));
				TextView text = (TextView) layout
						.findViewById(R.id.toast_text2);
				text.setText("User Deleted Successfully!!");
				toast = new Toast(getApplicationContext());
				toast.setDuration(Toast.LENGTH_LONG);
				toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
				toast.setView(layout);
				toast.show();
				intent = new Intent("com.muneebahmad.moneyman.USER");
				overridePendingTransition(R.anim.pull_in_from_left, R.anim.hold);
				startActivity(intent);
				finish();
			} else {
				LayoutInflater mInflater = getLayoutInflater();
				View layout = mInflater.inflate(R.layout.toast2,
						(ViewGroup) findViewById(R.id.custom_toast2));
				TextView text = (TextView) layout
						.findViewById(R.id.toast_text2);
				text.setText("User cannot be Deleted Successfully!!");
				toast = new Toast(getApplicationContext());
				toast.setDuration(Toast.LENGTH_LONG);
				toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
				toast.setView(layout);
				toast.show();
			}
			db.close();
		}
	}// end onClick()

}// end class
