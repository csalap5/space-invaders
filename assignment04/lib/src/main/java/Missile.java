import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import javax.sound.sampled.Clip;

public class Missile extends Drawable {
//	private String glyph = "|";
	private Font font = new Font("Arial", Font.BOLD, 25 );
	
	public Missile(int x, int y) {
		super(x, y, 2, 10);
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
		var y = getY() - 5;
		setY( y );
		return (y < -50) ? null : this;
	}
	public Missile fire() {
//		sound.setFramePosition( 0 );
//		sound.start();
		return this;
	}

}
