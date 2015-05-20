
import java.util.ArrayList;

public class Move {

    static ArrayList<Object[]> moveList = new ArrayList<Object[]>();

    static final int TACKLE = 1;
    static final int GROWL = 2;
    static final int SCRATCH = 3;
    static final int TAIL_WHIP = 4;
    static final int FORESIGHT = 5;
    static final int THUNDERSHOCK = 6;

    static void fill() {
	for (int i = 0; i < 10; i++) {
	    moveList.add(new String[4]);
	}

	moveList.set(TACKLE, new Object[]{"TACKLE", new Integer(50), new Integer(70), new Integer(Type.NORMAL)});
	moveList.set(GROWL, new Object[]{"GROWL", new Integer(-1), new Integer(100), new Integer(Type.NORMAL)});
	moveList.set(SCRATCH, new Object[]{"SCRATCH", new Integer(40), new Integer(100), new Integer(Type.NORMAL)});
	moveList.set(TAIL_WHIP, new Object[]{"TAIL_WHIP", new Integer(-3), new Integer(100), new Integer(Type.NORMAL)});
	moveList.set(FORESIGHT, new Object[]{"FORESIGHT", new Integer(0), new Integer(0), new Integer(Type.NORMAL)});
	moveList.set(THUNDERSHOCK, new Object[]{"THUNDERSHOCK", new Integer(40), new Integer(100), new Integer(Type.ELECTRIC)});

    }

    static String getName(int base) {
	return (String) moveList.get(base)[0];
    }

    static String getNameFromIndex(int x) {
	switch (x) {
	    case TACKLE:
		return "TACKLE";
	    case GROWL:
		return "GROWL";
	    case SCRATCH:
		return "SCRATCH";
	    case TAIL_WHIP:
		return "TAIL_WHIP";
	    case FORESIGHT:
		return "FORESIGHT";
	    case THUNDERSHOCK:
		return "THUNDERSHOCK";
	}
	return null;
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
