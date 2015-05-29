
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

/*
 TODO:
 Thread Animations -- NAH
 MouseListener -- DONE
 Attack GUI
 Status Effects -- NAH
 */

public class Main {

    public static void main(String[] args) {
	new Main();
    }

    public Main() {
	Init.initUI();
	Init.titleScreen();

	if (!V.TESTING) {
	    Init.playerInit();
	} else {
	    Init.playerAutoInit();
	}

	Init.startBattle();
    }
}
