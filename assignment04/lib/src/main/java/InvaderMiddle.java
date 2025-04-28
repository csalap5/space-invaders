/**
 * Class representing the middle row of invaders.
 */
public class InvaderMiddle extends Invader {
	/**
     * Constructs a middle-row invader with given position and size.
     *
     * @param x the x-coordinate
     * @param y the y-coordinate
     * @param w the width
     * @param h the height
     */
	public InvaderMiddle(int x, int y, int w, int h) {
		super(x,y,w,h,20);
		setNormImage(Drawable.getImage("img_invadermiddleA.gif"));
		setSwapImage(Drawable.getImage("img_invadermiddleB.gif"));
	}
}
