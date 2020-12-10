package student;

import java.util.ArrayList;

import ias.Player;
import ias.TextAdventureException;

/**
 * This class implements the player-interface.
 * It allows the steering of the player and the taking and dropping of items
 * 
 * @author Philipp Slebioda
 */
public class Adventurer implements Player {
	
	private GameField gameField;
	private DataStorage data;
	private ArrayList<Item> inventory;
	private int height;
	private int width;
	
	/**
	 * this is the constructor of the Adventurer class. It sets the gameField and the data base
	 * 
	 * @param width the wanted width of the game field
	 * @param height the wanted height of the game field
	 * @param data the reference of the one data base that is used for the whole game
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Adventurer(int width, int height, DataStorage data) {
		gameField = new GameField(width, height, data);
		inventory = new ArrayList();
		this.data = data;
		this.height = height;
		this.width = width;
	}
	
	/**
	 * this method sets the position of the player depending on the input-params.
	 * 
	 * @param startX the wanted width position for the player
	 * @param startY the wanted height position for the player
	 * @throws TextAdventureException Exception if position is invalid
	 */
	public void setPosition(int startX, int startY) throws TextAdventureException {
		if (startX < 0 || startY < 0 || startX > (width - 1) || startY > (height - 1)) {
			throw new TextAdventureException("Error! Die Positionierung "
					+ "ist invalide (entweder zu hoch oder kleiner als 0)");
		}
		gameField.setPosition(startX, startY);
	}

	/**
	 * this method allows it to move the player. It implements a method of the player-interface.
	 * 
	 * @param direction The direction where to go as a String
	 */
	@Override
	public String go(String direction) {
		String message = gameField.movePlayer(direction);
		if (message != null) {
			if (message.equals("north") || message.equals("northeast") 
					|| message.equals("east") || message.equals("southeast") 
						|| message.equals("south") || message.equals("southwest") 
							|| message.equals("west") || message.equals("northwest")) {
				return "You go " + message;
			} else {
				return "Sorry, die Richtung '" + direction + "' ist undefiniert."
						+ " Valide Richtungen : N, NE, E, SE, S, SW, W, NW.";
			}
		}
		return "Sorry, you can't go there.";
		
	}

	/**
	 * This method returns a String[] with all objects on the whole game field.
	 * It implements a method of the player-interface.
	 * (not used because I think
	 * that the other look() method was wanted)
	 * 
	 * @return all objects on the whole game field (not used because I think
	 * that the other look() method was wanted)
	 */
/*	@Override
	public String[] look() {	// look()-method that shows all objects on the whole game field!
		String[] lookArray =  new String[gameField.sizeOfFieldElements()];
		return gameField.getLookArray(lookArray);	// TYPBEZEICHNER - TYPBESCHREIBUNG pro Array-Eintrag
	}*/
	
	/**
	 * This method returns a String[] with all objects on the current player position.
	 * It implements a method of the player-interface.
	 * 
	 * @return all objects on the current position as string-array
	 */
	@Override
	public String[] look() { // look()-method that shows all objects on the current position 
		return gameField.showItemsOnPosition();
	}
	
	/**
	 * this method delivers the description of a given id. If the id doesn't exists it returns null.
	 * 
	 * @param id The id for which the description shall be delivered
	 */
	private String getDescription(String id) {
		for (ItemType type : data.getItemTypes()) {
			if (type.getId().equals(id)) {
				return type.getDescription();
			}
		}
		for (SceneryType type : data.getSceneryTypes()) {
			if (type.getId().equals(id)) {
				return type.getDescription();
			}
		}
		return null;
	}

	/**
	 * this method delivers the current inventory. It implements a method of the player-interface.
	 * 
	 * @return stringInventory The inventory as String Array with the string-structure: "id - description".
	 */
	@Override
	public String[] inventory() {
		String[] stringInventory =  new String[inventory.size()];
		int counter = 0;
		for (Item elmt : inventory) {
			try {
				stringInventory[counter] = elmt.getId();
				if (getDescription(elmt.getId()) != null) {
					stringInventory[counter] = stringInventory[counter].concat(
							" - ").concat(getDescription(elmt.getId()));
				} else {
					System.out.println("**** Fehler ein Inventory-Item hat kein Type! ****");
				}
				counter++;
			} catch (ArrayIndexOutOfBoundsException e) {
			}
		}
		return stringInventory;
	}

	/**
	 * This method trys to take a item from the current position on the game field. 
	 * If the process was successful will be returnt as a String. 
	 * 
	 * @param item The id of the item that the player want to take.
	 */
	@Override
	public String take(String item) {
		if (item == null) {
			return "Sorry, the item you want to take is null.";
		}
		Item itemToTake = this.gameField.takeItemToInventory(item);
		if (itemToTake != null) {
			this.inventory.add(itemToTake);
			return "You pick up the " + itemToTake.getId();
		} else {
			return "Sorry, this item is either a scenery or not on your position.";
		}
	}

	/**
	 * This method allows it to drop a item of the inventory one the current gamefield-position.
	 * 
	 * @param item The if of the item that you want to drop
	 */
	@Override
	public String drop(String item) {
		if (item == null) {
			return "Sorry, the item you want to drop is null.";
		}
		for (Item elmnt : inventory) {
			if (elmnt.getId().equals(item)) {
				this.gameField.putItemOnField(elmnt);
				inventory.remove(elmnt);
				return "You drop the " + item;
			}
		}
		return "Sorry, this item type isn't in your inventory";
	}
	
	/**
	 * This method allows it to convert or transform two items.
	 * First a conversion is searched and then a transformation.
	 * The items that are first searched in the inventory and then on game-field.
	 * 
	 * @param item1 The first id of an object that you want to convert/transform
	 * @param item2 The second id of an object that you want to convert/transform
	 */
	@Override
	public String convert(String item1, String item2) {
		if (item1 == null || item2 == null) {
			return "Sorry, one of the objects or both are null.";
		}
		GameFieldObject input1 = new GameFieldObject();
		GameFieldObject input2 = new GameFieldObject();
		for (Item itemElmt : inventory) {
			if (itemElmt.getId().equals(item1)) {
				input1.setDoesExist(true);
				input1.setItem(itemElmt);
				break;
			}
		}
		if (!input1.isDoesExist()) {
			gameField.searchItem(item1, input1);
		}
		int counter = 1;
		for (Item itemElmt : inventory) {
			if (itemElmt.getId().equals(item2)) {
				if (item1.equals(item2)) {
					if (counter > 1) {
						input2.setDoesExist(true);
						input2.setItem(itemElmt);
						break;
					}
				} else {
					input2.setDoesExist(true);
					input2.setItem(itemElmt);
					break;
				}
				counter++;
			}
		}
		if (!input2.isDoesExist()) {
			gameField.searchItem(item2, input2);
			if (input1.getItem() == null) {	// wenn false wird auf dem feld gesucht
				// if two id's are the same it has to be 
				//tested if there are two diffr. objects on field:
				if (item1.equals(item2)) {
					if (input1.getPosition() == input2.getPosition()) {	
						gameField.searchItemSameId(item2, input2);
					}
				}
			}
		}
		if (!input1.isDoesExist() || !input2.isDoesExist()) {
			return "Sorry, one of the inputs or both "
					+ "aren't available (not in your inventory and not on your position)";
		}
		for (Composition elmnt : data.getCompositions()) { 
			if (elmnt.getIn1().equals(item1)) { 
				// try first possible combination:
				if (elmnt.getIn2().equals(item2)) {
					doComposition(input1, input2, elmnt.getOut());
					return elmnt.getDescription();
				}
			}
			// test second combination:
			if (elmnt.getIn1().equals(item2)) {
				if (elmnt.getIn2().equals(item1)) {
					doComposition(input1, input2, elmnt.getOut());
					return elmnt.getDescription();
				}
			}
		}
		for (Transformation elmnt : data.getTransformations()) {
			if (elmnt.getIn1().equals(item1)) {
				// try first possible combination:
				if (elmnt.getIn2().equals(item2)) {
					doTransformation(input1, input2, elmnt.getOut1(), elmnt.getOut2());
					return elmnt.getDescription();
				}
			}
			// test second combination:
			if (elmnt.getIn1().equals(item2)) {
				if (elmnt.getIn2().equals(item1)) {
					doTransformation(input1, input2, elmnt.getOut1(), elmnt.getOut2());
					return elmnt.getDescription();
				}
			}
		}
		return "Sorry, there isn't a composition or transformation with the given inputs.";
	}
	
	/**
	 * This method allows it to do a transformation based on the inputs and outputs.
	 * @param input1 The first input id 
	 * @param input2 The second input id
	 * @param outputId1 The first output id
	 * @param outputId2 The second output id
	 */
	private void doTransformation(GameFieldObject input1, GameFieldObject input2,
			String outputId1, String outputId2) {
		deleteTwoInputs(input1, input2);
		// output-management
		createOutput(outputId1);
		createOutput(outputId2);
	}
	
	/**
	 * This method allows it to create a new output of a mutation.
	 * Out of the id the object gets created.
	 * 
	 * @param id The id of the item that you want to create
	 */
	public void createOutput(String id) {
		if (data.checkIfIdIsItem(id)) { // if the ouput of composition is item add it to inventory
			inventory.add(new Item(id, 0, 0));
		} else {	// if its a scenery put it on the field
			gameField.placeScenery(id);
		}
	}
	
	/**
	 * This method allows it to delete two objects.
	 * The GameFieldObject-class gives here the information where to delete the object.
	 * 
	 * @param in1 The first object that shall be deleted
	 * @param in2 The second object that shall be deleted
	 */
	public void deleteTwoInputs(GameFieldObject in1, GameFieldObject in2) { // method that deletes two G-F-Objs 
		// input-management:
		if (in1.getItem() != null) { // if a reference to a item is saved the object is in iventory
			inventory.remove(in1.getItem());
		} else {	// if item is null the object is on the Field and has to be there deleted
			gameField.deleteObject(in1);
		}
		if (in2.getItem() != null) {
			inventory.remove(in2.getItem());
		} else {
			gameField.deleteObject(in2);
		}
	}

	/**
	 * This method allows it to perform a composition.
	 * 
	 * @param in1 The first input of the composition
	 * @param in2 The second input of the composition
	 * @param outputId The output id of the composition
	 */
	private void doComposition(GameFieldObject in1, GameFieldObject in2, String outputId) {
		deleteTwoInputs(in1, in2);
		// Output-management:
		createOutput(outputId);
	}

	/**
	 * This method allows it to perform a decomposition.
	 * 
	 * @param item The id of the needed input for the decomposition
	 */
	@Override
	public String decompose(String item) {
		if (item == null) {
			return "Sorry, the object you want to decompose is null.";
		}
		// first searching for object and then searching of the mutation:
		boolean foundObject = false;
		for (Item itemElmt : inventory) {
			if(itemElmt != null) {
				if (itemElmt.getId().equals(item)) {
					foundObject = true;
					inventory.remove(itemElmt);
					break;
				}
			}
		}
		if (!foundObject) {
			foundObject = gameField.searchItem(item);
		}
		if (!foundObject) {
			return "Sorry, the searched Item is not in your inventory or on the field.";
		}
		for (Decomposition elmnt : data.getDecompositions()) {
			if(elmnt != null) {
				if (elmnt.getIn().equals(item)) {
					// items shall be placed in inventory & scenerys on field:
					placeOutput(elmnt.getOut1());
					placeOutput(elmnt.getOut2());
					return elmnt.getDescription();
				}
			}
		}
		return "Sorry, there isn't a decomposition for the wanted type.";
	}
	
	/**
	 * This method places a output object either in the inventory or on the game field.
	 * 
	 * @param outputId The id of the object that shall be placed
	 */
	private void placeOutput(String outputId) {
		if (data.checkIfIdIsItem(outputId)) {
			inventory.add(new Item(outputId, 0, 0));
		} else {
			gameField.placeScenery(outputId);
		}
	}
	
	/**
	 * This method allows it to place a new created item on the game field.
	 * 
	 * @param id The id of the item that shall be placed
	 * @param x The x position of the item on the game field
	 * @param y The y position of the item on the game field
	 */
	protected void placeItem(String id, int x, int y) {
		gameField.placeItem(id, x, y);
	}
	
	/**
	 * This method allows it to place a new created scenery on the game field.
	 * 
	 * @param id The id of the scenery that shall be placed
	 * @param x The x position of the scenery on the game field
	 * @param y The y position of the scenery on the game field
	 */
	protected void placeScenery(String id, int x, int y) {
		gameField.placeScenery(id, x, y);
	}
}
