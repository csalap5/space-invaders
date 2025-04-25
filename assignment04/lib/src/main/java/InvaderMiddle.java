import java.awt.Graphics2D;

public class InvaderMiddle extends Invader {
	
	public InvaderMiddle(int x, int y, int w, int h) {
		super(x,y,w,h,20);
		normImage = Drawable.getImage("img_invadermiddleA.gif");
		swapImage = Drawable.getImage("img_invadermiddleb.gif");
	}
}
