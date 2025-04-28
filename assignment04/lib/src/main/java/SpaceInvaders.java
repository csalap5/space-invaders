import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

/**
 * Represents the main window for the Space Invaders game.
 * Sets up the game menu and handles user interactions like starting a new game,
 * pausing, resuming, and quitting.
 * 
 * @author Carter Salapka and Kevin Farnsworth
 */
@SuppressWarnings("serial")
public class SpaceInvaders extends JFrame {
	private Panel panel;
	/**
     * Constructs the Space Invaders window, setting up the menu and game controls.
     * Initializes the pause/resume functionality and handles user actions.
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
		panel = new Panel();
		add(panel);
		
		pause.setEnabled(false);
	    resume.setEnabled(false);
		
	    game.addActionListener(e -> {
	    	pause.setEnabled(true);
		    resume.setEnabled(true);
	    });
		pause.addActionListener(e -> {
			panel.pauseGame();
			resume.setEnabled(true);
			pause.setEnabled(false);
			JOptionPane.showMessageDialog(this, "Game Paused. Click Resume to Continue.");
		});
		
		resume.addActionListener(e -> {
			 pause.setEnabled(true);
		     resume.setEnabled(false);
		     panel.resumeGame();
		});
		newGame.addActionListener(e -> {
			panel.pauseGame();
			var result = JOptionPane.showConfirmDialog(this, "Start a New Game?", "New Game", JOptionPane.YES_NO_OPTION);
			pause.setEnabled(false);
		    resume.setEnabled(false);
			if (result == JOptionPane.YES_OPTION) {
				remove(panel);
				panel.pauseGame();
				panel = new Panel();
				add(panel);
				revalidate();
				repaint();
				
				 pause.setEnabled(true);
			     resume.setEnabled(false);
			}
			else {
				panel.resumeGame();
			}
		});
		
		exit.addActionListener(e -> {
			panel.pauseGame();
			var result = JOptionPane.showConfirmDialog(this, "Do you want to exit?");
			pause.setEnabled(false);
		    resume.setEnabled(false);
			if (result == JOptionPane.OK_OPTION) {
				
				panel.pauseGame();
				dispose();
			}
		});
		about.addActionListener(e -> {
			panel.pauseGame();
			JOptionPane.showMessageDialog(this, 
				"Space Invaders:"+ "\n" + "By Carter Salapka and Kevin Farnsworth");
		});
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				var result = JOptionPane.showConfirmDialog(SpaceInvaders.this, "Dare to Quit?");
				pause.setEnabled(false);
			    resume.setEnabled(false);
				if (result == JOptionPane.YES_OPTION) {
					
					panel.pauseGame();
					dispose();
				}				
			}
		});
	
		setSize(500, 450);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);			
	}
	/**
     * Main method to launch the Space Invaders game.
     * 
     * @param args arguments
     */
	public static void main(String[] args) {
		var v = new SpaceInvaders();
		v.setVisible(true);
	}
}

