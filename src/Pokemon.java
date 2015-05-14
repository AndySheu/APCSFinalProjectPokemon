import java.awt.*;
import javax.swing.*;
import java.util.*;

public class Pokemon
{
    private int species;
    private Image image;
    private double x, y;
    private int level,health;
    private int hp, att, spA, def, spD, spd;
    private Type type1, type2;
    private Move[] moves;
    private ArrayList<Move> moveList;
    
    public Pokemon(int species, Image image, double xPos, double yPos, int hp, int att, int spA, int def, int spD, int spd, Move[] moves, int level, int health) {
	this.species = species;
	this.image = new ImageIcon("/Images/Pokemon/" + species + ".png").getImage();
	this.x = xPos;
	this.y = yPos;
	this.hp = hp;
	this.att = att;
	this.spA = spA;
	this.def = def;
	this.spD = spD;
	this.spd = spd;
	this.moves = moves;
	this.level = level;
	this.health = health;
    }
    
    public Pokemon(int species, int xPos, int yPos, Move[] moves, int level, int health) {
	this.species = species;
	this.image = new ImageIcon("/Images/Pokemon/" + species + ".png").getImage();
	this.x = xPos;
	this.y = yPos;
	
	int[] stats = PokemonStats.getStats(species);
	this.hp = stats[0];
	this.att = stats[1];
	this.spA = stats[2];
	this.def = stats[3];
	this.spD = stats[4];
	this.spd = stats[5];
	this.moves = moves;
	this.level = level;
	this.health = health;
    }
    
    int getHealth() {return health;}
    int getHp() {return hp;}
    int getAtt() {return att;}
    int getSpA() {return spA;}
    int getDef() {return def;}
    int getSpD() {return spD;}
    int getSpd() {return spd;}
    Move[] getMoves() {return moves;}
    Type getType1() {return type1;}
    Type getType2() {return type2;}
    
    public void doHealing(int heal) {
	health += heal;
	if (health > hp)
	    health = hp;
    }
    
    public void doDamage(int damage) {
	health -= damage;
    }
    
    public boolean checkFainted() {
	return health <= 0;
    }
}