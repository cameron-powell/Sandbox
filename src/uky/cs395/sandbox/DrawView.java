package uky.cs395.sandbox;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class DrawView extends View {
	
	public static final String TAG = "DrawView";
	private Paint backgroundColor;
	/*options*/
	private boolean allowElastic;
	private boolean allowInelastic;
	private boolean allowGravityP;
	private boolean allowGravityG;
	private boolean allowFriction;
	
	public DrawView(Context c, AttributeSet as) {
		super(c, as);
		backgroundColor = new Paint();
		backgroundColor.setColor(Color.BLACK);
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
	
	public void setOptions(boolean e, boolean i, boolean p, boolean g, boolean f) {
		allowElastic = e;
		allowInelastic = i;
		allowGravityP = p;
		allowGravityG = g;
		allowFriction = f;
	}

}
