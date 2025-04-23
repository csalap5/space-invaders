import java.awt.Graphics2D;

public class Mystery extends Invader {
	
	public Mystery(int x, int y, int w, int h) {
		super(x,y,w,h,1);
		//need to randomize points
		normImage = Drawable.getImage("img_mystery.gif");
	}
}
