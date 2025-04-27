import java.awt.Image;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/*
 * 
 */
public abstract class Ship extends Drawable {
	
	private boolean isHit = false;
	private final Clip hitSound = Ship.getSound("aud_hit.wav");
	
	/*
	 * 
	 */
	protected Clip sound;
	/*
	 * 
	 */
	protected Image normImage;
	/*
	 * 
	 */
	protected Image swapImage;
	/*
	 * 
	 */
	protected Image hitImage;
	
	/*
	 * 
	 */
	protected Ship(int x, int y, int w, int h) {
		super(x, y, w, h);
	}
	/*
	 * 
	 */
	public boolean isItHit() {
		return isHit;
	}
	/*
	 * 
	 */
	public void setHit() {
		sound.start();
		isHit = true;
	}
	/*
	 * 
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
