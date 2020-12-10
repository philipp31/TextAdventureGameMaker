package student;

/**
 * This class provides the game field for a textadventure game.
 * 
 * @author Philipp Slebioda
 */
public class GameField {
	
	private boolean[][] gameField;
	private Item[][][] itemField;
	private Scenery[][][] sceneryField;
	private int widthPos, heightPos, width, height;
	private DataStorage data;

	/**
	 * This is the constructor. It sets the width, height 
	 * and the data storage for this class.
	 * 
	 * @param width The width that is wanted for the game field
	 * @param height The height that is wanted for the game field
	 * @param data The reference for the data base of the textadventure game
	 */
	public GameField(int width, int height, DataStorage data) {
		this.width = width;
		this.height = height;
		this.data = data;
		gameField = new boolean[width][height];
		itemField = new Item[width][height][10];
		sceneryField = new Scenery[width][height][10];
	}
	
	/**
	 * Method that allows it to make the 3-dimens.-item-array double as big in the third dimension.
	 * 
	 * @param original The multi-dim. array to double the size
	 */ 
	private void doubleItemArray(Item[][][] original) {
		Item[][][] newArray = new Item[width][height][original[0][1].length * 2];
		for (int i = 0; i < width; i++) {
			for (int k = 0; k < original[0].length; k++) {
				for (int h = 0; h < original[0][0].length; h++) {
					try {
						newArray[i][k][h] = original[i][k][h];
					} catch (NullPointerException e) { }
				}
			}
		}
		itemField = newArray;
	}
	
	/**
	 * Method that allows it to make the 3-dimens.-scenery-array double as big in the third dimension.
	 * 
	 * @param original The multi-dim. array to double the size
	 */ 
	private void doubleSceneryArray(Scenery[][][] original) {
		Scenery[][][] newArray = new Scenery[width][height][original[0][1].length * 2];
		for (int i = 0; i < width; i++) {
			for (int k = 0; k < original[0].length; k++) {
				for (int h = 0; h < original[0][0].length; h++) {
					try {
						newArray[i][k][h] = original[i][k][h];
					} catch (NullPointerException e) { }
				}
			}
		}
		sceneryField = newArray;
	}
	
	/**
	 * Method that allows it to take a item from the field.
	 * 
	 * @param id The identifier of the item that you want to take to inventory
	 * @return the reference to the wanted item
	 */ 
	public Item takeItemToInventory(String id) {
		this.getActualPosition();
		for (int i = 0; i < (itemField[0][0].length); i++) {
			if (itemField[widthPos][heightPos][i] != null) {
				if (itemField[widthPos][heightPos][i].getId().equals(id)) {
					Item tempRef = itemField[widthPos][heightPos][i];
					// deleting the item from the field:
					itemField[widthPos][heightPos][i] = null;	
					return tempRef;
				}
			}
		}
		return null;
	}
	
	/**
	 * Method that allows it to put a given item on the field.
	 * 
	 * @param elmnt The reference to a existing item object
	 */ 
	public void putItemOnField(Item elmnt) {
		this.getActualPosition();
		for (int i = 0; i < itemField[0][0].length; i++) {
			if (itemField[widthPos][heightPos][i] == null) {
				itemField[widthPos][heightPos][i] = elmnt;
				return;
			}
			if (i == itemField[0][0].length - 1) {
				this.doubleItemArray(itemField);
				putItemOnField(elmnt);
			}
		}
	}
	
	/**
	 * Method that allows it to place a new item object on the fame field.
	 * 
	 * @param id The identifier of the wanted item object
	 * @param x The wanted x-position of the item
	 * @param y The wanted y-position of the item
	 */ 
	protected void placeItem(String id, int x, int y) {
		for (int i = 0; i < itemField[0][0].length; i++) {
			if (itemField[x][y][i] == null) {
				itemField[x][y][i] = new Item(id, x, y);
				return;
			}
		}
		this.doubleItemArray(itemField);
		placeItem(id, x, y);
	}
	
	/**
	 * Method that allows it to place a new scenery object on the game field.
	 * 
	 * @param id The identifier of the wanted scenery
	 * @param x The wanted x-position of the scenery
	 * @param y The wanted y-position of the scenery
	 */ 
	protected void placeScenery(String id, int x, int y) {
		for (int i = 0; i < sceneryField[0][0].length; i++) {
			if (sceneryField[x][y][i] == null) {
				sceneryField[x][y][i] = new Scenery(id, x, y);
			//	sceneryField[x][y][i].setDescription(data.getDescription(id));
				return;
			}
		}
		this.doubleSceneryArray(sceneryField);
		placeScenery(id, x, y);
	}
	
	/**
	 * Method that allows it to place a new scenery object on the game field.
	 * The scenery gets positioned on the current player position!
	 * 
	 * @param id The identifier of the wanted scenery
	 */ 
	protected void placeScenery(String id) {
		getActualPosition();
		for (int i = 0; i < sceneryField[0][0].length; i++) {
			if (sceneryField[widthPos][heightPos][i] == null) {
				sceneryField[widthPos][heightPos][i] = new Scenery(id, widthPos, heightPos);
				//sceneryField[widthPos][heightPos][i].setDescription(data.getDescription(id));
				return;
			}
			if (i == sceneryField[0][0].length - 1) {
				this.doubleSceneryArray(sceneryField);
				placeScenery(id);
			}
		}
	}
	
	/**
	 * Method that allows it to move the player on the field.
	 * 
	 * @param direction The direction to go
	 * @return the direction in which you went as String
	 */ 
	public String movePlayer(String direction) {
		if (direction == null) {
			return null;
		}
		switch (direction) {
			case "N":	// NORTH
				return moveNorth();
				//return "north";
			case "NE":	// NE
				return moveNorthEast();
			case "E":	// EAST
				return moveEast();
			case "SW":	// SW
				return moveSouthWest();
			case "S":	// SOUTH
				return moveSouth();
			case "SE": // SE
				return moveSouthEast();
			case "W":	// WEST
				return moveWest();
			case "NW":	// NOTRTHWEST
				return moveNorthWest();
			default:
				return direction;
		}
	}
	
	/**
	 * Method that allows it to move north.
	 * 
	 * @return north If the movement was successful
	 */ 
	private String moveNorth() {
		getActualPosition();
		if (heightPos > 0) {
			// second index gets decremented when going NORTH
			gameField[widthPos][heightPos - 1] = true;
			gameField[widthPos][heightPos] = false;	// deleting the old pos.
			getActualPosition();
			return "north";
		} 
		return null;
	}
	
	/**
	 * Method that allows it to move north east.
	 * 
	 * @return northeast If the movement was successful
	 */ 
	private String moveNorthEast() {
		getActualPosition();
		if (heightPos > 0 && widthPos < width - 1) {
			// second index gets decremented when going NORTH
			gameField[widthPos + 1][heightPos - 1] = true;	
			gameField[widthPos][heightPos] = false;	// deleting the old pos
			getActualPosition();
			return "northeast";
		} 
		return null;
	}
	
	/**
	 * Method that allows it to move east.
	 * 
	 * @return east If the movement was successful
	 */ 
	private String moveEast() {
		getActualPosition();
		if (widthPos < width - 1) {
			// second index gets decremented when going NORTH
			gameField[widthPos + 1][heightPos] = true;	
			gameField[widthPos][heightPos] = false;	// deleting the old pos
			getActualPosition();
			return "east";
		} 
		return null;
	}
	
	/**
	 * Method that allows it to move south east.
	 * 
	 * @return southeast If the movement was successful
	 */ 
	private String moveSouthEast() {
		getActualPosition();
		if (height - 1 > heightPos && widthPos < width - 1) {
			gameField[widthPos + 1][heightPos + 1] = true;	
			gameField[widthPos][heightPos] = false;	// deleting the old pos
			getActualPosition();
			return "southeast";
		} 
		return null;
	}
	
	/**
	 * Method that allows it to move south.
	 * 
	 * @return south If the movement was successful
	 */ 
	private String moveSouth() {
		getActualPosition();
		if (height - 1 > heightPos) {
			gameField[widthPos][heightPos + 1] = true;	
			gameField[widthPos][heightPos] = false;	// deleting the old pos
			getActualPosition();
			return "south";
		} 
		return null;
	}
	
	/**
	 * Method that allows it to move south west.
	 * 
	 * @return southwest If the movement was successful
	 */ 
	private String moveSouthWest() {
		getActualPosition();
		if (heightPos < height - 1  && widthPos > 0) {
			gameField[widthPos - 1][heightPos + 1] = true;	
			gameField[widthPos][heightPos] = false;	// deleting the old pos
			getActualPosition();
			return "southwest";
		} 
		return null;
	}
	
	/**
	 * Method that allows it to move west.
	 * 
	 * @return west If the movement was successful
	 */ 
	private String moveWest() {
		getActualPosition();
		if (widthPos > 0) {
			gameField[widthPos - 1][heightPos] = true;	
			gameField[widthPos][heightPos] = false;	// deleting the old pos
			getActualPosition();
			return "west";
		} 
		return null;
	}
	
	/**
	 * Method that allows it to move north west.
	 * 
	 * @return northwest If the movement was successful
	 */ 
	private String moveNorthWest() {
		getActualPosition();
		if (heightPos > 0 && widthPos > 0) {
			gameField[widthPos - 1][heightPos - 1] = true;	
			gameField[widthPos][heightPos] = false;	// deleting the old pos
			getActualPosition();
			return "northwest";
		} 
		return null;
	}
	
	/**
	 * Method that allows it to evaluated the current players position.
	 */ 
	private void getActualPosition() {
		for (int i = 0; i < width; i++) {
			for (int k = 0; k < gameField[0].length; k++) {
				if (gameField[i][k] == true) {
					widthPos = i;
					heightPos = k;
					return;
				}
			}
		}
	}

	/**
	 * Method that allows it to set the position of the player.
	 * 
	 * @param startX The width position that is wanted
	 * @param startY The height position that is wanted
	 */ 
	public void setPosition(int startX, int startY) {
		for (int i = 0; i < width; i++) {
			for (int k = 0; k < height; k++) {
				gameField[i][k] = false;
			}
		}	
		gameField[startX][startY] = true;
	}
	
	/**
	 * Method that delivers information if a object is on the game field and deletes it.
	 * 
	 * @param item The id of the object that you want to check
	 * @return boolean whether this id is on the field
	 */ 
	public boolean searchItem(String item) {	
		// this method searches a Input for a mutation and deletes it if it is available
		for (int h = 0; h < itemField[0][0].length; h++) {
			if (itemField[widthPos][heightPos][h] != null) {
				if (itemField[widthPos][heightPos][h].getId().equals(item)) {
					itemField[widthPos][heightPos][h] = null;
					return true;
				}
			}
		}
		for (int h = 0; h < sceneryField[0][0].length; h++) {
			if(sceneryField[widthPos][heightPos][h] != null) {
				if (sceneryField[widthPos][heightPos][h].getId().equals(item)) {
					sceneryField[widthPos][heightPos][h] = null;
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Method that deletes a object on the field.
	 * 
	 * @param obj The GameFieldObject that contains all info's for the deleting process
	 */ 
	public void deleteObject(GameFieldObject obj) {
		boolean isItem = obj.isItemField();
		if (isItem) {
			if (itemField[obj.getWidth()][obj.getHeight()][obj.getPosition()] == null) {
				System.out.println("Fehler die zulösche Stelle ist bereits leer.");
			}
			itemField[obj.getWidth()][obj.getHeight()][obj.getPosition()] = null;
		} else {
			if (sceneryField[obj.getWidth()][obj.getHeight()][obj.getPosition()] == null) {
				System.out.println("Fehler die zulösche Stelle ist bereits leer.");
			}
			sceneryField[obj.getWidth()][obj.getHeight()][obj.getPosition()] = null;
		}
	}

	/**
	 * Method that searches a given id on the field. The information 
	 * if it's a item or a scenery and where to find get saved in 
	 * the GameFieldObject.
	 * 
	 * @param item The id for the object that is wanted
	 * @param input The gameFieldObject that gets the informations
	 */ 
	public void searchItem(String item, GameFieldObject input) {	
		getActualPosition();
		for (int h = 0; h < itemField[0][0].length - 1; h++) {
			if (itemField[widthPos][heightPos][h] != null) {
				if (itemField[widthPos][heightPos][h].getId().equals(item)) {
					input.setDoesExist(true);
					input.setParams(widthPos, heightPos, h, true);
					return;
				}
			}
		}
		for (int h = 0; h < sceneryField[0][0].length - 1; h++) {
			if (sceneryField[widthPos][heightPos][h] != null) {
				if (sceneryField[widthPos][heightPos][h].getId().equals(item)) {
					input.setDoesExist(true);
					input.setParams(widthPos, heightPos, h, false);
					return;
				}
			}
		}
	}
	
	/**
	 * Method that searches a second object for a given identifier. The information gets
	 * saved in the object of the gameFieldObject.
	 * 
	 * @param item The id for the object that is wanted
	 * @param input The gameFieldObject that gets the informations
	 */ 
	public void searchItemSameId(String item, GameFieldObject input) {
		getActualPosition();
		int counter = 0;
		for (int h = 0; h < itemField[0][0].length; h++) {
			if (itemField[widthPos][heightPos][h] != null) {
				if (itemField[widthPos][heightPos][h].getId().equals(item)) {
					counter++;
					if (counter == 2) {
						input.setDoesExist(true);
						input.setParams(widthPos, heightPos, h, true);
						return;
					}
				}
			}
		}
		counter = 0;
		for (int h = 0; h < sceneryField[0][0].length - 1; h++) {
			if (sceneryField[widthPos][heightPos][h] != null) {
				if (sceneryField[widthPos][heightPos][h].getId().equals(item)) {
					counter++;
					if (counter == 2) {
						input.setDoesExist(true);
						input.setParams(widthPos, heightPos, h, false);
						return;
					}
				}
			}
		}
	}

	/**
	 * Method that evaluates the number of objects on the field.
	 * 
	 * @return counter The number of objects
	 */ 
	public int sizeOfFieldElements() {
		int counter = 0;
		for (int h = 0; h < itemField.length; h++) {
			for (int k = 0; k < itemField[0].length; k++) {
				for (int i = 0; i < itemField[0][0].length; i++) {
					if (itemField[h][k][i] != null) {
						counter++;
					}
				}
			}
		}
		for (int h = 0; h < sceneryField.length; h++) {
			for (int k = 0; k < sceneryField[0].length; k++) {
				for (int i = 0; i < sceneryField[0][0].length; i++) {
					if (sceneryField[h][k][i] != null) {
						counter++;
					}
				}
			}
		}
		return counter;
	}

	/**
	 * This method returns a string array with the format "id - description" of all objects.
	 * 
	 * @param lookArray The string array to fill
	 * @return lookArray The string array
	 */ 
	public String[] getLookArray(String[] lookArray) {
		int counter = 0;
		for (int h = 0; h < itemField.length; h++) {
			for (int k = 0; k < itemField[0].length; k++) {
				for (int i = 0; i < itemField[0][0].length; i++) {
					if (itemField[h][k][i] != null) {
						lookArray[counter] = itemField[h][k][i].getId();
						lookArray[counter] = lookArray[counter].concat(
								" - ").concat(data.getDescription(
										itemField[h][k][i].getId()));
						counter++;
					}
				}
			}
		}
		for (int h = 0; h < sceneryField.length; h++) {
			for (int k = 0; k < sceneryField[0].length; k++) {
				for (int i = 0; i < sceneryField[0][0].length; i++) {
					if (sceneryField[h][k][i] != null) {
						lookArray[counter] = sceneryField[h][k][i].getId();
						lookArray[counter] = lookArray[counter].concat(
								" - ").concat(data.getDescription(
										sceneryField[h][k][i].getId()));
						counter++;
					}
				}
			}
		}
		return lookArray;
	}

	/**
	 * Method that creates a String array with all objects on the 
	 * players position. The elements have the format "id - description".
	 *
	 * @return array with all objects on your position
	 */ 
	public String[] showItemsOnPosition() {
		int counter = 0;
		getActualPosition();
		for (int i = 0; i < itemField[widthPos][heightPos].length; i++) {
			if (itemField[widthPos][heightPos][i] != null) {
				counter++;
			}
		}
		for (int i = 0; i < sceneryField[widthPos][heightPos].length; i++) {
			if (sceneryField[widthPos][heightPos][i] != null) {
				counter++;
			}
		}
		String[] objects = new String[counter];
		counter = 0;
		for (int i = 0; i < itemField[widthPos][heightPos].length; i++) {
			if (itemField[widthPos][heightPos][i] != null) {
				objects[counter] = itemField[widthPos][heightPos][i].getId().concat(
						" - ").concat(data.getDescription(
								itemField[widthPos][heightPos][i].getId()));
				counter++;
			}
		}
		for (int i = 0; i < sceneryField[widthPos][heightPos].length; i++) {
			if (sceneryField[widthPos][heightPos][i] != null) {
				objects[counter] = sceneryField[widthPos][heightPos][i].getId().concat(
					 " - ").concat(data.getDescription(
							 sceneryField[widthPos][heightPos][i].getId()));
				counter++;
			}
		}
		return objects;
	}
}
