
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

public class Init {

    public static void initUI() {
	D.fill();
	V.panel = new ImagePanel("./src/Images/Title Screen.png");

	V.frame = new JFrame("Pokémon Diamond III " + V.VERSION + " | Coded by Andy Sheu and Dhruv Jhamb");
	V.frame.setAlwaysOnTop(true);
	V.frame.setSize(V.panel.getWidth(), V.panel.getHeight() + 12);
	V.frame.setLocationRelativeTo(null);
	V.frame.setVisible(true);
	V.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	new Listener();
	V.music.start();
	V.frame.add(V.panel);
	V.frame.repaint();
    }

    public static void titleScreen() {
	while (!V.enter) {
	    Timer.wait(1000);
	}

	V.frame.remove(V.panel);
	V.panel = new ImagePanel("./src/Images/Battle Backgrounds/Finale Loading.png");

	V.frame.setSize(V.panel.getWidth(), (V.panel.getHeight()) + 22);
	V.frame.setLocationRelativeTo(null);
	V.frame.setVisible(true);

	new Listener();
	V.music.nextSong();
	V.frame.add(V.panel);
	V.frame.repaint();
    }

    static void playerInit() {
	V.player = null;
	V.opp = null;
	System.out.print("What is your name? ");
	V.player = new Player(V.keys.nextLine(), "N", true);
	System.out.print("What is your opponent\'s name? ");
	V.opp = new Player(V.keys.nextLine(), "Pikachu", false);
	Password.input();

	V.player.fillTeam((int) (Math.random() * 6 + 1), true);
	V.opp.fillTeam((int) (Math.random() * 6 + 1), true);

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

    static void playerAutoInit() {
	V.player = new Player("N", "N", true);
	V.opp = new Player("Pikachu", "Pikachu", false);

	V.player.fillTeam((int) (Math.random() * 6 + 1), true);
	V.opp.fillTeam((int) (Math.random() * 6 + 1), false);

	V.player.nextPokemon();
	V.opp.nextPokemon();

	V.player.getCurrent().setLoc(V.PLAYER_X, V.PLAYER_Y);
	V.sprites.add(V.player.getCurrent());
	V.opp.getCurrent().setLoc(V.OPP_X, V.OPP_Y);
	V.sprites.add(V.opp.getCurrent());

	V.panel.setVisible(true);
	V.frame.add(V.panel);
	V.frame.repaint();
    }

    static void startBattle() {
	new Battle().run();
	System.out.print("Keep going? (true/false): ");
	try {
	    if (V.keys.nextBoolean()) {
		Timer.wait(1000);
		V.music.nextSong();
		V.player = null;
		V.opp = null;
		V.keys.nextLine();
		playerInit();
		startBattle();
	    }
	    System.exit(0);
	} catch (Exception e) {
	    System.exit(0);
	}
    }
}
