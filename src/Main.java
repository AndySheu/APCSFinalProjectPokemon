
import javax.swing.*;
import java.util.*;
import java.awt.*;

public class Main {

    private JFrame frame; // the window
    private ImagePanel titleScreenPanel; // the image panel

    private ArrayList<Pokemon> pokeList = new ArrayList<Pokemon>(12);

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
	while (!titleScreenPeriodic());
	System.out.println("Title Screen Periodic Complete!");
	titleScreenTerm();
	System.out.println("Title Screen Termination Complete!");
    }

    public void gameInit() {
	PokemonStats.fill();
	frame = new JFrame("" + VarMap.title + " ver " + VarMap.version);
    }

    public void titleScreenInit() {
	// Dimension[width=1440,height=873] is full screen
	titleScreenPanel = new ImagePanel("./src/Images/logo.jpeg"); // background image
	Dimension d = titleScreenPanel.getSize(); // size of image panel
	yMax = d.getHeight();
	xMax = d.getWidth();
	
	frame.setSize(1440,873);
	frame.setVisible(true);
	frame.setBackground(Color.white);
	frame.add(titleScreenPanel);
	Timer.wait(100);
	frame.repaint();
    }

    public boolean titleScreenPeriodic() {
	Timer.wait(10000);
	return true;
    }

    public void titleScreenTerm() {
	frame.remove(titleScreenPanel);
	frame.repaint();
    }
}
