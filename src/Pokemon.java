
import java.awt.*;
import javax.swing.*;

public class Pokemon extends Sprite {

    private int species;
    private int health;
    private int hp, att, def, spd;
    private int attMod, defMod, spdMod;
    private int[] moves;
    private int type1, type2;
    private boolean shiny = false;

    // Pre: int species, boolean player, Image img
    // Post: Initializes the private variables
    public Pokemon(int species, boolean player, Image img) {
	super(img, true, 0, 0);

	if ((int)(Math.random() * V.SHINY_RATE) == 0) {
	    shiny = true;
	    setImage(new ImageIcon("./src/Images/Shiny/" + species + ".png").getImage());
	}

	if (player) {
	    if (shiny) {
		setLargeImage(new ImageIcon("./src/Images/Shiny Back/" + species + ".png").getImage());
	    } else {
		setLargeImage(new ImageIcon("./src/Images/Back/" + species + ".png").getImage());
	    }
	    setLoc(V.PLAYER_X, V.PLAYER_Y);
	} else {
	    setLoc(V.OPP_X, V.OPP_Y);
	}

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

    // Pre: int species, boolean player
    // Post: Calls the other constructor and initializes the private variables
    public Pokemon(int species, boolean player) {
	this(species, player, new ImageIcon("./src/Images/Normal/" + species + ".png").getImage());
    }

    // Pre: double x, double y
    // Post: Sets x equal to x, and y equal to y
    public void setPosition(double x, double y) {
	x = x;
	y = y;
    }

    // Pre: None
    // Post: Returns a String that contains the name of the Pokemon
    public String getName() {
	return P.getName(getSpecies());
    }

    // Pre: None
    // Post: "Kills" the Pokemon by setting its health to Integer.MIN_VALUE
    public void kill() {
	health = Integer.MIN_VALUE;
    }

    // Pre: None
    // Post: Returns an int that is the species of the Pokemon
    public int getSpecies() {
	return species;
    }

    // Pre: None
    // Post: Returns an int that is the health of the Pokemon
    public int getHealth() {
	return health;
    }

    // Pre: int damage
    // Post: Subtracts int damage from health
    public void lowerHealth(int damage) {
	health -= damage;
    }

    // Pre: None
    // Post: Returns an int that is the hp of the Pokemon
    public int getHP() {
	return hp;
    }

    // Pre: None
    // Post: Returns an int that is the attack of the Pokemon
    public int getAtt() {
	return (int) (att * getModRate(attMod));
    }

    // Pre: None
    // Post: Returns an int that is the defense of the Pokemon
    public int getDef() {
	return (int) (def * getModRate(defMod));
    }

    // Pre: None
    // Post: Returns an int that is the speed of the Pokemon
    public int getSpd() {
	return (int) (spd * getModRate(spdMod));
    }

    // Pre: double mod
    // Post: Calculates and returns a double using double mod, the double is used to change the att, def, or spd of the Pokemon
    public double getModRate(double mod) {
	if (mod <= 0) {
	    return (2 / (-mod + 2));
	}
	return ((mod + 2) / 2);
    }

    // Pre: None
    // Post: If attMod is greater than -6, it is lowered by 1
    public void lowerAtt() {
	if (attMod > -6) {
	    attMod--;
	}
    }
    
    // Pre: None
    // Post: If defMod is greater than -6, it is lowered by 1
    public void lowerDef() {
	if (defMod > -6) {
	    defMod--;
	}
    }

    // Pre: None
    // Post: If spdMod is greater than -6, it is lowered by 1
    public void lowerSpd() {
	if (spdMod > -6) {
	    spdMod--;
	}
    }

    // Pre: None
    // Post: If attMod is less than 6, it is raised by 1
    public void raiseAtt() {
	if (attMod < 6) {
	    attMod++;
	}
    }
    
    // Pre: None
    // Post: If defMod is less than 6, it is raised by 1
    public void raiseDef() {
	if (defMod < 6) {
	    defMod++;
	}
    }

    // Pre: None
    // Post: If spdMod is less than 6, it is raised by 1
    public void raiseSpd() {
	if (spdMod < 6) {
	    spdMod++;
	}
    }

    // Pre: None
    // Post: Sets attMod, defMod, and spdMod to 0
    public void resetStats() {
	attMod = 0;
	defMod = 0;
	spdMod = 0;
    }

    // Pre: None
    // Post: Returns an array of ints that contains indices to refer to Moves
    public int[] getMoves() {
	return moves;
    }

    // Pre: None
    // Post: Returns the number of Moves the Pokemon has
    public int getNumMoves() {
	return moves.length;
    }

    // Pre: None
    // Post: Returns an int that is the index of the first Type of the Pokemon
    public int getType1() {
	return type1;
    }
    
    // Pre: None
    // Post: Returns an int that is the index of the second Type of the Pokemon
    public int getType2() {
	return type2;
    }

    // Pre: None
    // Post: Returns a boolean true if the health of the Pokemon is less than or equal to 0, false if otherwise
    public boolean checkFainted() {
	return getHealth() <= 0;
    }

    // Pre: None
    // Post: Returns an int that is the index of a random Pokemon that is not Arceus or Victini
    static int generateRandom() {
	int pkmn = (int) (Math.random() * V.NUM_POKE) + 1;
	while (P.getName(pkmn) == null || P.getName(pkmn).equals("ARCEUS") || P.getName(pkmn).equals("VICTINI")) {
	    pkmn = (int) (Math.random() * V.NUM_POKE) + 1;
	}
	return pkmn;
    }
}
