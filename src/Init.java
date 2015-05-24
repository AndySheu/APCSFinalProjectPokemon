
import javax.swing.JButton;
import javax.swing.JFrame;

public class Init {
    
    public static void initUI() {
	D.fill();
	V.panel = new ImagePanel("./src/Images/Transparent.png");

	JFrame passwordHint = new JFrame("PASSWORD HINT (4): Mike's full initials | Player.setName(\"MB\"); && pass.equals(/*MB's favorite word*/);");
	passwordHint.setSize(0, 0);
	passwordHint.setVisible(true);
	passwordHint.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	V.frame = new JFrame("Pokémon Diamond III " + V.VERSION + " | Coded by Andy Sheu and Dhruv Jhamb");
	V.frame.setSize(V.MAX_FRAME_WIDTH, V.MAX_FRAME_HEIGHT);
	V.frame.setLocationRelativeTo(null);
	V.frame.add(V.panel);
	V.frame.setVisible(true);
	V.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	V.startMusic = new JButton("START");
	V.startMusic.setBounds(V.MAX_PANEL_WIDTH / 2 - 150, V.MAX_PANEL_HEIGHT / 2, 200, 50);
	V.startMusic.addActionListener(new Listener("start"));

	V.stopMusic = new JButton("STOP");
	V.startMusic.setBounds(V.MAX_PANEL_WIDTH / 2 + 150, V.MAX_PANEL_HEIGHT / 2, 200, 50);
	V.stopMusic.addActionListener(new Listener("stop"));

	V.panel.add(V.startMusic);
	V.panel.add(V.stopMusic);

	V.music.start();
	new Listener();
    }
}