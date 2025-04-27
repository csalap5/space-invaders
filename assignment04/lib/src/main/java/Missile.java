import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import javax.sound.sampled.Clip;
/*
 * 
 */
public class Missile extends Drawable {
	private Font font = new Font("Arial", Font.BOLD, 15 );
	private boolean fromInvader;
	/*
	 * 
	 */
	public Missile(int x, int y, boolean fromInvader) {
		super(x, y, .2, 2);
		this.fromInvader = fromInvader;
	}
	/*
	 * 
	 */
	@Override
	public void draw(Graphics2D g2) {
		var x = getX();
		var y = getY();
		g2.setFont(font);
		g2.setColor(Color.WHITE);
		g2.drawString( "|", x, y);

	}
	/**
	 * 
	 */
	public void move() {
		if (fromInvader) {
			setY(getY() + 5);
		}
		else {
			setY(getY() - 10);
		}
	}
	/*
	 * 
	 */
	public Missile fire() {
		return this;
	}
	/*
	 * 
	 */
	public boolean isOutOfBounds() {
		var y = getY();
		return y < 0 || y > 500;
	}
	/*
	 * 
	 */
	public boolean isFromInvader() {
		return fromInvader;
	}

}
