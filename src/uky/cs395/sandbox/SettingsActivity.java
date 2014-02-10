package uky.cs395.sandbox;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

public class SettingsActivity extends Activity {
	
	/*views*/
	Button submitButton;
	CheckBox perfectlyElastic;
	CheckBox perfectlyInelastic;
	CheckBox gravityParticles;
	CheckBox gravityGround;
	CheckBox friction;
	/*values*/
	boolean perfElastic = false;
	boolean perfInelastic = false;
	boolean gravParticles = false;
	boolean gravGround = false;
	boolean fric = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		
		/*link objects to views*/
		submitButton = (Button)findViewById(R.id.submitButton);
		final CheckBox perfectlyElastic = (CheckBox)findViewById(R.id.perfectlyElasticCheckBox);
		final CheckBox perfectlyInelastic = (CheckBox)findViewById(R.id.perfectlyInelasticCheckBox);
		CheckBox gravityParticles = (CheckBox)findViewById(R.id.gravityParticlesCheckBox);
		CheckBox gravityGround = (CheckBox)findViewById(R.id.gravityGroundCheckBox);
		CheckBox friction = (CheckBox)findViewById(R.id.frictionCheckBox);
		
		/*link listeners to views*/
		submitButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent resultIntent = new Intent();
				Bundle results = new Bundle();
				/*populate Array with values*/
				boolean[] vals = new boolean[5];
				vals[0] = perfElastic;
				vals[1] = perfInelastic;
				vals[2] = gravParticles;
				vals[3] = gravGround;
				vals[4] = fric;
				/*package bundle and reslutIntent*/
				results.putBooleanArray("key", vals);
				resultIntent.putExtras(results);
				setResult(RESULT_OK, resultIntent);
				/*exit activity and send back results*/
				finish();
			}
		});
		
		perfectlyElastic.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				/*check if inelastic is checked*/
				if(perfInelastic){
					perfInelastic = false;
					perfectlyInelastic.setChecked(false);
				}
				perfElastic = !perfElastic;
			}
		});
		
		perfectlyInelastic.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				/*check if perfectly elastic is checked*/
				if(perfElastic) {
					perfElastic = false;
					perfectlyElastic.setChecked(false);
				}
				perfInelastic = !perfInelastic;
			}
		});
		
		gravityParticles.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {
				gravParticles = !gravParticles;
			}
		});
		
		gravityGround.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				gravGround = !gravGround;
			}
		});
		
		friction.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				fric = !fric;
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.settings, menu);
		return true;
	}

}
