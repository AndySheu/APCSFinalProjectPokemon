
import java.util.ArrayList;
import java.util.Scanner;

public class PokeMove {

    private static boolean race = false;
    private static double xMax, yMax;
    private static ArrayList<Pokemon> pokemonList;
    private static ImagePanel titleScreenPanel;

    private static int places = 0;

    public static void makePokemonRace(double xM, double yM, ArrayList<Pokemon> alp, ImagePanel ip) {
	xMax = xM;
	yMax = yM;
	pokemonList = alp;
	titleScreenPanel = ip;
	race = true;

	int ducks = 9;
	for (int i = 0; i < ducks; i++) {
	    int x = 0;	//Choose a random position in-bounds
	    int y = (int) (yMax - ducks) / ducks * (i) - 5;
	    //Choose random x and y velocity between -1 and 1 pixels/step
	    double vx = Math.abs(Math.random() * 2 - 1);
	    //Make a new bouncy ball with this position and the red ball picture
	    int duck = (int) (Math.random() * 649) + 1;
	    Pokemon bouncy = new Pokemon("./src/Images/Pokemon/" + duck + ".png", x, y);
	    bouncy.setRaceVelocity(vx, 0);	//Set its velocity to the random one we just made up

	    pokemonList.add(bouncy);		//Add it to the array list of bouncy balls
	}
	for (Pokemon bouncy : pokemonList) {
	    constrainToBoundaries(bouncy);
	}
	titleScreenPanel.updateImages(pokemonList);
//	Timer.wait(2500);
	Scanner keys = new Scanner(System.in);
	keys.next();
	racePokemon();
    }

    public static void makePokemonBounceOne(double xM, double yM, ArrayList<Pokemon> alp, ImagePanel ip) {
	xMax = xM;
	yMax = yM;
	pokemonList = alp;
	titleScreenPanel = ip;

	for (int i = 1; i <= 649; i++) {
	    int x = (int) (Math.random() * xMax);	//Choose a random position in-bounds
	    int y = (int) (Math.random() * yMax);
	    //Choose random x and y velocity between -1 and 1 pixels/step
//	    double vx = Math.random() * 2 - 1;
//	    double vy = Math.random() * 2 - 1;
	    double vx=0;double vy=0;
	    //Make a new bouncy ball with this position and the red ball picture
	    Pokemon bouncy = new Pokemon("./src/Images/Pokemon/" + i + ".png", x, y);
	    bouncy.setVelocity(vx, vy);	//Set its velocity to the random one we just made up

	    pokemonList.add(bouncy);		//Add it to the array list of bouncy balls
	}
	titleScreenPanel.updateImages(pokemonList);
	bouncePokemon();
    }

    public static void makePokemonBounce(double xM, double yM, ArrayList<Pokemon> alp, ImagePanel ip) {
	xMax = xM;
	yMax = yM;
	pokemonList = alp;
	titleScreenPanel = ip;

	int ducks = (int) (Math.random() * 400) + 1;
	for (int i = 0; i < ducks; i++) {
	    int x = (int) (Math.random() * xMax);	//Choose a random position in-bounds
	    int y = (int) (Math.random() * yMax);
	    //Choose random x and y velocity between -1 and 1 pixels/step
	    double vx = Math.random() * 5 - 1;
	    double vy = Math.random() * 5 - 1;
	    //Make a new bouncy ball with this position and the red ball picture
	    int duck = (int) (Math.random() * 649) + 1;
	    Pokemon bouncy = new Pokemon("./src/Images/Pokemon/" + duck + ".png", x, y);
	    bouncy.setVelocity(vx, vy);	//Set its velocity to the random one we just made up

	    pokemonList.add(bouncy);		//Add it to the array list of bouncy balls
	}
	titleScreenPanel.updateImages(pokemonList);
	bouncePokemon();
    }

    public static void racePokemon() {
	int inte = -1;
	boolean treu = true;

	long lastTime = System.currentTimeMillis();
	//Holds the last time we gave the bouncy balls a turn
	while (treu) {
	    long time = System.currentTimeMillis();	//The current time
	    if (time - 10 > lastTime) //Update every .01 second
	    {
		lastTime = time;
		//Reset the last time we gave the bouncy balls a turn to be now
		//Go through the array list and move each bouncy ball
		for (Pokemon bouncy : pokemonList) {
		    bouncy.setRaceVelocity(bouncy.getVX() + (0.05 * (Math.random() - 0.2)), 0);
		    if (bouncy.laps % 2 == 0) {
			bouncy.incrementPosition();
		    } else {
			bouncy.decrementPosition();
		    }
		    constrainToBoundaries(bouncy);	//Bounce off the walls if appropriate
		    if (bouncy.laps >= 4) {
			places++;
			if (places < 4) {
			    Timer.wait(500);
			    System.out.println("Place Number " + places + " : " + bouncy.getSpecies());
			} else if (places > 10) {
			    treu = false;
			}
			inte = pokemonList.indexOf(bouncy);
		    }
		}
		if (inte != -1) {
		    pokemonList.remove(inte);
		}
		inte = -1;
		titleScreenPanel.updateImages(pokemonList);
		//Repaint all the bouncy balls in their new positions

	    }

	}
    }

    public static void bouncePokemon() {
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
    public static void constrainToBoundaries(Pokemon bouncy) {
	//The size of the bouncy ball picture.  The position of a bouncy ball is the pixel 
	//at the top left corner of its picture (it's like the cursor) and we don't want 
	//it to go behind the right and bottom walls.
	int width = bouncy.getImage().getWidth(null);
	int height = bouncy.getImage().getHeight(null);

	//The right side of the ball is to the right of the edge of the panel
	if (bouncy.getX() + width > xMax) {
	    if (bouncy.laps % 2 == 0 && race) {
		bouncy.laps++;
		bouncy.setRaceVelocity(0.2, 0);
	    }
	    bouncy.setPosition(xMax - width, bouncy.getY());
	    //Put it just to the left of the edge of the panel
	    bouncy.reflect(true);	//Reflect off the wall (velocity-wise).  
	    //The "true" tells the bouncy that it's bouncing off a side wall.
	} //The left side of the ball is to the left of the edge of the panel
	else if (bouncy.getX() < 0) {
	    if (bouncy.laps % 2 == 1 && race) {
		bouncy.laps++;
		bouncy.setRaceVelocity(0.2, 0);
	    }
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
