
import java.util.Scanner;

public class Battle {

    Scanner keys = new Scanner(System.in);
    Player player, opp;

    public Battle(Player player, Player opp) {
	this.player = player;
	this.opp = opp;
    }

    public Player run() {
	while (!player.checkLoss() && !opp.checkLoss()) {
	    if (choice1() == 1) {
		int playerSpd = player.getCurr().getSpd(), oppSpd = opp.getCurr().getSpd();
		double rand = Math.random();
		int choice = choice2();
		System.out.println(choice);
		if (playerSpd > oppSpd || rand <= 0.5) {

		} else {

		}
	    }
	}
	if (player.checkLoss()) {
	    return player;
	}
	return opp;
    }

    private int choice1() {
	System.out.println("Choice 1:");
	int choice = keys.nextInt();
	return choice;
    }

    private int choice2() {
	System.out.println("Choice 2:");
	int choice = keys.nextInt();
	return choice;
    }
}
