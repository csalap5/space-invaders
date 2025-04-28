import java.awt.Graphics2D;
/**
 * Represents the player's base in the game.
 */
public class Base extends Ship {
	/**
     * Enum for possible movement directions.
     */
	public enum Move { RIGHT, LEFT }
	
//	private Image image = Drawable.getImage("img_base.gif");
//	private Image image2 = Drawable.getImage("img_basehit.gif");
//	private Clip  sound = Ship.getSound( "aud_basefire.wav" );
	/**
     * Creates a new Base object with specified position and size.
     * Loads the images and sound for the base.
     *
     * @param x the x-coordinate
     * @param y the y-coordinate
     * @param w the width
     * @param h the height
     */
	public Base(int x, int y, int w, int h) {
		super(x, y, w, h);
		normImage = Drawable.getImage("img_base.gif");
		hitImage = Drawable.getImage("img_basehit.gif");
		sound = Ship.getSound( "aud_basefire.wav" );
	}
	/**
     * Draws the base in its normal state.
     *
     * @param g2 the Graphics2D context to draw on
     */
	@Override
	public void draw(Graphics2D g2) {
		var x = getX();
		var y = getY();
		g2.drawImage(normImage, x, y, null);
	}
	 /**
     * Draws the base in its destroyed state.
     *
     * @param g2 the Graphics2D context to draw on
     */
	public void drawDestroyed(Graphics2D g2) {
		g2.drawImage( hitImage, getX(), getY(), 20, 20, null );
	}
	 /**
     * Fires a missile from the base.
     *
     * @return a new Missile object ready to be fired
     */
	public Missile fireMissile() {
		var x = getX();
		var y = getY();
//		sound.setFramePosition(0);
//		sound.start();
		return new Missile( x+25, y, false).fire();
	}
}
