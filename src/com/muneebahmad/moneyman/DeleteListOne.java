package com.muneebahmad.moneyman;

/*
 * @author:MuneebAhmad
 * */

import java.util.ArrayList;

import org.muneebahmad.jlib.DBAdapter;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DeleteListOne extends Activity implements OnClickListener {

	private TextView serial2, id2, name2, date2, day2, trans2, amount2,
			category2, tags2, desc2;
	private Intent intent;
	private Toast toast;
	private Button delete, edit, close;

	private ArrayList<String> id;
	private ArrayList<String> date;
	private ArrayList<String> day;
	private ArrayList<String> names;
	private ArrayList<String> desc;
	private ArrayList<String> transaction;
	private ArrayList<String> tags;
	private ArrayList<String> category;
	private ArrayList<String> currency;
	private ArrayList<String> amount;

	private DBAdapter db = new DBAdapter(this);
	int col, child;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		overridePendingTransition(R.anim.pull_in_from_left,
				R.anim.pull_out_to_left);
		setContentView(R.layout.delete_dialog);

		serial2 = (TextView) findViewById(R.id.dialog_serial2);
		id2 = (TextView) findViewById(R.id.dialog_id2);
		name2 = (TextView) findViewById(R.id.dialog_name2);
		date2 = (TextView) findViewById(R.id.dialog_date2);
		day2 = (TextView) findViewById(R.id.dialog_day2);
		trans2 = (TextView) findViewById(R.id.dialog_trans2);
		amount2 = (TextView) findViewById(R.id.dialog_amount2);
		category2 = (TextView) findViewById(R.id.dialog_category2);
		tags2 = (TextView) findViewById(R.id.dialog_tags2);
		desc2 = (TextView) findViewById(R.id.dialog_desc2);

		delete = (Button) findViewById(R.id.dialog_yes);
		edit = (Button) findViewById(R.id.dialog_no);
		close = (Button) findViewById(R.id.dialog_cl);

		delete.setOnClickListener(this);
		edit.setOnClickListener(this);
		close.setOnClickListener(this);

		// ------------------------------
		id = new ArrayList<String>();
		date = new ArrayList<String>();
		day = new ArrayList<String>();
		names = new ArrayList<String>();
		desc = new ArrayList<String>();
		transaction = new ArrayList<String>();
		tags = new ArrayList<String>();
		category = new ArrayList<String>();
		currency = new ArrayList<String>();
		amount = new ArrayList<String>();

		db.open();
		Cursor c = db.getAllRecords();
		col = c.getCount();
		if (c.moveToFirst()) {
			do {
				id.add("" + c.getString(0));
				date.add("" + c.getString(1));
				day.add("" + c.getString(2));
				names.add("" + c.getString(3));
				desc.add("" + c.getString(4));
				transaction.add("" + c.getString(5));
				tags.add("" + c.getString(6));
				category.add("" + c.getString(7));
				currency.add("" + c.getString(8));
				amount.add("" + c.getString(9));
			} while (c.moveToNext());
		}// end if
		db.close();

		// --------------------------------------------------
		intent = getIntent();
		String msg = intent.getStringExtra("delete");
		child = Integer.parseInt(msg);
		// ---------------------------------------------------------

		String mDay = day.get(child);
		if (mDay.equals("Sunday")) {
			day2.setTextColor(Color.RED);
		} else if (mDay.equals("Tuesday")) {
			day2.setTextColor(Color.GREEN);
		} else if (mDay.equals("Wednesday")) {
			day2.setTextColor(Color.LTGRAY);
		} else if (mDay.equals("Thursday")) {
			day2.setTextColor(Color.CYAN);
		} else if (mDay.equals("Friday")) {
			day2.setTextColor(Color.YELLOW);
		} else if (mDay.equals("Saturday")) {
			day2.setTextColor(Color.MAGENTA);
		}
		day2.setText(mDay);
		serial2.setText(child + 1 + "");
		id2.setText(id.get(child));
		name2.setText(names.get(child));
		date2.setText(date.get(child));
		String tra = transaction.get(child);
		String exp = "Expense";
		if (tra.equals(exp) || tra == exp) {
			trans2.setBackgroundColor(Color.RED);
		} else {
			trans2.setBackgroundColor(Color.BLUE);
		}
		trans2.setText(transaction.get(child));
		amount2.setText(currency.get(child) + " " + amount.get(child));
		category2.setText(category.get(child));
		tags2.setText(tags.get(child));
		desc2.setText(desc.get(child));

	}// end onCreate()

	@Override
	public void onClick(View v) {

		if (v == delete) {
			db.open();
			int mId = Integer.parseInt(id.get(child));
			if (db.deleteRecord(mId)) {
				LayoutInflater mInflater = getLayoutInflater();
				View layout = mInflater.inflate(R.layout.toast2,
						(ViewGroup) findViewById(R.id.custom_toast2));
				TextView text = (TextView) layout
						.findViewById(R.id.toast_text2);
				text.setText("Rocord Deleted Successfully!!");
				toast = new Toast(getApplicationContext());
				toast.setDuration(Toast.LENGTH_LONG);
				toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
				toast.setView(layout);
				toast.show();
				Intent intent = new Intent("com.muneebahmad.moneyman.LIST");
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
				finish();
			}// end if

			db.close();
		}
		if (v == edit) {
			Intent intent = new Intent("com.muneebahmad.moneyman.EDIT");
			intent.putExtra("hello", id.get(child) + "");
			startActivity(intent);
			finish();
		}
		if (v == close) {
			Intent intent = new Intent("com.muneebahmad.moneyman.LIST");
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
			finish();
		}
	}// end onClick()

}
