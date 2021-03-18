package player;

import java.util.*;

import Board.Board;

import Main.Backgammon;

import move.Move;

/** 
 * Class which implements a random player.  This player
 * will get the list of all available moves and return
 * a random one.
 */

public class AIPlayer2 implements Player {
	protected Board[] possibleBoard;
	// the random move selector
	protected Random random = new Random();

	/**
	 * Requests that the player make a move using the given
	 * backgammon setup.  The player should return a valid
	 * and complete move.  The list of valid moves can be
	 * found by calling backgammon.getAllMoves().
	 *
	 * @param backgammon The current backgammon situation
	 */
	public Move move(Backgammon backgammon) {
	
		int indexOfMaxValue = 0;
		Move[] moves = backgammon.getMoves();
		moves = AIPlayer2.selectBoard(backgammon, moves);
		moves[indexOfMaxValue]= AIPlayer2.bestMove(moves);
		
		AIPlayer2.selectBoard(backgammon, moves);
		
		return moves[indexOfMaxValue];
		
		// Use this code only if you want a randomly selected move. 
		//Move[] moves = backgammon.getMoves();
	    //return moves[random.nextInt(moves.length)];

	}

	public static Move[] selectBoard(Backgammon backgammon, Move[] moves) {

		if(moves.length>0){
			for (int i=0; i <moves.length; i++){
				
				double rnorm = EvalFuncTest.EvalPercentage(moves[i].getCurrentBoard());
				
				moves[i].setRnorm(rnorm);

			}}
		return moves;}



	public static Move bestMove(Move[] moves) {
		int indexOfMaxValue = 0;
		for (int i = 1; i < moves.length; i++){
			double newnumber = moves[i].getRnorm();
			if ((newnumber > moves[indexOfMaxValue].getRnorm())){
				indexOfMaxValue = i;
				
			}
		} 
		
		return moves[indexOfMaxValue];
	}  


	public void won() {
	
		
	}
   
	public void lost() {}

	
	}

