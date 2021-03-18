package player;
import Board.Board;
import Jama.Matrix;
import Genes.GeneticStart;


/** 
 * Evaluation function for the AIPlayer2.
 * 
 */

public class EvalFuncTest {

public static double EvalPercentage (Board possibleBoard){
	
	EvalFuncTest A = new EvalFuncTest();
	int wA = A.consecutiveBlocks(possibleBoard);

	
	int wB = A.numberOflocationsGreaterThanTwo(possibleBoard);
	

	int wC = A.numberOfBlacksOnMiddleBar(possibleBoard);

	
	int wD = A.numberOflocationsInHomeBoardGreaterThanTwo(possibleBoard);
	
	
	int wE = A.stragglersInStartingOneSix(possibleBoard);
	
	
	int wF = A.numberOfBlotsInHomeBoard(possibleBoard);
	

	int wG = A.numberOfBlotsGreatThan3(possibleBoard);
   
    int wJ = A.bearAPiece(possibleBoard);
    int wK= A.twoOneSplitPlayInitialMove(possibleBoard);
    int wL = A.twoOneSlotPlayInitialMove(possibleBoard);
    int wM = A.threeOneInitial(possibleBoard);
    int wN =A.threeTwoSplitThree(possibleBoard);
    int wO =A.threeTwoSplitTwo(possibleBoard);
    int wP =A.threeTwoSplit2Builders(possibleBoard);
    int wQ = A.fourOneSplit(possibleBoard);
    int wR = A.fourOneSlotBuilder(possibleBoard);
    int wS=A.fourTwo(possibleBoard);
    
    
    
    
    
    
    
	int w1A = Genes.GeneticStart.indivObj1.getAttribute(1).getValue();
   
	int w2A = Genes.GeneticStart.indivObj1.getAttribute(2).getValue();
	int w3A = Genes.GeneticStart.indivObj1.getAttribute(3).getValue();
	int w4A = Genes.GeneticStart.indivObj1.getAttribute(4).getValue();
	int w5A = Genes.GeneticStart.indivObj1.getAttribute(5).getValue();
	int w6A = Genes.GeneticStart.indivObj1.getAttribute(6).getValue();
	int w7A = Genes.GeneticStart.indivObj1.getAttribute(7).getValue();
	int w8A = Genes.GeneticStart.indivObj1.getAttribute(8).getValue();
	int w9A = Genes.GeneticStart.indivObj1.getAttribute(9).getValue();
	int w10A = Genes.GeneticStart.indivObj1.getAttribute(10).getValue();
	   
	int w11A = Genes.GeneticStart.indivObj1.getAttribute(11).getValue();
	int w12A = Genes.GeneticStart.indivObj1.getAttribute(12).getValue();
	int w13A = Genes.GeneticStart.indivObj1.getAttribute(13).getValue();
	int w14A = Genes.GeneticStart.indivObj1.getAttribute(14).getValue();
	int w15A = Genes.GeneticStart.indivObj1.getAttribute(15).getValue();
	int w16A = Genes.GeneticStart.indivObj1.getAttribute(16).getValue();
	int w17A = Genes.GeneticStart.indivObj1.getAttribute(17).getValue();

	
	double[][] arrA = { {wA, wB, wC, wD, wE, wF, wG, wJ, wK, wL, wM, wN, wO, wP, wQ, wR, wS} };
    double[][] arrB = { {w1A}, {w2A}, {w3A}, {w4A}, {w5A}, {w6A}, {w7A}, 
    		{w8A}, {w9A}, {w10A}, {w11A}, {w12A}, {w13A}, {w14A}, {w15A}, {w16A}
    , {w17A}};
    Matrix D  = new Matrix(arrA, 1, 17);
    Matrix B = new Matrix(arrB, 17, 1);



    Matrix c = D.times(B);

	double rnorm = c.normInf();
	
	
	return rnorm;

}

/**
 * how many consective blocks of at least 2 will be created if this
 * move is taken. 
 * 
 * @param possible boards states
 * @return number of consecutives spikes with at least 2. 
 */
public int consecutiveBlocks(Board possibleBoard) {
	 
	int counter = 0;
	
    for (int i = 0; i< 23; i++){
    	if ( possibleBoard.getWhitePieces(i)>=2 && possibleBoard.getWhitePieces(i+1)>=2)
    		counter++;
		}
    
	return counter;}


/**
 * number of spikes with at least 2 pieces. 
 * 
 * @param possible board states
 * @return number of spikes with 2 pieces. 
 */
public int numberOflocationsGreaterThanTwo (Board possibleBoard){
	int counter = 0;
	 for (int i = 0; i< 24;  i++) {
		 if ((possibleBoard.getWhitePieces(i)) >=2)
			 counter ++; 
	 }
	return counter;}


/**
 * how many blacks (opposite players colour) will be placed on miidle bar
 * 
 * @param possible boards states
 * @return blacks on middle bar
 */
public int numberOfBlacksOnMiddleBar (Board possibleBoard){
	int A = possibleBoard.getBlackBar();
	return A;
}

/**
 * how many  home board locations with have more than 2 pieces.
 * 
 * @param possible boards states
 * @return number of home board locations greater than 2
 */

public int numberOflocationsInHomeBoardGreaterThanTwo(Board possibleBoard){
int counter = 0;
for (int i =18; i<=23; i ++){
	if (possibleBoard.getColor(i) == Board.WHITE && possibleBoard.getWhitePieces(i)>=2)
        counter ++;
}

return counter;
}


/**
 * pieces left in starting 6 spikes.
 * 
 * @param possible boards states
 * @return negative of number of pieces left in starting 6
 */

public int stragglersInStartingOneSix(Board possibleBoard) {
   int neg = 0;
   int A = possibleBoard.getWhitePieces(0);
   int B = possibleBoard.getWhitePieces(1);
   int C = possibleBoard.getWhitePieces(2);
   int D = possibleBoard.getWhitePieces(3);
   int E = possibleBoard.getWhitePieces(4);
   int F = possibleBoard.getWhitePieces(5);

   int sum = neg - (A + B+ C+ D+ E +F);
	return sum;}


/**
 * how many single pieces in the home board. 
 * 
 * @param possible boards states
 * @return single pieces in home board
 */

public int numberOfBlotsInHomeBoard(Board possibleBoard){
int counter = 0;

for (int i =18; i<24; i ++){
 if (possibleBoard.getWhitePieces(i)==1){
 counter ++;
}}

return counter;}



/**
 * number of spikes with more than 3 pieces. 
 * 
 * @param possible boards states
 * @return number of spikes with more than 3 pieces
 */
public int numberOfBlotsGreatThan3(Board possibleBoard){

int counter = 0;
int counter1 = 0; 
 for (int i =0; i<24; i ++) {
    if (possibleBoard.getPieces(possibleBoard.WHITE, i)== 1)
        counter --;
    if (counter> 3 ){
        counter1--;
}}

return counter1;
}

/**
 * If pieces can be beared than how many
 * 
 * @param possible boards states
 * @return numbver of peices thant can be beared if this board state is selected. 
 */
public int bearAPiece (Board possibleBoard){
	int counter = 0;
	 for (int i = 0; i< 17;  i++) {
		 if (possibleBoard.getWhitePieces(i) ==0){
			 for (int j=18; j<24; j++){
				 if (possibleBoard.getWhitePieces(j)>=1){
					 counter ++;
				 }
			 }
		 }	 
	 }
	return counter;}


/**
 * opening strategy move
 * 
 * @param possible boards states
 *
 */

public int twoOneSplitPlayInitialMove (Board possibleBoard){
	int counter = 0;
	
	 for (int i = 0; i< 24;  i++) {
		 if (possibleBoard.getWhitePieces(0) ==1 &&
				 possibleBoard.getWhitePieces(1) == 1 &&
				 possibleBoard.getWhitePieces(11) == 4 &&
				 possibleBoard.getWhitePieces(13)== 1 &&
				 possibleBoard.getWhitePieces(16)==3 &&
				 possibleBoard.getWhitePieces(18) ==5){
		         counter++;
				 }
			 }
	return counter;}


/**
 * opening strategy move
 * 
 * @param possible boards states
 *
 */
public int twoOneSlotPlayInitialMove (Board possibleBoard){
	int counter = 0;
	
	 for (int i = 0; i<= 24;  i++) {
		 if (possibleBoard.getWhitePieces(0) ==2 &&
				
				 possibleBoard.getWhitePieces(11) == 4 &&
				 possibleBoard.getWhitePieces(13)== 1 &&
				 possibleBoard.getWhitePieces(16)==3 &&
				 possibleBoard.getWhitePieces(18) == 4 &&
				 possibleBoard.getWhitePieces(19) == 1 ){
		         counter++;
				 }
			 }
	return counter;}

/**
 * opening strategy move
 * 
 * @param possible boards states
 *
 */
public int threeOneInitial (Board possibleBoard){
	int counter = 0;
	
	 for (int i = 0; i<= 24;  i++) {
		 if (possibleBoard.getWhitePieces(0) ==2 &&
				
				 possibleBoard.getWhitePieces(11) == 5 &&
				 possibleBoard.getWhitePieces(16)==2 &&
				 possibleBoard.getWhitePieces(18) == 4 &&
				 possibleBoard.getWhitePieces(19) == 2 ){
		         counter++;
				 }
			 }
	return counter;}

/**
 * opening strategy move
 * 
 * @param possible boards states
 *
 */
public int threeTwoSplitThree (Board possibleBoard){
	int counter = 0;
	
	 for (int i = 0; i<= 24;  i++) {
		 if (possibleBoard.getWhitePieces(1) ==1 &&
				 possibleBoard.getWhitePieces(4) ==1 &&
				 possibleBoard.getWhitePieces(12) == 4 &&
				possibleBoard.getWhitePieces(14) == 1 &&
				 possibleBoard.getWhitePieces(17)==3 &&
				 possibleBoard.getWhitePieces(19) == 5 ){
		         counter++;
				 }
			 }
	return counter;}

/**
 * opening strategy move
 * 
 * @param possible boards states
 *
 */

public int threeTwoSplitTwo (Board possibleBoard){
	int counter = 0;
	
	 for (int i = 0; i<= 24;  i++) {
		 if (possibleBoard.getWhitePieces(0) ==1 &&
				 possibleBoard.getWhitePieces(2) ==1 &&
				 possibleBoard.getWhitePieces(11) == 4 &&
				possibleBoard.getWhitePieces(14) == 1 &&
				 possibleBoard.getWhitePieces(16)==3 &&
				 possibleBoard.getWhitePieces(18) == 5 ){
		         counter++;
				 }
			 }
	return counter;}

/**
 * opening strategy move
 * 
 * @param possible boards states
 *
 */
public int threeTwoSplit2Builders (Board possibleBoard){
	int counter = 0;
	
	 for (int i = 0; i<= 24;  i++) {
		 if (possibleBoard.getWhitePieces(1) ==2 &&
				 possibleBoard.getWhitePieces(12) == 3 &&
				possibleBoard.getWhitePieces(14) == 1 &&
				possibleBoard.getWhitePieces(15) == 1 &&
				 possibleBoard.getWhitePieces(17)==3 &&
				 possibleBoard.getWhitePieces(19) == 5 ){
		         counter++;
				 }
			 }
	return counter;}

/**
 * opening strategy move
 * 
 * @param possible boards states
 *
 */
public int fourOneSplit (Board possibleBoard){
	int counter = 0;
	
	 for (int i = 0; i< 24;  i++) {
		 if (possibleBoard.getWhitePieces(1) ==1 &&
				 possibleBoard.getWhitePieces(2) == 1 &&
				possibleBoard.getWhitePieces(12) == 4 &&
				possibleBoard.getWhitePieces(16) == 1 &&
				 possibleBoard.getWhitePieces(17)==3 &&
				 possibleBoard.getWhitePieces(19) == 5 ){
		         counter++;
				 }
			 }
	return counter;}

/**
 * opening strategy move
 * 
 * @param possible boards states
 *
 */
public int fourOneSlotBuilder (Board possibleBoard){
	int counter = 0;
	
	 for (int i = 0; i< 24;  i++) {
		 if (possibleBoard.getWhitePieces(1) ==2 &&
				 possibleBoard.getWhitePieces(12) == 4 &&
				possibleBoard.getWhitePieces(16) == 1 &&
			
				 possibleBoard.getWhitePieces(17)==3 &&
				 possibleBoard.getWhitePieces(19) == 5 &&
				 possibleBoard.getWhitePieces(20)==1){
		         counter++;
				 }
			 }
	return counter;}

/**
 * opening strategy move
 * 
 * @param possible boards states
 *
 */
public int fourTwo (Board possibleBoard){

	int counter = 0;
	
	 for (int i = 0; i< 24;  i++) {
		 if (possibleBoard.getWhitePieces(0) ==2 &&
				 possibleBoard.getWhitePieces(11) == 5 &&
		
			
				 possibleBoard.getWhitePieces(16)==2 &&
				 possibleBoard.getWhitePieces(18) == 4 &&
				 possibleBoard.getWhitePieces(20)==2){
		         counter++;
				 }
			 }
	 
	
	return counter;}
}
