package student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ias.AbstractTest;
import ias.Player;
import ias.TextAdventure;
import ias.TextAdventureException;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTestEigen extends AbstractTest {
	private static TextAdventure textAdventure;

    @BeforeEach
    public void createGame() throws TextAdventureException {
        textAdventure = Factory.getGame("Test", 5, 5);
    }
	
	@Test
	public void decomposeItemInItselfFromField0() throws TextAdventureException {
		textAdventure.addItemType("Taschenlampe", "Eine Taschenlampe");
		textAdventure.addItemType("Batterien", "Batterien für die Taschenlampe");
		textAdventure.addDecomposition("Taschenlampe", "Taschenlampe", "Batterien", "Du hast die Batterien aus der Taschenlampe genommen");
		textAdventure.placeItem("Taschenlampe", 0, 0);
		
		Player p = textAdventure.startGame(0, 0);
		p.decompose("Taschenlampe");
		String resultStr = "Taschenlampe - Eine Taschenlampe";
		assertContains(resultStr, p.inventory());
		assertContainsNot(resultStr, p.look());
		resultStr = "Batterien - Batterien für die Taschenlampe";
		assertContains(resultStr, p.inventory());
	}
	
	@Test
	public void decomposeItemFromInventory0() throws TextAdventureException {
		textAdventure.addItemType("Taschenlampe", "Eine Taschenlampe");
		textAdventure.addItemType("Batterien", "Batterien für die Taschenlampe");
		textAdventure.addItemType("LeereTaschenlampe", "Enthält keine Batterien");
		textAdventure.addDecomposition("Taschenlampe", "LeereTaschenlampe", "Batterien", "Du hast die Batterien aus der Taschenlampe genommen");
		textAdventure.placeItem("Taschenlampe", 0, 0);
		
		Player p = textAdventure.startGame(0, 0);
		p.take("Taschenlampe");
		p.decompose("Taschenlampe");
		String resultStr = "Batterien - Batterien für die Taschenlampe";
		assertContains(resultStr, p.inventory());
		assertContainsNot(resultStr, p.look());
		resultStr = "Taschenlampe - Eine Taschenlampe";
		assertContainsNot(resultStr, p.inventory());
	}
	
	@Test
	public void decomposeItemInItselfFromInventory0() throws TextAdventureException {
		textAdventure.addItemType("Taschenlampe", "Eine Taschenlampe");
		textAdventure.addItemType("Batterien", "Batterien für die Taschenlampe");
		textAdventure.addDecomposition("Taschenlampe", "Taschenlampe", "Batterien", "Du hast die Batterien aus der Taschenlampe genommen");
		textAdventure.placeItem("Taschenlampe", 0, 0);
		
		Player p = textAdventure.startGame(0, 0);
		p.take("Taschenlampe");
		p.decompose("Taschenlampe");
		String resultStr = "Taschenlampe - Eine Taschenlampe";
		assertContains(resultStr, p.inventory());
		assertContainsNot(resultStr, p.look());
	}
	
	@Test
	public void decomposeItemCheckDescription() throws TextAdventureException {
		textAdventure.addItemType("Taschenlampe", "Eine Taschenlampe");
		textAdventure.addItemType("Batterien", "Batterien für die Taschenlampe");
		textAdventure.addDecomposition("Taschenlampe", "Taschenlampe", "Batterien", "Du hast die Batterien aus der Taschenlampe genommen");
		textAdventure.placeItem("Taschenlampe", 0, 0);
		
		Player p = textAdventure.startGame(0, 0);
		p.take("Taschenlampe");
		String resultStr = "Du hast die Batterien aus der Taschenlampe genommen";
		String[] output = new String[1];
		output[0] = p.decompose("Taschenlampe");
		assertContains(resultStr, output);
	}
	
	@Test
	public void decomposeScenerySceneryOutput() throws TextAdventureException {
		textAdventure.addSceneryType("TotesReh", "Beim Jagen geschossen");
		textAdventure.addItemType("RehFleisch", "Frisches Filet");
		textAdventure.addSceneryType("RehKadaver", "Hier ist nichts mehr zu holen");
		textAdventure.addDecomposition("TotesReh", "RehKadaver", "RehFleisch", "Du hast das Reh ausgenommen");
		textAdventure.placeItem("TotesReh", 0, 0);
		
		Player p = textAdventure.startGame(0, 0);
		p.decompose("TotesReh");
		String resultStr = "RehKadaver - Hier ist nichts mehr zu holen";
		assertContains(resultStr, p.look());
		assertContainsNot(resultStr, p.inventory());
		assertContains("RehFleisch - Frisches Filet", p.inventory());
		assertContainsNot("RehFleisch - Frisches Filet", p.look());
		assertContainsNot("TotesReh - Beim Jagen geschossen", p.inventory());
		assertContainsNot("TotesReh - Beim Jagen geschossen", p.look());
	}
	
	@Test
	public void decomposeItemFromInventorySceneryOutput() throws TextAdventureException {
		textAdventure.addItemType("TotesReh", "Beim Jagen geschossen");
		textAdventure.addItemType("RehFleisch", "Frisches Filet");
		textAdventure.addSceneryType("RehKadaver", "Hier ist nichts mehr zu holen");
		textAdventure.addDecomposition("TotesReh", "RehKadaver", "RehFleisch", "Du hast das Reh ausgenommen");
		textAdventure.placeItem("TotesReh", 0, 0);
		
		Player p = textAdventure.startGame(0, 0);
		p.take("TotesReh");
		p.decompose("TotesReh");
		String resultStr = "RehKadaver - Hier ist nichts mehr zu holen";
		assertContains(resultStr, p.look());
		assertContainsNot(resultStr, p.inventory());
		assertContains("RehFleisch - Frisches Filet", p.inventory());
		assertContainsNot("RehFleisch - Frisches Filet", p.look());
		assertContainsNot("TotesReh - Beim Jagen geschossen", p.inventory());
		assertContainsNot("TotesReh - Beim Jagen geschossen", p.look());
	}
	
	@Test
	public void decomposeItemFromFieldSceneryOutput() throws TextAdventureException {
		textAdventure.addItemType("TotesReh", "Beim Jagen geschossen");
		textAdventure.addItemType("RehFleisch", "Frisches Filet");
		textAdventure.addSceneryType("RehKadaver", "Hier ist nichts mehr zu holen");
		textAdventure.addDecomposition("TotesReh", "RehKadaver", "RehFleisch", "Du hast das Reh ausgenommen");
		textAdventure.placeItem("TotesReh", 0, 0);
		
		Player p = textAdventure.startGame(0, 0);
		p.decompose("TotesReh");
		String resultStr = "RehKadaver - Hier ist nichts mehr zu holen";
		assertContains(resultStr, p.look());
		assertContainsNot(resultStr, p.inventory());
		assertContains("RehFleisch - Frisches Filet", p.inventory());
		assertContainsNot("RehFleisch - Frisches Filet", p.look());
		assertContainsNot("TotesReh - Beim Jagen geschossen", p.inventory());
		assertContainsNot("TotesReh - Beim Jagen geschossen", p.look());
	}
	
	@Test
	public void decomposeDoubledItemFromInventory() throws TextAdventureException {
		textAdventure.addItemType("TotesReh", "Beim Jagen geschossen");
		textAdventure.addItemType("RehFleisch", "Frisches Filet");
		textAdventure.addSceneryType("RehKadaver", "Hier ist nichts mehr zu holen");
		textAdventure.addDecomposition("TotesReh", "RehKadaver", "RehFleisch", "Du hast das Reh ausgenommen");
		textAdventure.placeItem("TotesReh", 0, 0);
		textAdventure.placeItem("TotesReh", 0, 0);
		
		Player p = textAdventure.startGame(0, 0);
		p.take("TotesReh");
		p.decompose("TotesReh");
		assertContains("RehKadaver - Hier ist nichts mehr zu holen", p.look());
		assertContainsNot("RehKadaver - Hier ist nichts mehr zu holen", p.inventory());
		assertContains("RehFleisch - Frisches Filet", p.inventory());
		assertContainsNot("RehFleisch - Frisches Filet", p.look());
		assertContainsNot("TotesReh - Beim Jagen geschossen", p.inventory());
		assertContains("TotesReh - Beim Jagen geschossen", p.look());
	}
	
	@Test
	public void decomposeItemNotAvailable() throws TextAdventureException {
		textAdventure.addItemType("TotesReh", "Beim Jagen geschossen");
		textAdventure.addItemType("RehFleisch", "Frisches Filet");
		textAdventure.addSceneryType("RehKadaver", "Hier ist nichts mehr zu holen");
		textAdventure.addDecomposition("TotesReh", "RehKadaver", "RehFleisch", "Du hast das Reh ausgenommen");
		textAdventure.placeItem("TotesReh", 1, 0);
		Player p = textAdventure.startGame(0, 0);
		assertError(p.decompose("TotesReh"));
	}
	
	@Test
	public void decomposeItemDecompositionNotExisting() throws TextAdventureException {
		textAdventure.addItemType("TotesReh", "Beim Jagen geschossen");
		textAdventure.addItemType("RehFleisch", "Frisches Filet");
		textAdventure.addSceneryType("RehKadaver", "Hier ist nichts mehr zu holen");
		textAdventure.placeItem("TotesReh", 0, 0);
		Player p = textAdventure.startGame(0, 0);
		assertError(p.decompose("TotesReh"));
	}
	
	@Test
	public void decomposeNonExistingItem() throws TextAdventureException {
		textAdventure.addItemType("TotesReh", "Beim Jagen geschossen");
		textAdventure.addItemType("RehFleisch", "Frisches Filet");
		textAdventure.addSceneryType("RehKadaver", "Hier ist nichts mehr zu holen");
		textAdventure.addDecomposition("TotesReh", "RehKadaver", "RehFleisch", "Du hast das Reh ausgenommen");
		textAdventure.placeItem("TotesReh", 0, 0);
		Player p = textAdventure.startGame(0, 0);
		assertError(p.decompose("LebendesReh"));
	}
	
	@Test
	public void decomposeNullItem() throws TextAdventureException {
		Player p = textAdventure.startGame(0, 0);
		assertError(p.decompose(null));
	}
	
	@Test
	public void onlyItems() throws TextAdventureException {
		textAdventure.addItemType("Torch", "Shining bright as hell.");
        textAdventure.addSceneryType("Sun", "The lovely sun.");
        textAdventure.placeItem("Torch", 0, 0);
		textAdventure.placeItem("Sun", 0, 0);

        Player p = textAdventure.startGame(0, 0);
		p.take("Sun");
        p.take("Torch");
		
        assertContains("Sun - The lovely sun.", p.look());
	}
	
	@Test
	public void takeSameItems() throws TextAdventureException {
		textAdventure.addItemType("Torch", "Shining bright as hell.");
        textAdventure.addSceneryType("Sun", "The lovely sun.");
        textAdventure.placeItem("Torch", 0, 0);
		textAdventure.placeItem("Sun", 0, 0);
		textAdventure.placeItem("Torch", 0, 0);

        Player p = textAdventure.startGame(0, 0);
		p.take("Sun");
        p.take("Torch");
		p.take("Torch");
		
        assertContains("Sun - The lovely sun.", p.look());
	}
	
	@Test
	public void takeNotScenery() throws TextAdventureException {
		textAdventure.addItemType("Torch", "Shining bright as hell.");
        textAdventure.addSceneryType("Sun", "The lovely sun.");
		textAdventure.addComposition("Torch", "Torch", "Sun", "Hot Fusion");
        textAdventure.placeItem("Torch", 0, 0);
		textAdventure.placeItem("Torch", 0, 0);
        Player p = textAdventure.startGame(0, 0);
		p.convert("Torch", "Torch");
		
			
        assertError(p.take("Sun"));
	}
	
	@Test
    public void nullTake() throws TextAdventureException {
        Player p = textAdventure.startGame(0, 0);
		textAdventure.addItemType("Torch", "Shining bright as hell.");
		textAdventure.placeItem("Torch", 0, 0);
        assertError(p.take(null));
    }
	
	@Test
	public void takeSameItemsInInventory() throws TextAdventureException {
		textAdventure.addItemType("Torch", "Shining bright as hell.");
        textAdventure.addSceneryType("Sun", "The lovely sun.");
		textAdventure.addComposition("Torch", "Torch", "Sun", "Hot Fusion");
        textAdventure.placeItem("Torch", 0, 0);
		textAdventure.placeItem("Torch", 0, 0);
		textAdventure.placeItem("Torch", 0, 0);
		textAdventure.placeItem("Torch", 0, 0);
        Player p = textAdventure.startGame(0, 0);
		p.take("Torch");
		p.take("Torch");
		p.take("Torch");
		p.take("Torch");
			
        assertContainsCount("Torch - Shining bright as hell.", p.inventory(), 4);
	}
	
	@Test
    public void nullDrop() throws TextAdventureException {
        Player p = textAdventure.startGame(0, 0);
        assertError(p.drop(null));
    }
	
	@Test
	public void dropSameItem() throws TextAdventureException {
		textAdventure.addItemType("Torch", "Shining bright as hell.");
        textAdventure.addSceneryType("Sun", "The lovely sun.");
		textAdventure.addComposition("Torch", "Torch", "Sun", "Hot Fusion");
        textAdventure.placeItem("Torch", 0, 0);
		textAdventure.placeItem("Torch", 0, 0);
		textAdventure.placeItem("Torch", 0, 0);
		textAdventure.placeItem("Torch", 0, 0);
        Player p = textAdventure.startGame(0, 0);
		p.take("Torch");
		p.take("Torch");
		p.take("Torch");
		p.take("Torch");
		p.drop("Torch");
			
        assertContainsCount("Torch - Shining bright as hell.", p.inventory(), 3);
	}
	
	@Test
    public void nullConvertItem1() throws TextAdventureException {
		textAdventure.addItemType("Torch", "Shining bright as hell.");
        textAdventure.addSceneryType("Sun", "The lovely sun.");
		textAdventure.addComposition("Torch", "Torch", "Sun", "Hot Fusion");
        textAdventure.placeItem("Torch", 0, 0);
		textAdventure.placeItem("Torch", 0, 0);
        Player p = textAdventure.startGame(0, 0);
        assertError(p.convert(null, "Torch"));
    }
	
	@Test
    public void nullConvertItem2() throws TextAdventureException {
		textAdventure.addItemType("Torch", "Shining bright as hell.");
        textAdventure.addSceneryType("Sun", "The lovely sun.");
		textAdventure.addComposition("Torch", "Torch", "Sun", "Hot Fusion");
        textAdventure.placeItem("Torch", 0, 0);
		textAdventure.placeItem("Torch", 0, 0);
        Player p = textAdventure.startGame(0, 0);
        assertError(p.convert("Torch", null));
    }
	
	@Test
    public void nonexistItem1Convert() throws TextAdventureException {
		textAdventure.addItemType("Torch", "Shining bright as hell.");
        textAdventure.addSceneryType("Sun", "The lovely sun.");
		textAdventure.addComposition("Torch", "Torch", "Sun", "Hot Fusion");
        textAdventure.placeItem("Torch", 0, 0);
		textAdventure.placeItem("Torch", 0, 0);
        Player p = textAdventure.startGame(0, 0);
        assertError(p.convert("Penis", "Torch"));
    }
	
	@Test
    public void nonexistItem2Convert() throws TextAdventureException {
		textAdventure.addItemType("Torch", "Shining bright as hell.");
        textAdventure.addSceneryType("Sun", "The lovely sun.");
		textAdventure.addComposition("Torch", "Torch", "Sun", "Hot Fusion");
        textAdventure.placeItem("Torch", 0, 0);
		textAdventure.placeItem("Torch", 0, 0);
        Player p = textAdventure.startGame(0, 0);
        assertError(p.convert("Torch", "Schwanz"));
    }
	
	@Test
    public void nonexistCompositionTransformation() throws TextAdventureException {
		textAdventure.addItemType("Torch", "Shining bright as hell.");
        textAdventure.addSceneryType("Sun", "The lovely sun.");
        textAdventure.placeItem("Torch", 0, 0);
		textAdventure.placeItem("Torch", 0, 0);
        Player p = textAdventure.startGame(0, 0);
        assertError(p.convert("Torch", "Torch"));
    }
	
	@Test
    public void missingItem1() throws TextAdventureException {
		textAdventure.addItemType("Torch", "Shining bright as hell.");
		textAdventure.addItemType("Penis", "Schwanz");
        textAdventure.addSceneryType("Sun", "The lovely sun.");
		textAdventure.addComposition("Penis", "Torch", "Sun", "Hot Fusion");
        textAdventure.placeItem("Torch", 0, 0);
        Player p = textAdventure.startGame(0, 0);
        assertError(p.convert("Penis", "Torch"));
    }
	
	public void missingItem2() throws TextAdventureException {
		textAdventure.addItemType("Torch", "Shining bright as hell.");
		textAdventure.addItemType("Penis", "Schwanz");
        textAdventure.addSceneryType("Sun", "The lovely sun.");
		textAdventure.addComposition("Penis", "Torch", "Sun", "Hot Fusion");
        textAdventure.placeItem("Torch", 0, 0);
        Player p = textAdventure.startGame(0, 0);
		p.take("Torch");
        assertError(p.convert("Penis", "Torch"));
    }
	
	@Test
    public void missingItem3() throws TextAdventureException {
		textAdventure.addItemType("Torch", "Shining bright as hell.");
		textAdventure.addItemType("Penis", "Schwanz");
        textAdventure.addSceneryType("Sun", "The lovely sun.");
		textAdventure.addComposition("Penis", "Torch", "Sun", "Hot Fusion");
        textAdventure.placeItem("Penis", 0, 0);
        Player p = textAdventure.startGame(0, 0);
        assertError(p.convert("Penis", "Torch"));
    }
	
	public void missingItem4() throws TextAdventureException {
		textAdventure.addItemType("Torch", "Shining bright as hell.");
		textAdventure.addItemType("Penis", "Schwanz");
        textAdventure.addSceneryType("Sun", "The lovely sun.");
		textAdventure.addComposition("Penis", "Torch", "Sun", "Hot Fusion");
        textAdventure.placeItem("Penis", 0, 0);
        Player p = textAdventure.startGame(0, 0);
		p.take("Penis");
        assertError(p.convert("Penis", "Torch"));
    }
	
	@Test
    public void convertFirstItemsFromInventory() throws TextAdventureException {
		textAdventure.addItemType("Torch", "Shining bright as hell.");
        textAdventure.addSceneryType("Sun", "The lovely sun.");
        textAdventure.placeItem("Torch", 0, 0);
		textAdventure.placeItem("Torch", 0, 0);
		textAdventure.placeItem("Torch", 0, 0);
		textAdventure.placeItem("Torch", 0, 0);
        Player p = textAdventure.startGame(0, 0);
		p.take("Torch");
		p.take("Torch");
        p.convert("Torch", "Torch");
		assertContainsCount("Torch - Shining bright as hell.", p.look(), 2);
    }
	
	@Test
    public void convertIteminTheInventory() throws TextAdventureException {
		textAdventure.addItemType("Torch", "Shining bright as hell.");
        textAdventure.addItemType("Sun", "The lovely sun.");
		textAdventure.addComposition("Torch", "Torch", "Sun", "Hot Fusion");
        textAdventure.placeItem("Torch", 0, 0);
		textAdventure.placeItem("Torch", 0, 0);
        Player p = textAdventure.startGame(0, 0);
        p.convert("Torch", "Torch");
		assertContains("Sun - The lovely sun.", p.inventory());
    }
	
	@Test
    public void convertItem1Item2SameLikeItem2Item1() throws TextAdventureException {
		textAdventure.addItemType("Torch", "Shining bright as hell.");
		textAdventure.addItemType("Penis", "DickesDing");
        textAdventure.addSceneryType("Sun", "The lovely sun.");
		textAdventure.addComposition("Penis", "Torch", "Sun", "Hot Fusion");
        textAdventure.placeItem("Penis", 0, 0);
		textAdventure.placeItem("Penis", 0, 0);
		textAdventure.placeItem("Torch", 0, 0);
		textAdventure.placeItem("Torch", 0, 0);
        Player p = textAdventure.startGame(0, 0);
        p.convert("Penis", "Torch");
		p.convert("Torch", "Penis");
		assertContainsCount("Sun - The lovely sun.", p.look(), 2);
    }
	
	@Test
    public void convertOutputDescription() throws TextAdventureException {
		textAdventure.addItemType("Torch", "Shining bright as hell.");
		textAdventure.addItemType("Penis", "DickesDing");
        textAdventure.addSceneryType("Sun", "The lovely sun.");
		textAdventure.addComposition("Penis", "Torch", "Sun", "BrennenderSchwanz");
        textAdventure.placeItem("Penis", 0, 0);

		textAdventure.placeItem("Torch", 0, 0);
        Player p = textAdventure.startGame(0, 0);
		
		String[] out = new String[1];
		out[0] = p.convert("Penis", "Torch");
		
		assertContains("BrennenderSchwanz", out);
    }
}