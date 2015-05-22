
public class P { // Pokemon

    static final int BULBASAUR = 1;
    static final int CHARMANDER = 4;
    static final int SQUIRTLE = 7;
    static final int PIDGEY = 16;
    static final int RATTATA = 19;
    static final int PIKACHU = 25;

    static String getName(int x) {
	switch (x) {
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
}
