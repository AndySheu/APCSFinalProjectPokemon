
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

/*
 TODO:
 Thread Animations -- NAH
 MouseListener -- DONE
 Attack GUI -- POSSIBLY
 Status Effects -- NAH
Commenting
 */

public class Main {

    public static void main(String[] args) {
	new Main();
    }

    // Pre: None
    // Post: Constructs a Main object and calls methods from the class Init
    public Main() {
	V.playerHealth.setLoc(310, 270);
	V.oppHealth.setLoc(0, 120);
	Init.initUI();
	Init.titleScreen();

	// Testing mode allows for skipping the initial sequence
	if (!V.TESTING) {
	    Init.playerInit();
	} else {
	    Init.playerAutoInit();
	}

	Init.startBattle();
    }
}
