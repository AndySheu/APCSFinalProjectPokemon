import javax.swing.*;
import java.awt.*;
import java.util.*;

public class PokemonStats
{
    private static int[][] stats = new int[721][6];
    private static int[][] moveList = new int[721][101];
    private static Type[][] types = new Type[721][2];
    
    static int BULBASAUR = 1;
    static int CHARMANDER = 4;
    static int SQUIRTLE = 7;
    static int PIDGEY = 16;
    static int RATTATA = 19;
    static int PIKACHU = 25;
    
    static int[] getStats(int pokemon) {
	return stats[pokemon];
    }
    
    static int[] getMoveList(int pokemon) {
	return moveList[pokemon];
    }
    
    static Type[] getTypes(int pokemon) {
	return types[pokemon];
    }
}