import java.awt.Graphics2D;
import java.awt.Image;
import java.util.Set;



public class Base extends Ship {

	public enum Move { RIGHT, LEFT }
	
	private Image image = Drawable.getImage("/img_base.gif");
	private Image image2 = Drawable.getImage("/img_basehit.gif");
	public Base(int x, int y, int w, int h) {
		super(x, y, w, h);
	}
	@Override
	public void draw(Graphics2D g2) {
		var x = getX();
		var y = getY();
		g2.drawImage(image, x, y, null);
	}
	public void move(Set<Move> moves) {
		var x = getX();
		var y = getY();
		for (var move : moves) {
			switch (move) {
			case RIGHT -> x += 20;
			case LEFT  -> x -= 20;
			}
		}
		setX(x);
		setY(y);
	}
	public Missile fireMissile() {
		var x = getX();
		var y = getY();
		return new Missile( x+25, y).fire();
	}

}
