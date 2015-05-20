
import java.util.Scanner;

public class Battle {

    Scanner keys = new Scanner(System.in);
    Player player, opp;
    int choice2;
    
    boolean crit = false, miss = false, notEff = false, superEff = false;

    public Battle(Player player, Player opp) {
	this.player = player;
	this.opp = opp;
    }

    // @ returns loser
    public Player run() {
	while (!player.checkLoss() && !opp.checkLoss()) {
	    int oppMove = (int) (Math.random() * 4);
//	    if (choice1() == 1) {
	    if (1==1) {
		int playerSpd = player.getCurr().getSpd(), oppSpd = opp.getCurr().getSpd();
		double rand = Math.random();
		int choice = choice2();
		this.choice2 = choice;
		if (playerSpd > oppSpd || ((playerSpd == oppSpd) && rand <= 0.5)) {

		    int ua = (int) userAttack();
		    System.out.println("Player's pokémon used " + Move.getName(player.getCurr().getMoves()[choice2]) + "! It did " + ua + " damage!");
		    opp.getCurr().health -= ua;
		    
		    System.out.println("Opponent: " + opp.getCurr().health + "/" + opp.getCurr().getHP());

		    if (opp.getCurr().checkFainted()) {
			System.out.println("Opponent's pokémon is fainted!");
			opp.nextPokemon();
		    }

		    int oa = (int) oppAttack(oppMove);
		    System.out.println("Opponent's pokémon used " + Move.getName(opp.getCurr().getMoves()[oppMove]) + "! It did " + oa + " damage!");
		    player.getCurr().health -= oa;

		    System.out.println("Player: " + player.getCurr().health + "/" + player.getCurr().getHP());
		    
		    if (player.getCurr().checkFainted()) {
			System.out.println("Player's pokémon is fainted!");
			player.nextPokemon();
		    }
		} else {
		    System.out.println("else");
		    if (player.getCurr().checkFainted()) {
			player.nextPokemon();
		    }

		    if (opp.getCurr().checkFainted()) {
			opp.nextPokemon();
		    }
		}
	    }

	}
	System.out.print("GAME OVER: ");
	if (player.checkLoss()) {
	    System.out.println("PLAYER LOSES");
	    return player;
	}
	System.out.println("OPPONENT LOSES");
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
	if (choice >= 0 && choice <= 3) {
	    return choice;
	}
	return choice2();
    }

    private double userAttack() {

	int attack = player.getCurr().getMoves()[choice2];

	if (Move.getPower(attack) == 0) {
	    return 0;
	}

	double damage = 0;
	if (Math.random() <= Move.getAcc(attack)) { // Checks accuracy
	    damage = ((((2 * (5) / 5 + 2) * player.getCurr().getAtt() * Move.getPower(attack) / opp.getCurr().getDef()) / 50) + 2);
	    if (Move.getType(attack) == player.getCurr().getType1() || Move.getType(attack) == player.getCurr().getType2()) {
		damage *= 2;
	    }
	    if (opp.getCurr().getType2() == -1) {
		damage *= Type.getEffectiveness(Move.getType(attack), player.getCurr().getType1());
	    } else {
		damage *= Type.getEffectiveness(Move.getType(attack), player.getCurr().getType1(), player.getCurr().getType2());
	    }
	    if (Math.random() <= 0.0625) {
		System.out.println("CRITICAL HIT!");
		damage *= 1.5;
	    }
	    damage *= Math.random() / 100 * 15 + 0.85;
	} else {
	    System.out.println("MISSED!");
	}
	return damage;
    }

    private double oppAttack(int oppMove) {

	int attack = opp.getCurr().getMoves()[oppMove];

	if (Move.getPower(attack) == 0) {
	    System.out.println("A - : " + attack);
	    return 0;
	}

	double damage = 0;
	if (Math.random() <= Move.getAcc(attack)) { // Checks accuracy
	    damage = ((((2 * (5) / 5 + 2) * opp.getCurr().getAtt() * Move.getPower(attack) / player.getCurr().getDef()) / 50) + 2);
	    System.out.println(damage);
	    if (Move.getType(attack) == opp.getCurr().getType1() || Move.getType(attack) == opp.getCurr().getType2()) {
		damage *= 2;
	    }
	    if (player.getCurr().getType2() == -1) {
		damage *= Type.getEffectiveness(Move.getType(attack), opp.getCurr().getType1());
	    } else {
		damage *= Type.getEffectiveness(Move.getType(attack), opp.getCurr().getType1(), opp.getCurr().getType2());
	    }
	    if (Math.random() <= 0.0625) {
		System.out.println("CRITICAL HIT!");
		damage *= 1.5;
	    }
	    damage *= Math.random() / 100 * 15 + 0.85;
	} else {
	    System.out.println("MISSED!");
	}
	return damage;
    }
    
    private void printResults() {
	if(miss)
	    System.out.println("The attack MISSED!");
	if(superEff)
	    System.out.println("Super Effective!");
	if(notEff)
	    System.out.println("Not Very Effective...");
	if(crit)
	    System.out.println("Critical Hit!");
	crit = false;
	miss = false;
	notEff = false;
	superEff = false;
    }
}
