
import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.KeyListener;

public class Main {

    private JFrame frame; // the window
    private ImagePanel imagePanel; // the image panel

    private double xMax, yMax; // height and width of panel
    // (0,0) is top left, (xMax, yMax) is bottom right
    // number of pixels is xMax+1 by yMax+1

    public static void main(String[] args) {
	Main m = new Main();
    }

    public Main() {
	gameInit();
    }

    public void gameInit() {
	// Dimension[width=1440,height=873] is full screen
	imagePanel = new ImagePanel("./Images/logo.jpeg"); // background image
	Dimension d = imagePanel.getSize(); // size of image panel
	yMax = d.getHeight();
	xMax = d.getWidth();

	frame = new JFrame("" + VarMap.title + " ver " + VarMap.version);
//	frame.setSize((int) (d.getWidth()), (int) (d.getHeight() + 25));
	frame.setSize(1440, 873); // Full Screen
	// 25 is title height

	frame.setBackground(Color.white);
	frame.add(imagePanel);

	frame.setVisible(true);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void titleScreenInit() {

    }

    public void titleScreenPeriodic() {

    }

    // @return winner
    public Player battle(Player user, Player opp) {
	while (!user.checkLoss() && !opp.checkLoss()) {

	}
	if (user.checkLoss()) // Tie goes to opponent
	{
	    return opp;
	} else {
	    return user;
	}
    }
}
