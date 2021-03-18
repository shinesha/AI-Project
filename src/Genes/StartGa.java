/**
 * parts of this class were obtained from the following under a GNU public license. 
 * Ref: https://github.com/
 */

package Genes;

import java.io.IOException;

/**
 * Main
 * 
 * The main class to run for the genetic algorithm to run
 * GA parameters should be changed within ParamStats class. 
 *  
 */
public class StartGa {

	public static void main(String[] args) {

		// initialise population
		SteadyState popA = new SteadyState(ParamStats.getPopulationSize(), true);
		// Get fittest
		popA.EvalFit();
		
		IndivChromas fittest = popA.getFittest();
		// Elitism, Tournament Selection, Crossover, Mutation
		popA = SteadyState.gaSteps(popA);
		
		
		fittest = popA.getFittest();
		// Repeat elistims, Tournamnet Selection, Crossover and Mutation for the 
		// number of generations selected. 
		for(int x = 0; x<ParamStats.numOfGens()-1;x++){
			
			popA = SteadyState.gaSteps(popA);
		
			fittest = popA.getFittest();
					
		}
		// outputs the weights of the optimal individual for the generation span. 
	    System.out.println(fittest.getAttribute(1).getValue());
	    System.out.println(fittest.getAttribute(2).getValue());
	    System.out.println(fittest.getAttribute(3).getValue());
	    System.out.println(fittest.getAttribute(4).getValue());
	    System.out.println(fittest.getAttribute(5).getValue());
	    System.out.println(fittest.getAttribute(6).getValue());
        System.out.println("this is to strong  "  +  popA.getFittest().toString());
        System.out.println(popA.getFittest().getAttribute(1).getValue());
        System.out.println(popA.getFittest().getAttribute(2).getValue());
        System.out.println(popA.getFittest().getAttribute(3).getValue());
        System.out.println(popA.getFittest().getAttribute(4).getValue());
        System.out.println(popA.getFittest().getAttribute(5).getValue());
        System.out.println(popA.getFittest().getAttribute(6).getValue());
        System.out.println(popA.getFittest().getAttribute(7).getValue());
        System.out.println(popA.getFittest().getAttribute(8).getValue());
        System.out.println(popA.getFittest().getAttribute(9).getValue());
        System.out.println(popA.getFittest().getAttribute(10).getValue());
        System.out.println(popA.getFittest().getAttribute(11).getValue());
        System.out.println(popA.getFittest().getAttribute(12).getValue());
        System.out.println(popA.getFittest().getAttribute(13).getValue());
        System.out.println(popA.getFittest().getAttribute(14).getValue());
        System.out.println(popA.getFittest().getAttribute(15).getValue());
        System.out.println(popA.getFittest().getAttribute(16).getValue());
        System.out.println(popA.getFittest().getAttribute(16).getValue());
       
	}
}
