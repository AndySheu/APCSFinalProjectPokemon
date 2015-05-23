
public class Battle {

    boolean crit = false, miss = false, notEff = false, superEff = false, att = false, def = false;
    int attackChoice;

    public Battle() {

    }

    // @ returns loser
    public Player run() {
	Pokemon playerCurr, oppCurr;

	while (!V.player.checkLoss() && !V.opp.checkLoss()) {
	    System.out.println();

	    // TODO Graphics
//	    pokemonList.add(oppCurr);
//	    pokemonList.add(playerCurr);
//
//	    V.panel.updateImages(pokemonList);
//	    V.frame.add(V.panel);
//	    V.frame.repaint();
	    int oppMove = (int) (Math.random() * V.opp.getCurrent().getNumMoves());
	    int turnType = chooseTurnType();

	    if (turnType == 1) { // ATTACK
		int playerSpd = V.player.getCurrent().getSpd(), oppSpd = V.opp.getCurrent().getSpd();
		double rand = Math.random();
		int choice = moveChoice();
		attackChoice = choice;

		if (playerSpd > oppSpd || ((playerSpd == oppSpd) && rand <= 0.5)) {

		    int userAttack = (int) userAttack();
		    System.out.println(V.player.getName() + "'s " + V.player.getCurrent().getName() + " used " + D.getName(V.player.getCurrent().getMoves()[attackChoice]) + "! It did " + userAttack + " damage!");
		    printResults(V.player, V.opp);
		    V.opp.getCurrent().health -= userAttack;

		    System.out.println(V.opp.getName() + "'s " + V.opp.getCurrent().getName() + ": " + V.opp.getCurrent().health + "/" + V.opp.getCurrent().getHP());

		    if (V.opp.getCurrent().checkFainted()) {
			System.out.println(V.opp.getName() + "'s " + V.opp.getCurrent().getName() + " fainted!");
			V.opp.nextPokemon();
			if (!V.opp.checkLoss()) {
			    System.out.println(V.opp.getName() + " sends out " + V.opp.getCurrent().getName() + "!");
			}
		    } else {
			int oppAttack = (int) oppAttack(oppMove);
			System.out.println(V.opp.getName() + "'s " + V.opp.getCurrent().getName() + " used " + D.getName(V.opp.getCurrent().getMoves()[oppMove]) + "! It did " + oppAttack + " damage!");
			printResults(V.opp, V.player);
			V.player.getCurrent().health -= oppAttack;

			System.out.println(V.player.getName() + "'s " + V.player.getCurrent().getName() + ": " + V.player.getCurrent().health + "/" + V.player.getCurrent().getHP());

			if (V.player.getCurrent().checkFainted()) {
			    System.out.println(V.player.getName() + "'s " + V.player.getCurrent().getName() + " fainted!");
			    V.player.nextPokemon();
			    if (!V.player.checkLoss()) {
				System.out.println(V.player.getName() + " sends out " + V.player.getCurrent().getName() + "!");
			    }
			}

		    }
		} else {
		    int oppAttack = (int) oppAttack(oppMove);
		    System.out.println(V.opp.getName() + "'s " + V.opp.getCurrent().getName() + " used " + D.getName(V.opp.getCurrent().getMoves()[oppMove]) + "! It did " + oppAttack + " damage!");
		    printResults(V.opp, V.player);
		    V.player.getCurrent().health -= oppAttack;

		    System.out.println(V.player.getName() + "'s " + V.player.getCurrent().getName() + ": " + V.player.getCurrent().health + "/" + V.player.getCurrent().getHP());

		    if (V.player.getCurrent().checkFainted()) {
			System.out.println(V.player.getName() + "'s " + V.player.getCurrent().getName() + " fainted!");
			V.player.nextPokemon();
			if (!V.player.checkLoss()) {
			    System.out.println(V.player.getName() + " sends out " + V.player.getCurrent().getName() + "!");
			}
		    } else {

			int userAttack = (int) userAttack();
			System.out.println(V.player.getName() + "'s " + V.player.getCurrent().getName() + " used " + D.getName(V.player.getCurrent().getMoves()[attackChoice]) + "! It did " + userAttack + " damage!");
			printResults(V.player, V.opp);
			V.opp.getCurrent().health -= userAttack;

			System.out.println(V.opp.getName() + "'s " + V.opp.getCurrent().getName() + ": " + V.opp.getCurrent().health + "/" + V.opp.getCurrent().getHP());

			if (V.opp.getCurrent().checkFainted()) {
			    System.out.println(V.opp.getName() + "'s " + V.opp.getCurrent().getName() + " fainted!");
			    V.opp.nextPokemon();
			    if (!V.opp.checkLoss()) {
				System.out.println(V.opp.getName() + " sends out " + V.opp.getCurrent().getName() + "!");
			    }
			}
		    }
		}
	    } else if (turnType == 2) { // BAG
		bagChoice();
	    } else if (turnType == 3) { // SWITCH
		if (!V.player.setCurrent(switchChoice())) { // If this fails, stop
		    System.out.println(new ArithmeticException("GOD DAMN YOU, NITIN! SWITCH FAILS!"));
		    while (true);
		}
		System.out.println(V.player.getName() + " sent out " + V.player.getCurrent().getName());
	    } else if (turnType == 4) { // RUN
		System.out.println("You can't run from a trainer battle!");
		System.out.println("Don't be a coward like Nitin, " + V.player.getName() + "!");
	    }
	    System.out.println();
	}

	System.out.print("GAME OVER: ");
	if (V.player.checkLoss()) {
	    System.out.println(V.opp.getName() + " WINS!!!");
	    System.out.println(V.player.getName() + " LOSES");
	    return V.player;
	}

	System.out.println(V.player.getName() + " WINS!!!");
	System.out.println(V.opp.getName() + " LOSES");
	if (V.opp.getName().equals("Nitin")) {
	    System.out.println("Ha ha!");
	}
	return V.opp;
    } // returns loser

    private int chooseTurnType() {
	System.out.println("(1) ATTACK, (2) BAG, (3) SWITCH OR (4) RUN");
	String choice = V.keys.nextLine();
	try {
	    int intChoice = Integer.parseInt(choice);
	    if (intChoice >= 1 && intChoice <= 4) {
		return intChoice;
	    }
	} catch (NumberFormatException mrBollhorstIsCool) {
	    if (choice.equals("ATTACK")) {
		return 1;
	    } else if (choice.equals("BAG")) {
		return 2;
	    } else if (choice.equals("SWITCH")) {
		return 3;
	    } else if (choice.equals("RUN")) {
		return 4;
	    }
	}
	return chooseTurnType();
    }

    private int bagChoice() {
	System.out.println("Oh. You don't have any items!");
	return -1;
    }

    private int switchChoice() {
	System.out.print("Please choose the pokemon to switch to:");
	for (Pokemon p : V.playerPokeParty) {
	    try {
		System.out.print(" (" + p.getSpecies() + ") " + p.getName());
	    } catch (NullPointerException e) {

	    }
	}
	System.out.println(": ");

	String choice = V.keys.nextLine();
	try {
	    int intChoice = Integer.parseInt(choice);
	    if (intChoice >= 1 && intChoice <= V.NUM_POKE) {
		return intChoice;
	    }
	} catch (NumberFormatException mrBollhorstLikesSports) {
	    for (int i = 0; i < V.player.getNumPokemon(); i++) {
		if (choice.equals(V.player.getParty()[i].getName())) {
		    return V.player.getParty()[i].getSpecies();
		}
	    }
	}
	return -1;
    }

    private int moveChoice() {
	System.out.print("Please choose your move (");
	int i;
	for (i = 0; i < V.player.getCurrent().getNumMoves() - 1; i++) {
	    System.out.print("(" + (i + 1) + ") " + D.getNameFromIndex(V.player.getCurrent().getMoves()[i]) + ", ");
	}
	System.out.print("(" + (i + 1) + ") " + D.getNameFromIndex(V.player.getCurrent().getMoves()[i++]) + "): ");
	String choice = V.keys.nextLine();
	try {
	    int intChoice = Integer.parseInt(choice) - 1;
	    if (intChoice >= 0 && intChoice <= V.player.getCurrent().getNumMoves()) {
		return intChoice;
	    }
	} catch (NumberFormatException dhruvIsSometimesADuck) {
	    for (i = 0; i < V.player.getCurrent().getNumMoves(); i++) {
		if (choice.equals(D.getNameFromIndex(V.player.getCurrent().getMoves()[i]))) {
		    return i;
		}
	    }
	}
	return moveChoice();
    }

    private double userAttack() {

	int attack = V.player.getCurrent().getMoves()[attackChoice];

	if (D.getPower(attack) <= 0) {
	    if (D.getPower(attack) == -1) {
		V.opp.getCurrent().lowerAtt();
		System.out.println(V.opp.getName() + "'s attack fell!");
	    }
	    if (D.getPower(attack) == -3) {
		V.opp.getCurrent().lowerDef();
		System.out.println(V.opp.getName() + "'s defense fell!");
	    }
	    return 0;
	}

	double damage = 0;
	if (Math.random() <= D.getAcc(attack)) { // Checks accuracy
	    damage = ((((2 * (5) / 5 + 2) * V.player.getCurrent().getAtt() * D.getPower(attack) / V.opp.getCurrent().getDef()) / 50) + 2);
	    if (D.getType(attack) == V.player.getCurrent().getType1() || D.getType(attack) == V.player.getCurrent().getType2()) {
		damage *= 2;
	    }
	    if (V.opp.getCurrent().getType2() == 0) {
		damage *= D.getEffectiveness(D.getType(attack), V.opp.getCurrent().getType1());
		if (D.getEffectiveness(D.getType(attack), V.opp.getCurrent().getType1()) > 1) {
		    superEff = true;
		} else if (D.getEffectiveness(D.getType(attack), V.opp.getCurrent().getType1()) < 1) {
		    notEff = true;
		}
	    } else {
		damage *= D.getEffectiveness(D.getType(attack), V.opp.getCurrent().getType1(), V.opp.getCurrent().getType2());
		if (D.getEffectiveness(D.getType(attack), V.opp.getCurrent().getType1(), V.opp.getCurrent().getType2()) > 1) {
		    superEff = true;
		} else if (D.getEffectiveness(D.getType(attack), V.opp.getCurrent().getType1(), V.opp.getCurrent().getType2()) < 1) {
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

	int attack = V.opp.getCurrent().getMoves()[oppMove];

	if (D.getPower(attack) <= 0) {
	    if (D.getPower(attack) == -1) {
		V.player.getCurrent().lowerAtt();
		att = true;
	    }
	    if (D.getPower(attack) == -3) {
		V.player.getCurrent().lowerDef();
		def = true;
	    }
	    return 0;
	}

	double damage = 0;

	if (Math.random()
		<= D.getAcc(attack)) { // Checks accuracy
	    damage = ((((2 * (5) / 5 + 2) * V.opp.getCurrent().getAtt() * D.getPower(attack) / V.player.getCurrent().getDef()) / 50) + 2);

	    if (D.getType(attack) == V.opp.getCurrent().getType1() || D.getType(attack) == V.opp.getCurrent().getType2()) {
		damage *= 2;
	    }
	    if (V.player.getCurrent().getType2() == 0) {
		damage *= D.getEffectiveness(D.getType(attack), V.player.getCurrent().getType1());
		if (D.getEffectiveness(D.getType(attack), V.player.getCurrent().getType1()) > 1) {
		    superEff = true;
		} else if (D.getEffectiveness(D.getType(attack), V.player.getCurrent().getType1()) < 1) {
		    superEff = true;
		}
	    } else {
		damage *= D.getEffectiveness(D.getType(attack), V.player.getCurrent().getType1(), V.player.getCurrent().getType2());
		if (D.getEffectiveness(D.getType(attack), V.player.getCurrent().getType1(), V.player.getCurrent().getType2()) > 1) {
		    notEff = true;
		} else if (D.getEffectiveness(D.getType(attack), V.player.getCurrent().getType1(), V.player.getCurrent().getType2()) < 1) {
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

    private void printResults(Player p, Player notP) {
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
	    System.out.println(notP.getCurrent().getName() + "'s attack fell!");
	}
	if (def) {
	    System.out.println(notP.getCurrent().getName() + "'s def fell!");
	}
	crit = false;
	miss = false;
	notEff = false;
	superEff = false;
	att = false;
	def = false;
    }
}
