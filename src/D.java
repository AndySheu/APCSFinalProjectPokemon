
import java.util.ArrayList;

public class D { // D stands for Data
    
    private static ArrayList<int[]> stats = new ArrayList<int[]>(V.NUM_POKE);
    private static int[][] moves = new int[V.NUM_POKE][4];
    private static int[][] types = new int[V.NUM_POKE][2];

    static void fill() {
	for (int i = 0; i <= V.NUM_POKE; i++) {
	    stats.add(new int[6]);
	}

	stats.set(P.BULBASAUR,	new int[]{45, 49, 65, 49, 65, 45});
	stats.set(P.CHARMANDER,	new int[]{39, 52, 60, 43, 50, 65});
	stats.set(P.SQUIRTLE,	new int[]{44, 48, 50, 65, 64, 43});
	stats.set(P.PIDGEY,	new int[]{40, 45, 35, 40, 35, 56});
	stats.set(P.RATTATA,	new int[]{30, 56, 25, 35, 35, 72});
	stats.set(P.PIKACHU,	new int[]{35, 55, 50, 40, 50, 90});

	types[P.BULBASAUR][0] = Type.GRASS; types[P.BULBASAUR][1] = Type.POISON;
	types[P.CHARMANDER][0] = Type.FIRE;
	types[P.SQUIRTLE][0] = Type.WATER;
	types[P.PIDGEY][0] = Type.NORMAL; types[P.PIDGEY][1] = Type.FLYING;
	types[P.RATTATA][0] = Type.NORMAL;
	types[P.PIKACHU][0] = Type.ELECTRIC;

	moves[P.BULBASAUR] =	new int[]{Move.THUNDERSHOCK, Move.FORESIGHT};
	moves[P.CHARMANDER] =	new int[]{Move.SCRATCH, Move.GROWL};
	moves[P.SQUIRTLE] =	new int[]{Move.TACKLE, Move.TAIL_WHIP};
	moves[P.PIDGEY] =	new int[]{Move.TACKLE, Move.FORESIGHT};
	moves[P.RATTATA] =	new int[]{Move.TACKLE, Move.TAIL_WHIP};
	moves[P.PIKACHU] =	new int[]{Move.THUNDERSHOCK, Move.GROWL};
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