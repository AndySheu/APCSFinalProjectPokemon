
import javax.swing.*;
import java.awt.*;

public class Player extends Sprite {

    private String name;
    private Image img;
    private boolean isPlayer;
    private Pokemon current;

    // Pre: String name, String trainerClass, boolean player
    // Post: Creates a Player Object and initializes the private variables
    public Player(String name, String trainerClass, boolean player) {
	super(name, false, 0, 0);
	this.name = name;
	img = new ImageIcon("./src/Images/Trainers/" + trainerClass + ".png").getImage();
	isPlayer = player;
	current = V.oppPokeParty[0];
    }

    // Pre: None
    // Post: Returns String name
    String getName() {
	return name;
    }

    // Pre: String name
    // Post: Sets this.name to String name
    void setName(String name) {
	this.name = name;
    }
    
    // Pre: None
    // Post: Returns Image img
    Image getImage() {
	return img;
    }

    // Pre: None
    // Post: Returns an array of Pokemon that belongs to the Player
    Pokemon[] getParty() {
	if (isPlayer) {
	    return V.playerPokeParty;
	}
	return V.oppPokeParty;
    }

    // Pre: None
    // Post: Returns an int that is the number of Pokemon in the Player's party
    int getNumPokemon() {
	int i = 0;
	for (Pokemon p : getParty()) {
	    try {
		p.getSpecies(); // Check if null
		i++;
	    } catch (NullPointerException e) {

	    }
	}
	return i;
    }

    // Pre: int x
    // Post: Returns a boolean true if the Pokemon in the Player's party given by int x is set as the current Pokemon, false if otherwise
    boolean setCurrent(int x) {
	for (int i = 0; i < getNumPokemon(); i++) {
	    Pokemon p = getParty()[i];
	    if (p.getSpecies() == x && !p.checkFainted() && current != p) {
		p.resetStats();
		current = p;
		return true;
	    }
	}
	return false;
    }

    // Pre: None
    // Post: Returns Pokemon current
    Pokemon getCurrent() {
	return current;
    }

    // Pre: None
    // Post: Returns a boolean true if the next Pokemon in the Player's party is set as the current Pokemon, false if otherwise
    boolean nextPokemon() {
	for (int i = 0; i < getNumPokemon(); i++) {
	    Pokemon p = getParty()[i];
	    if (!p.equals(null) && !p.checkFainted()) {
		current = p;
		return true;
	    }
	}
	return false;
    }

    // Pre: None
    // Post: Returns a boolean true if all the Pokemon in the Player's party have fainted, false if otherwise
    boolean checkLoss() {
	for (int i = 0; i < getNumPokemon(); i++) {
	    Pokemon p = getParty()[i];
	    if (!p.equals(null) && !p.checkFainted()) {
		return false;
	    }
	}
	return true;
    }

    // Pre: None
    // Post: Returns a boolean true if the number of Pokemon in the Player's party equals 6, false if otherwise
    boolean isFullParty() {
	return getNumPokemon() == 6;
    }

    // Pre: Pokemon p
    // Post: Returns a boolean true if Pokemon p is added to either Player's party, false if otherwise
    boolean addPokemon(Pokemon p) {
	if (isPlayer && !isFullParty()) {
	    V.playerPokeParty[getNumPokemon()] = p;
	    return true;
	} else if (!isPlayer && !isFullParty()) {
	    V.oppPokeParty[getNumPokemon()] = p;
	    return true;
	}
	return false;
    }

    // Pre: Pokemon n
    // Post: Returns a boolean true if the Player has Pokemon n in his or her party, false if otherwise
    boolean has(Pokemon n) {
	for (int i = 0; i < getNumPokemon(); i++) {
	    Pokemon p = getParty()[i];
	    if (n.getSpecies() == p.getSpecies()) {
		return true;
	    }
	}
	return false;
    }

    // Pre: int n, boolean player
    // Post: Fills the Player's party with Pokemon
    void fillTeam(int n, boolean player) {

	if (getNumPokemon() < 6) {
	    Pokemon p = new Pokemon(Pokemon.generateRandom(), player);
	    addPokemon(p);
	    for (int i = 0; i < n - 1; i++) {
		if (getNumPokemon() < 6) {
		    while (has(p)) {
			p = new Pokemon(Pokemon.generateRandom(), player);
		    }
		    addPokemon(p);
		}
	    }
	}
    }

    // Pre: None
    // Post: Empties both Player's parties
    static void emptyTeam() {
	V.playerPokeParty = new Pokemon[6];
	V.oppPokeParty = new Pokemon[6];
    }
}
