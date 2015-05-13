
import javax.swing.*;
import java.util.*;
import java.awt.*;

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
	imagePanel = new ImagePanel("Images\\logo.png"); // background image
	Dimension d = imagePanel.getSize(); // size of image panel
	yMax = d.getHeight();
	xMax = d.getWidth();

	frame = new JFrame("" + VarMap.title + " ver " + VarMap.version);
	frame.setSize((int) (d.getWidth()), (int) (d.getHeight() + 25));
	// 25 is title height
	
	System.out.println(d.getWidth() + " " + d.getHeight());

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
	while(!user.checkLoss() && !opp.checkLoss()) {
	    
	}
	if(user.checkLoss()) // Tie goes to opponent
	    return opp;
	else
	    return user;
    }
}
