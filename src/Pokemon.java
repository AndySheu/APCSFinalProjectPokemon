
import java.awt.*;
import javax.swing.*;

public class Pokemon {

    private Image image;
    private double x, y;
    private int species;
    public int health;
    private int hp, att, def, spd;
    private int[] moves;
    private int type1, type2;

    public Pokemon(int species, Image img, double xPos, double yPos) {
	image = img.getScaledInstance(250, 250, 1);
	x = xPos;
	y = yPos;

	this.species = species;

	hp = PokemonStats.getStats(species)[0];
	att = PokemonStats.getStats(species)[1];
	def = PokemonStats.getStats(species)[3];
	spd = PokemonStats.getStats(species)[5];

	moves = PokemonStats.getMoves(species);
	health = hp;

	type1 = PokemonStats.getTypes(species)[0];
	type2 = PokemonStats.getTypes(species)[1];
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
	switch (getSpecies()) {
	    case PokemonStats.BULBASAUR:
		return "BULBASAUR";
	    case PokemonStats.CHARMANDER:
		return "CHARMANDER";
	    case PokemonStats.SQUIRTLE:
		return "SQUIRTLE";
	    case PokemonStats.PIDGEY:
		return "PIDGEY";
	    case PokemonStats.RATTATA:
		return "RATTATA";
	    case PokemonStats.PIKACHU:
		return "PIKACHU";
	}
	return null;
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

    public void lowerAtt() {
	att -= 5;
	if (att <= 0) {
	    att = 1;
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
	if (moves[3] != 0) {
	    return 4;
	}
	if (moves[2] != 0) {
	    return 3;
	}
	if (moves[1] != 0) {
	    return 2;
	}
	if (moves[0] != 0) {
	    return 1;
	}
	return 0;
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
	int pkmn = (int) (Math.random() * 25) + 1;
	while (PokemonStats.getName(pkmn) == null) {
	    pkmn = (int) (Math.random() * 25) + 1;
	}
	return pkmn;
    }
}
