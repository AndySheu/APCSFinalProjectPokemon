
import javax.swing.*;
import java.util.*;
import java.awt.*;

public class Main {

    private JFrame frame; // the window
    private ImagePanel titleScreenPanel; // the image panel

    private ArrayList<Pokemon> pokemonList = new ArrayList<Pokemon>(12);

    private double xMax, yMax; // height and width of panel
    // (0,0) is top left, (xMax, yMax) is bottom right
    // number of pixels is xMax+1 by yMax+1

    public static void main(String[] args) {
	Main m = new Main();
    }

    public Main() {
	init();
	PokeBounce.makePokemonBounce(xMax, yMax,pokemonList, titleScreenPanel);
    }

    // Dimension[width=1440,height=873] is full screen

    public void init() {
	titleScreenPanel = new ImagePanel("./src/Images/logo.jpeg");

	Dimension d = titleScreenPanel.getSize();
	yMax = d.getHeight();
	xMax = d.getWidth();

	frame = new JFrame("Pokémon Diamond III " + VarMap.version);
	frame.setSize((int) d.getWidth(), (int) d.getHeight() + 25);
	frame.add(titleScreenPanel);
	frame.setVisible(true);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
