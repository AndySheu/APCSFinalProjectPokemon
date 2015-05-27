/*
TODO:
Thread Animations
MouseListener
GUI         --- YES
Status Effects
Back button --- YES
Music reset --- YES
*/


public class Main {

    public static void main(String[] args) {
	new Main();
    }

    public Main() {
	Init.initUI();
	Init.titleScreen();

	if (!V.TESTING) {
	    Init.playerInit();
	} else {
	    Init.playerAutoInit();
	}

	Init.startBattle();
    }

}
