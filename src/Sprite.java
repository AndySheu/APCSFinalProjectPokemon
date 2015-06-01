
import java.awt.Image;
import javax.swing.ImageIcon;

public class Sprite {

    private double x, y;
    private Image image;

    public Sprite(Image img, boolean pokemon, double x, double y) {
	if (pokemon) {
	    image = img.getScaledInstance(175, 175, 1);
	} else {
	    image = img;
	}
	this.x = x;
	this.y = y;
    }

    public Sprite(String path, boolean pokemon, double x, double y) {
	this(new ImageIcon(path).getImage(), pokemon, x, y);
    }

    double getX() {
	return x;
    }

    double getY() {
	return y;
    }

    void setLoc(double x, double y) {
	this.x = x;
	this.y = y;
    }

    Image getImage() {
	return image;
    }

    void setImage(Image img) {
	image = img.getScaledInstance(175, 175, 1);
    }
    
    void setLargeImage(Image img) {
	image = img.getScaledInstance(250, 250, 1);
    }
}
