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
	CheckBox wall;
	CheckBox gravityParticles;
	CheckBox gravityGround;
	CheckBox friction;
	CheckBox flingParticles;
	/*values*/
	boolean perfElastic = false;
	boolean perfInelastic = false;
	boolean wallCollision = false;
	boolean gravParticles = false;
	boolean gravGround = false;
	boolean fric = false;
	boolean fling = false;

	/* onCreate
	 * @params: standard inputs
	 * @end: linked objects and views, linked listeners to views, implements logic to
	 * maintain invariant that elastic and inelastic collision may not be implemented
	 * at the same time, handles sending data back to main activity through intent with
	 * submit button
	 * (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		/*link objects to views*/
		submitButton = (Button)findViewById(R.id.submitButton);
		final CheckBox perfectlyElastic = (CheckBox)findViewById(R.id.perfectlyElasticCheckBox);
		final CheckBox perfectlyInelastic = (CheckBox)findViewById(R.id.perfectlyInelasticCheckBox);
		wall = (CheckBox)findViewById(R.id.wallCheckBox);
		gravityParticles = (CheckBox)findViewById(R.id.gravityParticlesCheckBox);
		gravityGround = (CheckBox)findViewById(R.id.gravityGroundCheckBox);
		friction = (CheckBox)findViewById(R.id.frictionCheckBox);
		flingParticles = (CheckBox)findViewById(R.id.flingCheckBox);
		/*link listeners to views*/
		submitButton.setOnClickListener(new View.OnClickListener() {
			/* onClick
			 * @param: recieves current view
			 * @end: created intent and bundle, stores user's chosen values into array
			 * placed into bundle.  Bundle is packaged into intent which is returned
			 * to MainActivity
			 * (non-Javadoc)
			 * @see android.view.View.OnClickListener#onClick(android.view.View)
			 */
			@Override
			public void onClick(View v) {
				/*create intent and bundle for storing and returning data*/
				Intent resultIntent = new Intent();
				Bundle results = new Bundle();
				/*populate Array with values*/
				boolean[] vals = new boolean[7];
				vals[0] = perfElastic;
				vals[1] = perfInelastic;
				vals[2] = wallCollision;
				vals[3] = gravParticles;
				vals[4] = gravGround;
				vals[5] = fric;
				vals[6] = fling;
				/*package bundle and reslutIntent*/
				results.putBooleanArray("key", vals);
				resultIntent.putExtras(results);
				setResult(RESULT_OK, resultIntent);
				/*exit activity and send back results*/
				finish();
			}
		});
		
		perfectlyElastic.setOnClickListener(new View.OnClickListener() {
			/* onClick
			 * @param: standard this view
			 * @end: checks and maintains invariant, XOR(elastic, inelastic)
			 * possible for neither to be activated
			 * ALWAYS flips value of perfElastic
			 * (non-Javadoc)
			 * @see android.view.View.OnClickListener#onClick(android.view.View)
			 */
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
			/* onClick
			 * @param: standard this view
			 * @end: checks and maintains invariant, XOR(elastic, inelastic)
			 * possible for neither to be activated
			 * ALWAYS flips value of perfInelastic
			 * (non-Javadoc)
			 * @see android.view.View.OnClickListener#onClick(android.view.View)
			 */
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
		
		wall.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				wallCollision = !wallCollision;
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
		
		flingParticles.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				fling = !fling;
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
