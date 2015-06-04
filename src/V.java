
import java.awt.Image;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

public class V { // V stands for Variables
    // This class is comprised of a series of random variables that are considered important enough / commmonly used enough to warrant a space here

    static final String VERSION = "alpha 7";
    static final int NUM_POKE = 649;
    static final boolean TESTING = false;

    static JFrame frame;
    static ImagePanel panel;
    static Sprite bottomScreen;
    static JButton startMusic, stopMusic;
    static Scanner keys = new Scanner(System.in);
    
    static Music music = new Music();
    static String musicState = Music.DEFAULT;

    static Pokemon[] playerPokeParty = new Pokemon[6];
    static Pokemon[] oppPokeParty = new Pokemon[6];

    static ArrayList<Sprite> sprites = new ArrayList<Sprite>();
    
    static Player player;
    static Player opp;
    static Sprite playerHealthBar = new Sprite("./src/Images/Battle Parts/User HP Bar White.png", false, 0, 0);
    static Sprite oppHealthBar = new Sprite("./src/Images/Battle Parts/Opp HP Bar White.png", false, 0, 0);
    static Sprite green = new Sprite("./src/Images/Battle Parts/Green Bar.png", false, 0, 0);
    
    static final int SHINY_RATE = 20;

    static final int MAX_FRAME_HEIGHT = 873;
    static final int MAX_FRAME_WIDTH = 1440;

    static final int MAX_PANEL_HEIGHT = 851;
    static final int MAX_PANEL_WIDTH = 1440;
    
    static final int PLAYER_X = 70;
    static final int PLAYER_Y = 205;
    static final int OPP_X = 325;
    static final int OPP_Y = 85;

    static boolean enter = false;
    static int state = Battle.OVERWORLD;
    static int input = 0;
    static boolean click = false;
}
