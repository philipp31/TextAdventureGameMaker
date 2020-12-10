package student;

/**
 * This class allows it to safe a transformation.
 *
 * @author Philipp Slebioda
 */
public class Transformation {
	
	private String description;
	private String in1;
	private String in2;
	private String out1;
	private String out2;
	
	/**
	 * Constructor of this class initializes all attributes.
	 * @param description The description to save
	 * @param in1 The first input
	 * @param in2 The second input
	 * @param out1 The first output
	 * @param out2 The second output
	 */
	public Transformation(String in1, String in2, String out1, String out2, String description) {
		this.in1 = in1;
		this.in2 = in2;
		this.out1 = out1;
		this.out2 = out2;
		this.description = description;
	}
	
	/**
	 * getter-method for the description.
	 * @return description of this composition
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * getter-method for the first input of the transformation.
	 * @return first input id
	 */
	public String getIn1() {
		return in1;
	}

	/**
	 * getter-method for the second input of the transformation.
	 * @return second input id
	 */
	public String getIn2() {
		return in2;
	}

	/**
	 * getter-method for the first output of the transformation.
	 * @return first output id
	 */
	public String getOut1() {
		return out1;
	}

	/**
	 * getter-method for the second output of the transformation.
	 * @return second output id
	 */
	public String getOut2() {
		return out2;
	}
}
