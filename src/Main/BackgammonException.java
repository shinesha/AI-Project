
/**
 * reference: http://modelai.gettysburg.edu/2013/tdgammon/doc/overview-summary.html 
 * 
 */
package Main;

public abstract class BackgammonException extends RuntimeException {
  protected BackgammonException(String s) {
    super(s);
  }
}