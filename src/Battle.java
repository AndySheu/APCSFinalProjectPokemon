
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
	    playerCurr = player.getCurrent();
	    oppCurr = opp.getCurrent();

	    pokemonList.add(oppCurr);
	    pokemonList.add(playerCurr);

	    panel.updateImages(pokemonList);
	    frame.add(panel);
	    frame.repaint();

	    int oppMove = (int) (Math.random() * opp.getCurrent().getNumMoves());

	    int turnType = chooseTurnType();

	    if (turnType == 1) { // ATTACK
		int playerSpd = player.getCurrent().getSpd(), oppSpd = opp.getCurrent().getSpd();
		double rand = Math.random();
		int choice = moveChoice();
		this.choice2 = choice;
		
		if (playerSpd > oppSpd || ((playerSpd == oppSpd) && rand <= 0.5)) {

		    int ua = (int) userAttack();
		    System.out.println(player.getName() + "'s " + player.getCurrent().getName() + " used " + Move.getName(player.getCurrent().getMoves()[choice2]) + "! It did " + ua + " damage!");
		    printResults(player);
		    opp.getCurrent().health -= ua;

		    System.out.println(opp.getCurrent().getName() + ": " + opp.getCurrent().health + "/" + opp.getCurrent().getHP());

		    if (opp.getCurrent().checkFainted()) {
			System.out.println(opp.getName() + "'s " + opp.getCurrent().getName() + " is fainted!");
			opp.nextPokemon();
		    } else {
			int oa = (int) oppAttack(oppMove);
			System.out.println(opp.getName() + "'s " + opp.getCurrent().getName() + " used " + Move.getName(opp.getCurrent().getMoves()[oppMove]) + "! It did " + oa + " damage!");
			printResults(opp);
			player.getCurrent().health -= oa;

			System.out.println(player.getCurrent().getName() + ": " + player.getCurrent().health + "/" + player.getCurrent().getHP());
	
			if (player.getCurrent().checkFainted()) {
			    System.out.println(player.getName() + "'s " + player.getCurrent().getName() + " is fainted!");
			    player.nextPokemon();
			}
			
		    }
		} else {
		    int oa = (int) oppAttack(oppMove);
		    System.out.println(opp.getName() + "'s " + opp.getCurrent().getName() + " used " + Move.getName(opp.getCurrent().getMoves()[oppMove]) + "! It did " + oa + " damage!");
		    printResults(opp);
		    player.getCurrent().health -= oa;

		    System.out.println(player.getCurrent().getName() + ": " + player.getCurrent().health + "/" + player.getCurrent().getHP());

		    if (player.getCurrent().checkFainted()) {
			System.out.println(player.getName() + "'s " + player.getCurrent().getName() + " is fainted!");
			player.nextPokemon();
		    } else {

			int ua = (int) userAttack();
			System.out.println(player.getName() + "'s " + player.getCurrent().getName() + " used " + Move.getName(player.getCurrent().getMoves()[choice2]) + "! It did " + ua + " damage!");
			printResults(player);
			opp.getCurrent().health -= ua;

			System.out.println(opp.getCurrent().getName() + ": " + opp.getCurrent().health + "/" + opp.getCurrent().getHP());

			if (opp.getCurrent().checkFainted()) {
			    System.out.println(opp.getName() + "'s " + opp.getCurrent().getName() + " is fainted!");
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
	for (i = 0; i < player.getCurrent().getNumMoves() - 1; i++) {
	    System.out.print(Move.getNameFromIndex(player.getCurrent().getMoves()[i]) + ", ");
	}
	System.out.print(Move.getNameFromIndex(player.getCurrent().getMoves()[i++]) + "): ");
//	int choice = keys.nextInt();
//	if (choice >= 0 && choice <= player.getCurr().getNumMoves()) {
//	    return choice;
//	}
	String choice = keys.next();
	try {
	    int intChoice = Integer.parseInt(choice) - 1;
	    if (intChoice >= 0 && intChoice <= player.getCurrent().getNumMoves()) {
		return intChoice;
	    }
	} catch (NumberFormatException dhruvIsSometimesADuck) {
	    for (i = 0; i < player.getCurrent().getNumMoves(); i++) {
		if (choice.equals(Move.getNameFromIndex(player.getCurrent().getMoves()[i]))) {
		    return i;
		}
	    }
	}
	return moveChoice();
    }

    private double userAttack() {

	int attack = player.getCurrent().getMoves()[choice2];

	if (Move.getPower(attack) <= 0) {
	    if (Move.getPower(attack) == -1) {
		opp.getCurrent().lowerAtt();
		System.out.println(opp.getName() + "'s attack fell!");
	    }
	    if (Move.getPower(attack) == -3) {
		opp.getCurrent().lowerDef();
		System.out.println(opp.getName() + "'s defense fell!");
	    }
	    return 0;
	}

	double damage = 0;
	if (Math.random() <= Move.getAcc(attack)) { // Checks accuracy
	    damage = ((((2 * (5) / 5 + 2) * player.getCurrent().getAtt() * Move.getPower(attack) / opp.getCurrent().getDef()) / 50) + 2);
	    if (Move.getType(attack) == player.getCurrent().getType1() || Move.getType(attack) == player.getCurrent().getType2()) {
		damage *= 2;
	    }
	    if (opp.getCurrent().getType2() == -1) {
		damage *= Type.getEffectiveness(Move.getType(attack), opp.getCurrent().getType1());
		if (Type.getEffectiveness(Move.getType(attack), opp.getCurrent().getType1()) > 1) {
		    superEff = true;
		} else if (Type.getEffectiveness(Move.getType(attack), opp.getCurrent().getType1()) < 1) {
		    superEff = true;
		}
	    } else {
		damage *= Type.getEffectiveness(Move.getType(attack), opp.getCurrent().getType1(), opp.getCurrent().getType2());
		if (Type.getEffectiveness(Move.getType(attack), opp.getCurrent().getType1(), opp.getCurrent().getType2()) > 1) {
		    notEff = true;
		} else if (Type.getEffectiveness(Move.getType(attack), opp.getCurrent().getType1(), opp.getCurrent().getType2()) < 1) {
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

	int attack = opp.getCurrent().getMoves()[oppMove];

	if (Move.getPower(attack) <= 0) {
	    if (Move.getPower(attack) == -1) {
		player.getCurrent().lowerAtt();
		att = true;
	    }
	    if (Move.getPower(attack) == -3) {
		player.getCurrent().lowerDef();
		def = true;
	    }
	    return 0;
	}

	double damage = 0;

	if (Math.random()
		<= Move.getAcc(attack)) { // Checks accuracy
	    damage = ((((2 * (5) / 5 + 2) * opp.getCurrent().getAtt() * Move.getPower(attack) / player.getCurrent().getDef()) / 50) + 2);
	    if (Move.getType(attack) == opp.getCurrent().getType1() || Move.getType(attack) == opp.getCurrent().getType2()) {
		damage *= 2;
	    }
	    if (player.getCurrent().getType2() == -1) {
		damage *= Type.getEffectiveness(Move.getType(attack), player.getCurrent().getType1());
		if (Type.getEffectiveness(Move.getType(attack), player.getCurrent().getType1()) > 1) {
		    superEff = true;
		} else if (Type.getEffectiveness(Move.getType(attack), player.getCurrent().getType1()) < 1) {
		    superEff = true;
		}
	    } else {
		damage *= Type.getEffectiveness(Move.getType(attack), player.getCurrent().getType1(), player.getCurrent().getType2());
		if (Type.getEffectiveness(Move.getType(attack), player.getCurrent().getType1(), player.getCurrent().getType2()) > 1) {
		    notEff = true;
		} else if (Type.getEffectiveness(Move.getType(attack), player.getCurrent().getType1(), player.getCurrent().getType2()) < 1) {
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
	    System.out.println(p.getCurrent().getName() + "'s attack fell!");
	}
	if (def) {
	    System.out.println(p.getCurrent().getName() + "'s def fell!");
	}
	crit = false;
	miss = false;
	notEff = false;
	superEff = false;
	att = false;
	def = false;
    }
}
