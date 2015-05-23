
import javax.swing.*;
import java.util.*;
import java.awt.event.KeyEvent;

public class Main {

    public static void main(String[] args) {
	new Main();
    }

    public Main() {
	init();
	Timer.wait(3000); // Wait 3 seconds for the voices in my head to finish
	System.out.println("Pokémon Diamond III " + V.VERSION + " | Coded by Andy S. and Dhruv J.");
	System.out.println("Press [ENTER] to start game!");

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
	V.frame = new JFrame("Pokémon Diamond III " + V.VERSION + " | Coded by Andy Sheu and Dhruv Jhamb");
	V.frame.setSize(V.MAX_FRAME_WIDTH, V.MAX_FRAME_HEIGHT);
	V.frame.add(V.panel);
	V.frame.setVisible(true);
	V.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	new Listener();
    }

    public void gameInit() {
	V.keys.nextLine();
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
		    V.player.addPokemon(new Pokemon(P.VICTINI, V.PLAYER_X, V.PLAYER_Y));
		}
	    } else if (pass.equals("england")) {
		V.player.addPokemon(new Pokemon(P.CHARMANDER, V.PLAYER_X, V.PLAYER_Y));
		V.player.addPokemon(new Pokemon(P.ARCEUS, V.PLAYER_X, V.PLAYER_Y));
		V.player.fillTeam(4, V.PLAYER_X, V.PLAYER_Y);
	    } else if (pass.equals("excellent") && V.player.getName().equals("Mike Bollhorst")) {
		for (int i = 0; i < 6; i++) {
		    V.player.addPokemon(new Pokemon(P.ARCEUS, V.PLAYER_X, V.PLAYER_Y));
		}
	    } else if (pass.equals("meb")) {
		V.opp.setName("Michael E. Bollhorst");
		for (int i = 0; i < 6; i++) {
		    V.opp.addPokemon(new Pokemon(P.ARCEUS, V.OPP_X, V.OPP_Y));
		}
	    } else {
		wrong = true;
		System.out.println("Wrong!");
	    }
	    for (int i = 0; i < 100; i++) {
		System.out.println();
	    }
	}

	if (V.TESTING) {
	    V.player.addPokemon(new Pokemon(1, V.PLAYER_X, V.PLAYER_Y));
	    V.player.addPokemon(new Pokemon(4, V.PLAYER_X, V.PLAYER_Y));
	    V.player.addPokemon(new Pokemon(7, V.PLAYER_X, V.PLAYER_Y));
	    V.player.addPokemon(new Pokemon(16, V.PLAYER_X, V.PLAYER_Y));
	    V.player.addPokemon(new Pokemon(19, V.PLAYER_X, V.PLAYER_Y));
	    V.player.addPokemon(new Pokemon(25, V.PLAYER_X, V.PLAYER_Y));
	    V.opp.addPokemon(new Pokemon(129, V.OPP_X, V.OPP_Y));
	} else {
	    V.player.fillTeam((int) (Math.random() * 6 + 1), V.PLAYER_X, V.PLAYER_Y);
	    V.opp.fillTeam((int) (Math.random() * 6 + 1), V.OPP_X, V.OPP_Y);
	}

	V.player.nextPokemon();
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

	V.player.getCurrent().setLoc(V.PLAYER_X, V.PLAYER_Y);
	V.sprites.add(V.player.getCurrent());
	V.opp.getCurrent().setLoc(V.OPP_X, V.OPP_Y);
	V.sprites.add(V.opp.getCurrent());

	V.panel.setVisible(true);
	V.frame.add(V.panel);
	V.frame.repaint();
    }

    static void keyPressed(KeyEvent e) {

	try {
	    switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT: // 37
		    V.player.getCurrent().setLoc(V.player.getCurrent().getX() - 100, V.player.getCurrent().getY());
		    break;
		case KeyEvent.VK_RIGHT: // 39
		    V.player.getCurrent().setLoc(V.player.getCurrent().getX() + 100, V.player.getCurrent().getY());
		    break;
		case KeyEvent.VK_UP: // 38
		    V.player.getCurrent().setLoc(V.player.getCurrent().getX(), V.player.getCurrent().getY() - 100);
		    break;
		case KeyEvent.VK_DOWN: // 40
		    V.player.getCurrent().setLoc(V.player.getCurrent().getX(), V.player.getCurrent().getY() + 100);
		    break;
	    }
	    V.frame.repaint();
	} catch (NullPointerException ex) {
	}
    }

    static void keyReleased(KeyEvent e) {
//	switch (e.getKeyCode()) {
//	    case KeyEvent.VK_LEFT:
//		System.out.println("LEFT");
//		V.player.getCurrent().setLoc(V.player.getCurrent().getX() - 1, V.player.getCurrent().getY());
//	    case KeyEvent.VK_RIGHT:
//		System.out.println("RIGHT");
//		V.player.getCurrent().setLoc(V.player.getCurrent().getX() + 1, V.player.getCurrent().getY());
//	    case KeyEvent.VK_UP:
//		System.out.println("UP");
//		V.player.getCurrent().setLoc(V.player.getCurrent().getX(), V.player.getCurrent().getY() - 1);
//	    case KeyEvent.VK_DOWN:
//		System.out.println("DOWN");
//		V.player.getCurrent().setLoc(V.player.getCurrent().getX(), V.player.getCurrent().getY() + 1);
//	}
    }

    public void startBattle() {
	if (V.player.getName().equals("Nitin")) {
	    System.out.println("GAME OVER: " + V.opp.getName() + " WINS!!!");
	    System.out.println(V.player.getName() + " LOSERS!!!");
	    while (true);
	}
	new Battle().run();
	System.out.print("Keep going? (true/false): ");
	try {
	    if (V.keys.nextBoolean()) {
		Timer.wait(1000);
		V.player = null;
		V.opp = null;
		gameInit();
		startBattle();
	    }
	} catch (Exception e) {

	}
    }

}
