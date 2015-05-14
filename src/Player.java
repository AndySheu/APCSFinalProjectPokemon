import javax.swing.*;
import java.awt.*;

public class Player
{
    private String name;
    private Image img;
    Pokemon[] party = new Pokemon[6];
    Item[] items = new Item[10];
    
    public Player(String name, Image img, Pokemon[] party) {
	this.name = name;
	this.img = img;
	this.party = party;
	this.items = items;
    }
    
    String getName() {return name;}
    Image getImage() {return img;}
    Pokemon[] getParty() {return party;}
    Item[] getItems() {return items;}
    
//    boolean checkLoss() {
//	for(Pokemon p:party)
//	    if(!p.checkFainted())
//		return false;
//	return true;
//    }
}