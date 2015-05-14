
import javax.swing.*;
import java.util.*;
import java.awt.*;

public class Main {

    private JFrame frame; // the window
    private ImagePanel titleScreenPanel; // the image panel

    private ArrayList<Pokemon> pokemonList = new ArrayList<Pokemon>(12);

    private double xMax, yMax; // height and width of panel
    // (0,0) is top left, (xMax, yMax) is bottom right
    // number of pixels is xMax+1 by yMax+1

    public static void main(String[] args) {
	Main m = new Main();
    }

    public Main() {
//	gameInit();
//	System.out.println("Game Initiation Complete!");
//	titleScreenInit();
//	System.out.println("Title Screen Initiation Complete!");
//	while (!titleScreenPeriodic());
//	System.out.println("Title Screen Periodic Complete!");
//	titleScreenTerm();
//	System.out.println("Title Screen Termination Complete!");
		init();
		makePokemonBounce();

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

	frame.setSize(1440, 873);
	frame.setVisible(true);
	frame.setBackground(Color.white);
	frame.add(titleScreenPanel);
	frame.repaint();
    }

    public boolean titleScreenPeriodic() {
	Timer.wait(100);
	return true;
    }

    public void titleScreenTerm() {
	frame.remove(titleScreenPanel);
	frame.repaint();
    }
    
    	public void init()
	{
		titleScreenPanel = new ImagePanel("./src/Images/logo.jpeg");	
		
		Dimension d = titleScreenPanel.getSize();	
		//The size of the ImagePanel (happens to be the same as that of the background picture)
		//Set our stored height and width values to those of the ImagePanel
		yMax = d.getHeight();		
		xMax = d.getWidth();
		
		frame = new JFrame("Bouncy balls!"); 	//The window (with a name)
		//Set the frame to be the right size to hold the ImagePanel
		frame.setSize((int)d.getWidth(), (int)d.getHeight() + 25);	
		//25 is the height of the top bar that says Bouncy balls! (yes that counts in the size)
		frame.add(titleScreenPanel);		//Add the ImagePanel to the frame
		frame.setVisible(true);		
		//Make the frame visible (I don't like that they're invisible by default; it's silly)
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//end the program when you hit X
	}

    public void makePokemonBounce() {
	int ducks = (int)(Math.random() * 1000) + 1;
	System.out.println(ducks);
	for (int i = 0; i < ducks ; i++) {
	    int x = (int) (Math.random() * xMax);	//Choose a random position in-bounds
	    int y = (int) (Math.random() * yMax);
	    //Choose random x and y velocity between -1 and 1 pixels/step
	    double vx = Math.random() * 2 - 1;
	    double vy = Math.random() * 2 - 1;
	    //Make a new bouncy ball with this position and the red ball picture
	    int duck = (int)(Math.random() * 649) + 1;
	    Pokemon bouncy = new Pokemon("./src/Images/Pokemon/" + duck + ".png", x, y);
	    bouncy.setVelocity(vx, vy);	//Set its velocity to the random one we just made up

	    pokemonList.add(bouncy);		//Add it to the array list of bouncy balls
	}
	bouncePokemon();
    }

    public void bouncePokemon() {
	long lastTime = System.currentTimeMillis();
	//Holds the last time we gave the bouncy balls a turn
	while (true) {
	    long time = System.currentTimeMillis();	//The current time
	    if (time - 10 > lastTime) //Update every .01 second
	    {
		lastTime = time;
				//Reset the last time we gave the bouncy balls a turn to be now
		//Go through the array list and move each bouncy ball
		for (Pokemon bouncy : pokemonList) {
		    bouncy.incrementPosition();		//Augment position by velocity
		    constrainToBoundaries(bouncy);	//Bounce off the walls if appropriate
		}
		titleScreenPanel.updateImages(pokemonList);
		//Repaint all the bouncy balls in their new positions
	    }

	}
    }

    //Causes a bouncy ball to bounce off the walls, if it needs to
    public void constrainToBoundaries(Pokemon bouncy) {
		//The size of the bouncy ball picture.  The position of a bouncy ball is the pixel 
	//at the top left corner of its picture (it's like the cursor) and we don't want 
	//it to go behind the right and bottom walls.
	int width = bouncy.getImage().getWidth(null);
	int height = bouncy.getImage().getHeight(null);

	//The right side of the ball is to the right of the edge of the panel
	if (bouncy.getX() + width > xMax) {
	    bouncy.setPosition(xMax - width, bouncy.getY());
	    //Put it just to the left of the edge of the panel
	    bouncy.reflect(true);	//Reflect off the wall (velocity-wise).  
	    //The "true" tells the bouncy that it's bouncing off a side wall.
	} //The left side of the ball is to the left of the edge of the panel
	else if (bouncy.getX() < 0) {
	    bouncy.setPosition(0, bouncy.getY());
	    bouncy.reflect(true);
	}
	//The bottom of the ball is below the edge of the panel
	if (bouncy.getY() + height > yMax) {
	    bouncy.setPosition(bouncy.getX(), yMax - height);
	    bouncy.reflect(false);
	} //The top of the ball is above the edge of the panel
	else if (bouncy.getY() < 0) {
	    bouncy.setPosition(bouncy.getX(), 0);
	    bouncy.reflect(false);
	}
    }
}
