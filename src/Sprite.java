
import java.awt.Image;
import javax.swing.ImageIcon;

public class Sprite {
    
    private double x,y;
    private Image image;
    
   public Sprite(Image img, double x, double u) {
	image = img.getScaledInstance(250, 250, 1);
	this.x = x;
	this.y = y;
    }

    public Sprite(String path, double x, double y) {
	this(new ImageIcon(path).getImage(), x, y);
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
	image = img;
    }
}