package lazersmoke.botanicalworkshop.common.lexicon;

import lazersmoke.botanicalworkshop.api.BotanicalWorkshopAPI;
import lazersmoke.botanicalworkshop.common.crafting.ModCraftingRecipes;
import lazersmoke.botanicalworkshop.common.crafting.ModElvenTradeRecipes;
import lazersmoke.botanicalworkshop.common.crafting.ModPetalRecipes;
import lazersmoke.botanicalworkshop.common.lib.LibLexicon;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.lexicon.LexiconCategory;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.common.lexicon.page.PageCraftingRecipe;
import vazkii.botania.common.lexicon.page.PageElvenRecipe;
import vazkii.botania.common.lexicon.page.PagePetalRecipe;
import vazkii.botania.common.lexicon.page.PageText;

public final class LexiconData {

	public static LexiconEntry exAquainas;
	public static LexiconEntry elvenPool;
	public static LexiconEntry thaumicCore;
	public static LexiconEntry gatewayCore;
	public static LexiconEntry bindingCrystal;
	public static LexiconEntry basicCatalyst;
	
	public static void init(){
		
		BotaniaAPI.addCategory(BotanicalWorkshopAPI.categoryWorkshop = new WLexiconCategory(LibLexicon.CATEGORY_WORKSHOP, 8));
		BotaniaAPI.addCategory(BotanicalWorkshopAPI.categoryThaumic = new WLexiconCategory(LibLexicon.CATEGORY_THAUMIC, 7));
		
		LexiconCategory categoryWorkshop = BotanicalWorkshopAPI.categoryWorkshop;
		LexiconCategory categoryThaumic = BotanicalWorkshopAPI.categoryThaumic;
		
		// MAIN WORKSHOP ENTRIES
		elvenPool = new WLexiconEntry(LibLexicon.WORKSHOP_ELVENPOOL, categoryWorkshop);
		elvenPool.setLexiconPages(new PageText("0"), new PageCraftingRecipe("1", ModCraftingRecipes.recipeElvenPool), new PageElvenRecipe("2", ModElvenTradeRecipes.elvenPoolRecipe));
		
		exAquainas = new WLexiconEntry(LibLexicon.WORKSHOP_EXAQUAINAS, categoryWorkshop);
		exAquainas.setLexiconPages(new PageText("0"), new PagePetalRecipe("1", ModPetalRecipes.exAquainasRecipe));
		
		gatewayCore = new WLexiconEntry(LibLexicon.WORKSHOP_GATEWAYCORE, categoryWorkshop);
		gatewayCore.setLexiconPages(new PageText("0"), new PageCraftingRecipe("1", ModCraftingRecipes.recipeWeakGatewayCore), new PageElvenRecipe("2", ModElvenTradeRecipes.gatewayCoreRecipe));
		
		bindingCrystal = new WLexiconEntry(LibLexicon.WORKSHOP_BINDINGCRYSTAL, categoryWorkshop);
		bindingCrystal.setLexiconPages(new PageText("0"), new PageCraftingRecipe("1", ModCraftingRecipes.recipeElvenPool), new PageElvenRecipe("2", ModElvenTradeRecipes.elvenPoolRecipe));
		
		basicCatalyst = new WLexiconEntry(LibLexicon.WORKSHOP_BASICCATALYST, categoryWorkshop);
		basicCatalyst.setLexiconPages(new PageText("0"), new PageCraftingRecipe("1", ModCraftingRecipes.recipeElvenPool), new PageElvenRecipe("2", ModElvenTradeRecipes.elvenPoolRecipe));
		// THAUMIC ENTRIES
		thaumicCore = new WLexiconEntry(LibLexicon.THAUMIC_THAUMICCORE, categoryThaumic);
		
		
		
	}
}