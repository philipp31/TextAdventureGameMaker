package student;

/**
 * This class allows it to save item-types with all informations.
 *
 * @author Philipp Slebioda
 */
public class ItemType extends ObjectType {
	
	private String description;
	
	/**
	 * The constructor of this class sets the id and the description.
	 * @param id The wanted identifier
	 * @param description The wanted description
	 */
	public ItemType(String id, String description) {
		this.setId(id);
		this.description = description;
	}
	
	/**
	 * This method is a getter for the description.
	 * @return The description attribute
	 */
	public String getDescription() {
		return description;
	}
}
