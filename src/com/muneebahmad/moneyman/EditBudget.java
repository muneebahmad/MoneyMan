package com.muneebahmad.moneyman;

import org.muneebahmad.jlib.BudgetDB;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
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

public class EditBudget extends Activity implements OnClickListener {
	private Button done, cl, edit;
	private Spinner spin, spin2;
	private EditText name, amount;
	private Toast toast;
	String time, curr, nM, aM;
	private Intent intent;
	BudgetDB db = new BudgetDB(this);
	String id;
	int position, val;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		overridePendingTransition(R.anim.pull_in_from_left, R.anim.pull_out_to_left);
		setContentView(R.layout.budget_edit);

		name = (EditText) findViewById(R.id.budget_edit_name);
		amount = (EditText) findViewById(R.id.budget_edit_amount);
		spin = (Spinner) findViewById(R.id.budget_edit_spinner);
		spin2 = (Spinner) findViewById(R.id.budget_edit_spinner2);
		done = (Button) findViewById(R.id.budget_edit_save);
		cl = (Button) findViewById(R.id.budget_edit_cl);
		edit = (Button) findViewById(R.id.budget_edit_delete);
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

		db.open();
		Cursor c = db.getAllRecords();
		c.moveToFirst();
		if (c.getCount() > 0) {
			name.setText(c.getString(1));
			amount.setText(c.getString(3));
			id = c.getString(0);
		}
		db.close();
		
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
			db.open();
			boolean hello = db.updateRecord(Integer.valueOf(id), nM, curr,
					aM, time);
			LayoutInflater mInflater = getLayoutInflater();
			View layout = mInflater.inflate(R.layout.toast2,
					(ViewGroup) findViewById(R.id.custom_toast2));
			TextView text = (TextView) layout
					.findViewById(R.id.toast_text2);
			text.setText("Budget Updated Successfully!!");
			toast = new Toast(getApplicationContext());
			toast.setDuration(Toast.LENGTH_LONG);
			toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
			toast.setView(layout);
			toast.show();
			db.close();
			intent = new Intent(this, BudgetActivity.class);
			savePrefs("Val", position);
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
			db.open();
			if(db.deleteRecord(Integer.valueOf(id))){
				LayoutInflater mInflater = getLayoutInflater();
				View layout = mInflater.inflate(R.layout.toast2,
						(ViewGroup) findViewById(R.id.custom_toast2));
				TextView text = (TextView) layout
						.findViewById(R.id.toast_text2);
				text.setText("Budget Deleted Successfully!!");
				toast = new Toast(getApplicationContext());
				toast.setDuration(Toast.LENGTH_LONG);
				toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
				toast.setView(layout);
				toast.show();
				
				intent = new Intent(this, BudgetActivity.class);
				savePrefs("Val", position);
				startActivity(intent);
				finish();
			}
			db.close();
		}
	}// end onClick()

}// <----------------- end class ----------------->
