/**
 * Class representing the top row of invaders.
 */
public class InvaderTop extends Invader {
	/**
     * Constructs a top-row invader with given position and size.
     *
     * @param x the x-coordinate
     * @param y the y-coordinate
     * @param w the width
     * @param h the height
     */
	public InvaderTop(int x, int y, int w, int h) {
		super(x, y, w, h, 30);
		normImage = Drawable.getImage("img_invadertopA.gif");
		swapImage = Drawable.getImage("img_invadertopB.gif");
	}
}
