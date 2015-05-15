
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Player {

    private String name;
    private Image img;
    Pokemon[] party = new Pokemon[6];
    Item[] items = new Item[10];
    Pokemon curr;

    public Player(String name, char c, Pokemon[] pokemonList) {
	this.name = name;

	//TODO : Trainer doesn't exist
	img = new ImageIcon("./src/Images/trainer.png").getImage();

	int j;

	if (c == 'p') {
	    j = 0;
	} else {
	    j = 6;
	}

	for (int i = j; j <= i+5; j++) {
	    party[i%6] = pokemonList[i];
	}

	curr = party[0];

//	this.items = items;
    }

    String getName() {
	return name;
    }

    Image getImage() {
	return img;
    }

    Pokemon[] getParty() {
	return party;
    }

    Item[] getItems() {
	return items;
    }

    Pokemon getCurr() {
	return curr;
    }

    boolean checkLoss() {
	for (Pokemon p : party) {
	    try {
		if (!p.checkFainted()) {
		    return false;
		}
	    } catch (NullPointerException e) {
		return true;
	    }
	}
	return true;
    }
}
