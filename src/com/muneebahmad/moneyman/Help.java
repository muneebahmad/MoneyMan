package com.muneebahmad.moneyman;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Help extends Activity implements OnClickListener {

	private ImageView helpImg;
	private Button close;
	private TextView main, home, register, date;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		overridePendingTransition(R.anim.pull_in_from_left,
				R.anim.pull_out_to_left);
		setContentView(R.layout.help);

		close = (Button) findViewById(R.id.help_cl);
		helpImg = (ImageView) findViewById(R.id.helpimg);
		main = (TextView) findViewById(R.id.tv_id);
		main.setText("Welcome to MoneyMan Help menu"
				+ " This menu will guide you through the process "
				+ "of using this application software efficiently, "
				+ "This menu may not explain the working of every button and spinner "
				+ "all special and difficult procedures are explained.");

		home = (TextView) findViewById(R.id.tv_menu1);
		home.setText("The Tab clicked in above image opens HOME."
				+ " In Homepage you can see a List of Buttons which are explained "
				+ "one by one below,");

		register = (TextView) findViewById(R.id.tv_register);
		register.setText("By clicking this button, You can quickly navigate to "
				+ "the Transaction page,"
				+ " In Transaction's menu you can register your current or"
				+ " previous Transactions "
				+ "This is further explained below,");

		date = (TextView) findViewById(R.id.tv_date);
		date.setText("When the transaction page is opened the first thing "
				+ "you see is this date button, it automatically displays the "
				+ "current date of your device however if you wish to record a transaction "
				+ "for a previous date you can click this button a dialog will appear "
				+ "from which you can select your desired date!");

		// -------------------------------
		Animation animOne = AnimationUtils
				.loadAnimation(this, R.anim.help_anim);
		helpImg.setAnimation(animOne);

		close.setOnClickListener(this);
	}// end onCreate()

	@Override
	public void onClick(View view) {
		if (view == close) {
			Help.this.finish();
		}
	}// end onClick()

}// ----------------------------------------- end class
// -----------------------------
