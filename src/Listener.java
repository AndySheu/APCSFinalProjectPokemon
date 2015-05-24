
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Listener implements ActionListener {

    private String action;

    public Listener(String action) {
	this.action = action;
    }

    public Listener() {
	V.frame.addKeyListener(new Adapter());
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
	Sprite whatToMove = V.player;
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
	    }
	    V.frame.repaint();
	} catch (NullPointerException ex) {
	}
    }

    static void keyReleased(KeyEvent e) {
	Sprite whatToMove = V.player;
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
	    }
	    V.frame.repaint();
	} catch (NullPointerException ex) {
	}
    }

    // TODO Remove? Probably unnecessary
//    public void actionPerformed(ActionEvent e) {
//	V.panel.repaint();
//    }

    private class Adapter extends KeyAdapter {

	public void keyReleased(KeyEvent e) {
	    Listener.keyReleased(e);
	}

	public void keyPressed(KeyEvent e) {
	    Listener.keyPressed(e);
	}
    }

}
