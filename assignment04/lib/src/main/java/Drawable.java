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
	public int getW() {
		return width;
	}
	public int getH() {
		return height;
	}
	protected void setX(int x) {
		this.x = x;
	}
	protected void setY(int y) {
		this.y = y;
	}
	protected void setW(int w) {
		width = w;
	}
	protected void setH(int h) {
		height = h;
	}
	public abstract void draw(Graphics2D g2);
	protected static Image getImage(String filename) {
		URL url = Drawable.class.getResource("/" + filename);
		ImageIcon icon = new ImageIcon(url);
		return icon.getImage();
	}
	//defaults as a public method
	public void move(int pixels, int direction) {
		//initial thought to use int for direction (0 for up, etc.) or bitwise (0001, 0010, 0100, 1000)
		return;
	}
	
}
