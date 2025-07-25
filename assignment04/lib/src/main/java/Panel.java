import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Timer;
import javax.swing.JPanel;
/**
 * Represents the game panel for the Space Invaders game. Handles game logic, drawing 
 * of game objects, player input, and updating game states such as score, missile movements, 
 * invader movement, and collision detection.
 */
@SuppressWarnings("serial")
public class Panel extends JPanel implements KeyListener{
	/**
	 *  Timer to control the game loop updates 
	 */
	private Timer timer;
	/**
	 *  Whether the base is moving right 
	 */
	private boolean right = false;
	/**
	 *  Whether the base is moving left 
	 */
	private boolean left = false;
	/**
	 *  The player's base object 
	 */
	private Base base;
	/**
	 *  List of all active invaders 
	 */
	private List<Invader> invaders;
	/**
	 *  List of all active missiles 
	 */
	private List<Missile> missiles;
	/**
	 *  Horizontal movement amount for invaders 
	 */
	private int invaderX = 2;
	/**
	 *  Vertical movement amount for invaders when they bounce 
	 */
	private int invaderY = 12;
	/**
	 *  Counter for base movement pulses 
	 */
	private int basePulseCounter;
	/**
	 *  Counter for missile movement pulses 
	 */
	private int missilePulseCounter;
	/**
	 *  Counter for invader movement pulses 
	 */
	private int invaderPulseCounter;
	/**
	 *  Counter for mystery ship movement pulses 
	 */
	private int mysteryPulseCounter;
	/**
	 *  Counter for invader missile movement pulses 
	 */
	private int invaderMissilePulseCounter;
	/**
	 *  Limit to control base movement frequency 
	 */
	private int basePulseLimit = 1;
	/**
	 *  Limit to control missile movement frequency 
	 */
	private int missilePulseLimit = 1;
	/**
	 *  Limit to control invader movement frequency 
	 */
	private int invaderPulseLimit = 40;
	/**
	 *  Limit to control mystery ship movement frequency 
	 */
	private int mysteryPulseLimit = 2;
	/**
	 *  Limit to control mystery ship movement frequency
	 */
	private int invaderMissilePulseLimit = 2;
	/**
	 *  The mystery ship object 
	 */
	private Mystery mysteryShip;
	/**
	 *  Whether the mystery ship is currently active 
	 */
	private boolean mysteryShipActive = false;
	/**
	 *  Current player's score
	 */
    private int score = 0;
    /**
     *  Counter to track mystery ship display time after being hit 
     */
    private int mysteryCount=0;
    /**
     *  Font for large text (Game Over) 
     */
    private Font font = new Font("Arial", Font.BOLD, 40 );
    /** 
     * Font for smaller text (Final Score)
     */
    private Font font2 = new Font("Arial", Font.BOLD, 20 );
    
    /**
     * Constructs the game panel, initializes game objects, and sets up the game timer.
     */
	public Panel() {
		setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(this);
        base = new Base(250,380,100,100);
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
		invaderMissilePulseCounter++;
		
		if (!mysteryShipActive) {
			double random = Math.random();
			if (random > 0.998) {
				boolean goingRight = Math.random() < 0.5;
				if (goingRight) {
					mysteryShip = new Mystery(-60, 50, 60, 30);
					mysteryShip.setSpeed(5);
				}
				else {
					mysteryShip = new Mystery(500, 50, 60, 30);
					mysteryShip.setSpeed(-5);
				}
				mysteryShip.getMystSound().start();
				mysteryShipActive = true;
			}
		}	
		
		if (mysteryShipActive && mysteryPulseCounter >= mysteryPulseLimit) {
		    mysteryShip.setX(mysteryShip.getX()+mysteryShip.getSpeed());
		    if (mysteryShip.getX() < -60 || mysteryShip.getX() > getWidth()) {
		    	mysteryShipActive = false;
		    	mysteryShip.getMystSound().stop();
		    }
		    mysteryPulseCounter = 0;
		}
		if (basePulseCounter >= basePulseLimit) {
			if (right && base.getX() < getWidth()-35) {
				base.setX(base.getX()+5);
			}
	        if (left && base.getX() > 10) {
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
	        	if (m.getHitOne()) {
        			toRemove.add(m);
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
	        	if (i.getY() >= 380) {
	        		base.setHit();
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
		if (missiles.size() > 0 && invaders.size() > 0) {
		for (int mis = missiles.size()-1; mis >=0; mis--) {
			Missile m = missiles.get(mis);
			if (m.getHitOne()) continue;
			for (int invad = invaders.size()-1; invad >=0; invad--) {
				Invader i = invaders.get(invad);
				if (!m.isFromInvader() &&
						m.getX() - i.getX() < i.getW() &&
						m.getX() - i.getX() > 0 &&
						m.getY()- i.getY() < i.getH() && 
						m.getY()- i.getY() > 0) {
					score += i.getPoints();
					i.setHit();
					m.setHitOne();
					break;
				}
			}
			if (m.isFromInvader() && 
					m.getX() - base.getX() < 30 && 
					m.getX() - base.getX() > 0 &&
					m.getY() - base.getY() < 20 && 
					m.getY() - base.getY() > 0){
				base.setHit();
				m.setHitOne();
			}
			if (mysteryShipActive && 
					m.getX() - mysteryShip.getX() < 60 && 
					m.getX() - mysteryShip.getX() > 0 &&
					m.getY() - mysteryShip.getY() < 60 &&
					m.getY() - mysteryShip.getY() > 0) {
				score += mysteryShip.getPoints();
				mysteryShip.setHit();
				m.setHitOne();
				break;
			}
		}
		}
        repaint(); 
	}
	/**
     * Fires a missile from the player's base.
     */
	public void fireMissile() {
		missiles.add(new Missile(base.getX() + 10, base.getY(), false ));
	}
	/**
     * Paints the game components (base, invaders, missiles) to the screen.
     * 
     * @param g Graphics object used for drawing
     */
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        if (invaders.isEmpty() && !mysteryShipActive && missiles.isEmpty()) {
        	if (invaderPulseLimit <= 10) {
        		this.pauseGame();
        		g2.setFont(font);
        		g2.setColor(Color.YELLOW);
        		String victory = "VICTORY!!!";
        		FontMetrics fm = g2.getFontMetrics();
        		g2.drawString(victory, getWidth()/2-fm.stringWidth(victory)/2, getHeight()/2);
        		g2.setFont(font2);
            	g2.drawString("Final Score: " + score, getWidth()/2-fm.stringWidth("Final Score: " + score)/2 + 80,
            			getHeight()/2 + 40);
            	return;  
        	}
        	else createWave(score);
        	return;
        }
        if (base.isItHit()) {
        	base.drawDestroyed(g2);
        	this.pauseGame();
        	g2.setFont(font);
        	g2.setColor(Color.GREEN);
        	String gameOver = "GAME OVER";
        	FontMetrics fm = g2.getFontMetrics();
        	g2.drawString(gameOver, getWidth()/2-fm.stringWidth(gameOver)/2, getHeight()/2);
        	g2.setFont(font2);
        	g2.drawString("Final Score: " + score, getWidth()/2-fm.stringWidth("Final Score: " + score)/2 + 80,
        			getHeight()/2 + 40);
        	return;
        }
        else base.draw(g2);
        for (Invader inv : invaders) {
        	if (inv.isItHit()) {
        		inv.drawDestroyed(g2);
        		inv.incTimeFromHit();
        		if (inv.incTimeFromHit() >= 50) {
        			invaders.remove(inv);
        			break;
        		}
        	}
        	else inv.draw(g2);
        }
        for (Missile m : missiles) {
        	m.draw(g2);
        }
        if (mysteryShipActive) {
        	if (mysteryShip.isItHit()) {
        		mysteryShip.drawDestroyed(g2);
        		mysteryCount++;
        		mysteryShip.setSpeed(0);
        		
        		if (mysteryCount>=50) {
            		mysteryShipActive=false;
            		mysteryCount=0;
            	}
        	}
        	else mysteryShip.draw(g2);
        }
        g2.drawString("Score: " + score, 400,20);
    }
	
	/**
     * Handles key typed events, not needed for Panel
     * 
     * @param e The key event
     */
	@Override
	public void keyTyped(KeyEvent e) {	
	}
	/**
     * Handles key press events to control the base movement and firing missiles.
     * 
     * @param e The key event
     */
	@Override
	public void keyPressed(KeyEvent e) {
		var code = e.getKeyCode();
		if (code == KeyEvent.VK_LEFT) left = true;
		if (code == KeyEvent.VK_RIGHT) right = true;
		
		if (code == KeyEvent.VK_SPACE && countPlayerMissiles() < 1) {
			base.getSound().setFramePosition(0);
			base.getSound().start();

			fireMissile();
		}
	}
	/**
     * Handles key release events to stop base movement or missile firing.
     * 
     * @param e The key event
     */
	@Override
	public void keyReleased(KeyEvent e) {
		var code = e.getKeyCode();
		if (code == KeyEvent.VK_SPACE) base.getSound().stop();
		if (code == KeyEvent.VK_LEFT) left = false;
		if (code == KeyEvent.VK_RIGHT) right = false;
	}
	/**
     * Pauses the game by stopping the timer.
     */
	public void pauseGame() {
	    timer.stop();
	}
	/**
     * Resumes the game by starting the timer.
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
	private void createWave(int scoreVal) {
		invaderX = 2;
		invaderY = 12;
		
		mysteryShipActive = false;
	    score = scoreVal;
	    mysteryCount=0;
	    
	    base = new Base(250,380,100,100);
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
	    if (invaderPulseLimit>14) {
	    	invaderPulseLimit -= 5;
	    }
	    timer.start();	
	}
}
