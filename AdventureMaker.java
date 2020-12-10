package student;

import java.util.ArrayList;

import ias.Player;
import ias.TextAdventure;
import ias.TextAdventureException;

/**
 * This class allows it to create textadventure-games.
 *
 * @author Philipp Slebioda, 4809007
 */
public class AdventureMaker implements TextAdventure {	
	
	private Adventurer player;
	private String name;
	private int fieldWidth;
	private int fieldHeight;
	private DataStorage data;

	/**
	 * Constructor for this class. It sets the name, width and height of the new game.
	 * @param name The wanted name for the game
	 * @param boardWidth the wanted width of the game field
	 * @param boardHeight the wanted height of the game field
	 * @throws a textadventure-exception if the given inputs are invalid
	 */
	public AdventureMaker(String name, int boardWidth, int boardHeight) throws TextAdventureException {
		if (boardWidth < 1) {
			throw new TextAdventureException("Die Breite des Spielfelds darf nicht 0 oder kleiner sein!");
		}
		if (boardHeight < 1) {
			throw new TextAdventureException("Die Höhe des Spielfelds darf nicht 0 oder kleiner sein!");
		}
		if (name == null) {
			throw new TextAdventureException("Der Name ist null!");
		}
		this.name = name;
		this.fieldHeight = boardHeight;
		this.fieldWidth = boardWidth;
		data = new DataStorage();
		// creating a player on a field with the given lengths:
		this.player = new Adventurer(this.fieldWidth, this.fieldHeight, data);
	}

	/**
	 * This method allows it to create a new item-type.
	 * @param id The identifier for the new item-type
	 * @param description The description for the new item-type
	 * @throws textadventureException if id is already used or invalid
	 */
	@Override
	public void addItemType(String id, String description) throws TextAdventureException {	
		if (id == null) {
			throw new TextAdventureException("Die Id ist null!");
		}
		if (description == null) {
			throw new TextAdventureException("Die Description ist null!");
		}
		checkBezeichner(id); // items dürfen ins inventar des Spielers aufgenommen werden
		if (doesTypeExist(id)) {	// if id already used throw exception!
			throw new TextAdventureException("Die ID wurde bereits verwendet!");
		}
		data.getItemTypes().add(new ItemType(id, description));
	}

	/**
	 * This method allows it to create a new scenery-type.
	 * @param id The identifier for the new scenery-type
	 * @param description The description for the new scenery-type
	 * @throws textadventureException if id or description is invalid
	 */
	@Override
	public void addSceneryType(String id, String description) throws TextAdventureException { 
		if (id == null) {
			throw new TextAdventureException("Die Id ist null!");
		}
		if (description == null) {
			throw new TextAdventureException("Die Description ist null!");
		}
		checkBezeichner(id);	// check for big letters and null-reference
		if (doesTypeExist(id)) {	// if id already used throw exception!
		 	throw new TextAdventureException("ID wurde bereits verwendet!");
		}
		data.getSceneryTypes().add(new SceneryType(id, description));
	}
	
	/**
	 * This method allows it to check a identifier if it is ok. Throws exception if not. 
	 * @param id The identifier that shall be checked
	 * @throws textadventureException if the id is invalid
	 */
	private void checkBezeichner(String id) throws TextAdventureException {
		if (id == null) {
			throw new TextAdventureException("ID ist Null!");
		}
		for (int i = 0; i < id.length(); i++) {	// check if id cotains big letters!
			int elmt = (int) id.charAt(i);
			if (((64 < elmt) && (elmt < 91)) == false && ((96 < elmt) && (elmt < 123)) == false) {
				throw new TextAdventureException("Bezeichner darf nur Buchstaben enthalten!");
			}
		}
		if (id.equals("")) {
			throw new TextAdventureException("ID ist leer!");
		}
	}

	/**
	 * This method allows it to place a new object(item or scenery) on the game field.
	 * @param type The id of the item that shall be placed
	 * @param x The x position of the new item
	 * @param y The y position of the new item
	 * @throws textadventureException one of the input-params. is invalid
	 */
	@Override
	public void placeItem(String type, int x, int y) throws TextAdventureException {
		if (x > fieldWidth || x < 0 || y > fieldHeight || y < 0) {
			throw new TextAdventureException("Die Position des Items ist außerhalb des Spielfelds!");
		}
		// the type has to be available to add a object:
		boolean isScenery = checkTypBezeichner(type, data.getSceneryTypes());
		boolean isItem = checkTypBezeichner(type, data.getItemTypes());
		if (isItem) {
			player.placeItem(type, x, y);
		} else if (isScenery) {
			player.placeScenery(type, x, y);
		} else {
			throw new TextAdventureException("Der Bezeichner für das Item ist nicht existent!");
		}
	}

	/**
	 * This method allows it to create a new composition.
	 * @param in1 The id for the first input
	 * @param in2 The id for the second input
	 * @param out The id for the output object
	 * @param description the description for the composition
	 * @throws textAdventureException if one of the input-params is invalid
	 */
	@Override
	public void addComposition(String in1, String in2, String out, String description) 
			throws TextAdventureException {
		if (description == null || in1 == null || in2 == null || out == null) {
			throw new TextAdventureException("Einer der Parameter ist NULL!");
		}
		checkTypes(in1, in2, out);	// throw exc. if Bezeichner dont exist
		checkIfObjectPairCompositionExists(in1, in2); // throws except.
		//if a comp. with the same inputs already exists
		data.getCompositions().add(new Composition(description, in1, in2, out));
	}

	/**
	 * This method allows it to create a new decomposition.
	 * @param in The id for the input object
	 * @param out1 The id for the first output object
	 * @param out2 The id for the second output object
	 * @param description the description for the new decomposition
	 * @throws textAdventureException if one of the params is invalid
	 */
	@Override
	public void addDecomposition(String in, String out1, String out2, String description)
			throws TextAdventureException {
		if (description == null || in == null || out1 == null || out2 == null) {
			throw new TextAdventureException("Einer der Parameter ist NULL!");
		}
		checkTypes(in, out1, out2);
		checkIfObjectPairDecompositionExists(in);
		data.getDecompositions().add(new Decomposition(in, out1, out2, description));
	}
	
	/**
	 * This method allows it to check if a input of an decomposition already exists.
	 * @param name The identifier for the input that shall be checked
	 * @throws a TextAdventureException if the input id is already used
	 */
	public void checkIfObjectPairDecompositionExists(String name) throws TextAdventureException {
		String input;
		for (Decomposition elmt : data.getDecompositions()) {
			input =  elmt.getIn();
			if (name.equals(input)) {
				throw new 
					TextAdventureException("Es gibt schon"
							+ " eine Decomposition mit dem selben Input!");
			}
		}
	}

	/**
	 * This method allows it to add a new transformation.
	 * @param in1 The id for the first input
	 * @param in2 The id for the second input
	 * @param out1 The id for the first output object
	 * @param out2 The id for the second output object
	 * @param description the description for the transformation
	 */
	@Override
	public void addTransformation(String in1, String in2, String out1, String out2, String description) 
			throws TextAdventureException {
		if (description == null || in1 == null || in2 == null || out1 == null || out2 == null) {
			throw new TextAdventureException("Einer der Eingangsparameter ist NULL!");
		}
		checkTransformationType(in1, in2, out1, out2);
		checkIfObjectPairTransformationExists(in1, in2);
		data.getTransformations().add(new Transformation(in1, in2, out1, out2, description));
	}
	
	/**
	 * This method allows it to check if the three given identifiers exists. If not exceptions get thrown.
	 * @param param1 The first id that shall be checked
	 * @param param2 The second id that shall be checked
	 * @param param3 The third id that shall be checked
	 */
	private void checkTypes(String param1, String param2, String param3) throws TextAdventureException {
		if (!doesTypeExist(param1)) {
			throw new TextAdventureException("parameter1-Bezeichner existiert nicht!");
		}
		if (!doesTypeExist(param2)) {
			throw new TextAdventureException("parameter2-Bezeichner existiert nicht!");
		}
		if (!doesTypeExist(param3)) {
			throw new TextAdventureException("parameter3-Bezeichner existiert nicht!");
		}
	}
	
	/**
	 * This method allows it to check four id's if the exist. If not Exceptions get thrown.
	 * @param in1 The first id that shall be checked
	 * @param in2 The second id that shall be checked
	 * @param out1 The third id that shall be checked
	 * @param out2 The fourth id that shall be checked
	 */
	private void checkTransformationType(String in1, String in2, String out1, String out2) 
			throws TextAdventureException {
		if (!doesTypeExist(in1)) {
			throw new TextAdventureException("in1-Bezeichner existiert nicht!");
		}
		if (!doesTypeExist(in2)) {
			throw new TextAdventureException("in2-Bezeichner existiert nicht!");
		}
		if (!doesTypeExist(out1)) {
			throw new TextAdventureException("out1-Bezeichner existiert nicht!");
		}
		if (!doesTypeExist(out2)) {
			throw new TextAdventureException("out2-Bezeichner existiert nicht!");
		}
	}

	/**
	 * This method allows it to get a player-object with a set position.
	 * @param x The wanted x position of the player.
	 * @param y The wanted y position of the player.
	 */
	@Override
	public Player startGame(int x, int y) throws TextAdventureException {
		this.player.setPosition(x, y);
		return this.player;
	}	
	
	/**
	 * This method allows it to check if a given identifier already exists.
	 * @param type The identifier that shall be checked
	 */
	private boolean doesTypeExist(String type) {
		boolean cond1 = checkTypBezeichner(type, data.getItemTypes());
		boolean cond2 = checkTypBezeichner(type, data.getSceneryTypes());
		if (cond1 || cond2) {
			return true;
		}
		return false;
	}
	
	/**
	 * This method allows it to check if a id is in an given array list.
	 *
	 * @param id The identifier to check
	 * @param list The array list that shall be checked for the id
	 */
	private <T extends ObjectType> boolean checkTypBezeichner(String id, ArrayList<T> list) {
		for (ObjectType elmt : list) {
			if (elmt.getId().equals(id)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * This method allows it to check if two input obj1&2 are already used as inputs for a transformation.
	 * If they are used a TextAdventureException gets thrown.
	 *
	 * @param obj1 The first input that you want to use for a transformation
	 * @param obj2 The second input that you want to use for a transformation
	 */
	private void checkIfObjectPairTransformationExists(String obj1, String obj2) throws TextAdventureException {
		String in1;
		String in2;
		for (Transformation elmt : data.getTransformations()) {
			// 1. get elmts of every composition 
			in1 = elmt.getIn1();
			in2 = elmt.getIn2();
			if (doesCompExist(in1, in2, obj1, obj2)) { // 2. check if they are equal as the new ones
				throw new TextAdventureException("Leider gibt "
						+ "es bereits eine Transformation mit den selben zwei Inputs!");
			}
		}	
		for (Composition elmt : data.getCompositions()) {
			// 1. get elmts of every composition 
			in1 = elmt.getIn1();
			in2 = elmt.getIn2();
			if (doesCompExist(in1, in2, obj1, obj2)) { // 2. check if they are equal as the new ones
				throw new TextAdventureException("Leider gibt es"
						+ " bereits eine Komposition mit den selben zwei Inputs!");
			}
		}
	}
	
	/**
	 * This method allows it to check if two input obj1&2 are already used as inputs for a 
	 * composition or transformation.
	 * If they are used a TextAdventureException gets thrown.
	 *
	 * @param obj1 The first input that you want to use for a composition
	 * @param obj2 The second input that you want to use for a composition
	 */
	private void checkIfObjectPairCompositionExists(String obj1, String obj2) 
			throws TextAdventureException {
		String in1;
		String in2;
		for (Composition elmt : data.getCompositions()) {
			// 1. get elmts of every composition 
			in1 = elmt.getIn1();
			in2 = elmt.getIn2();
			if (doesCompExist(in1, in2, obj1, obj2)) { // 2. check if they are equal as the new ones
				throw new TextAdventureException("Leider gibt es"
						+ " bereits eine Komposition mit den selben zwei Inputs!");
			}
		}
		for (Transformation elmt : data.getTransformations()) {
			// 1. get elmts of every composition 
			in1 = elmt.getIn1();
			in2 = elmt.getIn2();
			if (doesCompExist(in1, in2, obj1, obj2)) { // 2. check if they are equal as the new ones
				throw new TextAdventureException("Leider gibt es"
						+ " bereits eine Transformation mit den selben zwei Inputs!");
			}
		}	
	}
	
	/**
	 * This method checks if obj1 & obj2 are the same string as in1 & in2.
	 * Both possible combinations get checked.
     *
	 * @param obj1 First word of the second pair
	 * @param obj2 Second word of the second pair
	 * @param in1 First word of the first pair
	 * @param in2 Second word of the second pair
	 * @return boolean whether the second pair is already used
	 */
	private boolean doesCompExist(String in1, String in2, String obj1, String obj2) {
		if (in1.equals(obj1) && in2.equals(obj2)) {
			return true;
		}
		if (in2.equals(obj1) && in1.equals(obj2)) {
			return true;
		}
		return false;
	}

	/**
	 * This method allows it get the name attribute.
	 *
	 * @return name The name attribute
	 */
	@Override
	public String getName() {
		return this.name;
	}
}
