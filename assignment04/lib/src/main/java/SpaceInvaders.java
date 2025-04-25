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
@SuppressWarnings("serial")
public class SpaceInvaders extends JFrame {
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
		
		Panel panel = new Panel();
		add(panel);
		
		exit.addActionListener(e -> {
			var result = JOptionPane.showConfirmDialog(this, "Do you want to exit?");
			if (result == JOptionPane.OK_OPTION) {
				panel.pauseGame();
				dispose();
			}
		});
		about.addActionListener(e -> JOptionPane.showMessageDialog(this, 
				"Space Invaders:"+ "\n" + "By Carter Salapka and Kevin Farnsworth"));
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				var result = JOptionPane.showConfirmDialog(SpaceInvaders.this, "Dare to Quit?");
				if (result == JOptionPane.YES_OPTION) {
					panel.pauseGame();
					dispose();
				}				
			}
		});

		
		
		setSize(500, 500);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);	
		
	}
	
	public static void main(String[] args) {
		var v = new SpaceInvaders();
		v.setVisible(true);
	}
}



	

