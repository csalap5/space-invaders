/**
 * Class representing the bottom row of invaders.
 */
public class InvaderBottom extends Invader {
	/**
     * Constructs a bottom-row invader with given position and size.
     *
     * @param x the x-coordinate
     * @param y the y-coordinate
     * @param w the width
     * @param h the height
     */
	public InvaderBottom(int x, int y, int w, int h) {
		super(x,y,w,h,10);
		normImage = Drawable.getImage("img_invaderbottomA.gif");
		swapImage = Drawable.getImage("img_invaderbottomB.gif");
	}
}
