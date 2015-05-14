public class Type
{
    private static double[][] chart = {
        {1.0, 1.0, 1.0, 1.0, 1.0, 0.5, 1.0, 0.0, 0.5, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0}, // NORMAL
	{2.0, 1.0, 0.5, 0.5, 1.0, 2.0, 0.5, 0.0, 2.0, 1.0, 1.0, 1.0, 1.0, 0.5, 2.0, 1.0, 2.0}, // FIGHTING
	{1.0, 2.0, 1.0, 1.0, 1.0, 0.5, 2.0, 1.0, 0.5, 1.0, 1.0, 2.0, 0.5, 1.0, 1.0, 1.0, 1.0}, // FLYING
	{1.0, 1.0, 1.0, 0.5, 0.5, 0.5, 1.0, 0.5, 0.0, 1.0, 1.0, 2.0, 1.0, 1.0, 1.0, 1.0, 1.0}, // POISON
	{1.0, 1.0, 0.0, 2.0, 1.0, 2.0, 0.5, 1.0, 2.0, 2.0, 1.0, 0.5, 2.0, 1.0, 1.0, 1.0, 1.0}, // GROUND
	{1.0, 0.5, 2.0, 1.0, 0.5, 1.0, 2.0, 1.0, 0.5, 2.0, 1.0, 1.0, 1.0, 1.0, 2.0, 1.0, 1.0}, // ROCK
	{1.0, 0.5, 0.5, 0.5, 1.0, 1.0, 1.0, 0.5, 0.5, 0.5, 1.0, 2.0, 1.0, 2.0, 1.0, 1.0, 2.0}, // BUG
	{0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 2.0, 0.5, 1.0, 1.0, 1.0, 1.0, 2.0, 1.0, 1.0, 0.5}, // GHOST
	{1.0, 1.0, 1.0, 1.0, 1.0, 2.0, 1.0, 1.0, 0.5, 0.5, 0.5, 1.0, 0.5, 1.0, 2.0, 1.0, 1.0}, // STEEL
	{1.0, 1.0, 1.0, 1.0, 1.0, 0.5, 2.0, 1.0, 2.0, 0.5, 0.5, 2.0, 1.0, 1.0, 2.0, 0.5, 1.0}, // FIRE
	{1.0, 1.0, 1.0, 1.0, 2.0, 2.0, 1.0, 1.0, 1.0, 2.0, 0.5, 0.5, 1.0, 1.0, 1.0, 0.5, 1.0}, // WATER
	{1.0, 1.0, 0.5, 0.5, 2.0, 2.0, 0.5, 1.0, 0.5, 0.5, 2.0, 0.5, 1.0, 1.0, 1.0, 0.5, 1.0}, // GRASS
	{1.0, 1.0, 2.0, 1.0, 0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 2.0, 0.5, 0.5, 1.0, 1.0, 0.5, 1.0}, // ELECTRIC
	{1.0, 2.0, 1.0, 2.0, 1.0, 1.0, 1.0, 1.0, 0.5, 1.0, 1.0, 1.0, 1.0, 0.5, 1.0, 1.0, 0.0}, // PSYCHIC
	{1.0, 1.0, 2.0, 1.0, 2.0, 1.0, 1.0, 1.0, 0.5, 0.5, 0.5, 2.0, 1.0, 1.0, 0.5, 2.0, 1.0}, // ICE
	{1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.5, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 2.0, 1.0}, // DRAGON
	{1.0, 0.5, 1.0, 1.0, 1.0, 1.0, 1.0, 2.0, 0.5, 1.0, 1.0, 1.0, 1.0, 2.0, 1.0, 1.0, 0.5}, // DARK
    };
	    
    static final int NORMAL = 0;
    static final int FIGHTING = 1;
    static final int FLYING = 2;
    static final int POISON = 3;
    static final int GROUND = 4;
    static final int ROCK = 5;
    static final int BUG = 6;
    static final int GHOST = 7;
    static final int STEEL = 8;
    static final int FIRE = 9;
    static final int WATER = 10;
    static final int GRASS = 11;
    static final int ELECTRIC = 12;
    static final int PSYCHIC= 13;
    static final int ICE = 14;
    static final int DRAGON = 15;
    static final int DARK = 16;

    public static double getEffectiveness(int moveType, int oppType)
    {
        return chart[moveType][oppType];
    }

    public static double getEffectiveness(int moveType, int oppType1, int oppType2) 
    {
        return getEffectiveness(moveType, oppType1) * getEffectiveness(moveType, oppType2);
    }
}
