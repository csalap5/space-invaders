import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Timer;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Panel extends JPanel implements KeyListener{
	private Timer timer;
	//private int x =325;
	//private int y = 430;
	private boolean right = false;
	private boolean left = false;
	
	private Base base;
	private List<Invader> invaders;
	private List<Missile> missiles;
	
	public Panel() {
		setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(this);
        
        base = new Base(325,430,100,100);
		invaders = new ArrayList<>();
        missiles = new ArrayList<>();
        
        invaders.add(new InvaderTop(100,200,150,150));
        invaders.add(new InvaderMiddle(100, 400, 150, 150));
        invaders.add(new InvaderBottom(300, 200, 150, 150));
        invaders.add(new Mystery(20, 300, 150, 150));
        
        timer = new Timer(50, e -> gameLoop());
        timer.start();

	}
	
	private void gameLoop() {
		if (right && base.getX() < getWidth()-40) {
			base.setX(base.getX()+20);
		}
        if (left && base.getX() > 20) {
        	base.setX(base.getX()-20);
        }
        List<Missile> toRemove = new ArrayList<>();
        for (Missile m : missiles) {
        	m.move();
        	if (m.isOutOfBounds()) {
        		toRemove.add(m);
        	}
        	//something to remove missile if it leaves the screen?
        }
        
        missiles.removeAll(toRemove);
        repaint(); 
	}
	
	public void fireMissile() {
		missiles.add(new Missile(base.getX() + 10, base.getY() ));
	}
	
	 @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        base.draw(g2);
        for (Invader inv : invaders) {
        	inv.draw(g2);
        }
        for (Missile m : missiles) {
        	m.draw(g2);
        }
    }
	
	@Override
	public void keyTyped(KeyEvent e) {	
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		var code = e.getKeyCode();
		if (code == KeyEvent.VK_LEFT) left = true;
		if (code == KeyEvent.VK_RIGHT) right = true;
		
		if (code == KeyEvent.VK_SPACE && missiles.isEmpty()) {
			base.sound.setFramePosition(0);
			base.sound.start();

			fireMissile();
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		var code = e.getKeyCode();
		if (code == KeyEvent.VK_SPACE) base.sound.stop();
		if (code == KeyEvent.VK_LEFT) left = false;
		if (code == KeyEvent.VK_RIGHT) right = false;
	}
	
	public void pauseGame() {
	    timer.stop();
	}
	public void resumeGame() {
	    timer.start();
	}
	
	
	
	
}
