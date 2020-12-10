package student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ias.Player;
import ias.TextAdventure;
import ias.TextAdventureException;
import ias.AbstractTest;
import student.Factory;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PlayerTest extends AbstractTest {

    private static TextAdventure textAdventure;
    
    @BeforeEach
    public void createGame() throws TextAdventureException {
        textAdventure = Factory.getGame("Test", 1, 100000);
    }
    @Test
    public void goHardcoreTest() throws TextAdventureException {
        Player player = textAdventure.startGame(0, 0);
        
        for (int i = 0; i < 99998; i++) {
        	
        	assertError(player.go("NW"));
        	assertError(player.go("NE"));
        	assertError(player.go("SW"));
        	assertError(player.go("SW"));
        	assertError(player.go("SW"));
        	assertError(player.go("SE"));
        	assertError(player.go("SE"));
        	assertError(player.go("SE"));
        	
        	player.go("S");
        }
        
        assertMoveSuccess(player.go("S"));
        assertError(player.go("S"));
    }
    @Test
    public void nullTake() throws TextAdventureException {
        Player p = textAdventure.startGame(0, 0);
        assertError(p.take(null));
    }
    @Test
    public void nullDrop() throws TextAdventureException {
        Player p = textAdventure.startGame(0, 0);
        assertError(p.drop(null));
    }
    @Test
    public void nullConvert() throws TextAdventureException {
        Player p = textAdventure.startGame(0, 0);
        assertError(p.convert(null, null));
    }
    @Test
    public void nullDecompose() throws TextAdventureException {
        Player p = textAdventure.startGame(0, 0);
        assertError(p.decompose(null));
    }
    @Test
    public void sameItemCompositionInventoryInventoryToSceneryOutputOneMissing() throws TextAdventureException {
        textAdventure.addItemType("Torch", "Shining bright as hell.");
        textAdventure.addSceneryType("Sun", "The lovely sun.");
        textAdventure.addComposition("Torch", "Torch", "Sun", "Hot Fusion");
        textAdventure.placeItem("Torch", 0, 0);
        Player p = textAdventure.startGame(0, 0);
        p.take("Torch");
        assertError(p.convert("Torch", "Torch"));
    }
    
}