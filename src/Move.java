
import java.util.ArrayList;

public class Move {

    static ArrayList<Object[]> moveList = new ArrayList<Object[]>();
    
    static final int TACKLE = 0;
    
    static void fill() {
	for(int i = 0; i < 10; i++) {
	    moveList.add(new String[4]);
	}
	
	moveList.set(TACKLE, new Object[]{"Tackle",new Integer(50),new Integer(70),new Integer(Type.NORMAL)});
    }
    
    static int getPower(int base) {
	return (Integer)(moveList.get(base)[1]);
    }
    
    static int getAcc(int base) {
	return (Integer)(moveList.get(base)[2]);
    }
    
    static int getType(int base) {
	return (Integer)(moveList.get(base)[3]);
    }
}
