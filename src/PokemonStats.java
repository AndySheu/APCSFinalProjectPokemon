
import javax.swing.*;
import java.awt.*;
import java.util.*;

public class PokemonStats {

    static ArrayList<int[]> stats = new ArrayList<int[]>(25);
//    static ArrayList<int[]> moves = new ArrayList<int[]>(25);

    static int[][] moves = new int[26][4];
    private static int[][] types = new int[721][2];

    static final int BULBASAUR = 1;
    static final int CHARMANDER = 4;
    static final int SQUIRTLE = 7;
    static final int PIDGEY = 16;
    static final int RATTATA = 19;
    static final int PIKACHU = 25;

    static void fill() {
	for (int i = 0; i <= 25; i++) {
	    stats.add(new int[6]);
	}
//	for(int i = 0; i <= 25; i++) {moves.add(new int[6]);}

	stats.set(BULBASAUR, new int[]{45, 49, 65, 49, 65, 45});
	stats.set(CHARMANDER, new int[]{39, 52, 60, 43, 50, 65});
	stats.set(SQUIRTLE, new int[]{44, 48, 50, 65, 64, 43});
	stats.set(PIDGEY, new int[]{40, 45, 35, 40, 35, 56});
	stats.set(RATTATA, new int[]{30, 56, 25, 35, 35, 72});
	stats.set(PIKACHU, new int[]{35, 55, 50, 40, 50, 90});

	types[BULBASAUR][0] = Type.GRASS;
	types[BULBASAUR][1] = Type.POISON;
	types[CHARMANDER][0] = Type.FIRE;
	types[SQUIRTLE][0] = Type.WATER;
	types[SQUIRTLE][1] = -1;
	types[PIDGEY][0] = Type.NORMAL;
	types[PIDGEY][1] = Type.FLYING;
	types[RATTATA][0] = Type.NORMAL;
	types[PIKACHU][0] = Type.ELECTRIC;
	types[PIKACHU][1] = -1;

	moves[BULBASAUR][0] = Move.TACKLE;
	moves[BULBASAUR][1] = Move.GROWL;
	moves[CHARMANDER][0] = Move.SCRATCH;
	moves[CHARMANDER][1] = Move.GROWL;
	moves[SQUIRTLE][0] = Move.TACKLE;
	moves[SQUIRTLE][1] = Move.TAIL_WHIP;
	moves[PIDGEY][0] = Move.TACKLE;
	moves[PIDGEY][1] = Move.FORESIGHT;
	moves[RATTATA][0] = Move.TACKLE;
	moves[RATTATA][1] = Move.TAIL_WHIP;
	moves[PIKACHU][0] = Move.THUNDERSHOCK;
	moves[PIKACHU][1] = Move.GROWL;

//	moves.set(BULBASAUR, new int[]{Move.TACKLE});
    }

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

    static int[] getStats(int pokemon) {
	return stats.get(pokemon);
    }

    static int[] getMoves(int pokemon) {
	return moves[pokemon];
    }

    static int[] getTypes(int pokemon) {
	return types[pokemon];
    }
}
