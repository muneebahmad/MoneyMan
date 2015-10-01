package com.muneebahmad.moneyman;
/*
 * @author:MuneebAhmad
 * */
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class CloseActivity extends Activity implements OnClickListener{

	private Button yes, no;
	private Button cli;
	private Intent intent;
	private Toast toast;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		overridePendingTransition(R.anim.pull_in_from_left, R.anim.pull_out_to_left);
		setContentView(R.layout.close);
		
		yes = (Button) findViewById(R.id.close_yes);
		no = (Button) findViewById(R.id.close_no);
		cli = (Button) findViewById(R.id.close_cl);
		
		yes.setOnClickListener(this);
		no.setOnClickListener(this);
		cli.setOnClickListener(this);
		
	}//end onCreate()

	@Override
	public void onClick(View view) {
		if(view == no){
			LayoutInflater inflater = getLayoutInflater();
			View layout = inflater.inflate(R.layout.toast1,
					(ViewGroup) findViewById(R.id.custom_toast1));
			TextView text = (TextView) layout.findViewById(R.id.text);
			text.setText("Welcome Back!");
			toast = new Toast(getApplicationContext());
			toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
			toast.setDuration(Toast.LENGTH_LONG);
			toast.setView(layout);
			toast.show();
			intent = new Intent("com.muneebahmad.moneyman.HOME");
			overridePendingTransition(R.anim.pull_in_from_left, R.anim.hold);
			startActivity(intent);
			finish();
		}
		if(view == yes){
			LayoutInflater inflater = getLayoutInflater();
			View layout = inflater.inflate(R.layout.toast1,
					(ViewGroup) findViewById(R.id.custom_toast1));
			TextView text = (TextView) layout.findViewById(R.id.text);
			text.setText("Saving usaved date please wait!\n"
					+ "\n"
					+ "MuneebAhmad (c)2013 Rwp.");
			toast = new Toast(getApplicationContext());
			toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
			toast.setDuration(Toast.LENGTH_LONG);
			toast.setView(layout);
			toast.show();
			finish();
		}
		if(view == cli){
			intent = new Intent("com.muneebahmad.moneyman.HOME");
			startActivity(intent);
			finish();
		}
	}//end onClick()

}//end class
