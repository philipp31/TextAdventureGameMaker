package student;

import ias.Terminal;

import java.util.Scanner;

/**
 * This class implements the Terminal-interface.
 * It implements the interaction with the console in the given textadventure framework.
 * 
 * @author Philipp Slebioda
 */
public class Interaktion implements Terminal {
	
	private String playerInput;
	private Scanner reader;
	
	/**
	 * This is the constructor of the class.
	 * The scanner gets initialized.
	 */
	public Interaktion() {
		reader = new Scanner(System.in);
	}

	/**
	 * This method prints strings on the console out for the interaction with the player.
	 * 
	 * @param input The string that should be printed on the console
	 */
	@Override
	public void promptInput(String input) {
		System.out.println(input);
	}

	/**
	 * This method reads a string from the console as input from the player.
	 * 
	 * @return the written line from the player
	 */
	@Override
	public String[] readInput() {
 		//System.out.println("Hat der Scanner ein Element ? " + reader.hasNext());
		playerInput = reader.nextLine();
		String[] output = seperate(playerInput);
		return output;
	}
	
	/**
	 * This method separates a string in a string array using a space as delimiter.
	 * 
	 * @param word The string that shall be separated
	 * @return The string array with all seperated elements
	 */
	public String[] seperate(String word) {
		String[] words = word.split(" ");
		return words;
	}
}
