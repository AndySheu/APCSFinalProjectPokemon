
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

	JFrame passwordHint = new JFrame("PASSWORD HINT (4): Mike's full initials | Player.setName(\"MB\"); && pass.equals(/*MB's favorite word*/);");
	passwordHint.setSize(0, 0);
	passwordHint.setVisible(true);
	passwordHint.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	V.frame = new JFrame("Pokémon Diamond III " + V.VERSION + " | Coded by Andy Sheu and Dhruv Jhambf");
	V.frame.setSize(V.MAX_WIDTH, V.MAX_HEIGHT);
	V.frame.add(V.panel);
//	V.frame.setVisible(true);
	V.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void gameInit() {
	System.out.print("What is your name? ");
	V.player = new Player(V.keys.nextLine(), null, true);
	System.out.print("What is your opponent\'s name? ");
	V.opp = new Player(V.keys.nextLine(), null, false);

	boolean wrong = false;
	while (!wrong) {
	    System.out.print("Enter a password (if you have one): ");
	    String pass = V.keys.nextLine();

	    if (pass.equals("france")) {
		for (int i = 0; i < 6; i++) {
		    V.player.addPokemon(new Pokemon(P.VICTINI, 0, 0));
		}
	    } else if (pass.equals("england")) {
		V.player.addPokemon(new Pokemon(P.CHARMANDER, 0, 0));
		V.player.addPokemon(new Pokemon(P.ARCEUS, 0, 0));
		V.player.fillTeam(4);
	    } else if (pass.equals("excellent") && V.player.getName().equals("Mike Bollhorst")) {
		for (int i = 0; i < 6; i++) {
		    V.player.addPokemon(new Pokemon(P.ARCEUS, 0, 0));
		}
	    } else if (pass.equals("meb")) {
		V.opp.setName("Michael E. Bollhorst");
		for (int i = 0; i < 6; i++) {
		    V.opp.addPokemon(new Pokemon(P.ARCEUS, 0, 0));
		}
	    } else {
		wrong = true;
		System.out.println("Wrong!");
	    }
	    for (int i = 0; i < 24; i++) {
		System.out.println();
	    }
	}

	V.player.fillTeam((int) (Math.random() * 6 + 1));
	V.player.nextPokemon();

	V.opp.fillTeam((int) (Math.random() * 6 + 1));
	V.opp.nextPokemon();

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
	if (V.player.getName().equals("Nitin")) {
	    System.out.println("GAME OVER: " + V.opp.getName() + " WINS!!!");
	    System.out.println(V.player.getName() + " LOSERS!!!");
	}
	Player loser = new Battle().run();
    }

}
