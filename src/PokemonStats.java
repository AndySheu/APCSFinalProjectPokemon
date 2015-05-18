import javax.swing.*;
import java.awt.*;
import java.util.*;

public class PokemonStats
{
    static ArrayList<int[]> stats = new ArrayList<int[]>(25);
//    static ArrayList<int[]> moves = new ArrayList<int[]>(25);

    static int[][] moves = new int[25][4];
    private static Type[][] types = new Type[721][2];
    
    static final int BULBASAUR = 1;
    static final int CHARMANDER = 4;
    static final int SQUIRTLE = 7;
    static final int PIDGEY = 16;
    static final int RATTATA = 19;
    static final int PIKACHU = 25;
    
    static void fill() {
	for(int i = 0; i <= 25; i++) {stats.add(new int[6]);}
//	for(int i = 0; i <= 25; i++) {moves.add(new int[6]);}
	
	stats.set(BULBASAUR, new int[]{45, 49, 65, 49, 65, 100});
	stats.set(CHARMANDER, new int[]{39, 52, 60, 43, 50, 65});
	stats.set(SQUIRTLE, new int[]{44, 48, 50, 65, 64, 43});
	stats.set(PIDGEY, new int[]{40, 45, 35, 40, 35, 56});
	stats.set(RATTATA, new int[]{30, 56, 25, 35, 35, 72});
	stats.set(PIKACHU, new int[]{35, 55, 50, 40, 50, 90});
	
//	moves.set(BULBASAUR, new int[]{Move.TACKLE});
	moves[BULBASAUR][0] = Move.TACKLE;
    }
    
    static int[] getStats(int pokemon) {
	return stats.get(pokemon);
    }
    
    static int[] getMoves(int pokemon) {
	return moves[pokemon];
    }
    
    static Type[] getTypes(int pokemon) {
	return types[pokemon];
    }
}