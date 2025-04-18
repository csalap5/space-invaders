import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
/**
 * 
 */
public class SpaceInvaders extends JFrame {
	private static final long serialVersionUID = 1L;
	private Timer timer;
	/**
	 * 
	 */
	public SpaceInvaders() {
		setTitle("Space Invaders");

		var menubar = new JMenuBar();
		setJMenuBar(menubar);
		
		var game = new JMenu("Game");
		var help = new JMenu("Help");
		menubar.add(game);
		menubar.add(help);
		
		var about = new JMenuItem("About...");
		help.add(about);
		var newGame = game.add("New Game");
		game.addSeparator();
		var pause = game.add("Pause");
		var resume = game.add("Resume");
		game.addSeparator();
		var exit = game.add("Quit");
		exit.addActionListener(e -> {
			var result = JOptionPane.showConfirmDialog(this, "Do you want to exit?");
			if (result == JOptionPane.OK_OPTION) {
				dispose();
			}
		});
		about.addActionListener(e -> JOptionPane.showMessageDialog(this, "Space Invaders:"+ "\n" + "By Carter Salapka and Kevin Farnsworth"));
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				var result = JOptionPane.showConfirmDialog(SpaceInvaders.this, "Dare to Quit?");
				if (result == JOptionPane.YES_OPTION) {
					dispose();
				}				
			}
		});
		
		add(new JPanel() {

			private int x = 325;
			private int y = 430;
			boolean fired = false;
			boolean right = false;
			boolean left = false;

			{
				setBackground(Color.BLACK);
				setFocusable(true);
				addKeyListener( new KeyListener() {
					@Override
					public void keyTyped(KeyEvent e) {
					}
					@Override
					public void keyPressed(KeyEvent e) {
						var code = e.getKeyCode();
						if (code == KeyEvent.VK_LEFT) left = true;
						if (code == KeyEvent.VK_RIGHT) right = true;
						
						if (code == KeyEvent.VK_SPACE) {
//							sound.setFramePosition(0);
//							sound.start();
							fired = true;
						}
						repaint();
					}
					@Override
					public void keyReleased(KeyEvent e) {
						var code = e.getKeyCode();
//						if (code == KeyEvent.VK_SPACE)
//							sound.stop();
						if (code == KeyEvent.VK_LEFT) left = false;
						if (code == KeyEvent.VK_RIGHT) right = false;
					}
				});
				timer = new Timer(50, e -> {
					if (fired) {
						//rocket
					}
					if (right && x < 650) x += 20;
					if (left && x > 20) x -= 20;
					repaint();
					
				});
				timer.start();
			}

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				var g2 = (Graphics2D) g;

//				g2.drawImage(image, x, y, null);
//			}
			
//		});
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				timer.stop();
			}
		});
		setSize(700, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);		
	}
	//put in Drawable already(getSound and getImage)
	
	public static void main(String[] args) {
		var v = new SpaceInvaders();
		v.setVisible(true);

	}

}

	

