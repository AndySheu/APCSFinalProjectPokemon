
import java.awt.*;
import javax.swing.*;

public class Pokemon {

    private Image image;
    private double x;
    private double y;
    private double vx;
    private double vy;
    private int species;

    public int laps = 0;

    public Pokemon(Image img, double xPos, double yPos) {
	image = img;
	x = xPos;
	y = yPos;
	vx = vy = 0;
    }

    public Pokemon(String path, double xPos, double yPos) {
	this(new ImageIcon(path).getImage(), xPos, yPos);
	try {
	    species = Integer.parseInt(path.substring(21, 24));
	} catch (NumberFormatException e) {
	    try {
		species = Integer.parseInt(path.substring(21, 23));
	    } catch (NumberFormatException ex) {
		try {
		    species = Integer.parseInt(path.substring(21, 22));
		} catch (NumberFormatException exc) {
		    species = 0;
		}
	    }
	}
    }

    public int getSpecies() {
	return species;
    }

    public void setPosition(double xPos, double yPos) {
	x = xPos;
	y = yPos;
    }

    public void setImage(Image img) {
	image = img;
    }

    public void setRaceVelocity(double newVx, double newVy) {
	vx = newVx;
	vy = newVy;
	if (vx < 0) {
	    vx = 0;
	}
	if (vy < 0) {
	    vy = 0;
	}
    }

    public void setVelocity(double newVx, double newVy) {
	vx = newVx;
	vy = newVy;
    }

    public void incrementPosition() {
	x += vx;
	y += vy;
    }

    public void decrementPosition() {
	x -= vx;
	y -= vy;
    }

    public void reflect(boolean side) {
	if (side) {
	    vx = -1 * vx;
	} else {
	    vy = -1 * vy;
	}
    }

    public double getX() {
	return x;
    }

    public double getY() {
	return y;
    }

    public double getVX() {
	return vx;
    }

    public double getVY() {
	return vy;
    }

    public Image getImage() {
	return image;
    }
}
