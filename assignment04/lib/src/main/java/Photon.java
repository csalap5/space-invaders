import java.awt.Font;
import java.awt.Graphics2D;

import javax.sound.sampled.Clip;

public class Photon extends Drawable {
	private String glyph = "|";
	private Font font = new Font("Arial", Font.BOLD, 25 );
	private Clip  sound = Drawable.getSound( "/aud_basefire.wav" );
	
	public Photon(int x, int y) {
		super(x, y, 25, 25);
	}

	@Override
	public void draw(Graphics2D g2) {
		var x = getX();
		var y = getY();
		g2.setFont(font);
		g2.drawString( glyph, x, y);

	}
	public Photon move() {
		var y = getY() - 5;
		setY( y );
		return (y < -50) ? null : this;
	}
	public Photon fire() {
		sound.setFramePosition( 0 );
		sound.start();
		return this;
	}

}
