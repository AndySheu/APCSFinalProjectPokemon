
import java.awt.*;
import javax.swing.*;
import java.util.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

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

    public void reset() {
	V.sprites = new ArrayList<Sprite>();

	if (V.state >= Battle.STARTED && V.state <= Battle.FINISHED) {
	    V.player.getCurrent().setLoc(V.PLAYER_X, V.PLAYER_Y);
	    V.sprites.add(V.player.getCurrent());
	    V.opp.getCurrent().setLoc(V.OPP_X, V.OPP_Y);
	    V.sprites.add(V.opp.getCurrent());
	    V.sprites.add(V.playerHealthBar);
	    V.sprites.add(V.oppHealthBar);
	    double playerPercentHealth = ((double) (V.player.getCurrent().getHealth()) / V.player.getCurrent().getHP());
	    Sprite green;
	    if (playerPercentHealth > 0) {
		green = new Sprite(V.green.getScaledInstance((int) (playerPercentHealth * 96), 6), false, V.playerHealthBar.getX() + 100, V.playerHealthBar.getY() + 18);
		V.sprites.add(green);
	    }

	    double oppPercentHealth = ((double) (V.opp.getCurrent().getHealth()) / V.opp.getCurrent().getHP());
	    if (oppPercentHealth > 0) {
		green = new Sprite(V.green.getScaledInstance((int) (oppPercentHealth * 96), 6), false, V.oppHealthBar.getX() + 76, V.oppHealthBar.getY() + 18);
		V.sprites.add(green);
	    }
	}
    }

    static void flash(Sprite s) {
	V.sprites.remove(s);
    }

    public void paint(Graphics g) {
	super.paint(g);
	try {
	    for (Sprite s : V.sprites) {
		g.drawImage(s.getImage(), (int) s.getX(), (int) s.getY(), this);
	    }
	    if (V.state == Battle.ATTACK) {
		g.setColor(Color.black);
		g.setFont(new Font("Arial", 1, 24));
		Point center = new Point(V.MAX_PANEL_WIDTH / 2, V.MAX_PANEL_HEIGHT / 2);

		Font f = new Font("Arial", 1, 24);
		FontMetrics fm = g.getFontMetrics(f);

		int location = 0;
		for (int move : V.player.getCurrent().getMoves()) {
		    if (location < 4) {
			String text = D.getName(move);
			Point c = new Point(0, 0);
			// need +20 for centering reasons
			// top left
			if (location == 0) {
			    c = new Point(134, 562 + 20);
			} // top right
			else if (location == 1) {
			    c = new Point(404, 562 + 20);
			} // bottom left
			else if (location == 2) {
			    c = new Point(134, 665 + 20);
			} // bottom right
			else {
			    c = new Point(404, 665 + 20);
			}
			java.awt.geom.Rectangle2D r = fm.getStringBounds(text, g);
			int tHeight = (int) (r.getHeight());
			int tWidth = (int) (r.getWidth());
			double cX = c.getX();
			double cY = c.getY();
			int x = (int) (cX - tWidth / 2.0);
			int y = (int) ((cY - tHeight / 2.0));
			g.drawString(text, x, y);
			location++;
		    }
		}
	    }
	} catch (ConcurrentModificationException e) {
	    paint(g);
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
