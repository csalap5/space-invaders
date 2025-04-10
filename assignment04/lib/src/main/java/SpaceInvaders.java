import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.*;
/**
 * 
 */
public class SpaceInvaders extends JFrame {
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	public SpaceInvaders() {
		setTitle("Window with Colors");
		//setLayout(new FlowLayout());
		
		
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
			{
				setBackground(Color.BLACK);
			}
			
		});
	
		setSize(700, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		
		
	}
	public static void main(String[] args) {
		var v = new SpaceInvaders();
		v.setVisible(true);

	}

}

	

