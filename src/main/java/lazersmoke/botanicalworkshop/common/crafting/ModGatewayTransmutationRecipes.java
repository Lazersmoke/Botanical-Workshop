package lazersmoke.botanicalworkshop.common.crafting;

import lazersmoke.botanicalworkshop.api.BotanicalWorkshopAPI;
import lazersmoke.botanicalworkshop.api.recipe.RecipeGatewayTransmutation;
import lazersmoke.botanicalworkshop.common.BotanicalWorkshop;
import lazersmoke.botanicalworkshop.common.block.ModBlocks;
import lazersmoke.botanicalworkshop.common.item.ModItems;
import lazersmoke.botanicalworkshop.common.lib.LibConfigs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ModGatewayTransmutationRecipes{

	private static RecipeGatewayTransmutation defaultRecipe = new RecipeGatewayTransmutation("default");
	public static RecipeGatewayTransmutation manaBindingCrystalRecipe = defaultRecipe;
	public static RecipeGatewayTransmutation bloodBindingCrystalRecipe = defaultRecipe;
	public static RecipeGatewayTransmutation thaumicBindingCrystalRecipe = defaultRecipe;
	public static RecipeGatewayTransmutation mechanicalBindingCrystalRecipe = defaultRecipe;
	public static RecipeGatewayTransmutation manaCatalystRecipe = defaultRecipe;
	public static RecipeGatewayTransmutation thaumicCoreRecipe = defaultRecipe;
	public static RecipeGatewayTransmutation thaumicCatalystRecipe = defaultRecipe;
	public static RecipeGatewayTransmutation shiftedMatterRecipe = defaultRecipe;
	public static RecipeGatewayTransmutation shiftedBootsRecipe = defaultRecipe;
	public static RecipeGatewayTransmutation shiftedLeggingsRecipe = defaultRecipe;
	public static RecipeGatewayTransmutation shiftedChestplateRecipe = defaultRecipe;
	public static RecipeGatewayTransmutation shiftedHelmetRecipe = defaultRecipe;
	public static RecipeGatewayTransmutation shiftedHopsUpgradeRecipe = defaultRecipe;
	public static RecipeGatewayTransmutation shiftedPhaseUpgradeRecipe = defaultRecipe;
	public static RecipeGatewayTransmutation shiftedRevealingUpgradeRecipe = defaultRecipe;

	public static void init(){
		if(LibConfigs.GATEWAY_CRAFT_MANA_BINDING_CRYSTAL)
			manaBindingCrystalRecipe = BotanicalWorkshopAPI.registerGatewayRecipe(
				new ItemStack(ModItems.bindingCrystal, 1, 0), // Mana Binding Crystal //Result
				new ItemStack(ModItems.simpleCatalyst), // Simple Catalyst
				new ItemStack(vazkii.botania.common.item.ModItems.manaResource, 1, 1), // mana diamond
				new ItemStack(Items.nether_star), // Nether Star
				new ItemStack(vazkii.botania.common.item.ModItems.manaResource,1, 2) // mana pearl
				);

		if(LibConfigs.GATEWAY_CRAFT_BLOOD_BINDING_CRYSTAL && BotanicalWorkshop.bloodMagicLoaded)
			bloodBindingCrystalRecipe = BotanicalWorkshopAPI.registerGatewayRecipe(
				new ItemStack(ModItems.bindingCrystal, 1, 1), // Blood Binding Crystal //Result
				new ItemStack(ModItems.simpleCatalyst),
				new ItemStack((Item) Item.itemRegistry.getObject("AWWayofTime:apprenticeBloodOrb")), // Apprentice Blood Orb
				new ItemStack((Item) Item.itemRegistry.getObject("AWWayofTime:bucketLife")) // Bucket of Life Essence
				);
		if(LibConfigs.GATEWAY_CRAFT_THAUMIC_BINDING_CRYSTAL && BotanicalWorkshop.thaumcraftLoaded)
			thaumicBindingCrystalRecipe = BotanicalWorkshopAPI.registerGatewayRecipe(
				new ItemStack(ModItems.bindingCrystal, 1, 2), // Thaumic Binding Crystal //Result
				new ItemStack(ModItems.simpleCatalyst),
				new ItemStack((Item) Item.itemRegistry.getObject("Thaumcraft:ItemThaumometer")), // Thaumometer
				new ItemStack((Item) Item.itemRegistry.getObject("Thaumcraft:ItemResource"), 1, 2), // Thaumium Ingot
				new ItemStack((Item) Item.itemRegistry.getObject("Thaumcraft:ItemResource"), 1, 14) // Salis Mundus
				);
		/*
		 * if(LibConfigs.GATEWAY_CRAFT_MECHANICAL_BINDING_CRYSTAL) mechanicalBindingCrystal = BotanicalWorkshopAPI.registerGatewayRecipe( new
		 * ItemStack(ModItems.bindingCrystal, 1, 3), //Mechanical Binding Crystal new ItemStack(ModItems.simpleCatalyst), //Simple Catalyst new ItemStack())
		 */
		if(LibConfigs.GATEWAY_CRAFT_MANA_CATALYST)
			manaCatalystRecipe = BotanicalWorkshopAPI.registerGatewayRecipe(
				new ItemStack(ModItems.manaCatalyst, 1, 0), //Result
				new ItemStack(ModItems.bindingCrystal, 1, 0), // Mana Binding Crystal
				new ItemStack(ModItems.botanicalResource, 1, 0), // elven crystal
				new ItemStack(vazkii.botania.common.item.ModItems.manaResource, 1, 2), // mana pearl
				new ItemStack(Items.diamond), // diamond
				new ItemStack(Items.blaze_powder) // blaze power
				);
		if(LibConfigs.GATEWAY_CRAFT_THAUMIC_CORE && BotanicalWorkshop.thaumcraftLoaded)
			thaumicCoreRecipe = BotanicalWorkshopAPI.registerGatewayRecipe(
				new ItemStack(ModBlocks.thaumicCore), //Result
				new ItemStack(ModItems.bindingCrystal, 1, 2), // Thaumic Binding Crystal
				new ItemStack(ModItems.botanicalResource, 1, 0), // Elven Crystal
				new ItemStack((Item) Item.itemRegistry.getObject("Thaumcraft:blockCosmeticSolid"), 1, 6), // Arcane Stone Block
				new ItemStack((Item) Item.itemRegistry.getObject("Thaumcraft:ItemResource"), 1, 8) // Vis Filter
				);
		if(LibConfigs.GATEWAY_CRAFT_THAUMIC_CATALYST && BotanicalWorkshop.thaumcraftLoaded)
			thaumicCatalystRecipe = BotanicalWorkshopAPI.registerGatewayRecipe(
				new ItemStack(ModItems.thaumicCatalyst), //Result
				new ItemStack(ModItems.bindingCrystal, 1, 2), // Thaumic Binding Crystal
				new ItemStack(ModItems.botanicalResource, 1, 0), // Elven Crystal
				new ItemStack((Item) Item.itemRegistry.getObject("Thaumcraft:FocusPortableHole")), // Portable Hole Focus
				new ItemStack((Item) Item.itemRegistry.getObject("Thaumcraft:ItemShard"), 1, 6) // Balanced Shard
				);
		
		if(LibConfigs.GATEWAY_CRAFT_SHIFTED_MATTER)
			shiftedMatterRecipe = BotanicalWorkshopAPI.registerGatewayRecipe(
				new ItemStack(ModItems.shiftedMatter),//Result
				new ItemStack(ModItems.bindingCrystal, 1, 0), // Mana Binding Crystal 
				new ItemStack(ModItems.botanicalResource, 1, 0), // Elven Crystal
				new ItemStack(vazkii.botania.common.item.ModItems.manaResource, 1, 2), // Mana Pearl
				new ItemStack(vazkii.botania.common.item.ModItems.manaResource, 1, 23) // Mana Powder
				);
		
		if(LibConfigs.GATEWAY_CRAFT_SHIFTED_ARMOR){
			shiftedBootsRecipe = BotanicalWorkshopAPI.registerGatewayRecipe(
				new ItemStack(ModItems.shiftedBoots),//Result
				new ItemStack(ModItems.bindingCrystal, 1, 0), // Mana Binding Crystal
				new ItemStack(vazkii.botania.common.item.ModItems.manasteelBoots),
				new ItemStack(ModItems.shiftedMatter, 3, 0)
				);
			shiftedLeggingsRecipe = BotanicalWorkshopAPI.registerGatewayRecipe(
				new ItemStack(ModItems.shiftedLeggings),//Result
				new ItemStack(ModItems.bindingCrystal, 1, 0), // Mana Binding Crystal
				new ItemStack(vazkii.botania.common.item.ModItems.manasteelLegs),
				new ItemStack(ModItems.shiftedMatter, 6, 0)
				);
			shiftedChestplateRecipe = BotanicalWorkshopAPI.registerGatewayRecipe(
				new ItemStack(ModItems.shiftedChestplate),//Result
				new ItemStack(ModItems.bindingCrystal, 1, 0), // Mana Binding Crystal
				new ItemStack(vazkii.botania.common.item.ModItems.manasteelChest),
				new ItemStack(ModItems.shiftedMatter, 7, 0)
				);
			shiftedHelmetRecipe = BotanicalWorkshopAPI.registerGatewayRecipe(
				new ItemStack(ModItems.shiftedHelmet),//Result
				new ItemStack(ModItems.bindingCrystal, 1, 0), // Mana Binding Crystal
				new ItemStack(vazkii.botania.common.item.ModItems.manasteelHelm),
				new ItemStack(ModItems.shiftedMatter, 4, 0)
				);
		}
		if(LibConfigs.CRAFT_SHIFTED_HOPS_UPGRADE)
			shiftedHopsUpgradeRecipe = BotanicalWorkshopAPI.registerGatewayRecipe(
				new ItemStack(ModItems.shiftedHopsUpgrade),//Result
				new ItemStack(ModItems.bindingCrystal, 1, 0), // Mana Binding Crystal
				new ItemStack(Items.feather, 2, 0),
				new ItemStack(vazkii.botania.common.block.ModBlocks.dreamwood, 2, 0),
				new ItemStack(Items.nether_star)
				);
		if(LibConfigs.CRAFT_SHIFTED_PHASE_UPGRADE)
			shiftedPhaseUpgradeRecipe = BotanicalWorkshopAPI.registerGatewayRecipe(
				new ItemStack(ModItems.shiftedPhaseUpgrade),//Result
				new ItemStack(ModItems.bindingCrystal, 1, 0), // Mana Binding Crystal
				new ItemStack(Blocks.glass, 2, 0),
				new ItemStack(ModItems.botanicalResource, 2, 0),//Elven Crystal
				new ItemStack(vazkii.botania.common.item.ModItems.phantomInk)
				);
		if(LibConfigs.CRAFT_SHIFTED_REVEALING_UPGRADE && BotanicalWorkshop.thaumcraftLoaded)
			shiftedRevealingUpgradeRecipe = BotanicalWorkshopAPI.registerGatewayRecipe(
				new ItemStack(ModItems.shiftedRevealingUpgrade),//Result
				new ItemStack(ModItems.bindingCrystal, 1, 2), // Thaumic Binding Crystal
				new ItemStack(vazkii.botania.common.block.ModBlocks.manaGlass, 2, 0),
				new ItemStack(vazkii.botania.common.block.ModBlocks.elfGlass, 2, 0),
				new ItemStack((Item) Item.itemRegistry.getObject("Thaumcraft:ItemGoggles"))
				);
	}
}