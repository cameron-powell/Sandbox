package uky.cs395.sandbox;

public class Particle {
	
	/*variables*/
	private float xPosition, yPosition;
	private float xVelocity, yVelocity;
	private final float RADIUS = 16;
	
	/*default constructor*/
	public Particle() {
		this.xPosition = 0;
		this.yPosition = 0;
		this.xVelocity = 0;
		this.yVelocity = 0;
	}
	
	/*param constructor*/
	public Particle(float xp, float yp, float xv, float yv) {
		this.xPosition = xp;
		this.yPosition = yp;
		this.xVelocity = xv;
		this.yVelocity = yv;
	}
	
	/*accessors*/
	public float getXPosition() {
		return this.xPosition;
	}
	
	public float getYPosition() {
		return this.yPosition;
	}
	
	public float getXVelocity() {
		return this.xVelocity;
	}
	
	public float getYVelocity() {
		return this.yVelocity;
	}
	
	public float getRadius() {
		return RADIUS;
	}
	
	/*mutators*/
	public void setXPosition(float xp) {
		this.xPosition = xp;
	}
	
	public void setYPosition(float yp) {
		this.yPosition = yp;
	}
	
	public void setXVelocity(float xv) {
		this.xVelocity = xv;
	}
	
	public void setYVelocity(float yv) {
		this.yVelocity = yv;
	}

}
