package lazersmoke.botanicalworkshop.common.lexicon;

import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import lazersmoke.botanicalworkshop.client.lib.LibResources;
import lazersmoke.botanicalworkshop.common.crafting.ModCraftingRecipes;
import lazersmoke.botanicalworkshop.common.crafting.ModElvenTradeRecipes;
import lazersmoke.botanicalworkshop.common.crafting.ModGatewayTransmutationRecipes;
import lazersmoke.botanicalworkshop.common.crafting.ModPetalRecipes;
import lazersmoke.botanicalworkshop.common.lexicon.page.PageGatewayTransmutationRecipe;
import lazersmoke.botanicalworkshop.common.lib.LibLexicon;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.lexicon.KnowledgeType;
import vazkii.botania.api.lexicon.LexiconCategory;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.common.lexicon.page.PageCraftingRecipe;
import vazkii.botania.common.lexicon.page.PageElvenRecipe;
import vazkii.botania.common.lexicon.page.PageImage;
import vazkii.botania.common.lexicon.page.PagePetalRecipe;
import vazkii.botania.common.lexicon.page.PageText;

public final class LexiconData {
	
	public static LexiconCategory categoryWorkshop;	
	public static LexiconCategory categoryThaumic;
	public static LexiconCategory categoryBlood;
	public static LexiconCategory categoryMechanical;	
	
	public static KnowledgeType workshopKnowledge;
	public static KnowledgeType thaumicKnowledge;
	public static KnowledgeType bloodKnowledge;
	public static KnowledgeType mechanicalKnowledge;
	
	public static LexiconEntry workshopIntro;
	public static LexiconEntry elvenPool;
	public static LexiconEntry gatewayCore;
	public static LexiconEntry bindingCrystal;
	public static LexiconEntry basicCatalyst;
	public static LexiconEntry logicalSound;
	
	public static LexiconEntry thaumicIntro;
	public static LexiconEntry thaumicCore;
	public static LexiconEntry thaumicCatalyst;
	
	public static LexiconEntry bloodIntro;
	
	public static LexiconEntry mechanicalIntro;
	public static LexiconEntry exAquainas;
	
	public static void init(){
		
		workshopKnowledge = BotaniaAPI.registerKnowledgeType(LibLexicon.KNOWLEDGE_WORKSHOP, EnumChatFormatting.DARK_RED, true);
		thaumicKnowledge = BotaniaAPI.registerKnowledgeType(LibLexicon.KNOWLEDGE_THAUMIC, EnumChatFormatting.DARK_PURPLE, false);
		bloodKnowledge = BotaniaAPI.registerKnowledgeType(LibLexicon.KNOWLEDGE_BLOOD, EnumChatFormatting.RED, false);
		mechanicalKnowledge = BotaniaAPI.registerKnowledgeType(LibLexicon.KNOWLEDGE_MECHANICAL, EnumChatFormatting.WHITE, false);
		
		categoryWorkshop=new WLexiconCategory(LibLexicon.CATEGORY_WORKSHOP, 8).setIcon(new ResourceLocation(LibResources.WORKSHOP_ICON + ".png"));
		BotaniaAPI.addCategory(categoryWorkshop);
		categoryThaumic=new WLexiconCategory(LibLexicon.CATEGORY_THAUMIC, 7).setIcon(new ResourceLocation(LibResources.THAUMIC_ICON + ".png"));
		BotaniaAPI.addCategory(categoryThaumic);
		categoryBlood=new WLexiconCategory(LibLexicon.CATEGORY_BLOOD, 7).setIcon(new ResourceLocation(LibResources.BLOOD_ICON + ".png"));
		BotaniaAPI.addCategory(categoryBlood);
		categoryMechanical=new WLexiconCategory(LibLexicon.CATEGORY_MECHANICAL, 7).setIcon(new ResourceLocation(LibResources.MECHANICAL_ICON + ".png"));
		BotaniaAPI.addCategory(categoryMechanical);
		
		// MAIN WORKSHOP ENTRIES
		workshopIntro = new WLexiconEntry(LibLexicon.WORKSHOP_INTRO, categoryWorkshop);
		workshopIntro.setPriority().setLexiconPages(
				new PageText("0"),
				new PageText("1"));
		
		elvenPool = new WLexiconEntry(LibLexicon.WORKSHOP_ELVENPOOL, categoryWorkshop);
		elvenPool.setLexiconPages(
				new PageText("0"), 
				new PageCraftingRecipe("1", ModCraftingRecipes.recipeElvenPool), 
				new PageElvenRecipe("2", ModElvenTradeRecipes.elvenPoolRecipe));
		
		logicalSound = new WLexiconEntry(LibLexicon.WORKSHOP_LOGICALSOUND, categoryWorkshop);
		logicalSound.setLexiconPages(
				new PageText("0"), 
				new PagePetalRecipe("1", ModPetalRecipes.logicalSoundRecipe));
		
		gatewayCore = new WLexiconEntry(LibLexicon.WORKSHOP_GATEWAYCORE, categoryWorkshop);
		gatewayCore.setPriority().setLexiconPages(
				new PageText("0"), 
				new PageCraftingRecipe("1", ModCraftingRecipes.recipeWeakGatewayCore), 
				new PageElvenRecipe("2", ModElvenTradeRecipes.gatewayCoreRecipe),
				new PageImage("3", LibResources.PREFIX_ENTRIES + LibResources.ENTRIES_GATEWAY[0]),
				new PageImage("4", LibResources.PREFIX_ENTRIES + LibResources.ENTRIES_GATEWAY[1]),
				new PageImage("5", LibResources.PREFIX_ENTRIES + LibResources.ENTRIES_GATEWAY[2]),
				new PageImage("6", LibResources.PREFIX_ENTRIES + LibResources.ENTRIES_GATEWAY[3]),
				new PageImage("7", LibResources.PREFIX_ENTRIES + LibResources.ENTRIES_GATEWAY[4]),
				new PageText("8"),
				new PageText("9"),
				new PageText("10"));
		
		bindingCrystal = new WLexiconEntry(LibLexicon.WORKSHOP_BINDINGCRYSTAL, categoryWorkshop);
		bindingCrystal.setPriority().setLexiconPages(
				new PageText("0"), 
				new PageGatewayTransmutationRecipe("1", ModGatewayTransmutationRecipes.manaBindingCrystalRecipe), 
				new PageGatewayTransmutationRecipe("2", ModGatewayTransmutationRecipes.bloodBindingCrystalRecipe), 
				new PageGatewayTransmutationRecipe("3", ModGatewayTransmutationRecipes.thaumicBindingCrystalRecipe), 
				new PageGatewayTransmutationRecipe("4", ModGatewayTransmutationRecipes.mechanicalBindingCrystalRecipe));
		
		basicCatalyst = new WLexiconEntry(LibLexicon.WORKSHOP_BASICCATALYST, categoryWorkshop);
		basicCatalyst.setPriority().setLexiconPages(
				new PageText("0"), 
				new PageText("1"), 
				new PageCraftingRecipe("2", ModCraftingRecipes.recipeSimpleCatalyst), 
				new PageText("3"), 
				new PageCraftingRecipe("4", ModCraftingRecipes.recipeEmptyCatalyst));
		
		// THAUMIC ENTRIES
		thaumicIntro = new TLexiconEntry(LibLexicon.THAUMIC_INTRO, categoryThaumic);
		thaumicIntro.setPriority().setLexiconPages(
				new PageText("0"));
		
		thaumicCore = new TLexiconEntry(LibLexicon.THAUMIC_THAUMICCORE, categoryThaumic);
		thaumicCore.setPriority().setLexiconPages(
				new PageText("0"),
				new PageGatewayTransmutationRecipe("1", ModGatewayTransmutationRecipes.thaumicCoreRecipe),
				new PageImage("2", LibResources.ENTRY_THAUMICCORE));
		
		thaumicCatalyst = new TLexiconEntry(LibLexicon.THAUMIC_THAUMICCATALYST, categoryThaumic);
		thaumicCatalyst.setLexiconPages(
				new PageText("0"),
				new PageGatewayTransmutationRecipe("1", ModGatewayTransmutationRecipes.thaumicCatalystRecipe));
		
		//BLOOD ENTRIES
		bloodIntro = new BLexiconEntry(LibLexicon.BLOOD_INTRO, categoryBlood);
		bloodIntro.setPriority().setLexiconPages(
				new PageText("0"));
		
		//MECHANICAL ENTRIES
		mechanicalIntro = new MLexiconEntry(LibLexicon.MECHANICAL_INTRO, categoryMechanical);
		mechanicalIntro.setPriority().setLexiconPages(
				new PageText("0"));
		
		exAquainas = new MLexiconEntry(LibLexicon.MECHANICAL_EXAQUAINAS, categoryMechanical);
		exAquainas.setLexiconPages(
				new PageText("0"), 
				new PagePetalRecipe("1", ModPetalRecipes.exAquainasRecipe));
	}
}