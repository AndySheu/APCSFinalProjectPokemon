
import javax.swing.*;
import java.awt.*;

public class Player extends Sprite {

    private String name;
    private Image img;
    private boolean isPlayer;
    private Pokemon current;

    public Player(String name, String trainerClass, boolean player) {
	super(name, 0, 0);
	this.name = name;
	img = new ImageIcon("./src/Images/Trainers/" + trainerClass + ".png").getImage();
	isPlayer = player;
	current = V.oppPokeParty[0];
    }

    String getName() {
	return name;
    }

    void setName(String name) {
	this.name = name;
    }

    Image getImage() {
	return img;
    }

    Pokemon[] getParty() {
	if (isPlayer) {
	    return V.playerPokeParty;
	}
	return V.oppPokeParty;
    }

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

    Pokemon getCurrent() {
	return current;
    }

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

    boolean checkLoss() {
	for (int i = 0; i < getNumPokemon(); i++) {
	    Pokemon p = getParty()[i];
	    if (!p.equals(null) && !p.checkFainted()) {
		return false;
	    }
	}
	return true;
    }

    boolean isFullParty() {
	return getNumPokemon() == 6;
    }

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

    boolean has(Pokemon n) {
	for (int i = 0; i < getNumPokemon(); i++) {
	    Pokemon p = getParty()[i];
	    if (n.getSpecies() == p.getSpecies()) {
		return true;
	    }
	}
	return false;
    }

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
}
