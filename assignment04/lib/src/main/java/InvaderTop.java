import java.awt.Graphics2D;

public class InvaderTop extends Invader {
	
	public InvaderTop(int x, int y, int w, int h) {
		super(x, y, w, h, 30);
		normImage = Drawable.getImage("img_invadertopA.gif");
		swapImage = Drawable.getImage("img_invadertopB.gif");
	}
}
