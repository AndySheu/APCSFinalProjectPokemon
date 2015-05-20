
import java.awt.*;
import javax.swing.*;

public class Pokemon {

    private Image image;
    private double x;
    private double y;
    private double vx;
    private double vy;
    private int species;
    public int health;
    private int hp;
    private int att;
    private int def;
    private int spd;
    private int[] moves;
    private int type1;
    private int type2;
    public int laps = 0;

    public Pokemon(int species, Image img, double xPos, double yPos) {
	image = img;
	x = xPos;
	y = yPos;
	vx = vy = 0;
	
	this.species = species;
	hp = PokemonStats.getStats(species)[0];
	att = PokemonStats.getStats(species)[1];
	def = PokemonStats.getStats(species)[3];
	spd = PokemonStats.getStats(species)[5];
	
	moves = PokemonStats.getMoves(species);
	health = hp;
    }

    public Pokemon(int species, double xPos, double yPos) {
	this(species, new ImageIcon("./src/Images/Pokemon/" + species + ".png").getImage(), xPos, yPos);
    }

    public void setPosition(double xPos, double yPos) {
	x = xPos;
	y = yPos;
    }

    public void setImage(Image img) {
	image = img;
    }

    public void setVelocity(double newVx, double newVy) {
	vx = newVx;
	vy = newVy;
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

    public Image getImage() {
	return image;
    }

    public int getSpecies() {
	return species;
    }

    public int getHealth() {
	return health;
    }

    public int getHP() {
	return hp;
    }

    public int getAtt() {
	return att;
    }

    public int getDef() {
	return def;
    }

    public int getSpd() {
	return spd;
    }

    public int[] getMoves() {
	return moves;
    }

    public int getType1() {
	return type1;
    }

    public int getType2() {
	return type2;
    }

    public double getVX() {
	return vx;
    }

    public double getVY() {
	return vy;
    }

    public boolean checkFainted() {
	return getHealth() <= 0;
    }
}
