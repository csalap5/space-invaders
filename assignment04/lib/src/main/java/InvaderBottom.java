import java.awt.Graphics2D;
/*
 * 
 */
public class InvaderBottom extends Invader {
	/*
	 * 
	 */
	public InvaderBottom(int x, int y, int w, int h) {
		super(x,y,w,h,10);
		normImage = Drawable.getImage("img_invaderbottomA.gif");
		swapImage = Drawable.getImage("img_invaderbottomB.gif");
	}
}
