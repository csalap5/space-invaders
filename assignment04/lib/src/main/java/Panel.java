import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Timer;

import javax.swing.JPanel;
/*
 * 
 */
@SuppressWarnings("serial")
public class Panel extends JPanel implements KeyListener{
	private Timer timer;
	private boolean right = false;
	private boolean left = false;
	
	private Base base;
	private List<Invader> invaders;
	private List<Missile> missiles;
	private int invaderX = 2;
	private int invaderY = 12;
	private int imgPause = 0;
	
	private int basePulseCounter;
	private int missilePulseCounter;
	private int invaderPulseCounter;
	private int mysteryPulseCounter;
	private int invaderMissilePulseCounter;
	private int basePulseLimit = 1;
	private int missilePulseLimit = 1;
	private int invaderPulseLimit = 40;
	private int mysteryPulseLimit = 2;
	private int invaderMissilePulseLimit = 2;
	
	private Mystery mysteryShip;
	private boolean mysteryShipActive = false;
	private int mysteryShipSpawnCounter = 0;
	private int mysteryShipSpawnLimit = 3000;
	
	/*
	 * 
	 */
	public Panel() {
		setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(this);
        
        base = new Base(325,380,100,100);
		invaders = new ArrayList<>();
        missiles = new ArrayList<>();
        
        int rows = 5;
        int columns = 10;
        int horizontalSpace = 35;
        int totalWidth = (columns - 1) * horizontalSpace;
        int startX = (500 - totalWidth) / 2;
        int startY = 80;
        int verticalSpace = 25;
        
        for (int row = 0; row < rows; row++) {
        	for (int col = 0; col < columns; col++) {
        		int x = startX + col * horizontalSpace;
        		int y = startY + row * verticalSpace;
        		Invader inv;
        		switch (row) {
            case 0 -> inv = new InvaderTop(x, y, 60, 60);
  
            case 1 -> inv = new InvaderMiddle(x, y, 60, 60);
  
            case 2 -> inv = new InvaderMiddle(x, y, 60, 60);
          		
            case 3 -> inv = new InvaderBottom(x, y, 60, 60);
            
            default -> inv = new InvaderBottom(x, y, 60, 60);
          		
          }
        		invaders.add(inv);
        	}
        }
               
        timer = new Timer(10, e -> gameLoop());
        timer.start();

	}
	
	private void gameLoop() {
	    basePulseCounter++;
	    missilePulseCounter++;
	    invaderPulseCounter++;
	    mysteryPulseCounter++;
		imgPause++;
		mysteryShipSpawnCounter++;
		invaderMissilePulseCounter++;
		
		if (!mysteryShipActive) {
			double random = Math.random();
			if (random > 0.95) {
				boolean goingRight = Math.random() < 0.5;
				if (goingRight) {
					mysteryShip = new Mystery(-60, 50, 60, 30);
					mysteryShip.setSpeed(5);
				}
				else {
					mysteryShip = new Mystery(getWidth(), 50, 60, 30);
					mysteryShip.setSpeed(-5);
				}
				mysteryShip.playSound();
				mysteryShipActive = true;
				mysteryShipSpawnCounter = 0;
			}

		}	
		
		if (mysteryShipActive && mysteryPulseCounter >= mysteryPulseLimit) {
		    mysteryShip.move();
		    if (mysteryShip.getX() < -60 || mysteryShip.getX() > getWidth()) {
		    	mysteryShipActive = false;
		    	//stop sound
		    }
		    mysteryPulseCounter = 0;
		}
		if (basePulseCounter >= basePulseLimit) {
			if (right && base.getX() < getWidth()-40) {
				base.setX(base.getX()+5);
			}
	        if (left && base.getX() > 20) {
	        	base.setX(base.getX()-5);
	        }
	        basePulseCounter = 0;
		}
		if (missilePulseCounter >= missilePulseLimit) {
	        List<Missile> toRemove = new ArrayList<>();
	        for (Missile m : missiles) {
	        	if (!m.isFromInvader()) {
	        		m.move();
	        		if (m.isOutOfBounds()) {
		        		toRemove.add(m);
		        	}
	        	}		        
	        }
	        
	        missiles.removeAll(toRemove);
	        missilePulseCounter = 0;
		}
		
		if (invaderMissilePulseCounter >= invaderMissilePulseLimit) {
	        List<Missile> toRemove = new ArrayList<>();
	        for (Missile m : missiles) {
	        	if (m.isFromInvader()) {
	        		m.move();
	        		if (m.isOutOfBounds()) {
		        		toRemove.add(m);
		        	}
	        	}	        	
	        }
	        
	        missiles.removeAll(toRemove);
	        invaderMissilePulseCounter = 0;
		}
		
		if (invaderPulseCounter >= invaderPulseLimit) {
	        boolean bounce = false;
	        for (Invader i : invaders) {
	        	i.setX(i.getX() + invaderX);
	        	
	        	if (i.getX() <= 0 || i.getX() + 30 >= getWidth()) {
	        		bounce = true;
	        	}
	        }
	        if (bounce) {
	        	invaderX = -invaderX;
	        	for (Invader i : invaders) {
	            	i.setY(i.getY() + invaderY);       
	            }
	        	invaderPulseLimit = Math.max(1,  (int)(invaderPulseLimit * 0.8));
	        }
	        invaderPulseCounter = 0;
	        for (Invader in : invaders) {
				in.swapImages();
			}
		}
		if (Math.random() < 0.01 && countInvaderMissiles() < 3) {
		    List<Invader> bottomInvaders = getBottomInvaders();
		    if (!bottomInvaders.isEmpty()) {
		        int randomIndex = (int) (Math.random() * bottomInvaders.size());
		        Invader shooter = bottomInvaders.get(randomIndex);
		        missiles.add(new Missile((int)(shooter.getX() + shooter.getW() / 2), shooter.getY() + shooter.getH(), true));
		    }
		}
        repaint(); 
	}
	/*
	 * 
	 */
	public void fireMissile() {
		missiles.add(new Missile(base.getX() + 10, base.getY(), false ));
	}
	/*
	 * 
	 */
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
        if (mysteryShipActive) {
        	mysteryShip.draw(g2);
        }
    }
	/*
	 * 
	 */
	@Override
	public void keyTyped(KeyEvent e) {	
	}
	/*
	 * 
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		var code = e.getKeyCode();
		if (code == KeyEvent.VK_LEFT) left = true;
		if (code == KeyEvent.VK_RIGHT) right = true;
		
		if (code == KeyEvent.VK_SPACE && countPlayerMissiles() < 1) {
			base.sound.setFramePosition(0);
			base.sound.start();

			fireMissile();
		}
	}
	/*
	 * 
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		var code = e.getKeyCode();
		if (code == KeyEvent.VK_SPACE) base.sound.stop();
		if (code == KeyEvent.VK_LEFT) left = false;
		if (code == KeyEvent.VK_RIGHT) right = false;
	}
	/*
	 * 
	 */
	public void pauseGame() {
	    timer.stop();
	}
	/*
	 * 
	 */
	public void resumeGame() {
	    timer.start();
	}
	private int countInvaderMissiles() {
	    int count = 0;
	    for (Missile m : missiles) {
	        if (m.isFromInvader()) count++;
	    }
	    return count;
	}
	private List<Invader> getBottomInvaders() {
		List<Invader> bottom = new ArrayList<>();
	    for (int col = 0; col <= getWidth(); col += 35) {
	    	 Invader lowest = null;
	         for (Invader inv : invaders) {
	        	 if (Math.abs(inv.getX() - col) <= 15) { 
	        		  if (lowest == null || inv.getY() > lowest.getY()) {
	                      lowest = inv;
	                  }
	              }
	          }
	          if (lowest != null) {
	              bottom.add(lowest);
	          }
	      }
	      return bottom;
	  }
	private int countPlayerMissiles() {
	    int count = 0;
	    for (Missile m : missiles) {
	        if (!m.isFromInvader()) {
	            count++;
	        }
	    }
	    return count;
	}
}
