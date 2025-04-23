import java.awt.Graphics2D;
import java.awt.Image;
import java.util.Set;

import javax.sound.sampled.Clip;

public class Base extends Ship {
	
	public enum Move { RIGHT, LEFT }
	
//	private Image image = Drawable.getImage("img_base.gif");
//	private Image image2 = Drawable.getImage("img_basehit.gif");
//	private Clip  sound = Ship.getSound( "aud_basefire.wav" );
	
	public Base(int x, int y, int w, int h) {
		super(x, y, w, h);
		normImage = Drawable.getImage("img_base.gif");
		hitImage = Drawable.getImage("img_basehit.gif");
		sound = Ship.getSound( "aud_basefire.wav" );
	}
	@Override
	public void draw(Graphics2D g2) {
		var x = getX();
		var y = getY();
		g2.drawImage(normImage, x, y, null);
	}
	public Missile fireMissile() {
		var x = getX();
		var y = getY();
		return new Missile( x+25, y).fire();
	}

}
