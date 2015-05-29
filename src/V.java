
import java.awt.Image;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFrame;

public class V { // V stands for Variables

    static final String VERSION = "alpha 5.0.0";
    static final int NUM_POKE = 649;
    static final boolean TESTING = true;

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
    
    static final int SHINY_RATE = 20;

    static final int MAX_FRAME_HEIGHT = 873;
    static final int MAX_FRAME_WIDTH = 1440;

    static final int MAX_PANEL_HEIGHT = 851;
    static final int MAX_PANEL_WIDTH = 1440;
    
    static final int PLAYER_X = 70;
    static final int PLAYER_Y = 200;
    static final int OPP_X = 325;
    static final int OPP_Y = 75;

    static boolean enter = false;
    static int state = Battle.OVERWORLD;
    static int input = 0;
    static boolean click = false;
}
