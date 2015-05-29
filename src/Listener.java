
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Listener implements ActionListener {

    private String action;

    public Listener() {
	V.frame.addKeyListener(new KAdapter());
	V.frame.addMouseListener(new MAdapter());
	V.frame.setFocusable(true);
    }

    public final void actionPerformed(ActionEvent e) {
	switch (action) {
	    case "start":
		System.out.println("START");
		V.music.start();
		break;
	    case "stop":
		System.out.println("STOP");
		V.music.stop();
		break;
	}
    }

    static void keyPressed(KeyEvent e) {
	if (V.state >= Battle.TYPE && V.state <= Battle.RUN) {
	    return;
	}
	Sprite whatToMove = V.player;
	try {
	    whatToMove = V.player;
	} catch (NullPointerException ex) {

	}
	int moveDistance = 3;

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
	Sprite whatToMove = V.player;
	try {
	    whatToMove = V.player;
	} catch (NullPointerException ex) {

	}
	int moveDistance = 3;

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
		case KeyEvent.VK_ENTER:
		    V.enter = true;
		    break;
	    }
	    V.frame.repaint();
	} catch (NullPointerException ex) {
	}
    }
    
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

    private class KAdapter extends KeyAdapter {

	public void keyReleased(KeyEvent e) {
	    Listener.keyReleased(e);
	}

	public void keyPressed(KeyEvent e) {
	    Listener.keyPressed(e);
	}
    }

    private class MAdapter extends MouseAdapter {

	public void mouseClicked(MouseEvent e) {
	    Listener.mouseClicked(e);
	}
    }
}
