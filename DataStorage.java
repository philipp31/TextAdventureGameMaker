package student;

import java.util.ArrayList;

/**
 * This class provides a data base for a textadventure game.
 *
 * @author Philipp Slebioda
 */
public class DataStorage {

	private ArrayList<ItemType> itemTypes;
	private ArrayList<SceneryType> sceneryTypes;
	private ArrayList<Composition> compositions;
	private ArrayList<Decomposition> decompositions;
	private ArrayList<Transformation> transformations;
	
	/**
	 * Constructor of this class initializes different data.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public DataStorage() {
		itemTypes = new ArrayList();
		sceneryTypes = new ArrayList();
		compositions = new ArrayList();
		decompositions = new ArrayList();
		transformations = new ArrayList();
	}
	
	/**
	 * This method checks if a given id is a item.
	 * @param id The identifier for the item
	 * @return boolean whether it's true or not
	 */
	public boolean checkIfIdIsItem(String id) {
		for (ItemType elmt : itemTypes) {
			if (elmt.getId().equals(id)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * This method is a getter for the description if you have a certain id.
	 * @param id The identifier for which you search a description
	 * @return The description
	 */
	public String getDescription(String id) {
		for (ItemType elmt : itemTypes) {
			if (elmt.getId().equals(id)) {
				return elmt.getDescription();
			}
		}
		for (SceneryType elmt : sceneryTypes) {
			if (elmt.getId().equals(id)) {
				return elmt.getDescription();
			}
		}
		return null;
	}
	
	/**
	 * This method is a getter for the item types.
	 * @return array list with all item types
	 */
	public ArrayList<ItemType> getItemTypes() {
		return itemTypes;
	}
	
	/**
	 * This method is a getter for the scenery types.
	 * @return array list with all scenery types
	 */
	public ArrayList<SceneryType> getSceneryTypes() {
		return sceneryTypes;
	}
	
	/**
	 * This method is a getter for the compositions.
	 * @return array list with all compositions
	 */
	public ArrayList<Composition> getCompositions() {
		return compositions;
	}
	
	/**
	 * This method is a getter for the decompositions.
	 * @return array list with all decompositions
	 */
	public ArrayList<Decomposition> getDecompositions() {
		return decompositions;
	}
	
	/**
	 * This method is a getter for the transformations.
	 * @return array list with all transformations
	 */
	public ArrayList<Transformation> getTransformations() {
		return transformations;
	}
}
