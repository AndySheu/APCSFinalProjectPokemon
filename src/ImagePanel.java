
import java.awt.*;
import javax.swing.*;
import java.util.*;

public class ImagePanel extends JPanel {

    private Image background;
    private ArrayList<Pokemon> pokemonList;

    //Constructs a new ImagePanel with the background image specified by the file path given
    public ImagePanel(String img) {
	this(new ImageIcon(img).getImage());	//The easiest way to make images from file paths in Swing
	pokemonList = new ArrayList<Pokemon>();
    }

    //Constructs a new ImagePanel with the background image given
    public ImagePanel(Image img) {
	background = img;
	Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
    	//Get the size of the image
	//Thoroughly make the size of the panel equal to the size of the image
	//(Various layout managers will try to mess with the size of things to fit everything)
	setPreferredSize(size);
	setMinimumSize(size);
	setMaximumSize(size);
	setSize(size);
    }

	//This is called whenever the computer decides to repaint the window
    //It's a method in JPanel that I've overwritten to paint the background and foreground images
    public void paintComponent(Graphics g) {
	//Paint the background with its upper left corner at the upper left corner of the panel
	g.drawImage(background, 0, 0, null);
	//Paint each image in the foreground where it should go
	for (Pokemon img : pokemonList) {
	    g.drawImage(img.getImage(), (int) (img.getX()), (int) (img.getY()), null);
	}
    }

    //Replaces the list of foreground images with the one given, and repaints the panel
    public void updateImages(ArrayList<Pokemon> newBouncers) {
	pokemonList = newBouncers;
	repaint();	//This repaints stuff... you don't need to know how it works
    }
}
