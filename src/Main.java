
import javax.swing.*;
import java.util.*;
import java.awt.*;

public class Main {

    private JFrame frame; // the window
    private ImagePanel titleScreenPanel, arceusPanel;

    private ArrayList<Pokemon> pokemonList = new ArrayList<Pokemon>(12);

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
	PokeMove.makePokemonBounceOne(xMax, yMax,pokemonList, titleScreenPanel);
//	KeyInput.run(frame, arceusPanel);

    }

    // Dimension[width=1440,height=873] is full screen
    public void init() {
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

    public void clear() {
	for(Component c:titleScreenPanel.getComponents()) {
	    frame.remove(c);
	    titleScreenPanel.remove(c);
	}
	frame.getContentPane().removeAll();
	frame.repaint();
    }
}
