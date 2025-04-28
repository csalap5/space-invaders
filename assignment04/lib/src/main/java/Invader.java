import java.awt.Graphics2D;
import java.awt.Image;

/**
 * Abstract class representing an invader ship.
 */
public abstract class Invader extends Ship {
	private int points;
	private boolean swapped;
	/**
     * Constructs an Invader with given position, size, and point value.
     *
     * @param x the x-coordinate
     * @param y the y-coordinate
     * @param w the width
     * @param h the height
     * @param points the points awarded for destroying the invader
     */
	public Invader(int x, int y, int w, int h, int points) {
		super(x , y, w, h);
		this.points = points;
		setHitImage(Drawable.getImage("img_invaderhit.gif"));
		
	}
	/**
     * Draws the invader in its destroyed state.
     *
     * @param g2 the Graphics2D context to draw on
     */
	public void drawDestroyed(Graphics2D g2) {
		g2.drawImage( this.getHitImage(), getX(), getY(), 20, 20, null );
	}
	/**
     * Draws the invader based on its current image state.
     *
     * @param g2 the Graphics2D context to draw on
     */
	@Override
	public void draw(Graphics2D g2) {
		var x = getX();
		var y = getY();
		Image current;
		if (swapped) {
			current = this.getNormImage();
		}
		else {
			//current = swapImage;
			current = this.getSwapImage();
		}
		g2.drawImage(current, x, y, null);
	}
	/**
     * Swaps between the normal and alternate image.
     */
	public void swapImages() {
		swapped = !swapped;
	}
	/**
     * Gets the point value of the invader.
     *
     * @return the point value
     */
	public int getPoints() {
		return points;
	}
	

}

