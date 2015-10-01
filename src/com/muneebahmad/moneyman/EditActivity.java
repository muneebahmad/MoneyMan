package com.muneebahmad.moneyman;

/*
 * @author:MuneebAhmad
 * */
import org.muneebahmad.jlib.DBAdapter;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditActivity extends Activity implements OnClickListener {

	private Button close, mini, date, day, id, save;
	private EditText name, desc, trans, category, tags, currency, amount;
	private Intent intent;
	String mId, mDate, mDay, mName, mDesc, mTransaction, mCategory, mTags,
			mCurrency, mAmount;
	DBAdapter db = new DBAdapter(this);
	private int value;
	private Toast toast;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		overridePendingTransition(R.anim.pull_in_from_left, R.anim.pull_out_to_left);
		setContentView(R.layout.edit);

		// <-- castings -->
		close = (Button) findViewById(R.id.edit_close);
		mini = (Button) findViewById(R.id.edit_min);
		date = (Button) findViewById(R.id.edit_dte);
		day = (Button) findViewById(R.id.edit_wDay);
		save = (Button) findViewById(R.id.edit_save);
		id = (Button) findViewById(R.id.edit_id);
		name = (EditText) findViewById(R.id.edit_name);
		desc = (EditText) findViewById(R.id.edit_desc);
		trans = (EditText) findViewById(R.id.edit_trans);
		category = (EditText) findViewById(R.id.edit_cat);
		tags = (EditText) findViewById(R.id.edit_tags);
		currency = (EditText) findViewById(R.id.edit_currency);
		amount = (EditText) findViewById(R.id.edit_amount);

		close.setOnClickListener(this);
		mini.setOnClickListener(this);
		save.setOnClickListener(this);

		intent = getIntent();
		String msg = intent.getStringExtra("hello");
		value = Integer.parseInt(msg);

		// <-- getting this record from database -->

		db.open();

		Cursor c = db.getRecords(value);
		mDate = c.getString(1);
		mDay = c.getString(2);
		mName = c.getString(3);
		mDesc = c.getString(4);
		mTransaction = c.getString(5);
		mTags = c.getString(6);
		mCategory = c.getString(7);
		mCurrency = c.getString(8);
		mAmount = c.getString(9);

		id.setText(value + "");
		date.setText(mDate);
		day.setText(mDay);
		name.setText(mName);
		desc.setText(mDesc);
		trans.setText(mTransaction);
		tags.setText(mTags);
		category.setText(mCategory);
		currency.setText(mCurrency);
		amount.setText(mAmount);

		// <-- Animations -->

		Animation animRotate = AnimationUtils
				.loadAnimation(this, R.anim.rotate);
		Animation rotateTwo = AnimationUtils
				.loadAnimation(this, R.anim.rotate2);
		Animation mEntire = AnimationUtils.loadAnimation(this,
				R.anim.anim_list_spinner);
		Animation mHeavy = AnimationUtils.loadAnimation(this, R.anim.anim_date);
		Animation mButtom = AnimationUtils.loadAnimation(this, R.anim.anim_nav);
		getWindow().setWindowAnimations(R.anim.anim_date);

		close.setAnimation(animRotate);
		mini.setAnimation(rotateTwo);

		date.setAnimation(mEntire);
		day.setAnimation(mHeavy);
		id.setAnimation(mEntire);
		name.setAnimation(mHeavy);
		desc.setAnimation(mEntire);
		trans.setAnimation(mHeavy);
		category.setAnimation(mEntire);
		tags.setAnimation(mHeavy);

		save.setAnimation(mButtom);

	}

	/*************************************************************************************
	 ******************* ---> end onCreate() <--- *****************************************
	 *************************************************************************************** 
	 **/

	@Override
	public void onClick(View mView) {
		if (mView == close) {
			intent = new Intent(this, ListActivity.class);
			startActivity(intent);
			finish();
		}
		if (mView == mini) {
			intent = new Intent(Intent.ACTION_MAIN);
			intent.addCategory(Intent.CATEGORY_HOME);
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			overridePendingTransition(R.anim.pull_in_from_left, R.anim.hold);
			startActivity(intent);
		}
		if (mView == save) {
			mDate = date.getText().toString();
			mDay = day.getText().toString();
			mName = name.getText().toString();
			mDesc = desc.getText().toString();
			mTransaction = trans.getText().toString();
			mTags = tags.getText().toString();
			mCategory = category.getText().toString();
			mCurrency = currency.getText().toString();
			mAmount = amount.getText().toString();
			
			toast = Toast.makeText(this, "Record Updated Successfully!", Toast.LENGTH_LONG);
			toast.setGravity(Gravity.AXIS_CLIP, 0, 0);
			db.open();
			boolean ids = db.updateRecord(value, mDate, mDay, mName, mDesc,
					mTransaction, mTags, mCategory, mCurrency, mAmount);
			toast.show();
			db.open();
			intent = new Intent("com.muneebahmad.moneyman.LIST");
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			overridePendingTransition(R.anim.pull_in_from_left, R.anim.hold);
			startActivity(intent);
			finish();
		}

	}// end onClick()

}/*
 * =================================== end class
 * ===========================================
 * ==================================
 * =========================================================
 */
