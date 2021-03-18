/**
 * parts of this class were obtained from the following under a GNU public license. 
 * Ref: https://github.com/
 */

package Genes;

/**
 * SteadyState Popoulation of Chromasome's/ Individuals. 
 */
public class SteadyState {

	// Each Chromasome. 
	IndivChromas[] Chromasome;

	/**
	 * Instantiates a new population.
	 *
	 * @param populationSize the population size
	 * @param initialise the initialise
	 */

	public SteadyState(int populationSize, boolean initialise) {

		Chromasome = new IndivChromas[populationSize];

		if(initialise){
			for(int x = 0; x<populationSize; x++){
				IndivChromas newIndivChromas = new IndivChromas();

				Chromasome[x] = newIndivChromas;
			}
		}
	}

	/**
	 * calculates fitness each chromasome 
	 * by playing against all others in population. 
	 */
	public void EvalFit(){
		IndivChromas.playAll(this);
	}

	/**
	 * Gets the fittest.
	 *
	 * @return the fittest
	 */
	public IndivChromas getFittest(){

		IndivChromas fittest = Chromasome[0];

		for(int x = 1;x<Chromasome.length; x++){
			if(fittest.getFitness() <= Chromasome[x].getFitness()){
				fittest=Chromasome[x];
			}
		}
		return fittest;
	}

	public int size(){
		return Chromasome.length;
	}

	public void saveIndivChromas(int index, IndivChromas indiv) {
		Chromasome[index] = indiv;
	}

	public IndivChromas getIndivChromas(int i) {
		return Chromasome[i];
	}

	public IndivChromas[] getPopulation(){
		return Chromasome;
	}

	public void setArray(IndivChromas[] indivs){
		Chromasome = indivs;
	}



	/**
	 * Evolve population.
	 * advances the population by one generation
	 * uses crossover and mutation to produce the new population
	 * @param pop the population to evolve
	 * @return the new population evolved
	 */
	public static SteadyState gaSteps(SteadyState popA) {

		System.out.println("GA - Elistism- Tournament - Crossover - Mutation");

		SteadyState newPopulation = new SteadyState(popA.size(), false);

		// Keep the best individual
		if (ParamStats.elitismOn()) {
			newPopulation.saveIndivChromas(0, popA.getFittest());
		}
		// Crossover population
		int retainElite;
		if (ParamStats.elitismOn()) {
			retainElite = 1;
		} else {
			retainElite = 0;
		}
		
		
		// Loop over the population size and create new individuals with the crossover function
		// the two individuals that are crossed over are the winners of the tournament selection

		for (int i = retainElite; i < popA.size(); i++) {

			IndivChromas indiv1 = tournamentSelection(popA);
			IndivChromas indiv2 = tournamentSelection(popA);
			
			IndivChromas newIndiv = crossover(indiv1, indiv2);

			newPopulation.saveIndivChromas(i, newIndiv);
		}

		// Mutate population

		for (int i = retainElite; i < newPopulation.size(); i++) {
			mutate(newPopulation.getIndivChromas(i));
		}

		newPopulation.EvalFit();

		return newPopulation;
	}
	
	
	/**
	 * Tournament selection.
	 * 
	 * Selects the individuals for crossover
	 * it does this by selecting a random population and playing them against each other until there is one victor
	 *
	 * @param pop the pop
	 * @return the individual
	 */


	public static IndivChromas tournamentSelection(SteadyState popA) {

		// Create a tournament population
		SteadyState tournament = new SteadyState(ParamStats.getTournamentSize(), false);

		// For each place in the tournament get a random individual
		for (int i = 0; i < ParamStats.getTournamentSize(); i++) {

			int randomId = (int) (Math.random() * popA.size());
			tournament.saveIndivChromas(i, popA.getIndivChromas(randomId));
		}
		
		//Get the fittest via battling against each other
		IndivChromas tournamentVictor = null;

		//until there is a winner
		while(tournamentVictor==null){

			//create a population for the winner of the round
			IndivChromas[] winnersOfRound = new IndivChromas[tournament.size()/2];

			IndivChromas winnerOfRound = null;
			//test the individuals
			int counter = 0;
			for(int i = 0; i < tournament.size(); i+=2){
				winnerOfRound = IndivChromas.getWinnerOf(null, null, tournament.getIndivChromas(i), tournament.getIndivChromas(i+1));
				winnersOfRound[counter++]=winnerOfRound;
			}

			//if there is only 1 left, then it is the winner!
			if(winnersOfRound.length==1){
				tournamentVictor = winnersOfRound[0];
			}
			//setting the tournament array to the winners of the round, so now only has the winners in
			else{
				tournament.setArray(winnersOfRound);
			}
		}
		return tournamentVictor;
	}

	/**
	 * Crossover.
	 * 
	 * Randomly takes one side or the others attributes according to the uniform rate
	 *
	 * @param indiv1 the indiv1
	 * @param indiv2 the indiv2
	 * @return the individual
	 */
	private static IndivChromas crossover(IndivChromas indiv1, IndivChromas indiv2) {


		//new Individual
		IndivChromas newIndiv = new IndivChromas();

		//loop the individuals attributes
		for(int x = 0; x<indiv1.getnumOfGenes(); x++){
			if (Math.random() <= ParamStats.getUniformRate()) {
				newIndiv.setAttribute(x, indiv1.getAttribute(x));
			} else {
				newIndiv.setAttribute(x, indiv2.getAttribute(x));
			}
		}
		return newIndiv;
	}


	/**
	 * mutate
	 * 
	 * Mutate the individual.
	 *
	 * gets a random number, if its <= to the mutation rate then switch the bit
	 *
	 * @param indiv the individual
	 */
	public static void mutate(IndivChromas indiv) {

		// get the indivs string
		char[] indivStr = indiv.getChromosome();

		//loop through all bits
		for(int x=0;x<indivStr.length;x++){
			//random chance
			if(Math.random() <= ParamStats.getMutationRate()){
				//swap the bit over
				if(indivStr[x]=='0'){
					indivStr[x]='1';
				}else{
					indivStr[x]='0';
				}
			}
		}
	}

	




	
}
