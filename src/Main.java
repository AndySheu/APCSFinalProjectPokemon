
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

	V.frame = new JFrame("Pokémon Diamond III " + V.VERSION + " | Coded by Andy Sheu and Dhruv Jhamb");
	V.frame.setSize(V.MAX_WIDTH, V.MAX_HEIGHT);
	V.frame.add(V.panel);
	V.frame.setVisible(true);
	V.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void gameInit() {
	System.out.print("What is your name? ");
	V.player = new Player(V.keys.next(), null, true);
	System.out.print("What is your rival\'s name? ");
	V.opp = new Player(V.keys.next(), null, false);

	V.player.fillTeam(3);
	V.opp.fillTeam(3);

	System.out.print(V.player.getName() + " has a(n): ");
	for (Pokemon p : V.playerPokeParty) {
	    try {
		System.out.print(p.getName() + " ");
	    } catch (NullPointerException e) {
		
	    }
	}
	System.out.println();
	System.out.print(V.opp.getName() + " has a(n): ");
	for (Pokemon p : V.oppPokeParty) {
	    try {
		System.out.print(p.getName() + " ");
	    } catch (NullPointerException e) {
		
	    }
	}
	System.out.println();

	V.frame.repaint();
    }

    public void startBattle() {
//	new Battle(p, o, titleScreenPanel, frame).run();
    }

}
