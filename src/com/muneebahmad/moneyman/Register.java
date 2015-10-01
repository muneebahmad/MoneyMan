package com.muneebahmad.moneyman;

/*
 * @author:MuneebAhmad
 * */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.muneebahmad.jlib.DBAdapter;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Register extends Activity implements OnClickListener {
	MediaPlayer buttonMusic;
	private ImageView calc;
	Button save, clear, close;
	static Button dte, week;
	EditText name, description, amount;
	TextView mTV;
	private ImageView mView1, mView2;
	Date d;
	Toast toast;
	String sD, amnt, nme, dt, day, desc, spin1, spin2, spin3, spin4, value1,
			file;
	Intent intent;
	Spinner spinner1, spinner2, spinner3, spinner4;
	int position, val;
	FileOutputStream fos;
	int counter = 0;
	static int mDay;
	static int mMonth;
	static int mYear;
	AnimationDrawable earthMotion;
	Animation animFour;

	@SuppressLint({ "SimpleDateFormat", "NewApi" })
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		overridePendingTransition(R.anim.pull_in_from_left,
				R.anim.pull_out_to_left);
		setContentView(R.layout.register);
		buttonMusic = MediaPlayer.create(this, R.raw.buttclick);

		// Spinner caste
		spinner1 = (Spinner) findViewById(R.id.spinner1);
		spinner2 = (Spinner) findViewById(R.id.spinner2);
		spinner3 = (Spinner) findViewById(R.id.spinner3);
		spinner4 = (Spinner) findViewById(R.id.spinner4);

		mView1 = (ImageView) findViewById(R.id.register_image1);
		mView2 = (ImageView) findViewById(R.id.register_image2);

		animFour = AnimationUtils.loadAnimation(this, R.anim.sett4);

		mView2.setAnimation(animFour);

		mView1.setBackgroundResource(R.drawable.earth_roatation_list);
		earthMotion = (AnimationDrawable) mView1.getBackground();

		/*****************************************************************************
		 * *********************** Spinners *************************
		 * ***********
		 * ****************************************************************
		 * */

		// -- Spinner1 ArrayAdapter -- Currency
		ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(
				this, R.array.currency_array,
				android.R.layout.simple_spinner_dropdown_item);
		adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner1.setAdapter(adapter1);

		spinner1.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int pos, long id) {
				buttonMusic.start();

				Object item = parent.getItemAtPosition(pos);
				spin1 = item.toString();
				position = pos;
				if (pos == 0) {
					spinner1.setSelection(val);
				}

			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {

			}

		});

		// -- Spinner2 ArrayAdapter -- Category
		ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(
				this, R.array.catagory_array,
				android.R.layout.simple_spinner_dropdown_item);
		adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner2.setAdapter(adapter2);

		spinner2.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int pos, long id) {
				buttonMusic.start();
				Object item = parent.getItemAtPosition(pos);
				spin2 = item.toString();

			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {

			}

		});

		// -- Spinner3 ArrayAdapter -- Tags
		ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(
				this, R.array.tag_array,
				android.R.layout.simple_spinner_dropdown_item);
		adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner3.setAdapter(adapter3);

		spinner3.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int pos, long id) {
				buttonMusic.start();
				Object item = parent.getItemAtPosition(pos);
				spin3 = item.toString();

			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {

			}

		});

		// -- Spinner4 ArrayAdapter -- Transaction type
		ArrayAdapter<CharSequence> adapter4 = ArrayAdapter.createFromResource(
				this, R.array.trans_array,
				android.R.layout.simple_spinner_dropdown_item);
		adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner4.setAdapter(adapter4);
		spinner4.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int pos, long id) {
				buttonMusic.start();

				Object item = parent.getItemAtPosition(pos);
				spin4 = item.toString();
				position = pos;

			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {

			}

		});

		/*---------------------------------- end Spinners -----------------------------------*/

		dte = (Button) findViewById(R.id.dte);

		name = (EditText) findViewById(R.id.name);
		name.setOnTouchListener(new View.OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				earthMotion.start();
				return false;
			}
		});
		description = (EditText) findViewById(R.id.description);
		amount = (EditText) findViewById(R.id.amount);
		amount.setOnTouchListener(new View.OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				earthMotion.start();
				return false;
			}
		});
		amount.setOnFocusChangeListener(new View.OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				earthMotion.start();
			}
		});

		// -- casting ImageView
		calc = (ImageView) findViewById(R.id.calc);
		close = (Button) findViewById(R.id.register_cl);
		save = (Button) findViewById(R.id.save);
		clear = (Button) findViewById(R.id.clear);
		week = (Button) findViewById(R.id.wDay);

		/*******************************************************************************
		 * *************** from classes: java.text.SimpleDateFormat
		 * ******************** ***************************** java.util.Date
		 * ********************************
		 * ***/

		SimpleDateFormat simpleFormat = new SimpleDateFormat("dd/MM/yyyy");
		d = new Date();
		sD = simpleFormat.format(d);

		final Calendar mCalandar = Calendar.getInstance();
		final int days = mCalandar.get(Calendar.DAY_OF_WEEK);
		switch (days) {
		case 1:
			week.setText("Sunday");
			break;

		case 2:
			week.setText("Monday");
			break;

		case 3:
			week.setText("Tuesday");
			break;

		case 4:
			week.setText("Wednesday");
			break;

		case 5:
			week.setText("Thursday");
			break;

		case 6:
			week.setText("Friday");
			break;

		case 7:
			week.setText("Saturday");
			break;

		}// end switch
		dte.setText(sD);

		// button OnClickListener
		save.setOnClickListener(this);
		clear.setOnClickListener(this);
		close.setOnClickListener(this);
		calc.setOnClickListener(this);
		/**
		 * *********************************************************************
		 * *********** ******************* loadPrefs() method/function call
		 * ***************************
		 * ******************************************
		 * **************************************
		 * */
		loadPrefs();
		// ---------------------------------------------------------------------------------

		// --> CopyDB method call

		try {
			String destPath = "/data/data" + getPackageName()
					+ "/databases/MoneyManDB";
			File file = new File(destPath);
			if (!file.exists()) {
				CopyDB(getBaseContext().getAssets().open("mydb"),
						new FileOutputStream(destPath));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}// end catch
	}// <------ end onCreate() ------>

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	public static class DatePickerFragment extends DialogFragment implements
			DatePickerDialog.OnDateSetListener {

		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			// Use the current date as the default date in the picker
			final Calendar c = Calendar.getInstance();
			int year = c.get(Calendar.YEAR);
			int month = c.get(Calendar.MONTH);
			int day = c.get(Calendar.DAY_OF_MONTH);

			// Create a new instance of DatePickerDialog and return it
			return new DatePickerDialog(getActivity(), this, year, month, day);
		}

		public void onDateSet(DatePicker view, int year, int month, int day) {
			// date chosen
			int hello = month + 1;
			dte.setText(day + "/" + hello + "/" + year);

		}// end onDateSet()
	}// end inner class

	// --> overriding showDatePickerDialog()
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	public void showDatePickerDialog(View v) {
		DialogFragment newFragment = new DatePickerFragment();
		newFragment.show(getFragmentManager(), "datePicker");
	}// end showDatePickerDialog()

	// --> CopyDB method()

	public void CopyDB(InputStream open, OutputStream fileOutputStream)
			throws IOException {
		// -- copy 1Kb at a time
		byte[] buffer = new byte[1024];
		int len;
		while ((len = open.read(buffer)) > 0) {
			fileOutputStream.write(buffer, 0, len);
		}
		open.close();
		fileOutputStream.close();
	}// end CopyDB()

	// --->> savePrefs() method
	private void savePrefs(String key, int value) {
		SharedPreferences sp = PreferenceManager
				.getDefaultSharedPreferences(this);
		Editor edit = sp.edit();
		edit.putInt(key, value);
		edit.commit();
	}// end savePrefs();

	/**************************************************************************************
	 * ******************************* Overriding onClick(View)
	 * ***************************
	 * **********************************************
	 * **************************************
	 * */

	@Override
	public void onClick(View view) {
		if (view == close) {
			savePrefs("Val", position);
			intent = new Intent(this, HomeActivity.class);
			startActivity(intent);
			finish();
		}
		if (view == clear) {
			buttonMusic.start();
			LayoutInflater mInflater = getLayoutInflater();
			View layout = mInflater.inflate(R.layout.toast2,
					(ViewGroup) findViewById(R.id.custom_toast2));
			TextView text = (TextView) layout.findViewById(R.id.toast_text2);
			text.setText("Form Cleared!!");
			toast = new Toast(getApplicationContext());
			toast.setDuration(Toast.LENGTH_LONG);
			toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
			toast.setView(layout);
			toast.show();
			name.setText("");
			description.setText("");
			amount.setText("");
			dte.setText(sD);
			earthMotion.start();

		}
		if (view == calc) {
			buttonMusic.start();

			savePrefs("Val", position);
			intent = new Intent("com.muneebahmad.moneyman.CALCULATOR");
			intent.putExtra("money", "amount");
			overridePendingTransition(R.anim.pull_in_from_left, R.anim.hold);
			startActivityForResult(intent, 1);
		}
		if (view == save) {
			buttonMusic.start();
			// <-- retrieving text from EditText -->
			DBAdapter db = new DBAdapter(this);
			amnt = amount.getText().toString();
			dt = dte.getText().toString();
			nme = name.getText().toString();
			desc = description.getText().toString();
			day = week.getText().toString();
			db.open();
			long id = db.insertRecord(dt, day, nme, desc, spin4, spin3, spin2,
					spin1, amnt);
			db.close();
			LayoutInflater mInflater = getLayoutInflater();
			View layout = mInflater.inflate(R.layout.toast2,
					(ViewGroup) findViewById(R.id.custom_toast2));
			TextView text = (TextView) layout.findViewById(R.id.toast_text2);
			text.setText("File [" + nme + "] @ date [" + dt + "] of [" + spin4
					+ " " + spin1 + " " + amnt + "] \n" + "Saved Successfully!");
			toast = new Toast(getApplicationContext());
			toast.setDuration(Toast.LENGTH_LONG);
			toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
			toast.setView(layout);
			toast.show();
			earthMotion.start();

		}

	}// end onClick()

	public void loadPrefs() {
		SharedPreferences sp = PreferenceManager
				.getDefaultSharedPreferences(this);
		val = sp.getInt("Val", 0);
	}

	@Override
	protected void onPause() {
		super.onDestroy();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		buttonMusic.release();
	}

	@Override
	public void onBackPressed() {
		savePrefs("Val", position);
		intent = new Intent(this, HomeActivity.class);
		startActivity(intent);
		finish();
	}

	/***************************************************************************************
	 * **************************** onActivityResult() for Activity Result
	 * ******************
	 * *******************************************************
	 * ******************************
	 * */

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (data.getExtras().containsKey("moneyInfo")) {
			amount.setText(data.getStringExtra("moneyInfo"));
		}
	}

}// <--========== end class ===========-->
