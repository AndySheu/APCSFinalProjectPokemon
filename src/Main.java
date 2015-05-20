
import javax.swing.*;
import java.util.*;
import java.awt.*;
import javafx.stage.Stage;

public class Main {

    private JFrame frame; // the window
    private ImagePanel titleScreenPanel;

    private Pokemon[] pokemonList = new Pokemon[12];
    private Player[] playerList = new Player[2];

    private double xMax, yMax; // height and width of panel
    // (0,0) is top left, (xMax, yMax) is bottom right
    // number of pixels is xMax+1 by yMax+1

    public static void main(String[] args) {
	System.out.println("DUCK1");
//	Music a = new Music();
//	a.main(args);
	Main m = new Main();
    }

    public Main() {
	System.out.println("Hello world!");
	System.out.println("DUCK!");
	init();
//	PokeMove.makePokemonRace(xMax, yMax, pokemonList, titleScreenPanel);
//	PokeMove.makePokemonBounce(xMax, yMax,pokemonList, titleScreenPanel);
//	PokeMove.makePokemonBounceOne(xMax, yMax,pokemonList, titleScreenPanel);
//	KeyInput.run(frame, arceusPanel);
	battleInitAndRun();

    }

    // Dimension[width=1440,height=873] is full screen
    public void init() {
	PokemonStats.fill();
	Move.fill();
	
	titleScreenPanel = new ImagePanel("./src/Images/white.png");

	Dimension d = titleScreenPanel.getSize();
	yMax = d.getHeight();
	xMax = d.getWidth();

	frame = new JFrame("Pokémon Diamond III " + VarMap.version);
//	frame.setSize((int) d.getWidth(), (int) d.getHeight() + 25);
	frame.setSize(1440,873);
	frame.add(titleScreenPanel);
	Timer.wait(1000);
	frame.setVisible(true);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public void battleInitAndRun() {
	pokemonList[0] = new Pokemon(PokemonStats.RATTATA, 1000,100);
	pokemonList[6] = new Pokemon(PokemonStats.BULBASAUR, 100,100);
	System.out.print("Player's Name? ");
	Player p = new Player(new Scanner(System.in).next(), 'p', pokemonList);
	System.out.print("Opponent's Name? ");
	Player o = new Player(new Scanner(System.in).next(), 'o', pokemonList);
	frame.repaint();
	new Battle(p,o, titleScreenPanel, frame).run();
    }

}
