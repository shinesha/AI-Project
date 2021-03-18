package player;
import Board.Board;
import Jama.Matrix;
import Genes.GeneticStart;


/** 
 * Evaluation function for AI player.
 */

public class EvalFuncTest2 {

		
public static double EvalPercentage (Board possibleBoard){
	
	EvalFuncTest2 A = new EvalFuncTest2();
	int wA = A.consecutiveBlocks(possibleBoard);

	int wB = A.numberOflocationsGreaterThanTwo(possibleBoard);
	

	int wC = A.numberOfWhitesOnMiddleBar(possibleBoard);

	
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
    int wP=A.threeTwoSplit2Builders(possibleBoard);
    int wQ = A.fourOneSplit(possibleBoard);
    int wR = A.fourOneSlotBuilder(possibleBoard);
    int wS=A.fourTwo(possibleBoard);

	


	int w1A = Genes.GeneticStart.indivObj2.getAttribute(1).getValue();
	
	int w2A = Genes.GeneticStart.indivObj2.getAttribute(2).getValue();
	int w3A = Genes.GeneticStart.indivObj2.getAttribute(3).getValue();
	int w4A = Genes.GeneticStart.indivObj2.getAttribute(4).getValue();
	int w5A = Genes.GeneticStart.indivObj2.getAttribute(5).getValue();
	int w6A = Genes.GeneticStart.indivObj2.getAttribute(6).getValue();
	int w7A = Genes.GeneticStart.indivObj2.getAttribute(7).getValue();
	int w8A = Genes.GeneticStart.indivObj2.getAttribute(8).getValue();
	int w9A = Genes.GeneticStart.indivObj2.getAttribute(9).getValue();
	int w10A = Genes.GeneticStart.indivObj2.getAttribute(10).getValue();
	   
	int w11A = Genes.GeneticStart.indivObj2.getAttribute(11).getValue();
	int w12A = Genes.GeneticStart.indivObj2.getAttribute(12).getValue();
	int w13A = Genes.GeneticStart.indivObj2.getAttribute(13).getValue();
	int w14A = Genes.GeneticStart.indivObj2.getAttribute(14).getValue();
	int w15A = Genes.GeneticStart.indivObj2.getAttribute(15).getValue();
	int w16A = Genes.GeneticStart.indivObj2.getAttribute(16).getValue();
	int w17A = Genes.GeneticStart.indivObj2.getAttribute(17).getValue();
	
	
	double[][] arrA = { {wA, wB, wC, wD, wE, wF, wG, wJ, wK, wL, wM, wN, wO, wP, wQ, wR, wS} };
    double[][] arrB = { {w1A}, {w2A}, {w3A}, {w4A}, {w5A}, {w6A}, {w7A}, {w8A}, {w9A}, {w10A}, {w11A}, {w12A}, {w13A}, {w14A}, {w15A}, {w16A}
    , {w17A}};
    Matrix D  = new Matrix(arrA, 1, 17);
    Matrix B = new Matrix(arrB, 17, 1);

//Multiply matrix a by matrix b to get matrix c

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
    	if ( possibleBoard.getBlackPieces(i)>=2 && possibleBoard.getBlackPieces(i+1)>=2)
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
	 for (int i = 0; i< 23;  i++) {
		 if ((possibleBoard.getBlackPieces(i)) >=2)
			 counter ++; 
	 }
	return counter;}



/**
 * how many whites (opposite players colour) will be placed on miidle bar
 * 
 * @param possible boards states
 * @return blacks on middle bar
 */

public int numberOfWhitesOnMiddleBar (Board possibleBoard){
	int A = possibleBoard.getWhiteBar();
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
for (int i =0; i<=5; i ++){
	if (possibleBoard.getColor(i) == Board.BLACK && possibleBoard.getBlackPieces(i)>=2)
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
   int A = possibleBoard.getBlackPieces(23);
   int B = possibleBoard.getBlackPieces(22);
   int C = possibleBoard.getBlackPieces(21);
   int D = possibleBoard.getBlackPieces(20);
   int E = possibleBoard.getBlackPieces(19);
   int F = possibleBoard.getBlackPieces(18);
 
   int sum = neg -( A + B+ C+ D+ E +F);


	return sum;}


/**
 * how many single pieces in the home board. 
 * 
 * @param possible boards states
 * @return single pieces in home board
 */

public int numberOfBlotsInHomeBoard(Board possibleBoard){
int counter = 0;

for (int i =0; i<=5; i ++){
 if (possibleBoard.getBlackPieces(i)==1){
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
 for (int i =0; i<=23; i ++) {
    if (possibleBoard.getPieces(possibleBoard.BLACK, i)== 1)
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
	 for (int i = 6; i<= 23;  i++) {
		 if (possibleBoard.getBlackPieces(i) ==0){
			 for (int j=0; j<5; j++){
				 if (possibleBoard.getBlackPieces(j)>=1){
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
	
	 for (int i = 0; i<= 23;  i++) {
		 if (possibleBoard.getBlackPieces(23) ==1 &&
				 possibleBoard.getBlackPieces(22) ==1 &&
				 possibleBoard.getBlackPieces(12) == 3 &&
				possibleBoard.getBlackPieces(10) == 1 &&
				 possibleBoard.getBlackPieces(7) == 3 &&
				 possibleBoard.getBlackPieces(5)== 5){
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
	
	 for (int i = 0; i<= 23;  i++) {
		 if (possibleBoard.getBlackPieces(23) ==2 &&
				
				 possibleBoard.getBlackPieces(12) == 4 &&
				 possibleBoard.getBlackPieces(10) == 1 &&
						
				possibleBoard.getBlackPieces(8) == 3 &&
				possibleBoard.getBlackPieces(5)== 5 &&
				possibleBoard.getBlackPieces(4)== 1){
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
	
	 for (int i = 0; i<= 23;  i++) {
		 if (possibleBoard.getBlackPieces(23) ==2 &&
				
				 possibleBoard.getBlackPieces(12) == 5 &&
				 
						
				possibleBoard.getBlackPieces(7) == 2 &&
				possibleBoard.getBlackPieces(5)== 4 &&
				possibleBoard.getBlackPieces(4)== 2){
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
	
	 for (int i = 0; i<= 23;  i++) {
		 if (possibleBoard.getBlackPieces(23) ==1 &&
				 possibleBoard.getBlackPieces(20) ==1 &&
				 possibleBoard.getBlackPieces(12) == 4 &&
			 possibleBoard.getBlackPieces(10) ==1 &&
						
				possibleBoard.getBlackPieces(7) == 3 &&
				possibleBoard.getBlackPieces(5)== 5){
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
	
	 for (int i = 0; i<= 23;  i++) {
		 if (possibleBoard.getBlackPieces(23) ==2 &&
				
				possibleBoard.getBlackPieces(12) == 3 &&
			    possibleBoard.getBlackPieces(10) ==1 &&
			    possibleBoard.getBlackPieces(9) ==1 &&	
				possibleBoard.getBlackPieces(7) == 3 &&
				possibleBoard.getBlackPieces(5)== 5){
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
	
	 for (int i = 0; i<= 23;  i++) {
		 if (possibleBoard.getBlackPieces(23) ==1 &&
				
				 possibleBoard.getBlackPieces(21) == 1&&
			 possibleBoard.getBlackPieces(12) ==4 &&
					 possibleBoard.getBlackPieces(9) ==1 &&	
				possibleBoard.getBlackPieces(8) == 3 &&
				possibleBoard.getBlackPieces(7)== 5){
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
	
	 for (int i = 0; i<= 23;  i++) {
		 if (possibleBoard.getBlackPieces(23) ==1 &&
				
			    possibleBoard.getBlackPieces(22) == 1&&
			    possibleBoard.getBlackPieces(12) ==4 &&
				possibleBoard.getBlackPieces(8) ==1 &&	
				possibleBoard.getBlackPieces(7) == 3 &&
				possibleBoard.getBlackPieces(5)== 5){
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
	
	 for (int i = 0; i<= 23;  i++) {
		 if (possibleBoard.getBlackPieces(23) ==2 &&
	 
			    possibleBoard.getBlackPieces(12) ==4 &&
				possibleBoard.getBlackPieces(8) ==1 &&	
				possibleBoard.getBlackPieces(7) == 3 &&
				possibleBoard.getBlackPieces(5)== 4 &&
			    possibleBoard.getBlackPieces(4) == 1 ){
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
	
	 for (int i = 0; i<= 23;  i++) {
		 if (possibleBoard.getBlackPieces(23) ==2 &&
				 
				    possibleBoard.getBlackPieces(12) ==5 &&	
					possibleBoard.getBlackPieces(7) == 2 &&
					possibleBoard.getBlackPieces(5)== 4 &&
				    possibleBoard.getBlackPieces(3) == 2 ){
		         counter++;
				 }
			 }
	 
	
	return counter;}
}



