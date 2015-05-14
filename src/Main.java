
import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.KeyListener;

public class Main {

    private JFrame frame; // the window
    private ImagePanel titleScreenPanel; // the image panel

    private double xMax, yMax; // height and width of panel
    // (0,0) is top left, (xMax, yMax) is bottom right
    // number of pixels is xMax+1 by yMax+1

    public static void main(String[] args) {
	Main m = new Main();
    }

    public Main() {
	gameInit();
	System.out.println("Game Initiation Complete!");
	titleScreenInit();
	System.out.println("Title Screen Initiation Complete!");
	while(!titleScreenPeriodic());
	System.out.println("Title Screen Periodic Complete!");
	titleScreenTerm();
	System.out.println("Title Screen Termination Complete!");
	pokeSpin();
	titleScreenInit();
	System.out.println("Title Screen Initiation Complete!");
    }

    public void panelUpdate() {
	frame.setSize(1440, 873); // Full Screen
	frame.setVisible(true);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void gameInit() {
	PokemonStats.fill();
	frame = new JFrame("" + VarMap.title + " ver " + VarMap.version);
    }

    public void titleScreenInit() {
	// Dimension[width=1440,height=873] is full screen
	titleScreenPanel = new ImagePanel("./Images/logo.jpeg"); // background image
	Dimension d = titleScreenPanel.getSize(); // size of image panel
	yMax = d.getHeight();
	xMax = d.getWidth();

	frame.setBackground(Color.white);
	frame.add(titleScreenPanel);
	panelUpdate();
    }

    public boolean titleScreenPeriodic() {
	Timer.wait(500);
	return true;
    }

    public void titleScreenTerm() {
	frame.remove(titleScreenPanel);
	panelUpdate();
    }
    
    public void pokeSpin() {
	for(int i = 1; i <= 151; i++) {
	    
	    ImagePanel poke = new ImagePanel("./Images/Pokemon/" + i + ".png");
	    poke.setLocation(1440/2,873/2);
	    frame.add(poke);
	    poke.setLocation(1440/2,873/2);
	    panelUpdate();
	    
	    Timer.wait(100);
	    
	    frame.remove(poke);
	}
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
