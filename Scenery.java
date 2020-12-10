package student;

/**
 * This class allows it to safe a scenery-object.
 *
 * @author Philipp Slebioda
 */
public class Scenery extends ObjectType {

	private int x;
	private int y;
	
	/**
	 * Constructor of this class initializes all attributes.
	 * @param id The id of this scenery
	 * @param x The x position of this scenery
	 * @param y The y position of this scenery
	 */
	public Scenery(String id, int x, int y) {
		setId(id);
		this.x = x;
		this.y = y;
	}

	/**
	 * getter method for the x position attribute.
	 * @return x The attribute
	 */
	public int getX() {
		return x;
	}

	/**
	 * getter method for the y position attribute.
	 * @return y The attribute
	 */
	public int getY() {
		return y;
	}
}
