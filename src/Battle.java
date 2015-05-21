
import java.util.Scanner;
import java.util.ArrayList;
import javax.swing.JFrame;

public class Battle {

    Scanner keys = new Scanner(System.in);
    Player player, opp;
    int choice2;
    ArrayList<Pokemon> pokemonList;
    ImagePanel panel;
    JFrame frame;

    boolean crit = false, miss = false, notEff = false, superEff = false, att = false, def = false;

    public Battle(Player player, Player opp, ImagePanel ip, JFrame f) {
	this.player = player;
	this.opp = opp;
	this.panel = ip;
	this.frame = f;
	frame.repaint();
	pokemonList = new ArrayList<Pokemon>();
    }

    // @ returns loser
    public Player run() {
	Pokemon playerCurr, oppCurr;

	while (!player.checkLoss() && !opp.checkLoss()) {
	    System.out.println();
	    playerCurr = player.getCurr();
	    oppCurr = opp.getCurr();

	    pokemonList.add(oppCurr);
	    pokemonList.add(playerCurr);

	    panel.updateImages(pokemonList);
	    frame.add(panel);
	    frame.repaint();

	    int oppMove = (int) (Math.random() * opp.getCurr().getNumMoves());

	    int turnType = chooseTurnType();

	    if (turnType == 1) { // ATTACK
		int playerSpd = player.getCurr().getSpd(), oppSpd = opp.getCurr().getSpd();
		double rand = Math.random();
		int choice = moveChoice();
		this.choice2 = choice;
		
		if (playerSpd > oppSpd || ((playerSpd == oppSpd) && rand <= 0.5)) {

		    int ua = (int) userAttack();
		    System.out.println(player.getName() + "'s " + player.getCurr().getName() + " used " + Move.getName(player.getCurr().getMoves()[choice2]) + "! It did " + ua + " damage!");
		    printResults(player);
		    opp.getCurr().health -= ua;

		    System.out.println(opp.getCurr().getName() + ": " + opp.getCurr().health + "/" + opp.getCurr().getHP());

		    if (opp.getCurr().checkFainted()) {
			System.out.println(opp.getName() + "'s " + opp.getCurr().getName() + " is fainted!");
			opp.nextPokemon();
		    } else {
			int oa = (int) oppAttack(oppMove);
			System.out.println(opp.getName() + "'s " + opp.getCurr().getName() + " used " + Move.getName(opp.getCurr().getMoves()[oppMove]) + "! It did " + oa + " damage!");
			printResults(opp);
			player.getCurr().health -= oa;

			System.out.println(player.getCurr().getName() + ": " + player.getCurr().health + "/" + player.getCurr().getHP());
	
			if (player.getCurr().checkFainted()) {
			    System.out.println(player.getName() + "'s " + player.getCurr().getName() + " is fainted!");
			    player.nextPokemon();
			}
			
		    }
		} else {
		    int oa = (int) oppAttack(oppMove);
		    System.out.println(opp.getName() + "'s " + opp.getCurr().getName() + " used " + Move.getName(opp.getCurr().getMoves()[oppMove]) + "! It did " + oa + " damage!");
		    printResults(opp);
		    player.getCurr().health -= oa;

		    System.out.println(player.getCurr().getName() + ": " + player.getCurr().health + "/" + player.getCurr().getHP());

		    if (player.getCurr().checkFainted()) {
			System.out.println(player.getName() + "'s " + player.getCurr().getName() + " is fainted!");
			player.nextPokemon();
		    } else {

			int ua = (int) userAttack();
			System.out.println(player.getName() + "'s " + player.getCurr().getName() + " used " + Move.getName(player.getCurr().getMoves()[choice2]) + "! It did " + ua + " damage!");
			printResults(player);
			opp.getCurr().health -= ua;

			System.out.println(opp.getCurr().getName() + ": " + opp.getCurr().health + "/" + opp.getCurr().getHP());

			if (opp.getCurr().checkFainted()) {
			    System.out.println(opp.getName() + "'s " + opp.getCurr().getName() + " is fainted!");
			    opp.nextPokemon();
			}
		    }
		} 
	    } else if (turnType == 2) { // BAG
		bagChoice();
	    } else if (turnType == 3) { // RUN
		System.out.println("You can't run from a trainer battle!");
		System.out.println("Don't be a coward, " + player.getName() +"!");
	    }
	    System.out.println();
	}

	System.out.print("GAME OVER: ");
	if (player.checkLoss()) {
	    System.out.println(opp.getName() + " WINS!!!");
	    System.out.println(player.getName() + " LOSES");
	    return player;
	}

	System.out.println(player.getName() + " WINS!!!");
	System.out.println(opp.getName() + " LOSES");
	return opp;
    }

    private int chooseTurnType() {
	System.out.println("ATTACK, BAG, OR RUN");
	String choice = keys.next();
	try {
	    int intChoice = Integer.parseInt(choice);
	    if (intChoice >= 1 && intChoice <= 3) {
		return intChoice;
	    }
	} catch (NumberFormatException mrBollhorstIsCool) {
	    if (choice.equals("ATTACK")) {
		return 1;
	    } else if (choice.equals("BAG")) {
		return 2;
	    } else if (choice.equals("RUN")) {
		return 3;
	    }
	}
	return chooseTurnType();
    }

    private int bagChoice() {
	System.out.println("Oh. You don't have any items!");
	return -1;
    }

    private int moveChoice() {
	System.out.print("Please choose your move (");
	int i;
	for (i = 0; i < player.getCurr().getNumMoves() - 1; i++) {
	    System.out.print(Move.getNameFromIndex(player.getCurr().getMoves()[i]) + ", ");
	}
	System.out.print(Move.getNameFromIndex(player.getCurr().getMoves()[i++]) + "): ");
//	int choice = keys.nextInt();
//	if (choice >= 0 && choice <= player.getCurr().getNumMoves()) {
//	    return choice;
//	}
	String choice = keys.next();
	try {
	    int intChoice = Integer.parseInt(choice) - 1;
	    if (intChoice >= 0 && intChoice <= player.getCurr().getNumMoves()) {
		return intChoice;
	    }
	} catch (NumberFormatException dhruvIsSometimesADuck) {
	    for (i = 0; i < player.getCurr().getNumMoves(); i++) {
		if (choice.equals(Move.getNameFromIndex(player.getCurr().getMoves()[i]))) {
		    return i;
		}
	    }
	}
	return moveChoice();
    }

    private double userAttack() {

	int attack = player.getCurr().getMoves()[choice2];

	if (Move.getPower(attack) <= 0) {
	    if (Move.getPower(attack) == -1) {
		opp.getCurr().lowerAtt();
		System.out.println(opp.getName() + "'s attack fell!");
	    }
	    if (Move.getPower(attack) == -3) {
		opp.getCurr().lowerDef();
		System.out.println(opp.getName() + "'s defense fell!");
	    }
	    return 0;
	}

	double damage = 0;
	if (Math.random() <= Move.getAcc(attack)) { // Checks accuracy
	    damage = ((((2 * (5) / 5 + 2) * player.getCurr().getAtt() * Move.getPower(attack) / opp.getCurr().getDef()) / 50) + 2);
	    if (Move.getType(attack) == player.getCurr().getType1() || Move.getType(attack) == player.getCurr().getType2()) {
		damage *= 2;
	    }
	    if (opp.getCurr().getType2() == -1) {
		damage *= Type.getEffectiveness(Move.getType(attack), opp.getCurr().getType1());
		if (Type.getEffectiveness(Move.getType(attack), opp.getCurr().getType1()) > 1) {
		    superEff = true;
		} else if (Type.getEffectiveness(Move.getType(attack), opp.getCurr().getType1()) < 1) {
		    superEff = true;
		}
	    } else {
		damage *= Type.getEffectiveness(Move.getType(attack), opp.getCurr().getType1(), opp.getCurr().getType2());
		if (Type.getEffectiveness(Move.getType(attack), opp.getCurr().getType1(), opp.getCurr().getType2()) > 1) {
		    notEff = true;
		} else if (Type.getEffectiveness(Move.getType(attack), opp.getCurr().getType1(), opp.getCurr().getType2()) < 1) {
		    notEff = true;
		}
	    }
	    if (Math.random() <= 0.0625) {
		crit = true;
		damage *= 1.5;
	    }
	    damage *= Math.random() / 100 * 15 + 0.85;
	} else {
	    miss = true;
	}
	return damage;
    }

    private double oppAttack(int oppMove) {

	int attack = opp.getCurr().getMoves()[oppMove];

	if (Move.getPower(attack) <= 0) {
	    if (Move.getPower(attack) == -1) {
		player.getCurr().lowerAtt();
		att = true;
	    }
	    if (Move.getPower(attack) == -3) {
		player.getCurr().lowerDef();
		def = true;
	    }
	    return 0;
	}

	double damage = 0;

	if (Math.random()
		<= Move.getAcc(attack)) { // Checks accuracy
	    damage = ((((2 * (5) / 5 + 2) * opp.getCurr().getAtt() * Move.getPower(attack) / player.getCurr().getDef()) / 50) + 2);
	    if (Move.getType(attack) == opp.getCurr().getType1() || Move.getType(attack) == opp.getCurr().getType2()) {
		damage *= 2;
	    }
	    if (player.getCurr().getType2() == -1) {
		damage *= Type.getEffectiveness(Move.getType(attack), player.getCurr().getType1());
		if (Type.getEffectiveness(Move.getType(attack), player.getCurr().getType1()) > 1) {
		    superEff = true;
		} else if (Type.getEffectiveness(Move.getType(attack), player.getCurr().getType1()) < 1) {
		    superEff = true;
		}
	    } else {
		damage *= Type.getEffectiveness(Move.getType(attack), player.getCurr().getType1(), player.getCurr().getType2());
		if (Type.getEffectiveness(Move.getType(attack), player.getCurr().getType1(), player.getCurr().getType2()) > 1) {
		    notEff = true;
		} else if (Type.getEffectiveness(Move.getType(attack), player.getCurr().getType1(), player.getCurr().getType2()) < 1) {
		    notEff = true;
		}
	    }
	    if (Math.random() <= 0.0625) {
		crit = true;
		damage *= 1.5;
	    }
	    damage *= Math.random() / 100 * 15 + 0.85;
	} else {
	    miss = true;
	}
	return damage;
    }

    private void printResults(Player p) {
	if (miss) {
	    System.out.println("The attack MISSED!");
	}
	if (superEff) {
	    System.out.println("Super Effective!");
	}
	if (notEff) {
	    System.out.println("Not Very Effective...");
	}
	if (crit) {
	    System.out.println("Critical Hit!");
	}
	if (att) {
	    System.out.println(p.getCurr().getName() + "'s attack fell!");
	}
	if (def) {
	    System.out.println(p.getCurr().getName() + "'s def fell!");
	}
	crit = false;
	miss = false;
	notEff = false;
	superEff = false;
	att = false;
	def = false;
    }
}
