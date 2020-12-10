package student;

/**
 * This class allows it to safe a composition.
 *
 * @author Philipp Slebioda
 */
public class Composition {

	private String description;
	private String in1;
	private String in2;
	private String out;
	
	/**
	 * Constructor of this class initializes all attributes.
	 * @param description The description to save
	 * @param in1 The first input
	 * @param in2 The second input
	 * @param out The output
	 */
	public Composition(String description, String in1, String in2, String out) {
		this.description = description;
		this.in1 = in1;
		this.in2 = in2;
		this.out = out;
	}
	
	/**
	 * This method is a getter for the description.
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * This method is a getter for the first input.
	 * @return the first input
	 */
	public String getIn1() {
		return in1;
	}

	/**
	 * This method is a getter for the second input.
	 * @return the second input
	 */
	public String getIn2() {
		return in2;
	}

	/**
	 * This method is a getter for the output.
	 * @return the output of the composition
	 */
	public String getOut() {
		return out;
	}
}
