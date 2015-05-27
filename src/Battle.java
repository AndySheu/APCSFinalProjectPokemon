
import javax.swing.ImageIcon;

public class Battle {

    private boolean crit = false, miss = false, notEff = false, superEff = false, att = false, def = false;
    private int attackChoice;
    private boolean run = false;
    private boolean trainer;

    static final int OUT = -1;
    static final int STARTED = 0;
    static final int LOADING = 1;
    static final int TYPE = 2;
    static final int ATTACK = 3;
    static final int ITEM = 4;
    static final int POKEMON = 5;
    static final int RUN = 6;
    static final int BACK = 7;
    static final int PRINTING = 8;
    static final int FINISHED = 9;
    static final int MAGIC_KILL = 17;

    public Battle(boolean trainer) {
	V.state = STARTED;
	this.trainer = trainer;
    }

    public Player run() {
	V.musicState = Music.TRAINER_BATTLE;
	V.music.start();
	V.state = STARTED;
	Pokemon playerCurr, oppCurr;

	while (!V.player.checkLoss() && !V.opp.checkLoss() && !run) {
	    System.out.println();

	    ImagePanel.reset();
	    V.frame.repaint();

	    System.out.println(V.player.getName() + "'s " + V.player.getCurrent().getName() + ": " + V.player.getCurrent().getHealth() + "/" + V.player.getCurrent().getHP());
	    System.out.println(V.opp.getName() + "'s " + V.opp.getCurrent().getName() + ": " + V.opp.getCurrent().getHealth() + "/" + V.opp.getCurrent().getHP());

	    System.out.println();

	    int oppMove = (int) (Math.random() * V.opp.getCurrent().getNumMoves());
	    V.state = chooseType();

	    switch (V.state) {
		case ATTACK:
		    int playerSpd = V.player.getCurrent().getSpd(),
		     oppSpd = V.opp.getCurrent().getSpd();
		    double rand = Math.random();
		    int choice = moveChoice();
		    if (choice == -1) {
			break;
		    }
		    attackChoice = choice;

		    if (playerSpd > oppSpd || ((playerSpd == oppSpd) && rand <= 0.5)) {
			int userAttack = (int) attack(V.player, V.opp, attackChoice);
			if (V.opp.getCurrent().checkFainted()) {
			    processFaint(V.opp);
			} else {
			    int oppAttack = (int) attack(V.opp, V.player, oppMove);
			    if (V.player.getCurrent().checkFainted()) {
				processFaint(V.player);
			    }

			}
		    } else {
			int oppAttack = (int) attack(V.opp, V.player, oppMove);
			if (V.player.getCurrent().checkFainted()) {
			    processFaint(V.player);
			} else {
			    int userAttack = (int) attack(V.player, V.opp, attackChoice);
			    if (V.opp.getCurrent().checkFainted()) {
				processFaint(V.opp);
			    }
			}
		    }
		    break;
		case ITEM:
		    bagChoice();
		case POKEMON:
		    if (V.player.setCurrent(switchChoice())) {
			System.out.println(V.player.getName() + " sent out " + V.player.getCurrent().getName());
			ImagePanel.reset();
			V.frame.repaint();
			int oppAttack = (int) attack(V.opp, V.player, oppMove);
			if (V.player.getCurrent().checkFainted()) {
			    processFaint(V.player);
			}
		    } else {
			System.out.println("You can't switch because your input is either fainted, is already out, or doesn't exist.");
		    }
		    break;
		case RUN:
		    if(trainer) {
		    System.out.println("You can't run from a trainer battle!");
		    System.out.println("Don't be a coward, " + V.player.getName() + "!");
			break;
		    }
		    if (Math.random() > 0.5) {
			run = true;
			break;
		    }
		    System.out.println("Couldn't escape!");
		    int oppAttack = (int) attack(V.opp, V.player, oppMove);
		    if (V.player.getCurrent().checkFainted()) {
			processFaint(V.player);
		    }
		    break;
		case BACK: // Go back
		    break;
		case MAGIC_KILL:
		    V.opp.getCurrent().kill();
		    if (V.opp.getCurrent().checkFainted()) {
			processFaint(V.opp);
		    }
		    break;
	    }
	}

	ImagePanel.reset();
	V.frame.repaint();

	V.state = OUT;

	if (run) {
	    System.out.println(V.player.getName() + " ran away!");
	    V.panel.clear();
	    V.musicState = Music.DEFAULT;
	    V.music.start();
	    return null;
	}

	System.out.print("GAME OVER: ");
	Player loser, winner;
	if (V.player.checkLoss()) { // TIE GOES TO OPPONENT
	    loser = V.player;
	    winner = V.opp;
	} else {
	    V.musicState = Music.TRAINER_VICTORY;
	    V.music.start();
	    loser = V.opp;
	    winner = V.player;
	}

	System.out.println(winner.getName() + " WINS!!!");
	System.out.println(loser.getName() + " LOSES");
	return winner;
    }

    private int chooseType() {
	V.panel.setImage(new ImageIcon("./src/Images/Battle Backgrounds/Finale Type.png").getImage());
	System.out.println("(1) ATTACK, (2) BAG, (3) SWITCH OR (4) RUN");

	String choice = V.keys.nextLine();
	try {
	    int intChoice = Integer.parseInt(choice);
	    if (intChoice >= 1 && intChoice <= 4) {
		return intChoice + 2; // To match states above
	    }
	} catch (NumberFormatException e) {
	    if (choice.equals("ATTACK")) {
		return 3;
	    } else if (choice.equals("BAG")) {
		return 4;
	    } else if (choice.equals("SWITCH")) {
		return 5;
	    } else if (choice.equals("RUN")) {
		return 6;
	    } else if (choice.equals("mk")) { // Stands for Magic Kill (Testing Only)
		if (V.TESTING) {
		    return 17;
		}
	    }
	}
	return chooseType();
    }

    private int bagChoice() {
	V.panel.setImage(new ImageIcon("./src/Images/Battle Backgrounds/Finale Loading.png").getImage());
	System.out.println("Oh. You don't have any items!");
	return -1;
    }

    private int switchChoice() {
	V.panel.setImage(new ImageIcon("./src/Images/Battle Backgrounds/Finale Loading.png").getImage());
	System.out.print("Please choose the pokemon to switch to:");
	int j = 0;
	for (Pokemon p : V.playerPokeParty) {
	    j++;
	    try {
		if (!p.checkFainted()) {
		    System.out.print(" (" + j + ") " + p.getName());
		}
	    } catch (NullPointerException e) {

	    }
	}
	System.out.println(": ");

	String choice = V.keys.nextLine();
	try {
	    int intChoice = Integer.parseInt(choice);
	    if (intChoice >= 1 && intChoice <= V.player.getNumPokemon()) {
		return V.player.getParty()[intChoice - 1].getSpecies();
	    }
	} catch (NumberFormatException e) {
	    for (int i = 0; i < V.player.getNumPokemon(); i++) {
		if (choice.equals(V.player.getParty()[i].getName())) {
		    return V.player.getParty()[i].getSpecies();
		}
	    }
	}
	return -1;
    }

    private int moveChoice() {
	V.panel.setImage(new ImageIcon("./src/Images/Battle Backgrounds/Finale Attack.png").getImage());
	System.out.print("Please choose your move (");
	int i;
	for (i = 0; i < V.player.getCurrent().getNumMoves() - 1; i++) {
	    System.out.print("(" + (i + 1) + ") " + D.getNameFromIndex(V.player.getCurrent().getMoves()[i]) + ", ");
	}
	System.out.print("(" + (i + 1) + ") " + D.getNameFromIndex(V.player.getCurrent().getMoves()[i++]) + ") or 0 (back): ");
	String choice = V.keys.nextLine();
	try {
	    int intChoice = Integer.parseInt(choice) - 1;
	    if (intChoice >= -1 && intChoice <= V.player.getCurrent().getNumMoves()) {
		V.panel.setImage(new ImageIcon("./src/Images/Battle Backgrounds/Finale Loading.png").getImage());
		return intChoice;
	    }
	} catch (NumberFormatException e) {
	    for (i = 0; i < V.player.getCurrent().getNumMoves(); i++) {
		if (choice.equals(D.getNameFromIndex(V.player.getCurrent().getMoves()[i]))) {
		    V.panel.setImage(new ImageIcon("./src/Images/Battle Backgrounds/Finale Loading.png").getImage());
		    return i;
		}
	    }
	}
	V.panel.setImage(new ImageIcon("./src/Images/Battle Backgrounds/Finale Loading.png").getImage());
	return moveChoice();
    }

    private double attack(Player p, Player o, int attackIndex) {
	V.panel.setImage(new ImageIcon("./src/Images/Battle Backgrounds/Finale Loading.png").getImage());
	V.state = LOADING;

	int attack = p.getCurrent().getMoves()[attackIndex];

	if (D.getEffect(attack) != 0) {

	    System.out.println(p.getName() + "'s " + p.getCurrent().getName() + " used " + D.getName(attack) + "!");

	    if (D.getEffect(attack) == D.ATT_DOWN) {
		o.getCurrent().lowerAtt();
		System.out.println(o.getCurrent().getName() + "'s attack fell!");
	    } else if (D.getEffect(attack) == D.ATT_UP) {
		p.getCurrent().raiseAtt();
		System.out.println(p.getCurrent().getName() + "'s attack rose!");
	    } else if (D.getEffect(attack) == D.DEF_DOWN) {
		o.getCurrent().lowerDef();
		System.out.println(o.getCurrent().getName() + "'s defense fell!");
	    } else if (D.getEffect(attack) == D.DEF_UP) {
		p.getCurrent().raiseDef();
		System.out.println(p.getCurrent().getName() + "'s defense rose!");
	    } else if (D.getEffect(attack) == D.SPD_DOWN) {
		o.getCurrent().lowerSpd();
		System.out.println(o.getCurrent().getName() + "'s speed fell!");
	    } else if (D.getEffect(attack) == D.SPD_UP) {
		p.getCurrent().raiseSpd();
		System.out.println(p.getCurrent().getName() + "'s speed rose!");
	    }
	}

	if (D.getPower(attack) < 10) {
	    return 0;
	}

	double damage = 0;
	if (Math.random() <= D.getAcc(attack)) { // Checks accuracy
	    damage = ((((2 * (10) / 5 + 2) * p.getCurrent().getAtt() * D.getPower(attack) / o.getCurrent().getDef()) / 50) + 2);
	    if (D.getType(attack) == p.getCurrent().getType1() || D.getType(attack) == p.getCurrent().getType2()) { // STAB
		damage *= 1.5;
	    }
	    if (o.getCurrent().getType2() == 0) {
		damage *= D.getEffectiveness(D.getType(attack), o.getCurrent().getType1());
		if (D.getEffectiveness(D.getType(attack), o.getCurrent().getType1()) > 1) {
		    superEff = true;
		} else if (D.getEffectiveness(D.getType(attack), o.getCurrent().getType1()) < 1) {
		    notEff = true;
		}
	    } else {
		damage *= D.getEffectiveness(D.getType(attack), o.getCurrent().getType1(), o.getCurrent().getType2());
		if (D.getEffectiveness(D.getType(attack), o.getCurrent().getType1(), o.getCurrent().getType2()) > 1) {
		    superEff = true;
		} else if (D.getEffectiveness(D.getType(attack), o.getCurrent().getType1(), o.getCurrent().getType2()) < 1) {
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

	// This should only be done for attacking moves
	System.out.println(p.getName() + "'s " + p.getCurrent().getName() + " used " + D.getName(attack) + "! It did " + (int) damage + " damage!");
	printResults(p, o);
	o.getCurrent().lowerHealth((int) damage);

	if (damage > 0) { // TODO - check
	    Timer.wait(250);
	    flashAnimation(o.getCurrent());
	    Timer.wait(100);
	}

	return damage;
    }

    private void processFaint(Player p) {
	V.state = LOADING;

	System.out.println(p.getName() + "'s " + p.getCurrent().getName() + " fainted!");
	p.nextPokemon();
	ImagePanel.reset();
	V.frame.repaint();
	if (!p.checkLoss()) {
	    System.out.println(p.getName() + " sends out " + p.getCurrent().getName() + "!");
	}
    }

    private void printResults(Player p, Player notP) {
	V.state = PRINTING;
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

    private void flashAnimation(Pokemon p) {
	V.panel.flash(p);
	V.panel.repaint();
	Timer.wait(200);
	V.panel.reset();
	V.panel.repaint();
	Timer.wait(300);
	V.panel.flash(p);
	V.panel.repaint();
	Timer.wait(500);
	V.panel.reset();
	V.panel.repaint();
    }
}
