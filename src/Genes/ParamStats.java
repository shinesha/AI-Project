/**
 * parts of this class were obtained from the following under a GNU public license. 
 * Ref: https://github.com/
 */

package Genes;

/**
 * class to change GA input paramters, save winners and scores. 
 */

public class ParamStats {
	
	private static double uniformRateCache = -1;
	private static double mutationRateCache = -1;
	private static int tournamentSizeCache = -1;
	private static Boolean elitismCache = null;
	private static int generationsCache = -1;
	private static int populationSizeCache = -1;
	private static String savePathCache = null;
	
	IndivChromas gameVictor = null;
	
	double playerOneScore = 0;
	double playerTwoScore = 0;
	
	/**
	 * ParamStats default constructor
	 * 
	 * @param winner
	 * @param p1score
	 * @param p2score
	 */
	

	public ParamStats(IndivChromas player, double p1score, double p2Score){
		gameVictor =  player;
		playerOneScore = p1score;
		playerTwoScore = p2Score;
	
	}

	
	public static int numOfGens() {
		int generationsCache = 2;
		
		return generationsCache;
	}
	
	public static boolean  elitismOn() {
		if(elitismCache==null){
			elitismCache = Boolean.valueOf(true);
		}
		return elitismCache;
	}
	
    
    public static double getUniformRate() {
    	if(uniformRateCache==-1){
    		uniformRateCache = Double.valueOf(0.5);
    	}
		return uniformRateCache;
	}
	public static double getMutationRate() {
		if(mutationRateCache==-1){
			mutationRateCache = Double.valueOf(0.3);
		}
		return mutationRateCache;
	}
	
	public static int getTournamentSize() {
		if(tournamentSizeCache==-1){
			tournamentSizeCache = 8;
				
		}
		return tournamentSizeCache;
	}
	public static int getPopulationSize() {
		if(populationSizeCache==-1){
			populationSizeCache = Integer.valueOf(3);
		}
		return populationSizeCache; 
	}
	
	
	/**
	 * getVictor
	 * 
	 * @return individual
	 */
	public IndivChromas getVictor() {
		return gameVictor;
	}
	
	/**
	 * getPlayerOneScore
	 * 
	 * @return individual
	 */
	public double getPlayerOneScore(){
		return playerOneScore;
	}
}