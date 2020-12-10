package student;

/**
 * Abstract class that gives Objects (Scenerys and Items) a id.
 * 
 * @author Philipp Slebioda
 */ 
public abstract class ObjectType {

	/**
	 * this attribute is the id of a object-type.
	 */
	private String id;
	
	/**
	 * getter method for the id attribute.
	 * @return id The identifier of this class
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * setter method for the id attribute.
	 * @param id The id you want to set
	 */
	public void setId(String id) {
		this.id = id;
	}
}
