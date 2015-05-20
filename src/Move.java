
import java.util.ArrayList;

public class Move {

    static ArrayList<Object[]> moveList = new ArrayList<Object[]>();

    static final int TACKLE = 0;
    static final int GROWL = 1;
    static final int SCRATCH = 2;
    static final int TAIL_WHIP = 3;
    static final int FORESIGHT = 4;
    static final int THUNDERSHOCK = 5;

    static void fill() {
	for (int i = 0; i < 10; i++) {
	    moveList.add(new String[4]);
	}

	moveList.set(TACKLE, new Object[]{"Tackle", new Integer(50), new Integer(70), new Integer(Type.NORMAL)});
	moveList.set(GROWL, new Object[]{"Growl", new Integer(0), new Integer(100), new Integer(Type.NORMAL)});
	moveList.set(SCRATCH, new Object[]{"Scratch", new Integer(40), new Integer(100), new Integer(Type.NORMAL)});
	moveList.set(TAIL_WHIP, new Object[]{"Tail Whip", new Integer(0), new Integer(100), new Integer(Type.NORMAL)});
	moveList.set(FORESIGHT, new Object[]{"Foresight", new Integer(0), new Integer(0), new Integer(Type.NORMAL)});
	moveList.set(THUNDERSHOCK, new Object[]{"Thundershock", new Integer(40), new Integer(100), new Integer(Type.ELECTRIC)});

    }

    static String getName(int base) {
	return (String) moveList.get(base)[0];
    }

    static int getPower(int base) {
	return (Integer) (moveList.get(base)[1]);
    }

    static int getAcc(int base) {
	return (Integer) (moveList.get(base)[2]);
    }

    static int getType(int base) {
	return (Integer) (moveList.get(base)[3]);
    }
}
