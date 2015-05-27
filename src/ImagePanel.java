
import java.awt.*;
import javax.swing.*;
import java.util.*;

public class ImagePanel extends JPanel {

    private Image background;

    //Constructs a new ImagePanel with the background image specified by the file path given
    public ImagePanel(String img) {
	this(new ImageIcon(img).getImage());	//The easiest way to make images from file paths in Swing
    }

    public ImagePanel(Image img) {
	background = img;

	setFocusable(true);
	setDoubleBuffered(true);
	Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
	setPreferredSize(size);
	setMinimumSize(size);
	setMaximumSize(size);
	setSize(size);
    }

    void setImage(Image img) {
	background = img;
	repaint();
    }
    
    void clear() {
	V.sprites = new ArrayList<Sprite>();
    }

    static void reset() {
	V.sprites = new ArrayList<Sprite>();
//	V.sprites.add(V.player);
//	V.sprites.add(V.opp);

	V.player.getCurrent().setLoc(V.PLAYER_X, V.PLAYER_Y);
	V.sprites.add(V.player.getCurrent());
	V.opp.getCurrent().setLoc(V.OPP_X, V.OPP_Y);
	V.sprites.add(V.opp.getCurrent());
    }
    
    static void flash(Sprite s) {
	ArrayList<Sprite> temp = new ArrayList<Sprite>();
	for (Sprite sp : V.sprites) {
	    if (!(sp == s)) {
		temp.add(sp);
	    }
	}
	V.sprites.remove(s);
    }

    public void paint(Graphics g) {
	super.paint(g);

	for (Sprite s : V.sprites) {
	    g.drawImage(s.getImage(), (int) s.getX(), (int) s.getY(), this);
	}

	Toolkit.getDefaultToolkit().sync();
	g.dispose();
    }

    public void paintComponent(Graphics g) {
	g.drawImage(background, 0, 0, null);
	for (Sprite spr : V.sprites) {
	    g.drawImage(spr.getImage(), (int) (spr.getX()), (int) (spr.getY()), null);
	}
    }
}
