
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

    // Pre: String img
    // Post: Constructs an ImagePanel object using String img
    //Constructs a new ImagePanel with the background image specified by the file path given
    public ImagePanel(String img) {
	this(new ImageIcon(img).getImage());	//The easiest way to make images from file paths in Swing
    }

    // Pre: Image img
    // Post: Constructs an ImagePanel object using Image img
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

    // Pre: Image img
    // Post: Sets the background image to Image img and calls repaint()
    void setImage(Image img) {
	background = img;
	repaint();
    }

    // Pre: None
    // Post: Clears the ArrayList of Sprites in class V
    void clear() {
	V.sprites = new ArrayList<Sprite>();
    }

    // Pre: None
    // Post: Clears the ArrayList of Sprites in class V and then fills it
    public void reset() {
	V.sprites = new ArrayList<Sprite>();
//	V.sprites.add(V.player);
//	V.sprites.add(V.opp);

	if (V.state >= Battle.STARTED && V.state <= Battle.FINISHED) {
	    V.player.getCurrent().setLoc(V.PLAYER_X, V.PLAYER_Y);
	    V.sprites.add(V.player.getCurrent());
	    V.opp.getCurrent().setLoc(V.OPP_X, V.OPP_Y);
	    V.sprites.add(V.opp.getCurrent());
	    V.sprites.add(V.playerHealth);
	    V.sprites.add(V.oppHealth);
	}
    }

    // Pre: Sprite s
    // Post: Removes Sprite s from the ArrayList of Sprites in class V
    static void flash(Sprite s) {
	ArrayList<Sprite> temp = new ArrayList<Sprite>();
	for (Sprite sp : V.sprites) {
	    if (!(sp == s)) {
		temp.add(sp);
	    }
	}
	V.sprites.remove(s);
    }

    // Pre: Graphics g
    // Post: Calls super.paint(g) and draws Images
    public void paint(Graphics g) {
	super.paint(g);

	try {
	    for (Sprite s : V.sprites) {
		g.drawImage(s.getImage(), (int) s.getX(), (int) s.getY(), this);
	    }
	    if (V.state >= Battle.ATTACK) {
		for (int i = 0; i < V.player.getCurrent().getNumMoves(); i++) {
		    Point center = new Point(135, 563);
		    g.setColor(Color.black);
//		    Font f = new Font("Courier New", 1, 0);
//		    FontMetrics fm = g.getFontMetrics(f);
		    String moveText = "duckz";
		    g.setFont(new Font(g.getFont().getName(), g.getFont().getStyle(), 50));
		    java.awt.geom.Rectangle2D rect = g.getFontMetrics().getStringBounds(moveText, g);
//		    java.awt.geom.Rectangle2D rect = fm.getStringBounds(moveText, g);

		    int textHeight = (int) (rect.getHeight());
		    int textWidth = (int) (rect.getWidth());
		    System.out.println("Text height: " + textHeight + " " + "Text width: " + textWidth);

		    double centerX = center.getX();
		    double centerY = center.getY();
		    System.out.println("centerX: " + centerX + " " + "centerY: " + centerY);

		    int x = (int) (centerX - (textWidth / 2.0));
		    int y = (int) ((centerY - (textHeight / 2.0)));
		    System.out.println("X: " + x + " "+ "Y: " + y);

		    g.drawString(moveText, x, y);
		}
	    }
	} catch (ConcurrentModificationException e) {
	    paint(g);
	}

	Toolkit.getDefaultToolkit().sync();
	g.dispose();
    }

    // Pre: Graphics g
    // Post: Draws the background and the Sprites in the ArrayList of Sprites in class V
    public void paintComponent(Graphics g) {
	g.drawImage(background, 0, 0, null);
	for (Sprite spr : V.sprites) {
	    g.drawImage(spr.getImage(), (int) (spr.getX()), (int) (spr.getY()), null);
	}
    }
}
