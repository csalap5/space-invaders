import java.awt.Graphics2D;


public abstract class Ship extends Drawable {
	
	protected Ship(int x, int y, int w, int h) {
		super(x, y, w, h);
	}
	
	public abstract Missile fireMissile();
	
}
