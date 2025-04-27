import java.awt.Graphics2D;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

/**
 * 
 */
@SuppressWarnings("serial")
public class SpaceInvaders extends JFrame {
	private Panel panel;
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
		panel = new Panel();
		add(panel);
		
		pause.addActionListener(e -> {
			panel.pauseGame();
			JOptionPane.showMessageDialog(this, "Game Paused. Click Resume to Continue.");
		});
		
		resume.addActionListener(e -> panel.resumeGame());
		newGame.addActionListener(e -> {
			panel.pauseGame();
			var result = JOptionPane.showConfirmDialog(this, "Start a New Game?", "New Game", JOptionPane.YES_NO_OPTION);
			if (result == JOptionPane.YES_OPTION) {
				remove(panel);
				panel.pauseGame();
				panel = new Panel();
				add(panel);
				revalidate();
				repaint();
			}
			else {
				panel.resumeGame();
			}
		});
		
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

		
		
		setSize(500, 450);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
			
	}
	/*
	 * 
	 */
	public static void main(String[] args) {
		var v = new SpaceInvaders();
		v.setVisible(true);
	}
}



	

