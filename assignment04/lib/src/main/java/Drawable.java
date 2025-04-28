import java.awt.Graphics2D;
import java.awt.Image;
import java.net.URL;
import java.util.Set;
import javax.swing.ImageIcon;
/**
 * Abstract class for drawable objects that can be moved and displayed on screen.
 */
public abstract class Drawable {
	/**
     * Enum representing movement directions.
     */
	public enum Move { 
		/**
		 * Up movement.
		 */
		UP, 
		/**
		 * Down movement.
		 */
		DOWN, 
		/**
		 * Right movement.
		 */
		RIGHT, 
		/**
		 * Left movement
		 */
		LEFT }
	
	private int x;
	private int y;
	private double width;
	private int height;
	/**
     * Constructs a Drawable object with given position and size.
     *
     * @param x the x-coordinate
     * @param y the y-coordinate
     * @param w the width
     * @param h the height
     */
	public Drawable(int x, int y, double w, int h) {
		this.x = x;
		this.y = y;
		this.width = w;
		this.height = h;
	}
	/**
     * Gets the x-coordinate.
     *
     * @return the x-coordinate
     */
	public int getX() {
		return x;
	}
	/**
     * Gets the y-coordinate.
     *
     * @return the y-coordinate
     */
	public int getY() {
		return y;
	}
	/**
     * Gets the width.
     *
     * @return the width
     */
	public double getW() {
		return width;
	}
	/**
     * Gets the height.
     *
     * @return the height
     */
	public int getH() {
		return height;
	}
	/**
     * Sets the x-coordinate.
     *
     * @param x the new x-coordinate
     */
	protected void setX(int x) {
		this.x = x;
	}
	/**
     * Sets the y-coordinate.
     *
     * @param y the new y-coordinate
     */
	protected void setY(int y) {
		this.y = y;
	}
	/**
     * Sets the width.
     *
     * @param w the new width
     */
	protected void setW(double w) {
		width = w;
	}
	/**
     * Sets the height.
     *
     * @param h the new height
     */
	protected void setH(int h) {
		height = h;
	}
	/**
     * Draws the image
     *
     * @param g2 Graphics 2D to draw
     */
	public abstract void draw(Graphics2D g2);
	/**
     * Loads an image from the resources folder.
     *
     * @param filename the filename of the image
     * @return the loaded Image
     */
	protected static Image getImage(String filename) {
		URL url = Drawable.class.getResource("/" + filename);
		ImageIcon icon = new ImageIcon(url);
		return icon.getImage();
	}
	/**
     * Moves the object based on a set of directions.
     *
     * @param moves the set of moves to apply
     */
	public void move(Set<Move> moves) {
		var x = getX();
		var y = getY();
		for (var move : moves) {
			switch (move) {
			case UP    -> y -= 20;
			case DOWN  -> y += 20;
			case RIGHT -> x += 20;
			case LEFT  -> x -= 20;
			}
		}
		setX(x);
		setY(y);
	}
	
}
