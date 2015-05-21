
import javax.swing.*;
import java.util.*;
import java.awt.*;
import javafx.stage.Stage;

public class Main {

    private JFrame frame; // the window
    private ImagePanel titleScreenPanel;

    private Pokemon[] pokemonList = new Pokemon[12];
    private Player[] playerList = new Player[2];
    
    private ArrayList<Pokemon> pl = new ArrayList<Pokemon>();

    private double xMax, yMax; // height and width of panel
    // (0,0) is top left, (xMax, yMax) is bottom right
    // number of pixels is xMax+1 by yMax+1

    public static void main(String[] args) {
//	Music a = new Music();
//	a.main(args);
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

	titleScreenPanel = new ImagePanel("./src/Images/White.png");

	Dimension d = titleScreenPanel.getSize();
	yMax = d.getHeight();
	xMax = d.getWidth();

	frame = new JFrame("Pokémon Diamond III " + VarMap.version + " | Coded by Andy Sheu and Dhruv Jhamb");
//	frame.setSize((int) d.getWidth(), (int) d.getHeight() + 25);
	frame.setSize(1440, 873);
	frame.add(titleScreenPanel);
	frame.setVisible(true);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void battleInitAndRun() {
	frame.repaint();

	int pkmn = (int) (Math.random() * 25) + 1;
	while (PokemonStats.getName(pkmn) == null) {
	    pkmn = (int) (Math.random() * 25) + 1;
	}
	pkmn = 1;
	pokemonList[0] = new Pokemon(pkmn, 1000, 100);

	frame.repaint();

	pkmn = (int) (Math.random() * 25) + 1;
	while (PokemonStats.getName(pkmn) == null) {
	    pkmn = (int) (Math.random() * 25) + 1;
	}
	pkmn = 7;
	pokemonList[6] = new Pokemon(pkmn, 100, 100);

	frame.repaint();
	System.out.print("Player's Name? ");
	Player p = new Player(new Scanner(System.in).next(), 'p', pokemonList);
	System.out.println(p.getName() + " has a " + pokemonList[0].getName());

	System.out.print("Opponent's Name? ");
	Player o = new Player(new Scanner(System.in).next(), 'o', pokemonList);
	System.out.println(o.getName() + " has a " + pokemonList[6].getName());

	pl.add(p.getCurr());
	pl.add(o.getCurr());

	
	titleScreenPanel.updateImages(pl);
	frame.add(titleScreenPanel);

	frame.repaint();
	new Battle(p, o, titleScreenPanel, frame).run();
    }

}
