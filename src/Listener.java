
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Listener implements ActionListener {

    private String action;

    // Pre: None
    // Post: Creates a Listener object
    public Listener() {
	// Adds both listeners to the frame
	V.frame.addKeyListener(new KAdapter());
	V.frame.addMouseListener(new MAdapter());
	V.frame.setFocusable(true);
    }

    // @override - Does nothing
    public final void actionPerformed(ActionEvent e) {
//	switch (action) {
//	    case "start":
//		System.out.println("START");
//		V.music.start();
//		break;
//	    case "stop":
//		System.out.println("STOP");
//		V.music.stop();
//		break;
//	}
    }

    // Pre: KeyEvent e
    // Post: Receives keyboard input from the user
    static void keyPressed(KeyEvent e) {
	if (V.state >= Battle.TYPE && V.state <= Battle.RUN) {
	    return;
	}
	
	Sprite whatToMove;
	try {
	    whatToMove = new Sprite("./src/Images/Transparent.png", false, 0, 0);
	} catch (NullPointerException ex) {
	    whatToMove = new Sprite("./src/Images/Transparent.png", false, 0, 0);
	}
	int moveDistance = 1;

	try {
	    switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT: // 37
		    whatToMove.setLoc(whatToMove.getX() - moveDistance, whatToMove.getY());
		    break;
		case KeyEvent.VK_RIGHT: // 39
		    whatToMove.setLoc(whatToMove.getX() + moveDistance, whatToMove.getY());
		    break;
		case KeyEvent.VK_UP: // 38
		    whatToMove.setLoc(whatToMove.getX(), whatToMove.getY() - moveDistance);
		    break;
		case KeyEvent.VK_DOWN: // 40
		    whatToMove.setLoc(whatToMove.getX(), whatToMove.getY() + moveDistance);
		    break;
		case KeyEvent.VK_EQUALS:
		    V.music.start();
		    break;
		case KeyEvent.VK_MINUS:
		    V.music.stop();
		    break;
		case KeyEvent.VK_ENTER:
		    V.enter = true;
		    break;
	    }
	    V.frame.repaint();
	} catch (NullPointerException ex) {
	}
    }

    static void keyReleased(KeyEvent e) {
//	Sprite whatToMove = V.player;
//	try {
//	    whatToMove = V.player;
//	} catch (NullPointerException ex) {
//
//	}
//	int moveDistance = 3;
//
//	try {
//	    // Directional release causes a bit of movement, too
//	    // This creates a more realistic movement
//	    switch (e.getKeyCode()) {
//		case KeyEvent.VK_LEFT: // 37
//		    whatToMove.setLoc(whatToMove.getX() - moveDistance, whatToMove.getY());
//		    break;
//		case KeyEvent.VK_RIGHT: // 39
//		    whatToMove.setLoc(whatToMove.getX() + moveDistance, whatToMove.getY());
//		    break;
//		case KeyEvent.VK_UP: // 38
//		    whatToMove.setLoc(whatToMove.getX(), whatToMove.getY() - moveDistance);
//		    break;
//		case KeyEvent.VK_DOWN: // 40
//		    whatToMove.setLoc(whatToMove.getX(), whatToMove.getY() + moveDistance);
//		    break;
//		case KeyEvent.VK_ENTER:
//		    V.enter = true;
//		    break;
//	    }
//	    V.frame.repaint();
//	} catch (NullPointerException ex) {
//	}
    }
    
    // Pre: MouseEvent e
    // Post: Receives mouse input from the user
    // These are just buttons
    static void mouseClicked(MouseEvent e) {
//	System.out.println(e.getX() + "," + e.getY());
	if (V.state == Battle.TYPE) {
	    if (e.getX() >= 103 && e.getX() <= 436 && e.getY() >= 534 && e.getY() <= 663) {
		V.state = Battle.ATTACK;
	    } else if (e.getX() >= 0 && e.getX() <= 162 && e.getY() >= 704 && e.getY() <= 851) {
		V.state = Battle.ITEM;
	    } else if (e.getX() >= 187 && e.getX() <= 350 && e.getY() >= 744 && e.getY() <= 851) {
		V.state = Battle.RUN;
	    } else if (e.getX() >= 375 && e.getX() <= 538 && e.getY() >= 704 && e.getY() <= 851) {
		V.state = Battle.POKEMON;
	    }
	} else if (V.state == Battle.ATTACK) {
	    if (e.getX() >= 0 && e.getX() < 269 && e.getY() >= 512 && e.getY() < 614) {
		Battle.attackChoice = 0;
	    } else if (e.getX() > 269 && e.getX() <= 538 && e.getY() >= 512 && e.getY() < 614) {
		Battle.attackChoice = 1;
	    } else if (e.getX() >= 0 && e.getX() < 269 && e.getY() > 614 && e.getY() <= 716) {
		Battle.attackChoice = 2;
	    } else if (e.getX() > 269 && e.getX() <= 538 && e.getY() >= 614 && e.getY() <= 716) {
		Battle.attackChoice = 3;
	    } else if (e.getX() >= 374 && e.getX() <= 538 && e.getY() >= 722 && e.getY() <= 851) {
		Battle.attackChoice = Integer.MAX_VALUE;
	    }
	    if (Battle.attackChoice >= V.player.getCurrent().getNumMoves() && Battle.attackChoice != Integer.MAX_VALUE) {
		Battle.attackChoice = -1;
	    }
	} else {
	    V.click = true;
	}
    }

    // These two classes call the proper methods, allowing us to keep just one main class
    private class KAdapter extends KeyAdapter {
	// Pre: KeyEvent e
	// Post: Calls keyPressed(e) in class Listener
	public void keyPressed(KeyEvent e) {
	    Listener.keyPressed(e);
	}
	// Pre: KeyEvent e
	// Post: Calls keyReleased(e) in class Listener
	public void keyReleased(KeyEvent e) {
	    Listener.keyReleased(e);
	}
    }

    private class MAdapter extends MouseAdapter {
        // Pre: MouseEvent e
        // Post: Calls mouseClicked(e) in class Listener
	public void mouseClicked(MouseEvent e) {
	    Listener.mouseClicked(e);
	}
    }
}
