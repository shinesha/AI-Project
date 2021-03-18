/**
 * parts of this class were obtained from the following under a GNU public license. 
 * Ref: https://github.com/
 */

package Genes;

import Main.Backgammon;
import player.AIPlayer;
import player.Player;
import player.AIPlayer2;
import player.Player;
import Genes.IndivChromas;


public class GeneticStart {

	private static Backgammon currentGame;
	public static IndivChromas indivObj1;
	public static IndivChromas indivObj2;
	
	/**
	 * playIndividualsVsEachOther
	 * 
	 * @param ip1 indiv 1
	 * @param ip2 indiv 2
	 * @return ParamStats result of game (winner, values)
	 */
	public static ParamStats playIndividualsVsEachOther(IndivChromas ip1, IndivChromas ip2){
		
		indivObj1 = ip1;
		indivObj2 = ip2;
		
		Player player1 = new AIPlayer2();
		Player player2 = new AIPlayer();
		//The Game Thread
		 currentGame = new Backgammon(player1, player2, ip1, ip2);
		
		
		currentGame.run();
		return currentGame.getParamStats();
	
	}
	public static IndivChromas getIndivObj2() {
		return indivObj2;
	}
	public static void setIndivObj2(IndivChromas indivObj2) {
		GeneticStart.indivObj2 = indivObj2;

	}
	public static IndivChromas getIndivObj1() {
		return indivObj1;
	}
	public static void setIndivObj1(IndivChromas indivObj1) {
		GeneticStart.indivObj1 = indivObj1;
	}
}