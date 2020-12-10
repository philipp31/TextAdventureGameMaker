package student;

/**
 * This class allows it to safe a decomposition.
 *
 * @author Philipp Slebioda
 */
public class Decomposition {
	
	private String in;
	private String out1;
	private String out2;
	private String description;

	/**
	 * Constructor of this class initializes all attributes.
	 * @param description The description to save
	 * @param in The input
	 * @param out1 The first output
	 * @param out2 The second output
	 */
	public Decomposition(String in, String out1, String out2, String description) {
		this.in = in;
		this.out1 = out1;
		this.out2 = out2;
		this.description = description;
	}

	/**
	 * getter-method for the input-id.
	 * @return input-id
	 */
	public String getIn() {
		return in;
	}

	/**
	 * getter-method for the first output-id.
	 * @return output-id
	 */
	public String getOut1() {
		return out1;
	}
	
	/**
	 * getter-method for the second output-id.
	 * @return output-id
	 */
	public String getOut2() {
		return out2;
	}

	/**
	 * getter-method for the description.
	 * @return description
	 */
	public String getDescription() {
		return description;
	}
}
