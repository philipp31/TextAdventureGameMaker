package student;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ias.Player;
import ias.TextAdventureException;
import ias. AbstractTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import student.Factory;

public class TextAdventureTest extends AbstractTest {

	private static ias.TextAdventure textAdventure;

    @BeforeEach
    public void createGame() throws TextAdventureException {
        	textAdventure = Factory.getGame("Test", 5, 5);
    }
    
    @Test
    public void startInside() throws TextAdventureException {
        	Player player = textAdventure.startGame(4, 4);
            assertNotNull(player);
    }
    
    @Test
    public void startNegative1() {
        TextAdventureException thrown = assertThrows(TextAdventureException.class, () -> {
        	Player player = textAdventure.startGame(-1, 0);
        });
        assertTrue(thrown.getMessage().startsWith("Error!"));
    }

    @Test
    public void startNegative2() {
        TextAdventureException thrown = assertThrows(TextAdventureException.class, () -> {
    		Player player = textAdventure.startGame(2, -123);
    	});
        assertTrue(thrown.getMessage().startsWith("Error!"));
    }
    
    @Test
    public void startNegative3() {
        TextAdventureException thrown = assertThrows(TextAdventureException.class, () -> {
        	Player player = textAdventure.startGame(-1, -1);
    	});
        assertTrue(thrown.getMessage().startsWith("Error!"));
    }
    
    @Test
    public void startOutside1() {
        TextAdventureException thrown = assertThrows(TextAdventureException.class, () -> {
        	Player player = textAdventure.startGame(5, 0);
    	});
        assertTrue(thrown.getMessage().startsWith("Error!"));
    }
    
    @Test
    public void startOutside2() {
        TextAdventureException thrown = assertThrows(TextAdventureException.class, () -> {
        	Player player = textAdventure.startGame(0, 123);
    	});
        assertTrue(thrown.getMessage().startsWith("Error!"));
    }
    
    @Test
    public void startOutside3() {
        TextAdventureException thrown = assertThrows(TextAdventureException.class, () -> {
        	Player player = textAdventure.startGame(6, 5);
    	});
        assertTrue(thrown.getMessage().startsWith("Error!"));
    }
    
    @Test
    public void hardcoreSceneryTest() throws TextAdventureException {
        ias.TextAdventure textAdventure = Factory.getGame("Custom2", 3, 3);
	    char[] letters = {
	    		'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 
	    		'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
	    		'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 
	    		'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
	    		};
		for (char first : letters) {
			String firstString = String.valueOf(first);
    		textAdventure.addItemType(firstString, "description");
    		for (char second : letters) {
    			String secondString = String.valueOf(second);
    			String combString = firstString + secondString;
	        		textAdventure.addItemType(combString, firstString + secondString + "description");
	    	}
		}
			
		for (char first : letters) {
			String firstString = String.valueOf(first);
	    	for (char second : letters) {
	    			String secondString = String.valueOf(second);
	    			String combString = firstString + secondString;
	        		textAdventure.placeItem(combString, 0, 0);
	        		textAdventure.placeItem(firstString, 1, 0);
	        		textAdventure.placeItem(secondString, 0, 1);
	    	}
		}
        
		for (char first : letters) {
			String firstString = String.valueOf(first);
	    	for (char second : letters) {
    			String secondString = String.valueOf(second);
    			String combString = firstString + secondString;
	        	textAdventure.addDecomposition(combString, firstString, secondString, (combString + " becomes " + firstString + " " + " " + secondString));
	    	}
		}
		
		for (char first : letters) {
			String firstString = String.valueOf(first);
	    	for (char second : letters) {
	    		if (second >= first) {
	    			continue;
	    		}
				String secondString = String.valueOf(second);
    			String combString = firstString + secondString;
	        		textAdventure.addComposition(firstString, secondString, combString, (firstString + " " + secondString + " becomes " + combString));
	    	}
		}
		for (char first : letters) {
			String firstString = String.valueOf(first);
	    	for (char second : letters) {
	    		if (second != first) {
	    			continue;
	    		}
    			String secondString = String.valueOf(second);
	    		String combString = firstString + secondString;
	    		String combStringInv = secondString + firstString;
	        	textAdventure.addTransformation(firstString, secondString, combString, combStringInv, (firstString + " " + secondString + " becomes " + combString + " and " + combStringInv));
	    	}
		}
    }
}