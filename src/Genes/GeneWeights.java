/**
 * parts of this class were obtained from the following under a GNU public license. 
 * Ref: https://github.com/
 */

package Genes;

/**
 * This class models the genes within the chromasome also known as Individual.
 * 
 */
public class GeneWeights {
	
	// Strategy name of gene weight
	private String name = "";
	// Value of gene weight
	private int value = 0;
	
	/**
	 * GeneWeight Constructor. 
	 * 
	 * @param n - the name
	 */
	public GeneWeights (String n){
		name = n;
		value = (int) (Math.random()*100);
	}
	
	/**
	 * getName
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * setName
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * getValue
	 * 
	 * @return value
	 */
	public int getValue() {
		return value;
	}
	/**
	 * setValue
	 * 
	 * @param value
	 */
	public void setValue(int value) {
		this.value = value;
	}
	
	@Override
	public String toString(){
		return name+": "+value;
	}
}