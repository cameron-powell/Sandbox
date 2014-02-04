package uky.cs395.sandbox;

public class Particle {
	
	//variables
	private double xPosition, yPosition;
	private double xVelocity, yVelocity;
	
	//constructors
	public Particle() {
		this.xPosition = 0;
		this.yPosition = 0;
		this.xVelocity = 0;
		this.yVelocity = 0;
	}
	
	public Particle(double xp, double yp, double xv, double yv) {
		this.xPosition = xp;
		this.yPosition = yp;
		this.xVelocity = xv;
		this.yVelocity = yv;
	}
	
	//accessors
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
	
	//mutators
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

}
