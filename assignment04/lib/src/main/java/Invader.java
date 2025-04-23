import java.awt.Graphics2D;
import java.awt.Image;

public abstract class Invader extends Ship {
	private int points;
	private Image hitImage = Drawable.getImage("/img_invaderhit.gif");
	public Invader(int x, int y, int w, int h, int points) {
		super(x , y, w, h);
		this.points = points;
	}
	public void drawDestroyed(Graphics2D g2) {
		g2.drawImage( hitImage, getX(), getY(), 20, 20, null );
	}

}
