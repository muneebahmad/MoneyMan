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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DeleteActivity extends Activity implements OnClickListener{

	private Button yes, no;
	private Button cli;
	private Intent intent;
	private Toast delete, nDelete;
	DBAdapter db = new DBAdapter(this);
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		overridePendingTransition(R.anim.pull_in_from_left, R.anim.pull_out_to_left);
		setContentView(R.layout.dialog);
		
		yes = (Button) findViewById(R.id.delete_yes);
		no = (Button) findViewById(R.id.delete_no);
		cli = (Button) findViewById(R.id.delete_cl);
		
		yes.setOnClickListener(this);
		no.setOnClickListener(this);
		cli.setOnClickListener(this);
	}//end onCreate()

	@Override
	public void onClick(View mView) {
		if(mView == yes){
			LayoutInflater mInflater = getLayoutInflater();
			View layout = mInflater.inflate(R.layout.toast2,
					(ViewGroup) findViewById(R.id.custom_toast2));
			TextView text = (TextView) layout
					.findViewById(R.id.toast_text2);
			text.setText("All Records deleted Successfully!!");
			delete = new Toast(getApplicationContext());
			delete.setDuration(Toast.LENGTH_LONG);
			delete.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
			delete.setView(layout);
			int mId = 0;
			db.open();
			Cursor c = db.getAllRecords();
			if (c.moveToLast()) {
				mId = Integer.parseInt(c.getString(0));
			}// end if
			for (int i = 0; i <= mId; i++) {
				if (db.deleteRecord(i)) {
					delete.show();
				} 
			}// end for
			db.close();
			Intent intent = new Intent(
					"com.muneebahmad.moneyman.LIST");
			startActivity(intent);
			DeleteActivity.this.finish();
		}
		if(mView == no){
			intent = new Intent("com.muneebahmad.moneyman.LIST");
			startActivity(intent);
			finish();
		}
		if(mView == cli){
			intent = new Intent(this, ListActivity.class);
			startActivity(intent);
			finish();
		}
	}//end onClick()

}//end class
