package uky.cs395.sandbox;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class DrawView extends View {
	
	/*required for accessing*/
	public static final String TAG = "DrawView";
	/*paint values*/
	private Paint backgroundColor;
	private Paint particleColor;
	/*options*/
	private boolean allowElastic;
	private boolean allowInelastic;
	private boolean allowGravityP;
	private boolean allowGravityG;
	private boolean allowFriction;
	
	/* DrawView Constructor
	 * @param: standard inputs
	 * @end: sets default values for each member variable and instantiates as needed
	 */
	public DrawView(Context c, AttributeSet as) {
		super(c, as);
		/*paint values*/
		backgroundColor = new Paint();
		backgroundColor.setColor(Color.BLACK);
		particleColor = new Paint();
		particleColor.setColor(Color.RED);
		/*set option values*/
		allowElastic = false;
		allowInelastic = false;
		allowGravityP = false;
		allowGravityG = false;
		allowFriction = false;
	}
	
	public void onDraw(Canvas c) {
		c.drawPaint(backgroundColor);
	}
	
	/* setOptions
	 * @param: receives boolean values to set options to
	 * @end: private member values have been set to their new values
	 */
	public void setOptions(boolean e, boolean i, boolean p, boolean g, boolean f) {
		allowElastic = e;
		allowInelastic = i;
		allowGravityP = p;
		allowGravityG = g;
		allowFriction = f;
	}

}
