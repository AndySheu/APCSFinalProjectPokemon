
import javax.swing.*;
import java.util.*;
import java.awt.*;

public class Main {

    private JFrame frame; // the window
    private ImagePanel titleScreenPanel, arceusPanel;

    private Pokemon[] pokemonList = new Pokemon[12];
    private Player[] playerList = new Player[2];

    private double xMax, yMax; // height and width of panel
    // (0,0) is top left, (xMax, yMax) is bottom right
    // number of pixels is xMax+1 by yMax+1

    public static void main(String[] args) {
	Main m = new Main();
    }

    public Main() {
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
	
	titleScreenPanel = new ImagePanel("./src/Images/logo.jpeg");

	Dimension d = titleScreenPanel.getSize();
	yMax = d.getHeight();
	xMax = d.getWidth();

	frame = new JFrame("Pokémon Diamond III " + VarMap.version);
//	frame.setSize((int) d.getWidth(), (int) d.getHeight() + 25);
	frame.setSize(1440,873);
	frame.add(titleScreenPanel);
	frame.setVisible(true);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public void battleInitAndRun() {
	pokemonList[0] = new Pokemon(1, 100,100);
	pokemonList[6] = new Pokemon(4, 150,150);
	Player p = new Player("A", 'p', pokemonList);
	Player o = new Player("B", 'o', pokemonList);
	new Battle(p,o).run();
    }

}
