package uky.cs395.sandbox;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;

public class DrawView extends View implements OnGestureListener {
	
	/*required for accessing*/
	public static final String TAG = "DrawView";
	/*for detecting user input*/
	GestureDetector gd;
	/*paint values*/
	private Paint backgroundColor;
	private Paint particleColor;
	/*options*/
	private boolean allowElastic;
	private boolean allowInelastic;
	private boolean allowGravityP;
	private boolean allowGravityG;
	private boolean allowFriction;
	private boolean allowFling;
	/*particle collection*/
	ArrayList<Particle> particles;
	
	/* DrawView Constructor
	 * @param: standard inputs
	 * @end: sets default values for each member variable and instantiates as needed
	 */
	public DrawView(Context c, AttributeSet as) {
		super(c, as);
		/*create a gesture detector*/
		gd = new GestureDetector(c, this);
		//gd.setIsLongpressEnabled(true);	// allow user to use long press gesture
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
		/*initialize collection of particles*/
		particles = new ArrayList<Particle>();
	}
	
	public void onDraw(Canvas c) {
		c.drawPaint(backgroundColor);
		for(Particle p: particles) {
			c.drawCircle((float)p.getXPosition(), (float)p.getYPosition(), 16, particleColor);
		}
	}
	
	public void update() {
		/*update particle positions*/
		for(Particle current: particles) {
			current.setXPosition(current.getXPosition()+current.getXVelocity());
			current.setYPosition(current.getYPosition()+current.getYVelocity());
		}
		/*friction*/
		if(allowFriction) {
			for(Particle p: particles) {
				p.setXVelocity(p.getXVelocity()*.98f);
				p.setYVelocity(p.getYVelocity()*.98f);
			}
		}
		/*render changes*/
		invalidate();
	}
	
	/* setOptions
	 * @param: receives boolean values to set options to
	 * @end: private member values have been set to their new values
	 */
	public void setOptions(boolean e, boolean i, boolean p, boolean g, boolean f, boolean af) {
		allowElastic = e;
		allowInelastic = i;
		allowGravityP = p;
		allowGravityG = g;
		allowFriction = f;
		allowFling = af;
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent me) {
		return gd.onTouchEvent(me);
	}
	
	@Override
	public boolean onDown(MotionEvent e) { return true;	}	//must return true for other events to register

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
		/*check if user has allowed particle fling*/
		if(allowFling) {
			/*check each particle to see if it was selected*/
			for(Particle p: particles) {
				/*where did the user touch*/
				float userX = e1.getX();
				float userY = e1.getY();
				/*check if it was within the particles selectable zone*/
				if(p.getXPosition()-32 <= userX && p.getXPosition()+32 >= userX) { // x axis
					if(p.getYPosition()-32 <= userY && p.getYPosition()+32 >= userY) { // y axis
						/*set proportional particle velocities*/
						p.setXVelocity(velocityX/100);
						p.setYVelocity(velocityY/100);
					}
				}
			}
		}
		return false;
	}

	@Override
	public void onLongPress(MotionEvent e) {
		/*add a new particle to the particles collection*/
		particles.add(new Particle(e.getX(), e.getY(), 0, 0));
		/*refresh screen*/
		invalidate();
	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onShowPress(MotionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}

}