
import javax.swing.JButton;
import javax.swing.JFrame;

public class Init {
    
    public static void initUI() {
	D.fill();
	V.panel = new ImagePanel("./src/Images/Transparent.png");
	
	V.frame = new JFrame("Pokémon Diamond III " + V.VERSION + " | Coded by Andy Sheu and Dhruv Jhamb");
	V.frame.setSize(V.MAX_FRAME_WIDTH, V.MAX_FRAME_HEIGHT);
//	V.frame.setLocationRelativeTo(null);
	V.frame.setVisible(true);
	V.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	new Listener();
	V.music.start();
	V.frame.add(V.panel);
    }
}