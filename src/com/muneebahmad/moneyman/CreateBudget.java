package com.muneebahmad.moneyman;

import org.muneebahmad.jlib.BudgetDB;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/*
 * @author:MuneebAhmad
 * */

public class CreateBudget extends Activity implements OnClickListener {
	private Button done, cl, edit;
	private Spinner spin, spin2;
	private EditText name, amount;
	private Toast toast;
	String time, curr, nM, aM;
	BudgetDB db = new BudgetDB(this);
	private Intent intent;
	int position, val;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		overridePendingTransition(R.anim.pull_in_from_left, R.anim.pull_out_to_left);
		setContentView(R.layout.create_budget);

		name = (EditText) findViewById(R.id.create_budget_name);
		amount = (EditText) findViewById(R.id.create_budget_amount);
		spin = (Spinner) findViewById(R.id.create_budget_spinner);
		spin2 = (Spinner) findViewById(R.id.create_budget_spinner2);
		done = (Button) findViewById(R.id.create_budget_ok);
		cl = (Button) findViewById(R.id.create_budget_cl);
		edit = (Button) findViewById(R.id.create_budget_edit);
		done.setOnClickListener(this);
		edit.setOnClickListener(this);
		cl.setOnClickListener(this);

		// timeline spinner
		spin.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int pos, long id) {
				Object item = parent.getItemAtPosition(pos);
				time = item.toString();
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {

			}

		});// end OnItemSelectedListener()

		// currency spinner
		spin2.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int pos, long id) {
				Object item = parent.getItemAtPosition(pos);
				curr = item.toString();
				position = pos;
				if (pos == 0) {
					spin2.setSelection(val);
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {

			}

		});// end OnItemSelectedListener()
		try{
		db.open();
		Cursor c = db.getAllRecords();
		if (c.getCount() < 1) {
			c.moveToFirst();
			edit.setClickable(false);
			edit.setBackgroundResource(R.drawable.keypad1);
		} else if (c.getCount() > 0) {
			done.setClickable(false);
			done.setBackgroundResource(R.drawable.keypad1);
			LayoutInflater mInflater = getLayoutInflater();
			View layout = mInflater.inflate(R.layout.toast2,
					(ViewGroup) findViewById(R.id.custom_toast2));
			TextView text = (TextView) layout
					.findViewById(R.id.toast_text2);
			text.setText( "There is already a budget [" + c.getString(1)
					+ "] edit or delete to continue!");
			toast = new Toast(getApplicationContext());
			toast.setDuration(Toast.LENGTH_LONG);
			toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
			toast.setView(layout);
			toast.show();
		}
		db.close();
		}catch(CursorIndexOutOfBoundsException ex){
			ex.printStackTrace();
		}
		
		//------------------------
		loadPrefs();
		//-------------------------------

	}// end <------------- end onCreate() ------------>
	
	private void savePrefs(String key, int value) {
		SharedPreferences sp = PreferenceManager
				.getDefaultSharedPreferences(this);
		Editor edit = sp.edit();
		edit.putInt(key, value);
		edit.commit();
	}// end savePrefs();
	
	private void loadPrefs() {
		SharedPreferences sp = PreferenceManager
				.getDefaultSharedPreferences(this);
		val = sp.getInt("Val", 0);
	}

	@Override
	public void onClick(View view) {

		if (view == done) {
			nM = name.getText().toString();
			aM = amount.getText().toString();
			if(nM.isEmpty() || nM.length() < 1){
				LayoutInflater mInflater = getLayoutInflater();
				View layout = mInflater.inflate(R.layout.toast2,
						(ViewGroup) findViewById(R.id.custom_toast2));
				TextView text = (TextView) layout
						.findViewById(R.id.toast_text2);
				text.setText("You have to provide a Name for Budget!!");
				toast = new Toast(getApplicationContext());
				toast.setDuration(Toast.LENGTH_LONG);
				toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
				toast.setView(layout);
				toast.show();
			}else if(aM.isEmpty() || aM.length() < 1){
				LayoutInflater mInflater = getLayoutInflater();
				View layout = mInflater.inflate(R.layout.toast2,
						(ViewGroup) findViewById(R.id.custom_toast2));
				TextView text = (TextView) layout
						.findViewById(R.id.toast_text2);
				text.setText("Please Describe Amount for your Budget");
				toast = new Toast(getApplicationContext());
				toast.setDuration(Toast.LENGTH_LONG);
				toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
				toast.setView(layout);
				toast.show();
			}else{
			db.open();
			long id = db.insertRecord(nM, curr, aM, time);
			LayoutInflater mInflater = getLayoutInflater();
			View layout = mInflater.inflate(R.layout.toast2,
					(ViewGroup) findViewById(R.id.custom_toast2));
			TextView text = (TextView) layout
					.findViewById(R.id.toast_text2);
			text.setText("Budget: [" + nM + "] of [" + curr
					+ "] [" + aM + "] created Successfully!!");
			toast = new Toast(getApplicationContext());
			toast.setDuration(Toast.LENGTH_LONG);
			toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
			toast.setView(layout);
			toast.show();
			db.close();
			}
			savePrefs("Val", position);
			intent = new Intent(this, BudgetActivity.class);
			startActivity(intent);
			finish();
		}
		if (view == cl) {
			savePrefs("Val", position);
			intent = new Intent(this, BudgetActivity.class);
			startActivity(intent);
			finish();
		}
		if (view == edit) {
			savePrefs("Val", position);
			intent = new Intent(this, EditBudget.class);
			startActivity(intent);
			finish();
		}
	}// end onClick()
	
	

}// <----------------- end class ----------------->
