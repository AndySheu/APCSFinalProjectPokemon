
import javax.swing.ImageIcon;

public class Battle {

    // These booleans are used in printing results
    private boolean crit = false, miss = false, notEff = false, superEff = false, att = false, def = false;

    static int attackChoice; // public because it is changed by Listener
    private boolean run = false;
    private boolean trainer;

    // State values
    static final int OVERWORLD = 0;
    static final int STARTED = 1;
    static final int LOADING = 2;
    static final int TYPE = 3;
    static final int ATTACK = 4;
    static final int ITEM = 5;
    static final int POKEMON = 6;
    static final int RUN = 7;
    static final int BACK = 8;
    static final int PRINTING = 9;
    static final int FINISHED = 10;
    static final int MAGIC_KILL = 17;

    // Pre: boolean trainer
    // Post: Constructs a Battle object
    public Battle(boolean trainer) {
	V.state = STARTED;
	this.trainer = trainer;
    }

    // Pre: None
    // Post: Starts the music, repaints the panel, and runs the battle between two players. It returns the winning player
    public Player run() {
	// Initiate battle
	V.musicState = Music.TRAINER_BATTLE;
	V.music.start();
	V.state = STARTED;
	Pokemon playerCurr, oppCurr;

	// Check if someone has lost of if the player has ran away
	while (!V.player.checkLoss() && !V.opp.checkLoss() && !run) {
	    System.out.println();

	    V.panel.reset();
	    V.frame.repaint();

	    // Print health
	    System.out.println(V.player.getName() + "'s " + V.player.getCurrent().getName() + ": " + V.player.getCurrent().getHealth() + "/" + V.player.getCurrent().getHP());
	    System.out.println(V.opp.getName() + "'s " + V.opp.getCurrent().getName() + ": " + V.opp.getCurrent().getHealth() + "/" + V.opp.getCurrent().getHP());

	    System.out.println();

	    // Opponent's move is completely randomized and can be considered harder than the real game's intentionally terrible AI
	    int oppMove = (int) (Math.random() * V.opp.getCurrent().getNumMoves());

	    chooseType(); // This methods sets the value of V.state
	    attackChoice = -1;

	    switch (V.state) {
		case ATTACK:
		    int playerSpd = V.player.getCurrent().getSpd(), oppSpd = V.opp.getCurrent().getSpd();
		    double rand = Math.random();
		    moveChoice();
		    
		    if (attackChoice == Integer.MAX_VALUE) { // Check if back button
			break;
		    }

		    // Player goes first
		    if (playerSpd > oppSpd || ((playerSpd == oppSpd) && Math.random() <= 0.5)) {
			int userAttack = (int) attack(V.player, V.opp, attackChoice);
			if (V.opp.getCurrent().checkFainted()) {
			    processFaint(V.opp);
			} else { // Only gets to move if not fainted
			    int oppAttack = (int) attack(V.opp, V.player, oppMove);
			    if (V.player.getCurrent().checkFainted()) {
				processFaint(V.player);
			    }

			}
		    } else { // Opponent goes first
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
		    V.click = false;
		    bagChoice();
		    while (!V.click) {
			Timer.wait(100);
		    }
		    break;
		case POKEMON:
		    if (V.player.setCurrent(switchChoice())) {
			System.out.println(V.player.getName() + " sent out " + V.player.getCurrent().getName());
			V.panel.reset();
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
		    if (trainer) {
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

	V.panel.reset();
	V.frame.repaint();

	V.state = OVERWORLD;

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

    // Pre: None
    // Post: Changes the image displayed on the panel and allows the user to choose to attack, switch Pokemon, or access bag
    private void chooseType() {
	V.panel.setImage(new ImageIcon("./src/Images/Battle Backgrounds/Finale Type.png").getImage());
	System.out.println("Please select a turn type.");

	V.state = TYPE;
	while (V.state == TYPE) {
	    Timer.wait(100);
	}
    }

    // Pre: None
    // Post: Changes the image displayed on the panel and prints out that the bag does not contain any items
    private void bagChoice() {
	V.state = ITEM;
	V.panel.setImage(new ImageIcon("./src/Images/Battle Backgrounds/Finale Loading.png").getImage());
	System.out.println("Oh. You don't have any items!");
    }

    // Pre: None
    // Post: Changes the image displayed on the panel and lets the user switch its player's current Pokemon to a different Pokemon in the player's party
    private int switchChoice() {
	V.state = POKEMON;
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

    // Pre: None
    // Post: Changes the image displayed on the panel and allows the user to choose which move its player's Pokemon performs
    private void moveChoice() {
	V.panel.setImage(new ImageIcon("./src/Images/Battle Backgrounds/Finale Attack.png").getImage());
	System.out.println("Please select a move.");

	V.state = ATTACK;

//	JLabel move0 = new JLabel(), move1 = new JLabel(), move2 = new JLabel(), move3 = new JLabel();
//	move0.setText(D.getNameFromIndex(V.player.getCurrent().getMoves()[0]));
//	move0.setVisible(true);
//	move0.setLocation(134, 512);
//	V.frame.add(move0);
//	if (V.player.getCurrent().getNumMoves() > 1) {
//	    move1.setText(D.getNameFromIndex(V.player.getCurrent().getMoves()[1]));
//	    move1.setLocation(403, 512);
//	    V.frame.add(move1);
//	    if (V.player.getCurrent().getNumMoves() > 2) {
//		move2.setText(D.getNameFromIndex(V.player.getCurrent().getMoves()[2]));
//		move2.setLocation(134, 614);
//		V.frame.add(move2);
//		if (V.player.getCurrent().getNumMoves() > 3) {
//		    move3.setText(D.getNameFromIndex(V.player.getCurrent().getMoves()[3]));
//		    move3.setLocation(403, 614);
//		    V.frame.add(move3);
//		}
//	    }
//	}
//	V.panel.reset();
	while (attackChoice == -1) {
	    Timer.wait(100);
	}

//	System.out.print("Please choose your move (");
//	int i;
//	for (i = 0; i < V.player.getCurrent().getNumMoves() - 1; i++) {
//	    System.out.print("(" + (i + 1) + ") " + D.getNameFromIndex(V.player.getCurrent().getMoves()[i]) + ", ");
//	}
//	System.out.print("(" + (i + 1) + ") " + D.getNameFromIndex(V.player.getCurrent().getMoves()[i++]) + ") or 0 (back): ");
//	String choice = V.keys.nextLine();
//	try {
//	    int intChoice = Integer.parseInt(choice) - 1;
//	    if (intChoice >= -1 && intChoice <= V.player.getCurrent().getNumMoves()) {
//		V.panel.setImage(new ImageIcon("./src/Images/Battle Backgrounds/Finale Loading.png").getImage());
//		return intChoice;
//	    }
//	} catch (NumberFormatException e) {
//	    for (i = 0; i < V.player.getCurrent().getNumMoves(); i++) {
//		if (choice.equals(D.getNameFromIndex(V.player.getCurrent().getMoves()[i]))) {
//		    V.panel.setImage(new ImageIcon("./src/Images/Battle Backgrounds/Finale Loading.png").getImage());
//		    return i;
//		}
//	    }
//	}
//	V.panel.setImage(new ImageIcon("./src/Images/Battle Backgrounds/Finale Loading.png").getImage());
//	return moveChoice();
    }

    // Pre: Player p, Player o, int attackIndex
    // Post: Changes the image displayed on the panel and calculates and returns a double that represents the damage a move does to a Pokemon
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

    // Pre: Player p
    // Post: Makes the next Pokemon in player's party the current Pokemon and resets the panel and repaints the frame
    private void processFaint(Player p) {
	V.state = LOADING;

	System.out.println(p.getName() + "'s " + p.getCurrent().getName() + " fainted!");
	p.nextPokemon();
	V.panel.reset();
	V.frame.repaint();
	if (!p.checkLoss()) {
	    System.out.println(p.getName() + " sends out " + p.getCurrent().getName() + "!");
	}
    }

    // Pre: Player p, Player notP
    // Post: Prints out descriptions about what is occurring
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

    // Pre: Pokemon p
    // Post: Resets and repaints the panel causing the Pokemon image to "flash"
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

    private void hpBars() {

    }
}
