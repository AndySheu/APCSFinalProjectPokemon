
public class P { // Pokemon

    static final int BULBASAUR = 1;
    static final int CHARMANDER = 4;
    static final int SQUIRTLE = 7;
    static final int PIDGEY = 16;
    static final int RATTATA = 19;
    static final int PIKACHU = 25;
    static final int MAGIKARP = 129;
    static final int ARCEUS = 493;
    static final int VICTINI = 494;

    static String getName(int x) {
	switch (x) {
	    case BULBASAUR:
		return "BULBASAUR";
	    case CHARMANDER:
		return "CHARMANDER";
	    case SQUIRTLE:
		return "SQUIRTLE";
	    case PIDGEY:
		return "PIDGEY";
	    case RATTATA:
		return "RATTATA";
	    case PIKACHU:
		return "PIKACHU";
	    case MAGIKARP:
		return "MAGIKARP";
	    case ARCEUS:
		return "ARCEUS";
	    case VICTINI:
		return "VICTINI";
		
	}
	return null;
    }
}
