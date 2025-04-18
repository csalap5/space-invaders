import java.awt.Image;

public abstract class Invader extends Ship {
	private int points;
	private Image image = Drawable.getImage("/img_invaderhit.gif");
	public Invader(int x, int y, int points) {
		super(x , y);
		this.points = points;
	}
	public void drawDestroyed() {
		
	}

}
