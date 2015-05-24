
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

public class Init {

    public static void initUI() {
	D.fill();
	V.panel = new ImagePanel("./src/Images/Pokemon/494.png");

	V.frame = new JFrame("Pokémon Diamond III " + V.VERSION + " | Coded by Andy Sheu and Dhruv Jhamb");
	V.frame.setSize(V.panel.getWidth(), V.panel.getHeight() + 12);
	V.frame.setLocationRelativeTo(null);
	V.frame.setVisible(true);
	V.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	new Listener();
	V.music.start();
	V.frame.add(V.panel);
	V.frame.repaint();
    }

    public static void titleScreen() {
	while (!V.enter) {
	    Timer.wait(1000);
	}

	V.frame.remove(V.panel);
	V.panel = new ImagePanel("./src/Images/Battle Backgrounds/Finale.png");
//	Image back = new ImageIcon("./src/Images/Battle Backgrounds/Finale.png").getImage();
//	System.out.println((V.MAX_PANEL_HEIGHT / 2));
//	System.out.println(back.getWidth(null));
//	System.out.println(back.getHeight(null));
//	double k = (double)(V.MAX_PANEL_HEIGHT / 2) / (back.getHeight(null));
//	back = back.getScaledInstance((int)(back.getWidth(null) * k), (int)(back.getHeight(null) * k), 0);
//	V.panel = new ImagePanel(back);
//	V.panel = new ImagePanel(new ImageIcon("./src/Images/Battle Backgrounds/Finale.png").getImage().getScaledInstance(200,100,100));
//
//	System.out.println(k);
//
//	System.out.println(back.getWidth(null));
//	System.out.println(back.getHeight(null));
//	System.out.println(V.panel.getWidth());

	V.frame.setSize(V.panel.getWidth(), (2 * V.panel.getHeight()) + 22);
	V.frame.setLocationRelativeTo(null);
	V.frame.setVisible(true);

	new Listener();
	V.music.nextSong();
	V.frame.add(V.panel);
	V.frame.repaint();
    }
}
