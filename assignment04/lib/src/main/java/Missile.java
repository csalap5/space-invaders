import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import javax.sound.sampled.Clip;

public class Missile extends Drawable {
//	private String glyph = "|";
	private Font font = new Font("Arial", Font.BOLD, 15 );
	
	public Missile(int x, int y) {
		super(x, y, .2, 2);
	}

	@Override
	public void draw(Graphics2D g2) {
		var x = getX();
		var y = getY();
		g2.setFont(font);
		g2.setColor(Color.WHITE);
		g2.drawString( "|", x, y);

	}
	public Missile move() {
		var y = getY() - 20;
		setY( y );
		return (y < -50) ? null : this;
	}
	public Missile fire() {

		return this;
	}
	public boolean isOutOfBounds() {
		var y = getY();
		return y < 0 || y > 500;
	}

}
