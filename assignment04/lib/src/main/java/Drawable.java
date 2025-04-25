import java.awt.Graphics2D;
import java.awt.Image;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Set;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;

public abstract class Drawable {
	
	public enum Move { UP, DOWN, RIGHT, LEFT }
	
	private int x;
	private int y;
	private double width;
	private int height;
	
	public Drawable(int x, int y, double w, int h) {
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
	public double getW() {
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
	protected void setW(double w) {
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
	public void move(Set<Move> moves) {
		var x = getX();
		var y = getY();
		for (var move : moves) {
			switch (move) {
			case UP    -> y -= 20;
			case DOWN  -> y += 20;
			case RIGHT -> x += 20;
			case LEFT  -> x -= 20;
			}
		}
		setX(x);
		setY(y);
	}
	
}
