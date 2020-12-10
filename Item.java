package student;

/**
 * This class allows it to save items in the textadventure game.
 * 
 * @author Philipp Slebioda
 */
public class Item extends ObjectType {
	
	private int x;
	private int y;

	/**
	 * This is the constructor. It sets the id and position.
	 * 
	 * @param id The wanted identifier
	 * @param x The x position of the item
	 * @param y The y position of the item
	 */
	public Item(String id, int x, int y) {
		setId(id);
		this.x = x;
		this.y = y;
	}

	/**
	 * getter-method for the x-attribute.
	 * 
	 * @return x The wanted x-position-attribute
	 */ 
	public int getX() {
		return x;
	}

	/**
	 * getter-method for the y-attribute.
	 * 
	 * @return y The wanted y-position-attribute
	 */ 
	public int getY() {
		return y;
	}
}
