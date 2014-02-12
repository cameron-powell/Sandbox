package uky.cs395.sandbox;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends Activity {

	/*views*/
	private DrawView view;
	private ImageButton settings;
	
	/*onCreate
	 * @param: standard input
	 * @end:links views, adds listeners 
	 * (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		/*link views*/
		view = (DrawView)findViewById(R.id.drawView);
		settings = (ImageButton)findViewById(R.id.imageButton);
		/*add listeners*/
		settings.setOnClickListener(new View.OnClickListener() {
			/* onClick
			 * @param: recieves current view
			 * @end: creates intent, starts settings activity to collect
			 * values from the user to be received on it's end
			 * (non-Javadoc)
			 * @see android.view.View.OnClickListener#onClick(android.view.View)
			 */
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
				startActivityForResult(intent, 0); // switch to settings activity
			}
		});
		/*create a frame update thread*/
		Thread updateThread = new Thread() {
			public void run() {
				try {
					while(!isInterrupted()) {
						Thread.sleep(20);	//pause between renders
						runOnUiThread(new Runnable() {
							public void run() {
								view.update();	//update the particles
							}
						});
					}
				}
				catch (InterruptedException e) {}
			}
		};
		/*start the update thread*/
		updateThread.start();
	}
	
	/* onActivityResult
	 * @param: recieves intent with data and return status
	 * @end: checks if returned okay, calls setOptions and sets the new
	 * physics options to be used in the draw view
	 * (non-Javadoc)
	 * @see android.app.Activity#onActivityResult(int, int, android.content.Intent)
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data){
		super.onActivityResult(requestCode, resultCode, data);
		if(resultCode == RESULT_OK){
			/*grab data from bundle*/
			Bundle results = data.getExtras();
			boolean[] vals = results.getBooleanArray("key");
			/*pass/set physics options to/in view*/
			view.setOptions(vals[0], vals[1], vals[2], vals[3], vals[4], vals[5]);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
