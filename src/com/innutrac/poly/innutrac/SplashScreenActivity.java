package com.innutrac.poly.innutrac;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.view.Display;
import android.view.Menu;
import android.view.Window;

public class SplashScreenActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash_screen);

		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {
				Intent i = checkForFirstLoad();
				startActivity(i);
				finish();
			}
		}, 2000);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.splash_screen, menu);
		return true;
	}

	public Intent checkForFirstLoad() {
		String sdPath = Environment.getExternalStorageDirectory()
				.getAbsolutePath();
		File cache = new File(sdPath + "/Innutrac/vals/cache");
		
		if (cache.exists()) {
			return new Intent(this, MainActivity.class);
		} else {
			File dir = new File(sdPath + "/Innutrac/vals");
			dir.mkdirs();
			cache = new File(dir, "cache");
			try {
				FileOutputStream f = new FileOutputStream(cache);
				f.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			// THIS IS WHERE SCREEN DIENSIONS ARE AQUIRED
			Display display = getWindowManager().getDefaultDisplay(); 
			int width = display.getWidth();
			int height = display.getHeight();
			// Need to pass these values to PieView.java
			// Perhaps need to save values to our data/ text file for access from PieView
			
			return new Intent(this, WelcomeMessageActivity.class);
		}
	}
}
