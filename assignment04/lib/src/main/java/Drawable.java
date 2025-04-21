import java.awt.Graphics2D;
import java.awt.Image;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;

public abstract class Drawable {

	private int x;
	private int y;
	private int width;
	private int height;
	
	public Drawable(int x, int y, int w, int h) {
		this.x = x;
		this.y = y;
		this.width = w;
		this.height = h;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public abstract void draw(Graphics2D g2);
	protected void setX(int x) {
		this.x = x;
	}
	protected void setY(int y) {
		this.y = y;
	}
	protected static Image getImage(String filename) {
		URL url = Drawable.class.getResource("/" + filename);
		ImageIcon icon = new ImageIcon(url);
		return icon.getImage();
	}
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
