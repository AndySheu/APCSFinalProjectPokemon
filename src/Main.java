
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;

public class Main {

    public static void main(String[] args) {
	new Main();
    }

    public Main() {
	Init.initUI();
	Init.titleScreen();
	if (V.FINAL) {
//	    Timer.wait(3000); // Wait 3 seconds for the voices in my head to finish
	    System.out.println("Pokémon Diamond III " + V.VERSION + " | Coded by Andy S. and Dhruv J.");
	    System.out.println("Press [ENTER] to start game!");
	}

	if (!V.TESTING) {
	    gameInit();
	    startBattle();
	} else {
	    V.player = new Player("N", "N", true);
	    V.opp = new Player("Pikachu", "Pikachu", false);

	    V.player.fillTeam((int) (Math.random() * 6 + 1), V.PLAYER_X, V.PLAYER_Y);
	    V.opp.fillTeam((int) (Math.random() * 6 + 1), V.OPP_X, V.OPP_Y);

	    V.player.nextPokemon();
	    V.opp.nextPokemon();
	    
	    V.player.getCurrent().setLoc(V.PLAYER_X, V.PLAYER_Y);
	    V.sprites.add(V.player.getCurrent());
	    V.opp.getCurrent().setLoc(V.OPP_X, V.OPP_Y);
	    V.sprites.add(V.opp.getCurrent());

	    V.panel.setVisible(true);
	    V.frame.add(V.panel);
	    V.frame.repaint();
	    
	    startBattle();
	}
    }

    public void gameInit() {
	System.out.print("What is your name? ");
	V.player = new Player(V.keys.nextLine(), "N", true);
	System.out.print("What is your opponent\'s name? ");
	V.opp = new Player(V.keys.nextLine(), "Pikachu", false);

//	boolean wrong = false;
//
//	while (!wrong) {
//	    System.out.print("Enter a password (if you have one): ");
//	    String pass = V.keys.nextLine();
//
//	    if (pass.equals("france")) {
//		for (int i = 0; i < 6; i++) {
//		    V.player.addPokemon(new Pokemon(P.VICTINI, V.PLAYER_X, V.PLAYER_Y));
//		}
//	    } else if (pass.equals("england")) {
//		V.player.addPokemon(new Pokemon(P.CHARMANDER, V.PLAYER_X, V.PLAYER_Y));
//		V.player.addPokemon(new Pokemon(P.ARCEUS, V.PLAYER_X, V.PLAYER_Y));
//		V.player.fillTeam(4, V.PLAYER_X, V.PLAYER_Y);
//	    } else if (pass.equals("excellent") && V.player.getName().equals("Mike Bollhorst")) {
//		for (int i = 0; i < 6; i++) {
//		    V.player.addPokemon(new Pokemon(P.ARCEUS, V.PLAYER_X, V.PLAYER_Y));
//		}
//	    } else if (pass.equals("meb")) {
//		V.opp.setName("Michael E. Bollhorst");
//		for (int i = 0; i < 6; i++) {
//		    V.opp.addPokemon(new Pokemon(P.ARCEUS, V.OPP_X, V.OPP_Y));
//		}
//	    } else {
//		wrong = true;
//		System.out.println("Wrong!");
//	    }
//	    for (int i = 0; i < 100; i++) {
//		System.out.println();
//	    }
//	}
	V.player.fillTeam((int) (Math.random() * 6 + 1), V.PLAYER_X, V.PLAYER_Y);
	V.opp.fillTeam((int) (Math.random() * 6 + 1), V.OPP_X, V.OPP_Y);

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
		V.keys.nextLine();
		gameInit();
		startBattle();
	    }
	    System.exit(0);
	} catch (Exception e) {

	}
    }

}
