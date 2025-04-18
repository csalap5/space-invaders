import java.awt.Graphics2D;


public abstract class Ship extends Drawable {
	
	protected Ship(int x, int y) {
		super(x, y);
	}
	
	public abstract Photon firePhoton();
	
}
