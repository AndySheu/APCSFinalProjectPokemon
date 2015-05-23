
import java.awt.*;
import javax.swing.*;

public class Pokemon {

    private Image image;
    private double x, y;
    private int species;
    public int health;
    private int hp, att, def, spd;
    private int attMod, defMod, spdMod;
    private int[] moves;
    private int type1, type2;

    public Pokemon(int species, Image img, double xPos, double yPos) {
	image = img.getScaledInstance(250, 250, 1);
	x = xPos;
	y = yPos;

	this.species = species;

	hp = D.getStats(species)[0];
	att = D.getStats(species)[1];
	def = D.getStats(species)[3];
	spd = D.getStats(species)[5];

	moves = D.getMoves(species);
	health = hp;

	type1 = D.getTypes(species)[0];
	type2 = D.getTypes(species)[1];
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

    public double getX() {
	return x;
    }

    public double getY() {
	return y;
    }

    public Image getImage() {
	return image;
    }

    public String getName() {
	return P.getName(getSpecies());
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
	return (int)(att * getModRate(attMod));
    }

    public int getDef() {
	return (int)(def * getModRate(defMod));
    }

    public int getSpd() {
	return (int)(spd * getModRate(spdMod));
    }

    public double getModRate(int mod) {
	if (mod <= 0) {
	    return (2/(-mod + 2));
	}
	return ((mod + 2)/2);
    }

    public void lowerAtt() {
	if (attMod > -6) {
	    attMod--;
	}
    }

    public void lowerDef() {
	def -= 5;
	if (def <= 0) {
	    def = 1;
	}
    }

    public void lowerSpd() {
	spd -= 5;
	if (spd <= 0) {
	    spd = 1;
	}
    }

    public int[] getMoves() {
	return moves;
    }

    public int getNumMoves() {
	return moves.length;
    }

    public int getType1() {
	return type1;
    }

    public int getType2() {
	return type2;
    }

    public boolean checkFainted() {
	return getHealth() <= 0;
    }

    static int generateRandom() {
	int pkmn = (int) (Math.random() * V.NUM_POKE) + 1;
	while (P.getName(pkmn) == null || P.getName(pkmn).equals("ARCEUS") || P.getName(pkmn).equals("VICTINI")) {
	    pkmn = (int) (Math.random() * V.NUM_POKE) + 1;
	}
	return pkmn;
    }
}
