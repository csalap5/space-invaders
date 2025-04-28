import java.awt.Image;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Represents a generic Ship object that can be hit and has images and sounds associated with it.
 */
public abstract class Ship extends Drawable {
	
	private boolean isHit = false;
	private boolean wasHit = false;
	private final Clip hitSound = Ship.getSound("aud_hit.wav");
	
	/** Sound associated with the ship,firing sound. */
	protected Clip sound;
	/** Normal (default) ship image. */
	protected Image normImage;
	/** Alternate ship image (for swapping animation). */
	protected Image swapImage;
	/** Image used when the ship is hit. */
	protected Image hitImage;
	
	/**
     * Constructs a Ship at a specific position with width and height.
     *
     * @param x the x-coordinate
     * @param y the y-coordinate
     * @param w the width
     * @param h the height
     */
	protected Ship(int x, int y, int w, int h) {
		super(x, y, w, h);
	}
	/**
     * Checks if the ship has been hit.
     *
     * @return true if the ship is currently hit, false otherwise
     */
	public boolean isItHit() {
		return isHit;
	}
	/**
     * Marks the ship as hit and plays the hit sound.
     */
	public void setHit() {
		hitSound.start();
		isHit = true;
	}
	/**
     * Marks the ship as having been hit previously.
     */
	public void setWasHit() {
		wasHit=true;
	}
	/**
     * Checks if the ship was hit previously.
     *
     * @return true if the ship was previously hit, false otherwise
     */
	public boolean getWasHit() {
		return wasHit;
	}
	/**
     * Loads and returns a sound clip from the given filename.
     *
     * @param filename the name of the sound file
     * @return the loaded Clip, or null if loading fails
     */
	protected static Clip getSound(String filename) {
		Clip clip = null;
		try {
		InputStream in = Drawable.class.getResourceAsStream( filename );
		InputStream buf = new BufferedInputStream( in );
		AudioInputStream stream = AudioSystem.getAudioInputStream( buf );
		clip = AudioSystem.getClip();
		clip.open( stream );
		} catch (UnsupportedAudioFileException |
		IOException | LineUnavailableException e) {
		e.printStackTrace();
		}
		return clip;
	}
}
