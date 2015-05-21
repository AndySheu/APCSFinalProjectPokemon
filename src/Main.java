
import javax.swing.*;
import java.util.*;
import java.awt.*;
public class Main {

    public static void main(String[] args) {
	new Main();
    }

    public Main() {
	init();
	gameInit();
	startBattle();
    }

    public void init() {
	D.fill();
	V.panel = new ImagePanel("./src/Images/Transparent.png");

	V.frame = new JFrame("Pokémon Diamond III " + V.version + " | Coded by Andy Sheu and Dhruv Jhamb");
	V.frame.setSize(V.MAX_WIDTH, V.MAX_HEIGHT);
	V.frame.add(V.panel);
	V.frame.setVisible(true);
	V.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public void gameInit() {
	System.out.print("What is your name? ");
	Player p = new Player(V.keys.next(), null, true);
	System.out.print("What is your rival\'s name? ");
	Player o = new Player(V.keys.next(), null, false);
	
	V.frame.repaint();
    }

    public void startBattle() {
//	new Battle(p, o, titleScreenPanel, frame).run();
    }

}
