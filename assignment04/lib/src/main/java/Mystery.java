import java.awt.Graphics2D;
import java.util.Random;

import javax.sound.sampled.Clip;
/*
 * 
 */
public class Mystery extends Invader {
	/*
	 * 
	 */
	public enum PointValue{
		FIFTY(50),
		HUNDRED(100),
		ONEFIFTY(150),
		THREEHUNDRED(300);
		
		private final int points;
		
		
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
	/*
	 * 
	 */
	public Mystery(int x, int y, int w, int h) {
		super(x,y,w,h,getRandom());
		normImage = Drawable.getImage("img_mystery.gif");
		this.speed = 5;
		
	}
	
	/*
	 * 
	 */
	public void move() {
		this.setX(this.getX()+this.speed);
	}
	/*
	 * 
	 */
	public void setSpeed(int s) {
		this.speed = s; 
	}
	
	/*
	 * 
	 */
	public void playSound() {
		
	}
}
