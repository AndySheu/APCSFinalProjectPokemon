
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
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

    // TODO -- double if scanner vs keyinput (var ctrld)
    static void battlePress(KeyEvent ke) {
	try {
	    if (ke.getKeyCode() > KeyEvent.VK_0 && ke.getKeyCode() <= KeyEvent.VK_9) {
		V.input = ke.getKeyCode();
		try {
		    Robot robot = new Robot();

		    robot.mousePress(InputEvent.BUTTON1_MASK);
		    robot.mouseRelease(InputEvent.BUTTON1_MASK);

		    robot.keyPress(KeyEvent.VK_A);
		    robot.keyRelease(KeyEvent.VK_A);

		} catch (AWTException e) {
		    e.printStackTrace();
		}
	    }
	} catch (NullPointerException ex) {

	}
    }

    static void keyPressed(KeyEvent e) {
	if (V.state >= Battle.TYPE && V.state <= Battle.RUN) {
	    battlePress(e);
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
		case KeyEvent.VK_0:
		    V.music.nextSong();
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
		case KeyEvent.VK_EQUALS:
		    V.music.start();
		    break;
		case KeyEvent.VK_MINUS:
		    V.music.stop();
		    break;
		case KeyEvent.VK_0:
		    // DO NOTHING, IDIOT!
		    break;
		case KeyEvent.VK_ENTER:
		    V.enter = true;
		    break;
	    }
	    V.frame.repaint();
	} catch (NullPointerException ex) {
	}
    }

    private class Adapter extends KeyAdapter {

	public void keyReleased(KeyEvent e) {
	    Listener.keyReleased(e);
	}

	public void keyPressed(KeyEvent e) {
	    Listener.keyPressed(e);
	}
    }

}
