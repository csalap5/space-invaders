import java.util.Random;

import javax.sound.sampled.Clip;
/**
 * Represents the special "Mystery" ship that moves across the screen 
 * and awards a random number of points when destroyed.
 */
public class Mystery extends Invader {
	/**
     * Enum representing possible point values awarded for destroying the Mystery ship.
     */
	public enum PointValue{
		FIFTY(50),
		HUNDRED(100),
		ONEFIFTY(150),
		THREEHUNDRED(300);
		
		private final int points;
		
		/**
         * Constructs a PointValue enum with a specific point amount.
         *
         * @param points the number of points
         */
		PointValue(int points){
			this.points=points;
		}
	}
	private static int getRandom() {
		var values = PointValue.values();
		var index  = new Random().nextInt( values.length );
		return values[ index ].points;
	}
	private int speed;
	private Clip mystSound;
	 /**
     * Constructs a Mystery ship at the given position with a random point value.
     *
     * @param x the x-coordinate
     * @param y the y-coordinate
     * @param w the width of the ship
     * @param h the height of the ship
     */
	public Mystery(int x, int y, int w, int h) {
		super(x,y,w,h,getRandom());
		normImage = Drawable.getImage("img_mystery.gif");
		swapImage = normImage;
		this.speed = 5;
		
	}
	
	/**
     * Moves the Mystery ship horizontally based on its speed.
     */
	public void move() {
		this.setX(this.getX()+this.speed);
	}
	/**
     * Sets the speed of the Mystery ship.
     *
     * @param s the new speed
     */
	public void setSpeed(int s) {
		this.speed = s; 
	}
	
	/**
     * Plays the Mystery ship's sound.
     */
	public void playSound() {
		
	}
	
	/**
     * Gets the speed of the Mystery ship.
     *
     * @return the speed
     */
	public int getSpeed() {
		return this.speed; 
	}
}
