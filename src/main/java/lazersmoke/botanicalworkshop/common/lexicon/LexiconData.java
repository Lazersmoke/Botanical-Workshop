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
import vazkii.botania.api.recipe.RecipePetals;
import vazkii.botania.common.lexicon.page.PageCraftingRecipe;
import vazkii.botania.common.lexicon.page.PageElvenRecipe;
import vazkii.botania.common.lexicon.page.PageImage;
import vazkii.botania.common.lexicon.page.PagePetalRecipe;
import vazkii.botania.common.lexicon.page.PageText;

public final class LexiconData{

	public static LexiconCategory categoryWorkshop;
	public static LexiconCategory categoryThaumic;
	public static LexiconCategory categoryBlood;
	public static LexiconCategory categoryLightning;

	public static KnowledgeType workshopKnowledge;
	public static KnowledgeType thaumicKnowledge;
	public static KnowledgeType bloodKnowledge;
	public static KnowledgeType lightningKnowledge;

	public static LexiconEntry workshopIntro;
	public static LexiconEntry elvenPool;
	public static LexiconEntry gatewayCore;
	public static LexiconEntry bindingCrystal;
	public static LexiconEntry basicCatalyst;
	public static LexiconEntry logicalSound;
	public static LexiconEntry thaumicIntro;
	public static LexiconEntry thaumicCore;
	public static LexiconEntry thaumicCatalyst;
	public static LexiconEntry shiftedArmor;
	public static LexiconEntry shiftedArmorUpgrade;
	public static LexiconEntry botanicalResources;
	
	public static LexiconEntry bloodIntro;

	public static LexiconEntry lightningIntro;
	public static LexiconEntry exAquainas;
	public static LexiconEntry lightningCore;
	public static LexiconEntry lightningRod;
	public static LexiconEntry thaumtanicalTransposer;

	public static void init(){
		//Unlocked by using binding crystals, except for the default one
		workshopKnowledge = BotaniaAPI.registerKnowledgeType(LibLexicon.KNOWLEDGE_WORKSHOP, EnumChatFormatting.DARK_RED, true);
		thaumicKnowledge = BotaniaAPI.registerKnowledgeType(LibLexicon.KNOWLEDGE_THAUMIC, EnumChatFormatting.DARK_PURPLE, false);
		bloodKnowledge = BotaniaAPI.registerKnowledgeType(LibLexicon.KNOWLEDGE_BLOOD, EnumChatFormatting.RED, false);
		lightningKnowledge = BotaniaAPI.registerKnowledgeType(LibLexicon.KNOWLEDGE_LIGHTNING, EnumChatFormatting.WHITE, false);

		categoryWorkshop = new WLexiconCategory(LibLexicon.CATEGORY_WORKSHOP, 8).setIcon(new ResourceLocation(LibResources.WORKSHOP_ICON + ".png"));
		BotaniaAPI.addCategory(categoryWorkshop);
		categoryThaumic = new WLexiconCategory(LibLexicon.CATEGORY_THAUMIC, 7).setIcon(new ResourceLocation(LibResources.THAUMIC_ICON + ".png"));
		BotaniaAPI.addCategory(categoryThaumic);
		categoryBlood = new WLexiconCategory(LibLexicon.CATEGORY_BLOOD, 7).setIcon(new ResourceLocation(LibResources.BLOOD_ICON + ".png"));
		BotaniaAPI.addCategory(categoryBlood);
		categoryLightning = new WLexiconCategory(LibLexicon.CATEGORY_LIGHTNING, 7).setIcon(new ResourceLocation(LibResources.LIGHTNING_ICON + ".png"));
		BotaniaAPI.addCategory(categoryLightning);
		
		
		/* How to format these entries:
		 * 
		 * name = new ?LexiconEntry(LibLexicon.?, category?);
		 * name[.setPriority()].setLexiconPages(
		 * new Page?("0"),
		 * new Page?("1"),
		 * .
		 * .
		 * .
		 * new Page?("n")
		 * );
		 * <LINEBREAK>
		 * name = new ?LexiconEntry(LibLexicon.?, category?);
		 * .
		 * .
		 * .
		 * etc
		 * */
		
		
		// MAIN WORKSHOP ENTRIES
		workshopIntro = new WLexiconEntry(LibLexicon.WORKSHOP_INTRO, categoryWorkshop);
		workshopIntro.setPriority().setLexiconPages(
			new PageText("0"),
			new PageText("1")
			);
		
		elvenPool = new WLexiconEntry(LibLexicon.WORKSHOP_ELVENPOOL, categoryWorkshop);
		elvenPool.setLexiconPages(
			new PageText("0"), 
			new PageCraftingRecipe("1", ModCraftingRecipes.recipeElvenPool), 
			new PageElvenRecipe("2", ModElvenTradeRecipes.elvenPoolRecipe)
			);
		
		logicalSound = new WLexiconEntry(LibLexicon.WORKSHOP_LOGICALSOUND, categoryWorkshop);
		logicalSound.setLexiconPages(
			new PageText("0"), 
			new PagePetalRecipe<RecipePetals>("1", ModPetalRecipes.logicalSoundRecipe)
			);
		
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
			new PageText("10")
			);
		
		bindingCrystal = new WLexiconEntry(LibLexicon.WORKSHOP_BINDINGCRYSTAL, categoryWorkshop);
		bindingCrystal.setPriority().setLexiconPages(
			new PageText("0"), 
			new PageGatewayTransmutationRecipe("1", ModGatewayTransmutationRecipes.manaBindingCrystalRecipe), 
			new PageGatewayTransmutationRecipe("2", ModGatewayTransmutationRecipes.bloodBindingCrystalRecipe), 
			new PageGatewayTransmutationRecipe("3", ModGatewayTransmutationRecipes.thaumicBindingCrystalRecipe), 
			new PageGatewayTransmutationRecipe("4", ModGatewayTransmutationRecipes.lightningBindingCrystalRecipe)
			);
		
		shiftedArmor = new WLexiconEntry(LibLexicon.WORKSHOP_SHIFTEDARMOR, categoryWorkshop);
		shiftedArmor.setLexiconPages(
			new PageText("0"), 
			new PageGatewayTransmutationRecipe("1", ModGatewayTransmutationRecipes.shiftedMatterRecipe),
			new PageCraftingRecipe("2", ModCraftingRecipes.recipeShiftedBoots),
			new PageGatewayTransmutationRecipe("3", ModGatewayTransmutationRecipes.shiftedBootsRecipe),
			new PageCraftingRecipe("4", ModCraftingRecipes.recipeShiftedLeggings), 
			new PageGatewayTransmutationRecipe("5", ModGatewayTransmutationRecipes.shiftedLeggingsRecipe),
			new PageCraftingRecipe("6", ModCraftingRecipes.recipeShiftedChestplate), 
			new PageGatewayTransmutationRecipe("7", ModGatewayTransmutationRecipes.shiftedChestplateRecipe),
			new PageCraftingRecipe("8", ModCraftingRecipes.recipeShiftedHelmet),
			new PageGatewayTransmutationRecipe("9", ModGatewayTransmutationRecipes.shiftedHelmetRecipe)
			);
		
		shiftedArmorUpgrade = new WLexiconEntry(LibLexicon.WORKSHOP_SHIFTEDARMORUPGRADE, categoryWorkshop);
		shiftedArmorUpgrade.setLexiconPages(
			new PageText("0"), 
			new PageCraftingRecipe("1", ModCraftingRecipes.recipeShiftedHopsUpgrade),
			new PageGatewayTransmutationRecipe("2", ModGatewayTransmutationRecipes.shiftedHopsUpgradeRecipe),
			new PageText("3"),
			new PageCraftingRecipe("4", ModCraftingRecipes.recipeShiftedPhaseUpgrade),
			new PageGatewayTransmutationRecipe("5", ModGatewayTransmutationRecipes.shiftedPhaseUpgradeRecipe),
			new PageText("6"),
			new PageCraftingRecipe("7", ModCraftingRecipes.recipeShiftedRevealingUpgrade),
			new PageGatewayTransmutationRecipe("8", ModGatewayTransmutationRecipes.shiftedRevealingUpgradeRecipe),
			new PageText("9")
			);
		
		exAquainas = new WLexiconEntry(LibLexicon.WORKSHOP_EXAQUAINAS, categoryWorkshop);
		exAquainas.setLexiconPages(
			new PageText("0"),
			new PagePetalRecipe<RecipePetals>("1", ModPetalRecipes.exAquainasRecipe)
			);
		
		botanicalResources = new WLexiconEntry(LibLexicon.WORKSHOP_RESOURCES, categoryWorkshop);
		botanicalResources.setLexiconPages(
			new PageText("0"),
			new PageGatewayTransmutationRecipe("1", ModGatewayTransmutationRecipes.elvenCrystalRecipe),
			new PageGatewayTransmutationRecipe("2", ModGatewayTransmutationRecipes.elvenKnottingRecipe),
			new PageGatewayTransmutationRecipe("3", ModGatewayTransmutationRecipes.scrapMetalRecipe)
			);
		thaumtanicalTransposer = new LLexiconEntry(LibLexicon.THAUMTANICAL_TRANSPOSER, categoryWorkshop);
		thaumtanicalTransposer.setLexiconPages(
			new PageText("0"),
			new PageGatewayTransmutationRecipe("1", ModGatewayTransmutationRecipes.thaumtanicalTransposerRecipe)
			);
		// THAUMIC ENTRIES
		thaumicIntro = new TLexiconEntry(LibLexicon.THAUMIC_INTRO, categoryThaumic);
		thaumicIntro.setPriority().setLexiconPages(
			new PageText("0")
			);

		thaumicCore = new TLexiconEntry(LibLexicon.THAUMIC_THAUMICCORE, categoryThaumic);
		thaumicCore.setPriority().setLexiconPages(
			new PageText("0"),
			new PageGatewayTransmutationRecipe("1", ModGatewayTransmutationRecipes.thaumicCoreRecipe),
			new PageImage("2", LibResources.ENTRY_THAUMICCORE)
			);

		thaumicCatalyst = new TLexiconEntry(LibLexicon.THAUMIC_THAUMICCATALYST, categoryThaumic);
		thaumicCatalyst.setLexiconPages(
			new PageText("0"),
			new PageGatewayTransmutationRecipe("1", ModGatewayTransmutationRecipes.thaumicCatalystRecipe)
			);

		// BLOOD ENTRIES
		bloodIntro = new BLexiconEntry(LibLexicon.BLOOD_INTRO, categoryBlood);
		bloodIntro.setPriority().setLexiconPages(
			new PageText("0")
			);

		// LIGHTNING ENTRIES
		lightningIntro = new LLexiconEntry(LibLexicon.LIGHTNING_INTRO, categoryLightning);
		lightningIntro.setPriority().setLexiconPages(
			new PageText("0"),
			new PageText("1"),
			new PageCraftingRecipe("2", ModCraftingRecipes.recipeVoltmeter)
			);
		lightningCore = new LLexiconEntry(LibLexicon.LIGHTNING_CORE, categoryLightning);
		lightningCore.setPriority().setLexiconPages(
			new PageText("0"),
			new PageGatewayTransmutationRecipe("1", ModGatewayTransmutationRecipes.lightningCoreRecipe),
			new PageText("2"),
			new PageGatewayTransmutationRecipe("3", ModGatewayTransmutationRecipes.lightningGenerationCatalystRecipe),
			new PageGatewayTransmutationRecipe("4", ModGatewayTransmutationRecipes.lightningConsumingCatalystRecipe)
			);
		lightningRod = new LLexiconEntry(LibLexicon.LIGHTNING_ROD, categoryLightning);
		lightningRod.setPriority().setLexiconPages(
			new PageText("0"),
			new PageCraftingRecipe("1", ModCraftingRecipes.recipeLightningRod)
			);
	}
}