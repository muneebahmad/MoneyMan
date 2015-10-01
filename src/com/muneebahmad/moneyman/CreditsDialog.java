package com.muneebahmad.moneyman;

/*
 * @author:MuneebAhmad
 * */
import java.io.File;
import java.io.IOException;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class CreditsDialog extends Activity implements OnClickListener {

	private Button close;
	private TextView tView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		overridePendingTransition(R.anim.pull_in_from_left,
				R.anim.pull_out_to_left);
		setContentView(R.layout.creditsdialog);

		tView = (TextView) findViewById(R.id.credits_display);
		close = (Button) findViewById(R.id.credits_cl);

		File[] roots = File.listRoots();

		close.setOnClickListener(this);
		tView.setText("com.muneebahmad.moneyman\n"
				+ "com.muneebahmad.java.chart\n" + "org.muneebahmad.jlib\n\n"
				+ "@author: Muneeb Ahmad\n" + "@designer: Muneeb Ahmad\n\n"
				+ "@vendor: MuneebAhmad ~ MGG (A Murree-Galiyat Group ~ 0PKC)\n\n"
				+ "@dB Designer: Muneeb Ahmad\n" + "@software: MoneyMan\n"
				+ "@version: 1.0\n" + "@minimum API: 11 ~ Runnable on HoneyComb or above\n"
				+ "@property: Buff Solutions\n\n"
				+ "@copy defined rate: 1024 b/sec\n" 
				+ "\n" + "@cpu count: "
				+ Runtime.getRuntime().availableProcessors() + "\n"
				+ "@cpu arch: " + System.getenv("PROCESSOR_ARCHITECTURE")
				+ "\n" + "@dalvik vm~mem: "
				+ Runtime.getRuntime().totalMemory() + " bytes" + "\n\n");

		for (File root : roots) {
			tView.append("@file sys root: " + root.getAbsolutePath() + "\n");
			}//end loop
	}// end onCreate()

	@Override
	public void onClick(View v) {
		if (v == close) {
			overridePendingTransition(R.anim.pull_in_from_left, R.anim.hold);
			finish();
		}
	}// end onClick()

}// end class
