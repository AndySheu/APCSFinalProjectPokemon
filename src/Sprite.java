
import java.awt.Image;
import javax.swing.ImageIcon;

public class Sprite {

    private double x, y;
    private Image image;

    // Pre: Image img, boolean pokemon, double x, double y
    // Post: Initializes the private variables
    public Sprite(Image img, boolean pokemon, double x, double y) {
	if (pokemon) {
	    image = img.getScaledInstance(175, 175, 1);
	} else {
	    image = img;
	}
	this.x = x;
	this.y = y;
    }
    
    // Pre: String path, boolean pokemon, double x, double y
    // Post: Calls the other constructor and intializes the private variables
    public Sprite(String path, boolean pokemon, double x, double y) {
	this(new ImageIcon(path).getImage(), pokemon, x, y);
    }

    // Pre: None
    // Post: Returns the double x
    double getX() {
	return x;
    }

    // Pre: None
    // Post: Returns the double y
    double getY() {
	return y;
    }
    
    // Pre: double x, double y
    // Post: Sets this.x to x and this.y to y
    void setLoc(double x, double y) {
	this.x = x;
	this.y = y;
    }

    // Pre: None
    // Post: Returns the Image image
    Image getImage() {
	return image;
    }

    // Pre: Image img
    // Post: Sets image to a scaled version of img
    void setImage(Image img) {
	image = img.getScaledInstance(175, 175, 1);
    }
    
    // Pre: Image img
    // Post: Sets image to a larger scaled version of img
    void setLargeImage(Image img) {
	image = img.getScaledInstance(250, 250, 1);
    }
}
