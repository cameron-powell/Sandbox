package uky.cs395.sandbox;

public class Particle {
	
	/*variables*/
	private double xPosition, yPosition;
	private double xVelocity, yVelocity;
	private double radius;
	private double mass;
	
	/*default constructor*/
	public Particle() {
		this.xPosition = 0;
		this.yPosition = 0;
		this.xVelocity = 0;
		this.yVelocity = 0;
		this.radius = 0;
		this.mass = 0;
	}
	
	/*param constructor*/
	public Particle(double xp, double yp, double xv, double yv, double r, double m) {
		this.xPosition = xp;
		this.yPosition = yp;
		this.xVelocity = xv;
		this.yVelocity = yv;
		this.radius = r;
		this.mass = m;
	}
	
	/*accessors*/
	public double getXPosition() {
		return this.xPosition;
	}
	
	public double getYPosition() {
		return this.yPosition;
	}
	
	public double getXVelocity() {
		return this.xVelocity;
	}
	
	public double getYVelocity() {
		return this.yVelocity;
	}
	
	public double getRadius() {
		return radius;
	}
	
	public double getMass() {
		return mass;
	}
	
	/*mutators*/
	public void setXPosition(double xp) {
		this.xPosition = xp;
	}
	
	public void setYPosition(double yp) {
		this.yPosition = yp;
	}
	
	public void setXVelocity(double xv) {
		this.xVelocity = xv;
	}
	
	public void setYVelocity(double yv) {
		this.yVelocity = yv;
	}
	
	public void setRadius(double r){
		this.radius = r;
	}
	
	public void setMass(double m) {
		this.mass = m;
	}

}
