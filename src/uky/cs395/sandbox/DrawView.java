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
	private GestureDetector gd;
	/*paint values*/
	private Paint backgroundColor;
	private Paint particleColor;
	/*screen dimensions*/
	private int xSize;
	private int ySize;
	/*options*/
	private boolean allowElastic;
	private boolean allowInelastic;
	private boolean allowWalls;
	private boolean allowGravityP;
	private boolean allowGravityG;
	private boolean allowFriction;
	private boolean allowFling;
	/*particle collection*/
	private ArrayList<Particle> particles;
	
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
		allowWalls = false;
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
	
	public synchronized void update() {
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
		/*ground gravity*/
		if(allowGravityG) {
			for(Particle p: particles) {
				p.setYVelocity(p.getYVelocity()+0.25f);
			}
		}
		/*particle to particle gravity*/
		if(allowGravityP) {
			for(int i=0; i<particles.size()-1; i++) {
				for(int j=i+1; j<particles.size(); j++) {
					float xDist = particles.get(i).getXPosition()-particles.get(j).getXPosition();
					float yDist = particles.get(i).getYPosition()-particles.get(j).getYPosition();
					float dist = (float)Math.sqrt(xDist*xDist+yDist*yDist);
					
					float accel = 100/(dist*dist);
					float xAccel = accel*(xDist/dist);
					float yAccel = accel*(yDist/dist);
					
					particles.get(j).setXVelocity(particles.get(j).getXVelocity()+xAccel);
					particles.get(i).setXVelocity(particles.get(i).getXVelocity()-xAccel);
					
					particles.get(j).setYVelocity(particles.get(j).getYVelocity()+yAccel);
					particles.get(i).setYVelocity(particles.get(i).getYVelocity()-yAccel);
				}
			}
		}
		/*wall collisions*/
		if(allowWalls) {
			/*check each particle*/
			for(int i=0; i<particles.size(); i++) {
				float tempXPos = particles.get(i).getXPosition();	//x position of current particle
				float tempYPos = particles.get(i).getYPosition();	//y position of current particle
				float tempRad = particles.get(i).getRadius();		//radius of current particle
				if(particles.get(i).getXVelocity() > 0) { //right wall
					if(tempXPos+tempRad >= xSize)
						particles.get(i).setXVelocity(particles.get(i).getXVelocity()*-1f);	//flip x velocity
				} else if(particles.get(i).getXVelocity() < 0) { //left wall
					if(tempXPos-tempRad <= 0)
						particles.get(i).setXVelocity(particles.get(i).getXVelocity()*-1f); //flip x velocity
				}
				if(particles.get(i).getYVelocity() > 0) { //bottom wall
					if(tempYPos+tempRad >= ySize)
						particles.get(i).setYVelocity(particles.get(i).getYVelocity()*-1f); //flip y velocity
						
 				} else if(particles.get(i).getYVelocity() < 0) { //top wall
 					if(tempYPos-tempRad <= 0)
 						particles.get(i).setYVelocity(particles.get(i).getYVelocity()*-1f); //flip y velocity
 				}
				
			}
		}
		/*detect collisions*/
		for(int i=0; i<particles.size(); i++) {
			for(int j=i+1; j<particles.size(); j++){
				/*check the distance between the particles*/
				float xDistance = particles.get(i).getXPosition()-particles.get(j).getXPosition();
				float yDistance = particles.get(i).getYPosition()-particles.get(j).getYPosition();
				xDistance *= xDistance;
				yDistance *= yDistance;
				float distance = (float)Math.sqrt(xDistance+yDistance);
				/*check if collided*/
				if(distance < (particles.get(i).getRadius()+particles.get(j).getRadius())) {
					/*separate particles*/
					if(allowElastic || allowInelastic) {
						while(distance < (particles.get(i).getRadius()+particles.get(j).getRadius())){
							/*move apart in appropriate directions*/
							if(particles.get(i).getXPosition() > particles.get(j).getXPosition()) {
								particles.get(i).setXPosition(particles.get(i).getXPosition()+1);
								particles.get(j).setXPosition(particles.get(j).getXPosition()-1);
							} else {
								particles.get(i).setXPosition(particles.get(i).getXPosition()-1);
								particles.get(j).setXPosition(particles.get(j).getXPosition()+1);
							}
							if(particles.get(i).getYPosition() > particles.get(j).getYPosition()) {
								particles.get(i).setYPosition(particles.get(i).getYPosition()+1);
								particles.get(j).setYPosition(particles.get(j).getYPosition()-1);
							} else {
								particles.get(i).setYPosition(particles.get(i).getYPosition()-1);
								particles.get(j).setYPosition(particles.get(j).getYPosition()+1);
							}
							/*recalculate new distance*/
							xDistance = particles.get(i).getXPosition()-particles.get(j).getXPosition();
							yDistance = particles.get(i).getYPosition()-particles.get(j).getYPosition();
							xDistance *= xDistance;
							yDistance *= yDistance;
							distance = (float)Math.sqrt(xDistance+yDistance);
						}
					}
					/*elastic collision*/
					if(allowElastic) {
						double dx = particles.get(i).getXPosition() - particles.get(j).getXPosition();
						double dy = particles.get(i).getYPosition() - particles.get(j).getYPosition();
					    double collisionAngle = Math.atan2(dy, dx);
					    double magnitude1 = Math.sqrt(particles.get(i).getXVelocity()*particles.get(i).getXVelocity()+particles.get(i).getYVelocity()*particles.get(i).getYVelocity());
					    double magnitude2 = Math.sqrt(particles.get(j).getXVelocity()*particles.get(j).getXVelocity()+particles.get(j).getYVelocity()*particles.get(j).getYVelocity());
					    double direction1 = Math.atan2(particles.get(i).getYVelocity(), particles.get(i).getXVelocity());
					    double direction2 = Math.atan2(particles.get(j).getYVelocity(), particles.get(j).getXVelocity());
					    double new_xspeed1 = magnitude1*Math.cos(direction1-collisionAngle);
					    double new_yspeed1 = magnitude1*Math.sin(direction1-collisionAngle);
					    double new_xspeed2 = magnitude2*Math.cos(direction2-collisionAngle);
					    double new_yspeed2 = magnitude2*Math.sin(direction2-collisionAngle);
					    double final_xspeed1 = new_xspeed2;
					    double final_xspeed2 = new_xspeed1;
					    double final_yspeed1 = new_yspeed1;
					    double final_yspeed2 = new_yspeed2;
					    particles.get(i).setXVelocity((float)(Math.cos(collisionAngle)*final_xspeed1+Math.cos(collisionAngle+Math.PI/2)*final_yspeed1));
					    particles.get(i).setYVelocity((float)(Math.sin(collisionAngle)*final_xspeed1+Math.sin(collisionAngle+Math.PI/2)*final_yspeed1));
					    particles.get(j).setXVelocity((float)(Math.cos(collisionAngle)*final_xspeed2+Math.cos(collisionAngle+Math.PI/2)*final_yspeed2));
					    particles.get(j).setYVelocity((float)(Math.sin(collisionAngle)*final_xspeed2+Math.sin(collisionAngle+Math.PI/2)*final_yspeed2));
						/*
						double theta0 = Math.atan(particles.get(i).getYVelocity()/particles.get(i).getXVelocity());
						double theta1 = Math.atan(particles.get(j).getYVelocity()/particles.get(j).getYVelocity());
						double phi = Math.atan2(Math.sqrt(yDistance), Math.sqrt(xDistance)); 
								//(Math.PI/2)+Math.atan((particles.get(j).getYPosition()-particles.get(i).getYPosition())/(particles.get(j).getXPosition()-particles.get(i).getXPosition()));
						double velocity0 = Math.sqrt(Math.pow(particles.get(i).getXVelocity(), 2)+Math.pow(particles.get(i).getYVelocity(), 2));
						double velocity1 = Math.sqrt(Math.pow(particles.get(j).getXVelocity(), 2)+Math.pow(particles.get(j).getYVelocity(), 2));
						double mass0 = particles.get(i).getMass();
						double mass1 = particles.get(j).getMass();
						double finalVelocity0X = velocity0*Math.cos(theta0-phi)*(mass0-mass1)+2*mass1*velocity1*Math.cos(theta1-phi);
						finalVelocity0X /= (mass0+mass1);
						finalVelocity0X *= Math.cos(phi);
						finalVelocity0X += velocity0*Math.sin(theta0-phi)*Math.cos(phi+(Math.PI/2));
						double finalVelocity0Y = velocity0*Math.cos(theta0-phi)*(mass0-mass1)+2*mass1*velocity1*Math.cos(theta1-phi);
						finalVelocity0Y /= (mass0+mass1);
						finalVelocity0Y *= Math.sin(phi);
						finalVelocity0Y += velocity0*Math.sin(theta0-phi)*Math.sin(phi+(Math.PI/2));
						double finalVelocity1X = velocity1*Math.cos(theta1-phi)*(mass1-mass0)+2*mass0*velocity0*Math.cos(theta0-phi);
						finalVelocity1X /= (mass0+mass1);
						finalVelocity1X *= Math.cos(phi);
						finalVelocity1X += velocity1*Math.sin(theta1-phi)*Math.cos(phi+(Math.PI/2));
						double finalVelocity1Y = velocity1*Math.cos(theta1-phi)*(mass1-mass0)+2*mass0*velocity0*Math.cos(theta0-phi);
						finalVelocity1Y /= (mass0+mass1);
						finalVelocity1Y *= Math.sin(phi);
						finalVelocity1Y += velocity1*Math.sin(theta1-phi)*Math.sin(phi+(Math.PI/2));
						particles.get(i).setXVelocity((float)finalVelocity0X);
						particles.get(i).setYVelocity((float)finalVelocity0Y);
						particles.get(j).setXVelocity((float)finalVelocity1X);
						particles.get(j).setYVelocity((float)finalVelocity1Y);
						*/
					}
				}
			}
		}
		/*render changes*/
		invalidate();
	}
	
	/* setOptions
	 * @param: receives boolean values to set options to
	 * @end: private member values have been set to their new values
	 */
	public void setOptions(boolean e, boolean i,boolean w, boolean p, boolean g, boolean fr, boolean af) {
		allowElastic = e;
		allowInelastic = i;
		allowWalls = w;
		allowGravityP = p;
		allowGravityG = g;
		allowFriction = fr;
		allowFling = af;
	}
	
	/* onSizeChanged
	 * @param: accepts new view width, height, oldWidth and oldHeight
	 * @end: fixes the app's incorrect dimensions for screen height and width after creation
	 * 		for all devices
	 * (non-Javadoc)
	 * @see android.view.View#onSizeChanged(int, int, int, int)
	 */
	@Override
	public void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		/*reset wall boundaries*/
		xSize = w;
		ySize = h;
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
		particles.add(new Particle(e.getX(), e.getY(), 0, 0, 1));
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