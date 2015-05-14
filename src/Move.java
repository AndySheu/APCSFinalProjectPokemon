
import java.util.ArrayList;

public class Move {

    private boolean physical;
    private int power;
    private int accuracy;
    Type type;

    public Move(boolean physical, int power, int accuracy, Type type) {
	this.physical = physical;
	this.power = power;
	this.accuracy = accuracy;
	this.type = type;
    }

    public int getPower() {
	return power;
    }

    public int getAccuracy() {
	return accuracy;
    }

    public Type getType() {
	return type;
    }
}
