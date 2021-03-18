/**
 * reference: http://modelai.gettysburg.edu/2013/tdgammon/doc/overview-summary.html 
 * 
 */

package move;


import Main.BackgammonException;


public class IllegalMoveException extends BackgammonException {
  public IllegalMoveException(String s) {
    super(s);
  }
} 
