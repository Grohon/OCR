/* 
 * Abu Foysal
 * ID:12101131
 * BRAC University
 */
package bracu.ac.bd.ocr;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreen extends Activity {

	private static int SPLASH_TIME_OUT = 3;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);

		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {
				Intent i = new Intent(SplashScreen.this, CaptureActivity.class);
				startActivity(i);
				finish();
			}
		}, SPLASH_TIME_OUT);
	}

}