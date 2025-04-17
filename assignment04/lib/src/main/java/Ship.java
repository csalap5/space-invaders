import java.awt.Graphics2D;


public abstract class Ship extends Drawable {
	
	protected Ship(int x, int y) {
		super(x, y);
	}
	@Override
	public abstract void draw(Graphics2D g2);
	public abstract Photon firePhoton();
	
}
