/**
 * reference: http://modelai.gettysburg.edu/2013/tdgammon/doc/overview-summary.html 
 * 
 */

package Main;


import Board.Board;
import Board.Dice;
import Genes.IndivChromas;
import Genes.ParamStats;
import move.Move;
import player.Player;


/** 
 * This class wraps all of the backgammon functionality.  Basically, the use model
 * for this class is build a new Backgammon with two players, execute 
 * backgammon.run(),
 * which runs the game, and the call backgammon.reset(), backgammon.run() if you
 * want to play again.
 */
public class Backgammon {
  private static Backgammon currentGame;
 
 // the internal board, which is the state before the current move
  protected Board board;
  
  // the dice 
  protected Dice dice;
  
  // the color of the current player
  protected int player;
  
  // the list of players
  protected Player[] players;
  
  // Chromasome behavior of players. 
  protected IndivChromas indiv1, indiv2;
 

  
/**
   * Builds a new backgammon instance, given the two players
   * to play the game between.
   *
   * @param player1 The first player (BLACK)
   * @param player2 The second player (WHITE)
   */
  public Backgammon(Player player1, Player player2, IndivChromas individual1, IndivChromas individual2) {
    this.players = new Player[2];
    this.players[0] = player1;
    this.players[1] = player2;
    this.indiv1 = individual1;
    this.indiv2 = individual2;
    reset();
  }
  
  /**
   * Runs a game of backgammon, and does not return until the game
   * is over.  Returns the player who won the game.
   */
  
  public Player run() {
	
    while (! board.isGameOver()) {
      getMove(players[0]);
      if (board.isGameOver()) break;
      
      getMove(players[1]);
      if (board.isGameOver()) break;
    }
    
    if (board.getOff(board.BLACK) == 15) {
      players[0].won();
     
    
      players[1].lost();
  
      return players[0];
      
    } else if (board.getOff(board.WHITE) == 15) {
      players[1].won();
      
     
      players[0].lost();
      
      return players[1];
    }
	return null;}
  	
			
  public ParamStats getParamStats(){
		
		IndivChromas gameVictor = null;
		
		double playerOneScore = 0;
		double playerTwoScore = 0;
		
		if(board.getWinner() == Board.BLACK){
			playerOneScore = 0;
			playerTwoScore = 0- board.getWhitePips();
			gameVictor = getIndiv1();
			
		}else if(board.getWinner() == Board.WHITE){
			playerTwoScore = 0- board.getBlackPips();
			playerOneScore = 0;
			gameVictor = getIndiv2();
		}
		return new ParamStats(gameVictor, playerOneScore, playerTwoScore);
	}
  
  
   public IndivChromas getIndiv1() {
		return indiv1;
	}

   public void setIndiv1(IndivChromas indiv1) {
		this.indiv1 = indiv1;
	}

	public IndivChromas getIndiv2() {
		return indiv2;
	}

	public void setIndiv2(IndivChromas indiv2) {
		this.indiv2 = indiv2;
	}
 
  /**
   * Gets a player's move 
   *
   * @param player The play
   */
  private void getMove(Player player) {
    Move move = player.move(Backgammon.this);
    
    if (! move.getOriginalBoard().equals(board)) {
      
      getMove(player);
    } else {
      doMove(move);
    }
  }
  
  /**
   * Resets this backgammon instance to the initial state, with
   * a new board and the black player's move
   */
  public void reset() {
    this.board = new Board();
    this.dice = Dice.roll();
    this.player = Board.BLACK;
  }
  
  /**
   * Returns the current player, either Board.BLACK or Board.WHITE
   *
   * @return The current player
   */
  public int getCurrentPlayer() {
    return player;
  }
  
  /**
   * Returns a list of all of the possible moves which the player
   * can currently make
   *
   * @return The current available moves
   */
  public Move[] getMoves() {
    return Move.getAllMoves(player, dice, board);
    
  }
  
  /**
   * Returns the current dice
   *
   * @return The current dice
   */
  public Dice getDice() {
    return dice;
  }
  
  /**
   * Returns the current board.  Note that this is the actual
   * board, so *NO CHANGES SHOULD BE MADE TO THIS OBJECT*.
   *
   * @return The current board
   */
  public Board getCurrentBoard() {
    return board;
  }
  
  /**
   * Returns whether or not the game is over
   *
   * @return Whether or not the game is over
   */
  public boolean isGameOver() {
    return board.isGameOver();
  }
  
  /**
   * Performs the provided move
   *
   * @param movement The movement to do
   * @return Whether or not more moves are required
   */
  private void doMove(Move move)  {
    board = move.getCurrentBoard();
    dice = Dice.roll();
    player = board.getOtherPlayer(player);
  }   
  
}
  