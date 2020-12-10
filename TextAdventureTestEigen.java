package student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ias.AbstractTest;
import ias.Player;
import ias.TextAdventure;
import ias.TextAdventureException;

import static org.junit.jupiter.api.Assertions.*;

public class TextAdventureTestEigen extends AbstractTest {

    private static TextAdventure textAdventure;
    
    @BeforeEach
    public void createGame() throws TextAdventureException {
        textAdventure = Factory.getGame("Test", 5, 5);
    }
	
	@Test
	public void placeALotOfItems() throws TextAdventureException {
		textAdventure.addItemType("Sexspielzeug", "Black Mamba");
		for (int i = 0; i < 1000; i++) {
			textAdventure.placeItem("Sexspielzeug", 2, 2);
		}
		Player p = textAdventure.startGame(2, 2);
		assertEquals(1000, p.look().length);
	}
	
	@Test
	public void takeALotOfItems() throws TextAdventureException {
		textAdventure.addItemType("Sexspielzeug", "Black Mamba");
		for (int i = 0; i < 1000; i++) {
			textAdventure.placeItem("Sexspielzeug", 2, 2);
		}
		Player p = textAdventure.startGame(2, 2);
		for (int i = 0; i < 1000; i++) {
			p.take("Sexspielzeug");
		}
		boolean invFlg = p.inventory().length == 1000;
		boolean lookFlg = p.look().length == 0;
		assertTrue(invFlg && lookFlg);
	}
	
	@Test
	public void nullName() throws TextAdventureException {
		TextAdventureException thrown = assertThrows(TextAdventureException.class, () -> {
			TextAdventure nullGame = Factory.getGame(null, 4, 4);
		});
		assertTrue(thrown.getMessage().startsWith("Error!"));
	}
	
	@Test
	public void addItemWithNullDescript() {
		TextAdventureException thrown = assertThrows(TextAdventureException.class, () -> {
			textAdventure.addItemType("Sexspielzeug", null);
		});
		assertTrue(thrown.getMessage().startsWith("Error!"));
	}
	
	@Test
	public void addItemWithNullTypeId() {
		TextAdventureException thrown = assertThrows(TextAdventureException.class, () -> {
			textAdventure.addItemType(null, "Sexspielzeug");
		});
		assertTrue(thrown.getMessage().startsWith("Error!"));
	}
	
	@Test
	public void addItemWithoutTypeId() {
		TextAdventureException thrown = assertThrows(TextAdventureException.class, () -> {
			textAdventure.addItemType("", "Sexspielzeug");
		});
		assertTrue(thrown.getMessage().startsWith("Error!"));
	}
	
	@Test
	public void addSceneryWithNullDescript() {
		TextAdventureException thrown = assertThrows(TextAdventureException.class, () -> {
			textAdventure.addSceneryType("Sexshop", null);
		});
		assertTrue(thrown.getMessage().startsWith("Error!"));
	}
	
	@Test
	public void addSceneryWithNullTypeId() {
		TextAdventureException thrown = assertThrows(TextAdventureException.class, () -> {
			textAdventure.addSceneryType(null, "Sexshop");
		});
		assertTrue(thrown.getMessage().startsWith("Error!"));
	}
	
	@Test
	public void addSceneryWithoutTypeId() {
		TextAdventureException thrown = assertThrows(TextAdventureException.class, () -> {
			textAdventure.addSceneryType("", "Sexshop");
		});
		assertTrue(thrown.getMessage().startsWith("Error!"));
	}
	
	@Test
	public void addSceneryWithInvalidTypeId() {
		TextAdventureException thrown = assertThrows(TextAdventureException.class, () -> {
			textAdventure.addSceneryType("a_", "Sexshop");
		});
		assertTrue(thrown.getMessage().startsWith("Error!"));
	}
	
	@Test
	public void placeItemWithNonExistingId() {
		TextAdventureException thrown = assertThrows(TextAdventureException.class, () -> {
			textAdventure.addSceneryType("Sexshop", "Maurice's Lieblingsladen");
			textAdventure.placeItem("Sxshop", 0, 0);
		});
		assertTrue(thrown.getMessage().startsWith("Error!"));
	}
	
	@Test
	public void placeNullItem() {
		TextAdventureException thrown = assertThrows(TextAdventureException.class, () -> {
			textAdventure.addSceneryType("Sexshop", "Maurice's Lieblingsladen");
			textAdventure.placeItem(null, 0, 0);
		});
		assertTrue(thrown.getMessage().startsWith("Error!"));
	}
	
	@Test
	public void placeItemInvalidX() {
		TextAdventureException thrown = assertThrows(TextAdventureException.class, () -> {
			textAdventure.addSceneryType("Sexshop", "Maurice's Lieblingsladen");
			textAdventure.placeItem("Sexshop", 10, 0);
		});
		assertTrue(thrown.getMessage().startsWith("Error!"));
	}
	
	@Test
	public void placeItemInvalidY() {
		TextAdventureException thrown = assertThrows(TextAdventureException.class, () -> {
			textAdventure.addSceneryType("Sexshop", "Maurice's Lieblingsladen");
			textAdventure.placeItem("Sexshop", 0, 10);
		});
		assertTrue(thrown.getMessage().startsWith("Error!"));
	}
	
	@Test
	public void placeSameItemAndScenery() throws TextAdventureException {
		textAdventure.addItemType("Sexspielzeug", "Maurice's Lieblingsbeschäftigung");
		textAdventure.addSceneryType("Sexshop", "Maurice's Lieblingsladen");
		textAdventure.placeItem("Sexspielzeug", 0, 0);
		textAdventure.placeItem("Sexshop", 0, 0);
		textAdventure.placeItem("Sexspielzeug", 0, 0);
		textAdventure.placeItem("Sexshop", 0, 0);
		textAdventure.placeItem("Sexspielzeug", 0, 0);
		textAdventure.placeItem("Sexshop", 0, 0);
		Player p = textAdventure.startGame(0, 0);
		assertEquals(6, p.look().length);
	}
	
	@Test
	public void addCompositionNullItem1() {
		TextAdventureException thrown = assertThrows(TextAdventureException.class, () -> {
			textAdventure.addItemType("NiceSchwanz", "Ein nicer Schwanz");
			textAdventure.addItemType("Bro", "Ein nicer Bro");
			textAdventure.addSceneryType("NiceSchwanzBro", "Ein nicer Schwanz bro");
			textAdventure.addComposition(null, "Bro", "NiceSchwanzBro", "Du hast jetzt einen nicen Schwanz Bro.");
		});
		assertTrue(thrown.getMessage().startsWith("Error!"));
	}
	
	@Test
	public void addCompositionNullItem2() {
		TextAdventureException thrown = assertThrows(TextAdventureException.class, () -> {
			textAdventure.addItemType("NiceSchwanz", "Ein nicer Schwanz");
			textAdventure.addItemType("Bro", "Ein nicer Bro");
			textAdventure.addSceneryType("NiceSchwanzBro", "Ein nicer Schwanz bro");
			textAdventure.addComposition("NiceSchwanz", null, "NiceSchwanzBro", "Du hast jetzt einen nicen Schwanz Bro.");
		});
		assertTrue(thrown.getMessage().startsWith("Error!"));
	}
	
	@Test
	public void addCompositionNullOut() {
		TextAdventureException thrown = assertThrows(TextAdventureException.class, () -> {
			textAdventure.addItemType("NiceSchwanz", "Ein nicer Schwanz");
			textAdventure.addItemType("Bro", "Ein nicer Bro");
			textAdventure.addSceneryType("NiceSchwanzBro", "Ein nicer Schwanz bro");
			textAdventure.addComposition("NiceSchwanz", "Bro", null, "Du hast jetzt einen nicen Schwanz Bro.");
		});
		assertTrue(thrown.getMessage().startsWith("Error!"));
	}
	
	@Test
	public void addCompositionNullDescript() {
		TextAdventureException thrown = assertThrows(TextAdventureException.class, () -> {
			TextAdventure textAdventure = Factory.getGame("Test", 10, 10);
			textAdventure.addItemType("NiceSchwanz", "Ein nicer Schwanz");
			textAdventure.addItemType("Bro", "Ein nicer Bro");
			textAdventure.addSceneryType("NiceSchwanzBro", "Ein nicer Schwanz bro");
			textAdventure.addComposition("NiceSchwanz", "Bro", "NiceSchwanzBro", null);
			Player p = textAdventure.startGame(0, 0);
		});
		assertTrue(thrown.getMessage().startsWith("Error!"));
	}
	
	@Test
	public void addCompositionNonExistingItem1() {
		TextAdventureException thrown = assertThrows(TextAdventureException.class, () -> {
			textAdventure.addItemType("Bro", "Ein nicer Bro");
			textAdventure.addSceneryType("NiceSchwanzBro", "Ein nicer Schwanz bro");
			textAdventure.addComposition("NiceSchwanz", "Bro", "NiceSchwanzBro", "Du hast jetzt einen nicen Schwanz Bro.");
		});
		assertTrue(thrown.getMessage().startsWith("Error!"));
	}
	
	@Test
	public void addCompositionNonExistingItem2() {
		TextAdventureException thrown = assertThrows(TextAdventureException.class, () -> {
			textAdventure.addItemType("NiceSchwanz", "Ein nicer Schwanz");
			textAdventure.addSceneryType("NiceSchwanzBro", "Ein nicer Schwanz bro");
			textAdventure.addComposition("NiceSchwanz", "Bro", "NiceSchwanzBro", "Du hast jetzt einen nicen Schwanz Bro.");
		});
		assertTrue(thrown.getMessage().startsWith("Error!"));
	}
	
	@Test
	public void addCompositionNonExistingOutItem() {
		TextAdventureException thrown = assertThrows(TextAdventureException.class, () -> {
			textAdventure.addItemType("NiceSchwanz", "Ein nicer Schwanz");
			textAdventure.addItemType("Bro", "Ein nicer Bro");
			textAdventure.addComposition("NiceSchwanz", "Bro", "NiceSchwanzBro", "Du hast jetzt einen nicen Schwanz Bro.");
		});
		assertTrue(thrown.getMessage().startsWith("Error!"));
	}
	
	@Test
	public void addCompositionAlreadyExisting() {
		TextAdventureException thrown = assertThrows(TextAdventureException.class, () -> {
			textAdventure.addItemType("NiceSchwanz", "Ein nicer Schwanz");
			textAdventure.addItemType("Bro", "Ein nicer Bro");
			textAdventure.addSceneryType("NiceSchwanzBro", "Ein nicer Schwanz bro");
			textAdventure.addComposition("NiceSchwanz", "Bro", "NiceSchwanzBro", "Du hast jetzt einen nicen Schwanz Bro.");
			textAdventure.addComposition("Bro", "NiceSchwanz", "BroNiceSchwanz", "Bro, du hast einen nice Schwanz.");
		});
		assertTrue(thrown.getMessage().startsWith("Error!"));
	}
	
	@Test
	public void addCompositionTransAlreadyExisting() {
		TextAdventureException thrown = assertThrows(TextAdventureException.class, () -> {
			textAdventure.addItemType("NiceSchwanz", "Ein nicer Schwanz");
			textAdventure.addItemType("Bro", "Ein nicer Bro");
			textAdventure.addItemType("Yeah", "Yeah Yeah");
			textAdventure.addSceneryType("NiceSchwanzBro", "Ein nicer Schwanz bro");
			textAdventure.addTransformation("NiceSchwanz", "Bro", "NiceSchwanzBro", "Yeah", "Du hast jetzt einen nicen Schwanz Bro.");
			textAdventure.addComposition("Bro", "NiceSchwanz", "BroNiceSchwanz", "Bro, du hast einen nicen Schwanz.");
		});
		assertTrue(thrown.getMessage().startsWith("Error!"));
	}
	
	@Test
	public void addTransformationNullItem1() {
		TextAdventureException thrown = assertThrows(TextAdventureException.class, () -> {
			textAdventure.addItemType("MutzesGolf", "Ein VW Golf");
			textAdventure.addSceneryType("Hagel", "Tennisballgroßer Hagel");
			textAdventure.addItemType("MutzesGolfMitHagelschaden", "Das Schändermobil");
			textAdventure.addTransformation(null, "Hagel", "MutzesGolfMitHagelschaden", "Hagel", "Du hast jetzt ein Schändermobil");
		});
		assertTrue(thrown.getMessage().startsWith("Error!"));
	}
	
	@Test
	public void addTransformationNullItem2() {
		TextAdventureException thrown = assertThrows(TextAdventureException.class, () -> {
			textAdventure.addItemType("MutzesGolf", "Ein VW Golf");
			textAdventure.addSceneryType("Hagel", "Tennisballgroßer Hagel");
			textAdventure.addItemType("MutzesGolfMitHagelschaden", "Das Schändermobil");
			textAdventure.addTransformation("MutzesGolf", null, "MutzesGolfMitHagelschaden", "Hagel", "Du hast jetzt ein Schändermobil");
		});
		assertTrue(thrown.getMessage().startsWith("Error!"));
	}
	
	@Test
	public void addTransformationNullOut1() {
		TextAdventureException thrown = assertThrows(TextAdventureException.class, () -> {
			textAdventure.addItemType("MutzesGolf", "Ein VW Golf");
			textAdventure.addSceneryType("Hagel", "Tennisballgroßer Hagel");
			textAdventure.addItemType("MutzesGolfMitHagelschaden", "Das Schändermobil");
			textAdventure.addTransformation("MutzesGolf", "Hagel", null, "Hagel", "Du hast jetzt ein Schändermobil");
		});
		assertTrue(thrown.getMessage().startsWith("Error!"));
	}
	
	@Test
	public void addTransformationNullOut2() {
		TextAdventureException thrown = assertThrows(TextAdventureException.class, () -> {
			textAdventure.addItemType("MutzesGolf", "Ein VW Golf");
			textAdventure.addSceneryType("Hagel", "Tennisballgroßer Hagel");
			textAdventure.addItemType("MutzesGolfMitHagelschaden", "Das Schändermobil");
			textAdventure.addTransformation("MutzesGolf", "Hagel", "MutzesGolfMitHagelschaden", null, "Du hast jetzt ein Schändermobil");
		});
		assertTrue(thrown.getMessage().startsWith("Error!"));
	}
	
	@Test
	public void addTransformationNullDescript() {
		TextAdventureException thrown = assertThrows(TextAdventureException.class, () -> {
			textAdventure.addItemType("MutzesGolf", "Ein VW Golf");
			textAdventure.addSceneryType("Hagel", "Tennisballgroßer Hagel");
			textAdventure.addItemType("MutzesGolfMitHagelschaden", "Das Schändermobil");
			textAdventure.addTransformation("MutzesGolf", "Hagel", "MutzesGolfMitHagelschaden", "Hagel", null);
		});
		assertTrue(thrown.getMessage().startsWith("Error!"));
	}
	
	@Test
	public void addTransformationAlreadyExisting() {
		TextAdventureException thrown = assertThrows(TextAdventureException.class, () -> {
			textAdventure.addItemType("MutzesGolf", "Ein VW Golf");
			textAdventure.addSceneryType("Hagel", "Tennisballgroßer Hagel");
			textAdventure.addItemType("MutzesGolfMitHagelschaden", "Das Schändermobil");
			textAdventure.addTransformation("MutzesGolf", "Hagel", "MutzesGolfMitHagelschaden", "Hagel", "Du hast jetzt ein Schändermobil");
			textAdventure.addTransformation("Hagel", "MutzesGolf", "Hagel", "MutzesGolf", "Du hast jetzt ein Schändermobil");
		});
		assertTrue(thrown.getMessage().startsWith("Error!"));
	}
	
	@Test
	public void addTransformationCompAlreadyExisting() {
		TextAdventureException thrown = assertThrows(TextAdventureException.class, () -> {
			textAdventure.addItemType("MutzesGolf", "Ein VW Golf");
			textAdventure.addSceneryType("Hagel", "Tennisballgroßer Hagel");
			textAdventure.addItemType("MutzesGolfMitHagelschaden", "Das Schändermobil");
			textAdventure.addComposition("MutzesGolf", "Hagel", "MutzesGolfMitHagelschaden", "Du hast jetzt ein Schändermobil");
			textAdventure.addTransformation("Hagel", "MutzesGolf", "Hagel", "MutzesGolfMitHagelschaden", "Du hast jetzt ein Schändermobil");
		});
		assertTrue(thrown.getMessage().startsWith("Error!"));
	}
	
	@Test
	public void addTransformationNonExistingItem1() {
		TextAdventureException thrown = assertThrows(TextAdventureException.class, () -> {
			textAdventure.addSceneryType("Hagel", "Tennisballgroßer Hagel");
			textAdventure.addItemType("MutzesGolfMitHagelschaden", "Das Schändermobil");
			textAdventure.addTransformation("MutzesGolf", "Hagel", "MutzesGolfMitHagelschaden", "Hagel", "Du hast jetzt ein Schändermobil");
		});
		assertTrue(thrown.getMessage().startsWith("Error!"));
	}
	
	@Test
	public void addTransformationNonExistingItem2() {
		TextAdventureException thrown = assertThrows(TextAdventureException.class, () -> {
			textAdventure.addItemType("MutzesGolf", "Ein VW Golf");
			textAdventure.addItemType("MutzesGolfMitHagelschaden", "Das Schändermobil");
			textAdventure.addTransformation("MutzesGolf", "Hagel", "MutzesGolfMitHagelschaden", "Hagel", "Du hast jetzt ein Schändermobil");
		});
		assertTrue(thrown.getMessage().startsWith("Error!"));
	}
	
	@Test
	public void addTransformationNonExistingOutItem1() {
		TextAdventureException thrown = assertThrows(TextAdventureException.class, () -> {
			textAdventure.addItemType("MutzesGolf", "Ein VW Golf");
			textAdventure.addSceneryType("Hagel", "Tennisballgroßer Hagel");
			textAdventure.addTransformation("MutzesGolf", "Hagel", "MutzesGolfMitHagelschaden", "Hagel", "Du hast jetzt ein Schändermobil");
		});
		assertTrue(thrown.getMessage().startsWith("Error!"));
	}
	
	@Test
	public void addTransformationNonExistingOutItem2() {
		TextAdventureException thrown = assertThrows(TextAdventureException.class, () -> {
			textAdventure.addItemType("MutzesGolf", "Ein VW Golf");
			textAdventure.addSceneryType("Hagel", "Tennisballgroßer Hagel");
			textAdventure.addItemType("MutzesGolfMitHagelschaden", "Das Schändermobil");
			textAdventure.addTransformation("MutzesGolf", "Hagel", "MutzesGolfMitHagelschaden", "Eis", "Du hast jetzt ein Schändermobil");
		});
		assertTrue(thrown.getMessage().startsWith("Error!"));
	}
	
	@Test
	public void addDecompositionNullIn() {
		TextAdventureException thrown = assertThrows(TextAdventureException.class, () -> {
			textAdventure.addSceneryType("MutzeGlatze", "Der Schänder");
			textAdventure.addSceneryType("Mutze", "Maurice Kaiser");
			textAdventure.addItemType("Glatze", "Haarfrisur");
			textAdventure.addDecomposition(null, "Mutze", "Glatze", "Du hast Mutze Glatze zerlegt");
		});
		assertTrue(thrown.getMessage().startsWith("Error!"));
	}
	
	@Test
	public void addDecompositionNullOut1() {
		TextAdventureException thrown = assertThrows(TextAdventureException.class, () -> {
			textAdventure.addSceneryType("MutzeGlatze", "Der Schänder");
			textAdventure.addSceneryType("Mutze", "Maurice Kaiser");
			textAdventure.addItemType("Glatze", "Haarfrisur");
			textAdventure.addDecomposition("MutzeGlatze", null, "Glatze", "Du hast Mutze Glatze zerlegt");
		});
		assertTrue(thrown.getMessage().startsWith("Error!"));
	}
	
	@Test
	public void addDecompositionNullOut2() {
		TextAdventureException thrown = assertThrows(TextAdventureException.class, () -> {
			textAdventure.addSceneryType("MutzeGlatze", "Der Schänder");
			textAdventure.addSceneryType("Mutze", "Maurice Kaiser");
			textAdventure.addItemType("Glatze", "Haarfrisur");
			textAdventure.addDecomposition("MutzeGlatze", "Mutze", null, "Du hast Mutze Glatze zerlegt");
		});
		assertTrue(thrown.getMessage().startsWith("Error!"));
	}
	
	@Test
	public void addDecompositionNullDescript() {
		TextAdventureException thrown = assertThrows(TextAdventureException.class, () -> {
			textAdventure.addSceneryType("MutzeGlatze", "Der Schänder");
			textAdventure.addSceneryType("Mutze", "Maurice Kaiser");
			textAdventure.addItemType("Glatze", "Haarfrisur");
			textAdventure.addDecomposition("MutzeGlatze", "Mutze", "Glatze", null);
		});
		assertTrue(thrown.getMessage().startsWith("Error!"));
	}
	
	@Test
	public void addDecompositionMissingItem() {
		TextAdventureException thrown = assertThrows(TextAdventureException.class, () -> {
			//textAdventure.addSceneryType("MutzeGlatze", "Der Schänder");
			textAdventure.addSceneryType("Mutze", "Maurice Kaiser");
			textAdventure.addItemType("Glatze", "Haarfrisur");
			textAdventure.addDecomposition("MutzeGlatze", "Mutze", "Glatze", "Du hast Mutze Glatze zerlegt");
		});
		assertTrue(thrown.getMessage().startsWith("Error!"));
	}
	
	@Test
	public void addDecompositionMissingOut1() {
		TextAdventureException thrown = assertThrows(TextAdventureException.class, () -> {
			textAdventure.addSceneryType("MutzeGlatze", "Der Schänder");
			//textAdventure.addSceneryType("Mutze", "Maurice Kaiser");
			textAdventure.addItemType("Glatze", "Haarfrisur");
			textAdventure.addDecomposition("MutzeGlatze", "Mutze", "Glatze", "Du hast Mutze Glatze zerlegt");
		});
		assertTrue(thrown.getMessage().startsWith("Error!"));
	}
	
	@Test
	public void addDecompositionMissingOut2() {
		TextAdventureException thrown = assertThrows(TextAdventureException.class, () -> {
			textAdventure.addSceneryType("MutzeGlatze", "Der Schänder");
			textAdventure.addSceneryType("Mutze", "Maurice Kaiser");
			//textAdventure.addItemType("Glatze", "Haarfrisur");
			textAdventure.addDecomposition("MutzeGlatze", "Mutze", "Glatze", "Du hast Mutze Glatze zerlegt");
		});
		assertTrue(thrown.getMessage().startsWith("Error!"));
	}
	
	@Test
	public void addDecompositionAlreadyExisting() {
		TextAdventureException thrown = assertThrows(TextAdventureException.class, () -> {
			textAdventure.addSceneryType("MutzeGlatze", "Der Schänder");
			textAdventure.addSceneryType("Mutze", "Maurice Kaiser");
			textAdventure.addItemType("Glatze", "Haarfrisur");
			textAdventure.addItemType("Geld", "Mutzes Geld");
			textAdventure.addDecomposition("MutzeGlatze", "Mutze", "Glatze", "Du hast Mutze Glatze zerlegt");
			textAdventure.addDecomposition("MutzeGlatze", "MutzeGlatze", "Geld", "Du hast Mutze Glatze Geld abgenommen");
		});
		assertTrue(thrown.getMessage().startsWith("Error!"));
	} 
}