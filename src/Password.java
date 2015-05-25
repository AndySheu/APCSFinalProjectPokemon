
public class Password {

    static void input() {

	boolean wrong = false;

	while (!wrong) {
	    System.out.print("Enter a password (if you have one): ");
	    String pass = V.keys.nextLine();

	    if (pass.equals("france")) {
		for (int i = 0; i < 6; i++) {
		    V.player.addPokemon(new Pokemon(P.VICTINI, true));
		}
	    } else if (pass.equals("england")) {
		V.player.addPokemon(new Pokemon(P.CHARMANDER, true));
		V.player.addPokemon(new Pokemon(P.ARCEUS, true));
		V.player.fillTeam(4, true);
	    } else if (pass.equals("excellent") && V.player.getName().equals("Mike Bollhorst")) {
		for (int i = 0; i < 6; i++) {
		    V.player.addPokemon(new Pokemon(P.ARCEUS, true));
		}
	    } else if (pass.equals("meb")) {
		V.opp.setName("Michael E. Bollhorst");
		for (int i = 0; i < 6; i++) {
		    V.opp.addPokemon(new Pokemon(P.ARCEUS, false));
		}
	    } else {
		wrong = true;
		System.out.println("Wrong!");
	    }
	    for (int i = 0; i < 100; i++) {
		System.out.println();
	    }
	}
    }
}
