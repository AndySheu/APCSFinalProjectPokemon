
import java.util.Scanner;
import javax.swing.JFrame;

public class V { // V stands for Variables
    
    static String version = "dev 3.0";
    
    static JFrame frame;
    static ImagePanel panel;
    static Scanner keys = new Scanner(System.in);
    
    static Pokemon[] playerPokeParty = new Pokemon[6];
    static Pokemon[] oppPokeParty = new Pokemon[6];
    
    static Player player;
    static Player opp;
    
    static final int MAX_WIDTH = 1440;
    static final int MAX_HEIGHT = 873;
}