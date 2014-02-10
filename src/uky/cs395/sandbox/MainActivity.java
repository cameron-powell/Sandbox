package uky.cs395.sandbox;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends Activity {

	private DrawView view;
	private ImageButton settings;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		view = (DrawView)findViewById(R.id.drawView);
		settings = (ImageButton)findViewById(R.id.imageButton);
		
		settings.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
				startActivityForResult(intent, 0);
			}
		});
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data){
		super.onActivityResult(requestCode, resultCode, data);
		if(resultCode == RESULT_OK){
			Bundle results = data.getExtras();
			boolean[] vals = results.getBooleanArray("key");
			Log.i("ELEMENTS", "el"+vals[0]);
			Log.i("ELEMENTS", "il"+vals[1]);
			Log.i("ELEMENTS", "gp"+vals[2]);
			Log.i("ELEMENTS", "gg"+vals[3]);
			Log.i("ELEMENTS", "fr"+vals[4]);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
