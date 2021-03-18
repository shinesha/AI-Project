/**
 * reference: http://modelai.gettysburg.edu/2013/tdgammon/doc/overview-summary.html 
 * 
 */

package move;


import java.util.*;

import Board.Board;
import Board.Dice;


/** 
 * Class which represents a move in backgammon.  Clients
 * can either build specific moves themselves, possibly from 
 * human input, or use the movement factory to build moves.
 * Moves are constructed by adding a number of movements until
 * all of the dice have been used or no more moves are possible.
 *
 * When evalutaing a move, a client can use the 
 * move.getCurrentBoard()
 * method, which will return the state of the board after the
 * move.
 */
public class Move  {
  
  // the placeholder for a used move
  public static int USED = -1;
  
  // the dice used for this move
  protected Dice dice;
  
  // the set of movements used to perform this move, in this order
  protected Movement[] movements;
  
  // whether or not each die has been used
  protected int[] used;
  
  // the original board
  protected Board board;
  
  // the hypothetical boards, with the intermediate movements applied
  protected Board[] boards;
  
  // the player this move is for
  protected int player;
  
  protected double rnorm;
  
  /**
   * Builds a new Move, using the provided dice roll
   *
   * @param dice The dice roll
   */
  public Move(Dice dice, Board board, int player) {
    this.dice = dice; 
    this.board = board;
    this.player = player;
    this.rnorm = 0; 
    
    if (this.dice.isDoubles()) {
      this.movements = new Movement[Dice.NUM_DICE * 2];
      this.boards = new Board[Dice.NUM_DICE * 2];
      this.used = new int[Dice.NUM_DICE * 2];
      Arrays.fill(used, this.dice.getDie1());
    } else {
      this.movements = new Movement[Dice.NUM_DICE];
      this.boards = new Board[Dice.NUM_DICE];
      this.used = new int[Dice.NUM_DICE];
      this.used[0] = dice.getDie1();
      this.used[1] = dice.getDie2();
    }
  }
  
  /**
   * Clones the given move
   *
   * @param move The move to clone
   */
  public Move(Move move) {
    this.dice = move.dice;
    this.board = move.board;
    this.player = move.player;
    this.rnorm = 0;
    
    this.movements = new Movement[move.movements.length];
    this.boards = new Board[move.boards.length];
    this.used = new int[move.used.length];
    
    System.arraycopy(move.movements, 0, movements, 0, movements.length);
    System.arraycopy(move.boards, 0, boards, 0, boards.length);
    System.arraycopy(move.used, 0, used, 0, used.length);
  }
  
  /**
   * Returns whether or not a move is possible for the
   * current user.
   *
   * @param player The current player
   * @return Whether or not a move is possible for the user
   */
  public boolean movePossible() {
    for (int i=0; i<used.length; i++) 
      if ((used[i] != USED) && Move.movePossible(used[i], player, getCurrentBoard()))
        return true;
    
    return false;
  }
  
  /**
   * Adds a movement to this move.
   *
   * @param movement The movement ot add
   */

  public void addMovement(Movement movement) throws IllegalMoveException {
    if (isFull())
      throw new IllegalMoveException("You have already made all of your moves.");
    
    int use = USED;
    
    for (int i=0; (i<used.length) && (use == USED); i++) 
      if ((used[i] != USED) && (movement.canUse(getCurrentBoard(), used[i]))) 
        use = i;

    if (use == USED)
      throw new IllegalMoveException("You cannot make that move using the dice.");
    
    // do the movement
    boards[getNumMovements()] = getCurrentBoard().getScratch();
    movement.apply(boards[getNumMovements()]);
    
    // update the metadata
    used[use] = USED;
    movements[getNumMovements()] = movement;
  }
  
  /**
   * Returns the current scratch board, or the board with all internmediate
   * movements applied
   *
   * @return the current scratch board
   */
  public Board getCurrentBoard() {
    for (int i=boards.length-1; i>=0; i--)
      if (boards[i] != null)
        return boards[i];
    
    return board;
  }
  
  /**
   * Returns the starting board of the move
   *
   * @return The starting board
   */
  public Board getOriginalBoard() {
    return board;
  }
  
  /**
   * Returns whether or not there are move moves to be made
   *
   * @return Whether or not there are more moves
   */
  public boolean isFull() {
    return getNumMovements() == movements.length;
  }
  
  /**
   * Returns the number of movements currently in this move
   *
   * @return The number of movements in this move
   */
  protected int getNumMovements() {
    for (int i=0; i<movements.length; i++)
      if (movements[i] == null)
        return i;
    
    return movements.length;
  }
  
  /**
   * Returns the hashCode of this move
   *
   * @return the hasCode of the final board
   */
  public int hashCode() {
    return getCurrentBoard().hashCode();
  }
  
  /**
   * Returns whether or not this move is equal to the other
   *
   * @param other TO come to
   * @return Whether or not they are eqaul
   */
  public boolean equals(Object other) {
    Move move = (Move) other;
    
    return ((player == move.player) && (dice == move.dice) && Board.isEqual(used, move.used) &&
            getCurrentBoard().equals(move.getCurrentBoard()));
  }
  
  
  public double getRnorm() {
	  return rnorm;
  }

  public void setRnorm(double rnorm) {
	  this.rnorm = rnorm;
  }
  

  /**
   * Return whether or not a move is possible using 
   * the given dice roll by the given player in the given board.
   * Checks to see if a bar, bear off, or normal movement 
   * are possible.
   *
   * @param int die The dice roll
   * @param player The player
   * @param board The board
   * @return Wether or not a move is possible
   */
  public static boolean movePossible(int die, int player, Board board) {
    return (BarMovement.movePossible(die, player, board) ||
            BearOffMovement.movePossible(die, player, board) ||
            NormalMovement.movePossible(die, player, board));
  }
  
  /**
   * Returns all possible distinct moves for the given player
   * given the starting board and the given dice roll.
   *
   * @param player The player who is active
   * @param dice The dice roll
   * @param board The current board
   * @return All of the possible moves
   */
  public static Move[] getAllMoves(int player, Dice dice, Board board) {
    LinkedHashSet result = new LinkedHashSet();
    result.addAll(generateMoves(new Move(dice, board, player)));
   
     return(Move[]) result.toArray(new Move[0]);
  }
  
  protected static Collection generateMoves(Move base) {
    Collection result = new LinkedHashSet();
    
    // if this is a full move, or no more moves are possible,
    // return just this move
    
    // if the move paramters are avilable or move is not possible
    if (base.isFull() || (! base.movePossible())) {
      result.add(base);
      return result;
    }

    // get the current boards
    Board board = base.getCurrentBoard();
    int player = base.player;
    
    // otherwise, first try to do any off-bar moves
    if (board.getBar(base.player) > 0) {
    	
      for (int i=0; i<base.used.length; i++) 
        if (base.used[i] != base.USED) { 
          try {
            Move bar = new Move(base);
            int destination = board.getBase(board.getOtherPlayer(player)) + (bar.used[i]*board.getDirection(player));
           bar.addMovement(new BarMovement(player, destination));
            result.addAll(generateMoves(bar));
          } catch (IllegalMoveException e) {}
        }
    } else {
      // now try for any normal moves
    	// board is an array of boards as it quals getCurrentBoard
    	    //     currentboard.homeloacation of other player + direction of the player
      for (int i=board.getBase(board.getOtherPlayer(player))+   board.getDirection(player); 
    		  // is location i on the board. 
    		  board.onBoard(i); 
    		  i+=board.getDirection(player)) {
        if (board.getPieces(player, i) > 0) {
          if (base.used.length == 2) {
            for (int j=0; j<base.used.length; j++) 
              if (base.used[j] != base.USED) { 
                try {
                  Move normal = new Move(base);
                  int destination = i + board.getDirection(player) * base.used[j];
                  normal.addMovement(new NormalMovement(player, i, destination));
                  result.addAll(generateMoves(normal));
                } catch (IllegalMoveException e) {}
              }
          } else {
            try {
              Move normal = new Move(base);
              int destination = i + board.getDirection(player) * base.dice.getDie1();
           normal.addMovement(new NormalMovement(player, i, destination));
              result.addAll(generateMoves(normal));
            } catch (IllegalMoveException e) {}
          }
        }
      }
        
      // and lastly try any bear-off moves
      for (int i=board.getBase(player)-board.getDirection(player); board.inHomeQuadrant(i, player); i-=board.getDirection(player)) 
        if (board.getPieces(player, i) > 0) {
          try {
            Move bearoff = new Move(base);
            bearoff.addMovement(new BearOffMovement(player, i));
            result.addAll(generateMoves(bearoff));
          } catch (IllegalMoveException e) {}
        }
    }
        
    return result;
  }
  /**
   * Returns a String representation of this move
   *
   * @return a String
   */
  public String toString() {
    StringBuffer result = new StringBuffer();
    result.append("Move (" + (player == Board.WHITE ? "O" : "X") + "): ");
    
    for (int i=0; i<movements.length; i++) {
      int j = i+1;
      
      for (; j<movements.length && movements[j] != null && movements[j].equals(movements[i]); j++) {}
      
      result.append((movements[i] == null ? "? " : movements[i] + " "));
      
      if (j > i+1) {
        result.append("(" + (j-i) + ") ");
        i = j-1;
      }
    }
        
    return result.toString();
  }}


