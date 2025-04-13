import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.net.URL;

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
			private Image image = getImage("img_base.gif");
			private int x = 325;
			private int y = 420;

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
						if (code == KeyEvent.VK_LEFT) x -= 20;
						if (code == KeyEvent.VK_RIGHT) x += 20;
						repaint();
					}
					@Override
					public void keyReleased(KeyEvent e) {
						
					}
				}
				);
			}

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				var g2 = (Graphics2D) g;

				g2.drawImage(image, x, y, null);
			}
			
		});
	
		setSize(700, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);		
	}
	private Image getImage(String filename) {
		URL url = getClass().getResource("/" + filename);
		ImageIcon icon = new ImageIcon(url);
		return icon.getImage();
	}
	public static void main(String[] args) {
		var v = new SpaceInvaders();
		v.setVisible(true);

	}

}

	

