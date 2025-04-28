import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

/**
 * Class representing a missile fired by either the base or an invader.
 */
public class Missile extends Drawable {
	private Font font = new Font("Arial", Font.BOLD, 15 );
	private boolean fromInvader;
	private boolean hitOne = false;
	/**
     * Constructs a missile at the given position and direction.
     *
     * @param x the x-coordinate
     * @param y the y-coordinate
     * @param fromInvader true if fired by an invader, false if fired by the base
     */
	public Missile(int x, int y, boolean fromInvader) {
		super(x, y, .2, 2);
		this.fromInvader = fromInvader;
	}
	 /**
     * Draws the missile on the screen.
     *
     * @param g2 the graphics context
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
     * Moves the missile in the correct direction.
     */
	public void move() {
		if (fromInvader) {
			setY(getY() + 5);
		}
		else {
			setY(getY() - 10);
		}
	}
	/**
     * Returns this missile (used for method chaining).
     *
     * @return the missile itself
     */
	public Missile fire() {
		return this;
	}
	/**
     * Checks if the missile has gone off the screen.
     *
     * @return true if out of bounds, false otherwise
     */
	public boolean isOutOfBounds() {
		var y = getY();
		return y < 0 || y > 500;
	}
	/**
     * Checks if the missile was fired by an invader.
     *
     * @return true if from an invader, false if from the base
     */
	public boolean isFromInvader() {
		return fromInvader;
	}
   /**
    * Marks that this missile has hit something.
    */
	public void setHitOne() {
		hitOne=true;
	}
	/**
     * Checks if the missile has already hit something.
     *
     * @return true if it has hit, false otherwise
     */
	public boolean getHitOne() {
		return hitOne;
	}

}
