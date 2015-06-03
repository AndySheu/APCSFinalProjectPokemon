
import java.util.ArrayList;

public class D { // D stands for Data

    private static ArrayList<int[]> stats = new ArrayList<int[]>(V.NUM_POKE);
    private static int[][] moves = new int[V.NUM_POKE][4];
    private static int[][] types = new int[V.NUM_POKE][2];
    private static Object[][] moveList = new Object[101][5];

    private static double[][] chart = {
	{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,},
	//	 0  NOR  FIG  FLY  POI  GRO  ROC  BUG  GHO  STE  FIR  WAT  GRA  ELE  PSY  ICE  DRA  DAR
	{0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.5, 1.0, 0.0, 0.5, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0}, // NORMAL
	{0, 2.0, 1.0, 0.5, 0.5, 1.0, 2.0, 0.5, 0.0, 2.0, 1.0, 1.0, 1.0, 1.0, 0.5, 2.0, 1.0, 2.0}, // FIGHTING
	{0, 1.0, 2.0, 1.0, 1.0, 1.0, 0.5, 2.0, 1.0, 0.5, 1.0, 1.0, 2.0, 0.5, 1.0, 1.0, 1.0, 1.0}, // FLYING
	{0, 1.0, 1.0, 1.0, 0.5, 0.5, 0.5, 1.0, 0.5, 0.0, 1.0, 1.0, 2.0, 1.0, 1.0, 1.0, 1.0, 1.0}, // POISON
	{0, 1.0, 1.0, 0.0, 2.0, 1.0, 2.0, 0.5, 1.0, 2.0, 2.0, 1.0, 0.5, 2.0, 1.0, 1.0, 1.0, 1.0}, // GROUND
	{0, 1.0, 0.5, 2.0, 1.0, 0.5, 1.0, 2.0, 1.0, 0.5, 2.0, 1.0, 1.0, 1.0, 1.0, 2.0, 1.0, 1.0}, // ROCK
	{0, 1.0, 0.5, 0.5, 0.5, 1.0, 1.0, 1.0, 0.5, 0.5, 0.5, 1.0, 2.0, 1.0, 2.0, 1.0, 1.0, 2.0}, // BUG
	{0, 0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 2.0, 0.5, 1.0, 1.0, 1.0, 1.0, 2.0, 1.0, 1.0, 0.5}, // GHOST
	{0, 1.0, 1.0, 1.0, 1.0, 1.0, 2.0, 1.0, 1.0, 0.5, 0.5, 0.5, 1.0, 0.5, 1.0, 2.0, 1.0, 1.0}, // STEEL
	{0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.5, 2.0, 1.0, 2.0, 0.5, 0.5, 2.0, 1.0, 1.0, 2.0, 0.5, 1.0}, // FIRE
	{0, 1.0, 1.0, 1.0, 1.0, 2.0, 2.0, 1.0, 1.0, 1.0, 2.0, 0.5, 0.5, 1.0, 1.0, 1.0, 0.5, 1.0}, // WATER
	{0, 1.0, 1.0, 0.5, 0.5, 2.0, 2.0, 0.5, 1.0, 0.5, 0.5, 2.0, 0.5, 1.0, 1.0, 1.0, 0.5, 1.0}, // GRASS
	{0, 1.0, 1.0, 2.0, 1.0, 0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 2.0, 0.5, 0.5, 1.0, 1.0, 0.5, 1.0}, // ELECTRIC
	{0, 1.0, 2.0, 1.0, 2.0, 1.0, 1.0, 1.0, 1.0, 0.5, 1.0, 1.0, 1.0, 1.0, 0.5, 1.0, 1.0, 0.0}, // PSYCHIC
	{0, 1.0, 1.0, 2.0, 1.0, 2.0, 1.0, 1.0, 1.0, 0.5, 0.5, 0.5, 2.0, 1.0, 1.0, 0.5, 2.0, 1.0}, // ICE
	{0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.5, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 2.0, 1.0}, // DRAGON
	{0, 1.0, 0.5, 1.0, 1.0, 1.0, 1.0, 1.0, 2.0, 0.5, 1.0, 1.0, 1.0, 1.0, 2.0, 1.0, 1.0, 0.5}, // DARK
    };

    static final int NULL = 0;
    static final int NORMAL = 1;
    static final int FIGHTING = 2;
    static final int FLYING = 3;
    static final int POISON = 4;
    static final int GROUND = 5;
    static final int ROCK = 6;
    static final int BUG = 7;
    static final int GHOST = 8;
    static final int STEEL = 9;
    static final int FIRE = 10;
    static final int WATER = 11;
    static final int GRASS = 12;
    static final int ELECTRIC = 13;
    static final int PSYCHIC = 14;
    static final int ICE = 15;
    static final int DRAGON = 16;
    static final int DARK = 17;

//    static final int NULL = 0; IMPLIED
    static final int TACKLE = 1;
    static final int GROWL = 2;
    static final int SCRATCH = 3;
    static final int TAIL_WHIP = 4;
    static final int FORESIGHT = 5;
    static final int THUNDERSHOCK = 6;
    static final int JUDGEMENT = 7;
    static final int SEARING_SHOT = 8;
    static final int V_CREATE = 9;
    static final int BOLT_STRIKE = 10;
    static final int PSYCHIC_ = 11; // the move, not the type
    static final int SPLASH = 12;
    static final int VINE_WHIP = 13;
    static final int EMBER = 14;
    static final int BUBBLE = 15;
    static final int WITHDRAW = 16;
//    static final int = 17;
//    static final int = 18;
//    static final int = 19;
//    static final int = 20;

    static final int ATT_UP = 1;
    static final int ATT_DOWN = 2;
    static final int DEF_UP = 3;
    static final int DEF_DOWN = 4;
    static final int SPD_UP = 5;
    static final int SPD_DOWN = 6;

    // Pre: None
    // Post: Creates all the moves and fills the stats, types, and moves for each Pokemon
    static void fill() {
	for (int i = 0; i <= V.NUM_POKE; i++) {
	    stats.add(new int[6]);
	}

	stats.set(P.BULBASAUR, new int[]{45, 49, 65, 49, 65, 45});
	stats.set(P.CHARMANDER, new int[]{39, 52, 60, 43, 50, 65});
	stats.set(P.SQUIRTLE, new int[]{44, 48, 50, 65, 64, 43});
	stats.set(P.PIDGEY, new int[]{40, 45, 35, 40, 35, 56});
	stats.set(P.RATTATA, new int[]{30, 56, 25, 35, 35, 72});
	stats.set(P.PIKACHU, new int[]{35, 55, 50, 40, 50, 90});
	stats.set(P.MAGIKARP, new int[]{20, 10, 55, 15, 20, 80});
	stats.set(P.ARCEUS, new int[]{120, 120, 120, 120, 120, 120});
	stats.set(P.VICTINI, new int[]{170, 170, 170, 170, 170, 170}); // Raised

	types[P.BULBASAUR][0] = GRASS;
	types[P.BULBASAUR][1] = POISON;
	types[P.CHARMANDER][0] = FIRE;
	types[P.SQUIRTLE][0] = WATER;
	types[P.PIDGEY][0] = NORMAL;
	types[P.PIDGEY][1] = FLYING;
	types[P.RATTATA][0] = NORMAL;
	types[P.PIKACHU][0] = ELECTRIC;
	types[P.MAGIKARP][0] = WATER;
	types[P.ARCEUS][0] = NORMAL;
	types[P.VICTINI][0] = FIRE;
	types[P.VICTINI][1] = PSYCHIC;

	moves[P.BULBASAUR] = new int[]{TACKLE, GROWL, VINE_WHIP};
	moves[P.CHARMANDER] = new int[]{SCRATCH, GROWL, EMBER};
	moves[P.SQUIRTLE] = new int[]{TACKLE, TAIL_WHIP, BUBBLE, WITHDRAW};
	moves[P.PIDGEY] = new int[]{TACKLE, FORESIGHT};
	moves[P.RATTATA] = new int[]{TACKLE, TAIL_WHIP};
	moves[P.PIKACHU] = new int[]{THUNDERSHOCK, GROWL};
	moves[P.MAGIKARP] = new int[]{SPLASH};
	moves[P.ARCEUS] = new int[]{JUDGEMENT, SPLASH};
	moves[P.VICTINI] = new int[]{SEARING_SHOT, V_CREATE, BOLT_STRIKE, PSYCHIC_};

	moveList[TACKLE] = new Object[]{"TACKLE", new Integer(50), new Integer(95), new Integer(NORMAL)};
	moveList[GROWL] = new Object[]{"GROWL", new Integer(0), new Integer(100), new Integer(NORMAL), new Integer(ATT_DOWN)};
	moveList[SCRATCH] = new Object[]{"SCRATCH", new Integer(40), new Integer(100), new Integer(NORMAL)};
	moveList[TAIL_WHIP] = new Object[]{"TAIL WHIP", new Integer(0), new Integer(100), new Integer(NORMAL), new Integer(DEF_DOWN)};
	moveList[FORESIGHT] = new Object[]{"FORESIGHT", new Integer(0), new Integer(0), new Integer(NORMAL), new Integer(0)};
	moveList[THUNDERSHOCK] = new Object[]{"THUNDERSHOCK", new Integer(40), new Integer(100), new Integer(ELECTRIC)};
	moveList[JUDGEMENT] = new Object[]{"JUDGEMENT", new Integer(100), new Integer(100), new Integer(NORMAL)};
	moveList[SEARING_SHOT] = new Object[]{"SEARING SHOT", new Integer(100), new Integer(100), new Integer(FIRE)};
	moveList[V_CREATE] = new Object[]{"V-CREATE", new Integer(180), new Integer(100), new Integer(FIRE)};
	moveList[BOLT_STRIKE] = new Object[]{"BOLT STRIKE", new Integer(130), new Integer(85), new Integer(ELECTRIC)};
	moveList[PSYCHIC_] = new Object[]{"PSYCHIC", new Integer(90), new Integer(100), new Integer(PSYCHIC)};
	moveList[SPLASH] = new Object[]{"SPLASH", new Integer(0), new Integer(100), new Integer(NORMAL), new Integer(0)};
	moveList[VINE_WHIP] = new Object[]{"VINE WHIP", new Integer(35), new Integer(100), new Integer(GRASS)};
	moveList[EMBER] = new Object[]{"EMBER", new Integer(40), new Integer(100), new Integer(FIRE)};
	moveList[BUBBLE] = new Object[]{"BUBBLE", new Integer(20), new Integer(100), new Integer(WATER), new Integer(SPD_DOWN)};
	moveList[WITHDRAW] = new Object[]{"WITHDRAW", new Integer(0), new Integer(100), new Integer(WATER), new Integer(DEF_UP)};
    }

    // Pre: int moveType, int oppType
    // Post: Returns a double that is the effectiveness of the player's current Pokemon's attacks on the opponent's current Pokemon
    static double getEffectiveness(int moveType, int oppType) {
	return chart[moveType][oppType];
    }

    // Pre: int moveType, int oppType1, int oppType2
    // Post: Returns a double that is the effectiveness of the player's current Pokemon's attacks on the opponent's current Pokemon
    static double getEffectiveness(int moveType, int oppType1, int oppType2) {
	return getEffectiveness(moveType, oppType1) * getEffectiveness(moveType, oppType2);
    }

    // Pre: int pokemon
    // Post: Returns an array of ints that contains the stats for the Pokemon given by index pokemon
    static int[] getStats(int pokemon) {
	return stats.get(pokemon);
    }
    
    // Pre: int pokemon
    // Post: Returns an array of ints that contains the indexes of Moves for the Pokemon given by index pokemon
    static int[] getMoves(int pokemon) {
	return moves[pokemon];
    }

    // Pre: int pokemon
    // Post: Returns an array of ints that contains the indexes of Types for the Pokemon given by index pokemon
    static int[] getTypes(int pokemon) {
	return types[pokemon];
    }

    // Pre: int x
    // Post: Returns a String that is the name of a Move given by int x, which is the index of the Move
    static String getNameFromIndex(int x) {
	return (String) moveList[x][0];
    }

    // Pre: int base
    // Post: Returns a String that is the name of a Move given by int base, which is the index of the Move
    static String getName(int base) {
	return (String) moveList[base][0];
    }

    // Pre: int base
    // Post: Returns an Integer that is the power of a Move given int base, which is the index of the Move
    static int getPower(int base) {
	return (Integer) moveList[base][1];
    }

    // Pre: int base
    // Post: Returns an int that is the accuracy of a Move given int base, which is the index of the Move
    static int getAcc(int base) {
	return (Integer) moveList[base][2];
    }

    // Pre: int base
    // Post: Returns an int that is the index of the Type of a Move given by int base, which is the index of the Move
    static int getType(int base) {
	return (Integer) moveList[base][3];
    }

    // Pre: int base
    // Post: Returns an int that is the index of the effect of a Move given by int base, which is the index of the Move
    static int getEffect(int base) {
	try {
	    return (Integer) moveList[base][4];
	} catch (ArrayIndexOutOfBoundsException e) {
	    return 0;
	}
    }
}
