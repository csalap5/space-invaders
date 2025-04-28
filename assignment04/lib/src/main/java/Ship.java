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
	private final Clip hitSound = Ship.getSound("aud_hit.wav");
	private int timeFromHit = 0;
	
	
	private Clip sound;
	private Image normImage;
	private Image swapImage;
	private Image hitImage;
	
	/**
	 * Getter method for sound clip
	 * 
	 * @return sound
	 */
	public Clip getSound() {
		return sound;
	}

	/**
	 * Getter method for normImage
	 * 
	 * @return normImage
	 */
	public Image getNormImage() {
		return normImage;
	}

	/**
	 * Getter method for swapImage
	 * 
	 * @return swapImage
	 */
	public Image getSwapImage() {
		return swapImage;
	}
	/**
	 * Getter method for hitImage
	 * 
	 * @return hitImage
	 */
	public Image getHitImage() {
		return hitImage;
	}
	/**
	 * Setter method for sound
	 * 
	 * @param sound Sound to be set
	 */
	public void setSound(Clip sound) {
		this.sound = sound;
	}
	/**
	 * Setter method for normImage
	 * 
	 * @param normImage Image to be set
	 */
	public void setNormImage(Image normImage) {
		this.normImage = normImage;
	}
	/**
	 * Setter method for swapImage
	 * 
	 * @param swapImage Image to be set
	 */
	public void setSwapImage(Image swapImage) {
		this.swapImage = swapImage;
	}
	/**
	 * Setter method for hitImage
	 * 
	 * @param hitImage Image to be set
	 */
	public void setHitImage(Image hitImage) {
		this.hitImage = hitImage;
	}

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
	 * Increments the amount of time since object was hit.
	 * 
	 * @return timeFromHit amount of time since object was hit
	 */
	public int incTimeFromHit() {
		timeFromHit++;
		return timeFromHit;
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
