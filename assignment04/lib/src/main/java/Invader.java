import java.awt.Graphics2D;
import java.awt.Image;
/*
 * 
 */
public abstract class Invader extends Ship {
	private int points;
	private boolean swapped;
	/*
	 * 
	 */
	public Invader(int x, int y, int w, int h, int points) {
		super(x , y, w, h);
		this.points = points;
		hitImage=Drawable.getImage("img_invaderhit.gif");
	}
	/*
	 * 
	 */
	public void drawDestroyed(Graphics2D g2) {
		g2.drawImage( hitImage, getX(), getY(), 20, 20, null );
	}
	/*
	 * 
	 */
	@Override
	public void draw(Graphics2D g2) {
		var x = getX();
		var y = getY();
		Image current;
		if (swapped) {
			current = normImage;
		}
		else {
			current = swapImage;
		}
		g2.drawImage(current, x, y, null);
	}
	/*
	 * 
	 */
	public void swapImages() {
		swapped = !swapped;
	}
	
	public int getPoints() {
		return points;
	}
	

}

