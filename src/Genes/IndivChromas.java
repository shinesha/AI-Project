/**
 * parts of this class were obtained from the following under a GNU public license. 
 * Ref: https://github.com/
 */


package Genes;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import player.Player;


/**
 * Class that models Individual (AKA Chromasome) that decides the players move. 
 */
public class IndivChromas {

	//number of genes in a chromasome
	private int numOfGenes;

	//Strategy alligned to each gene	
	private GeneWeights[] GeneStrategies = null;

	///chromosome
	private char[] chromosome;

	/// fitness
	private double fitness = 0;

	/**
	 * Instantiates a new Chromasome. 
	 */
	public IndivChromas(){

		//add attributes to the array
		GeneStrategies = new GeneWeights[]{
				new GeneWeights("consecutiveBlocks"), 					
				new GeneWeights("numberOflocationsGreaterThanTwo"), 		
				new GeneWeights("numberOfOppositesOnMiddleBar"), 		
				new GeneWeights("blockAnOpponent"),				       
				new GeneWeights("numberOflocationsInHomeBoardGreaterThanTwo"), 
				new GeneWeights("stragglersInStartingOneSix"),			
				new GeneWeights(" numberOfBlotsInHomeBoard"),			
				new GeneWeights("numberOfBlotsGreatThan3"),				
				new GeneWeights("bearAPiece"),							
				new GeneWeights("twoOneSplitPlayInitialMove"),		
				new GeneWeights("twoOneSlotPlayInitialMove"),		
				new GeneWeights("threeOneInitial"),						
				new GeneWeights("threeTwoSplitThree"),					
				new GeneWeights("threeTwoSplitTwo"),						
				new GeneWeights("threeTwoSplit2Builders"),				
				new GeneWeights("fourOneSplit"),						
				new GeneWeights("fourOneSlotBuilder"),					
				new GeneWeights("fourTwo"),								

		};

		numOfGenes = GeneStrategies.length;

		//convert to binary string
		chromosome = IndivChromas.convertFromIntToBinaryCharAry(GeneStrategies);
	}

	/**
	 * Gets the fitness.
	 *
	 * @return the fitness
	 */
	public double getFitness(){
		return fitness;
	}

	/**
	 * Sets the fitness.
	 *
	 * @param fit the new fitness
	 */
	public void setFitness(double fit){
		fitness = fit;
	}


	/**
	 * Gets the number of attributes.
	 *
	 * @return the number of attributes
	 */
	public int getnumOfGenes(){
		return numOfGenes;
	}

	public String toString(){
		String result = "Indiviudal with chromosome string: "+String.valueOf(chromosome) +" and fitness of: "+fitness;
		for(int x = 0; x<GeneStrategies.length; x++){
			result+="| "+GeneStrategies[x].getName()+": "+GeneStrategies[x].getValue();
		}
		return result;
	}

	/**
	 * Gets the chromosome.
	 *
	 * @return the chromosome
	 */
	public char[] getChromosome() {
		return chromosome;
	}


	/**
	 * setAttribute
	 * 
	 * @param attr
	 * @param data
	 */
	public void setAttribute(int attr, GeneWeights data){
		GeneStrategies[attr] = data;
		updateToBinary();
	}

	/**
	 * getAttribute
	 * 
	 * @param pos
	 * @return
	 */
	public GeneWeights getAttribute(int pos){
		return GeneStrategies[pos];

	}

	/**
	   * Calculates fitness of population
	   *
	   * @param population
	   * @return fitness for each individual chromasome
	   */
	public static void playAll(SteadyState popA){

		IndivChromas player1;
		IndivChromas player2;
		double perCentWins;

		//looping the whole population, x is the one we are measuring
		for(int x = 0; x<popA.Chromasome.length; x++){
			System.out.println("player " + x);
			//the one we are generating the fitness of
			player1 = popA.Chromasome[x];

			//will be added to over the course of the games
			perCentWins = 0;

			//looping the whole population, y is the one currently playing against x
			for(int y = 0; y<popA.Chromasome.length; y++){
				
				//make sure its not playing itself
				if(y!=x){
					System.out.println(" playing against " + y);
					//Opponent individual
					player2 = popA.Chromasome[y];

					@SuppressWarnings("static-access")
					ParamStats gs = GeneticStart.playIndividualsVsEachOther(player1, player2);
					//  (this was genetic start)
					perCentWins+=gs.getPlayerOneScore();
				}
			}
			if(perCentWins!=0){

				//divides by the number of games and therefore gets an average
				perCentWins=perCentWins/(popA.size()-1);

			}
			player1.setFitness(perCentWins);
		}
	}

	public static IndivChromas getWinnerOf(Player player1, Player player2, IndivChromas i1, IndivChromas i2) {

		ParamStats winner = (ParamStats) Genes.GeneticStart.playIndividualsVsEachOther(i1, i2);
		return winner.getVictor();
	}

	/**
	 * Update to binary.
	 */
	public void updateToBinary() {
		chromosome = IndivChromas.convertFromIntToBinaryCharAry(GeneStrategies);
	}

	/**
	 * Convert from int to binary char array.
	 *
	 * @param numberToConvert the number to convert
	 * @return the string
	 */

	public static char[] convertFromIntToBinaryCharAry (GeneWeights[] numbersToConvert){
		StringBuffer binaryString = new StringBuffer();
		for(int x = 0; x<numbersToConvert.length;x++){
			binaryString.append(IndivChromas.convertFromIntToBinaryStr(numbersToConvert[x].getValue()));

		}
		return binaryString.toString().toCharArray();
	}

	/**
	 * Convert from int to binary str.
	 *
	 * @param numberToConvert the number to convert
	 * @return the string
	 */

	public static String convertFromIntToBinaryStr (int numberToConvert){
		StringBuffer binaryString = new StringBuffer();

		binaryString.append(Integer.toBinaryString(numberToConvert));
		for(int n=binaryString.length(); n<7; n++) {
			binaryString.insert(0, "0");
		}
		return binaryString.toString();
	}

}
