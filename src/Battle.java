
import java.util.Scanner;

public class Battle {

    Scanner keys = new Scanner(System.in);
    Player player, opp;
    int choice2;

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
		this.choice2 = choice;
		if (playerSpd > oppSpd || ((playerSpd == oppSpd) && rand <= 0.5)) {
		    userAttack();
		    if(opp.getCurr().checkFainted()) {
			opp.nextPokemon();
		    }
		    
		    if(player.getCurr().checkFainted()) {
			player.nextPokemon();
		    }
		} else {
		    
		    if(player.getCurr().checkFainted()) {
			player.nextPokemon();
		    }
		    
		    if(opp.getCurr().checkFainted()) {
			opp.nextPokemon();
		    }
		}
	    }
	}
	if (player.checkLoss()) {
	    return player;
	}
	return opp;
    }

    private int choice1() {
	System.out.println("Choice 1: Press 1 to Attack");
	int choice = keys.nextInt();
	return choice;
    }

    // Returns value from 0 to 3
    private int choice2() {
	System.out.println("Choice 2: Choose Move");
	int choice = keys.nextInt();
	if (choice >= 0 && choice <= 3)
	    return choice;
	return choice2();
    }
    
    private void userAttack() {
	int attack = player.getCurr().getMoves()[choice2], damage, modifier = 1;
	if (Math.random() <= Move.getAcc(attack)) { // Checks accuracy
	    damage = (((2*5+10)/250) * (player.getCurr().getAtt() / opp.getCurr().getDef()) * Move.getPower(attack) + 2);
	    if (Move.getType(attack) == player.getCurr().getType1() || Move.getType(attack) == player.getCurr().getType2()) {
		modifier *= 2;
	    }
	    if (opp.getCurr().getType2() == -1) {
		modifier *= Type.getEffectiveness(Move.getType(attack), player.getCurr().getType1());
	    } else {
		modifier *= Type.getEffectiveness(Move.getType(attack), player.getCurr().getType1(), player.getCurr().getType2());
	    }
	    if (Math.random() <= 0.0625) {
		modifier *= 1.5;
	    }
	    modifier *= Math.random() / 100 * 15 + 0.85;
	}
    }
}
