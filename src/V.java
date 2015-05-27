
import java.awt.Image;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFrame;

public class V { // V stands for Variables

    static final String VERSION = "alpha 4.0.0";
    static final int NUM_POKE = 649;
    static final boolean TESTING = false;

    static JFrame frame;
    static ImagePanel panel;
    static Sprite bottomScreen;
    static JButton startMusic, stopMusic;
    static Scanner keys = new Scanner(System.in);
    
    static String[] musicList = new String[]{"./src/Music/FireRed/02 Pallet Town Theme.wav", "./src/Music/FireRed/15 Battle (Vs Trainer).wav", "./src/Music/FireRed/16 Victory (Vs Trainer).wav"};
    static Music music = new Music(musicList);

    static Pokemon[] playerPokeParty = new Pokemon[6];
    static Pokemon[] oppPokeParty = new Pokemon[6];

    static ArrayList<Sprite> sprites = new ArrayList<Sprite>();
    
    static Player player;
    static Player opp;
    
    static final int SHINY_RATE = 100;

    static final int MAX_FRAME_HEIGHT = 873;
    static final int MAX_FRAME_WIDTH = 1440;

    static final int MAX_PANEL_HEIGHT = 851;
    static final int MAX_PANEL_WIDTH = 1440;
    
    static final int PLAYER_X = 55;
    static final int PLAYER_Y = 220;
    static final int OPP_X = 275;
    static final int OPP_Y = 43;

    static boolean enter = false;
    static int state = Battle.OUT;
    static int input = 0;
}
