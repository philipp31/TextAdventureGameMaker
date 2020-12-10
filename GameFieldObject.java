package student;

/**
 * This class provides certain informations about a object  (item or scenery) that are needed for the mutations.
 *
 * @author Philipp Slebioda
 */
public class GameFieldObject {
	
	private int width;
	private int height;
	private int position;
	private boolean wantToDelete;
	private boolean doesExist;
	private Item reference;
	private boolean isItemField;

	/**
	 * This method is a getter for the width position of the obj.
	 * @return width
	 */
	public int getWidth() {
		return width;
	}
	
	/**
	 * This method is a setter for four attributes of this class.
	 * @param width The width position to set
	 * @param height The height position to set
	 * @param position The position in the 3 dimensional field array
	 * @param itemField Information whether the object is in the item field or not
	 */
	public void setParams(int width, int height, int position, boolean itemField) {
		this.width = width;
		this.height = height;
		this.position = position;
		this.setItemField(itemField);
	}

	/**
	 * This method is a setter for the width position of the obj.
	 * @param width The width position to set
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * This method is a getter for the height attribute.
	 * @return height The height attribute
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * This method is a setter for the height.
	 * @param height The value to set height
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * This method is a getter for the position of this class.
	 * @return position The position attribute
	 */
	public int getPosition() {
		return position;
	}

	/**
	 * This method is a setter for the position of this class.
	 * @param position The position attribute
	 */
	public void setPosition(int position) {
		this.position = position;
	}

	/**
	 * This method is a getter for the boolean wantToDelete of this class.
	 * @return wantToDelete The value to get
	 */
	public boolean isWantToDelete() {
		return wantToDelete;
	}

	/**
	 * This method is a setter for the boolean wantToDelete of this class.
	 * @param wantToDelete The value to set
	 */
	public void setWantToDelete(boolean wantToDelete) {
		this.wantToDelete = wantToDelete;
	}

	/**
	 * This method is a getter for the boolean doesExist of this class.
	 * @return doesExist The attribute to get
	 */
	public boolean isDoesExist() {
		return doesExist;
	}

	/**
	 * This method is a setter for the boolean doesExist of this class.
	 * @param doesExist The boolean to set the attribute
	 */
	public void setDoesExist(boolean doesExist) {
		this.doesExist = doesExist;
	}

	/**
	 * This method is a getter for the item attribute.
	 * @return reference The items reference
	 */
	public Item getItem() {
		return reference;
	}

	/**
	 * This method is a setter for the item attribute.
	 * @param reference The reference to set
	 */
	public void setItem(Item reference) {
		this.reference = reference;
	}

	/**
	 * This method is a getter for the boolean isItemField.
	 * @return isItemField whether the object is in the itemField or in the sceneryField
	 */
	public boolean isItemField() {
		return isItemField;
	}

	/**
	 * This method is a setter for the boolean isItemField.
	 * @param isItemField whether the object is in the itemField or in the sceneryField
	 */
	public void setItemField(boolean isItemField) {
		this.isItemField = isItemField;
	}
}
