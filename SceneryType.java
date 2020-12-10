package student;

/**
 * Class that allows it to save scenery-types.
 * 
 * @author Philipp Slebioda
 */
public class SceneryType extends ObjectType {
	
	private String description;

	/**
	 * Constructor of this class sets the id and the description.
	 * @param id The id that you want to safe
	 * @param description The description you want to safe
	 */
	public SceneryType(String id, String description) {
		this.setId(id);
		this.description = description;
	}
	
	/**
	 * This is a getter-method for the description.
	 * @return description The attribute of this class
	 */
	public String getDescription() {
		return description;
	}
}
