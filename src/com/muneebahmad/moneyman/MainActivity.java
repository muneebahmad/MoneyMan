
package com.muneebahmad.moneyman;





/**
 * @author:MuneebAhmad
 * */
import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

public class MainActivity extends Activity {
	MediaPlayer startUp;
	StartAnim sA;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		sA = new StartAnim(this);
		setContentView(sA);
		startUp = MediaPlayer.create(this, R.raw.electric);
		startUp.start();
		thread.start();
	}

	Thread thread = new Thread() {

		public void run() {

			try {
				sleep(2000);
				Intent intent = new Intent("com.muneebahmad.moneyman.TRANSITION");
				startActivity(intent);
			} catch (InterruptedException e) {

				e.printStackTrace();
			} finally {
				finish();
			}
		}
	};// end Thread()

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_settings:
			return true;

		case R.id.help:
			return true;

		case R.id.shutdown:
			Toast toast = Toast.makeText(this, "Force Shutdown in Progress",
					Toast.LENGTH_SHORT);
			toast.show();
			System.exit(0);
			return true;
		}

		return false;

	}

}// end class
