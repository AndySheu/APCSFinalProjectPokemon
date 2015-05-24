
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFrame;

public class V { // V stands for Variables
    
    static final String VERSION = "alpha 2.5.0";
    static final int NUM_POKE = 649;
    static final boolean TESTING = false; 
    static final boolean FINAL = false;
    
    static JFrame frame;
    static ImagePanel panel;
    static JButton startMusic, stopMusic;
    static Scanner keys = new Scanner(System.in);
    static Music music = new Music(new String[]{"./src/Music/FireRed/01 Opening.wav", "./src/Music/FireRed/02 Pallet Town Theme.wav"});
    
    static Pokemon[] playerPokeParty = new Pokemon[6];
    static Pokemon[] oppPokeParty = new Pokemon[6];
    
    static ArrayList<Sprite> sprites = new ArrayList<Sprite>();
    
    static Player player;
    static Player opp;
    
    static final int MAX_FRAME_HEIGHT = 873;
    static final int MAX_FRAME_WIDTH = 1440;
    
    static final int MAX_PANEL_HEIGHT = 851;
    static final int MAX_PANEL_WIDTH = 1440;
    
    static final int PLAYER_X = 250;
    static final int PLAYER_Y = MAX_PANEL_HEIGHT - 400;
    static final int OPP_X = MAX_PANEL_WIDTH - 400;
    static final int OPP_Y = 100;
}