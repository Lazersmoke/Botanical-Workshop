package lazersmoke.botanicalworkshop.common.crafting;

import java.util.List;

import lazersmoke.botanicalworkshop.api.BotanicalWorkshopAPI;
import lazersmoke.botanicalworkshop.api.recipe.RecipeGatewayTransmutation;
import lazersmoke.botanicalworkshop.common.BotanicalWorkshop;
import lazersmoke.botanicalworkshop.common.block.ModBlocks;
import lazersmoke.botanicalworkshop.common.item.ModItems;
import lazersmoke.botanicalworkshop.common.lib.LibConfigs;
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
	
	public static void init(){
		if(LibConfigs.GATEWAY_CRAFT_MANA_BINDING_CRYSTAL)
			manaBindingCrystalRecipe = BotanicalWorkshopAPI.registerGatewayRecipe(
					new ItemStack(ModItems.bindingCrystal, 1, 0), //Mana Binding Crystal
					new ItemStack(ModItems.simpleCatalyst), //Simple Catalyst
					new ItemStack(vazkii.botania.common.item.ModItems.manaResource, 1, 1), //mana diamond
					new ItemStack(Items.nether_star), //Nether Star
					new ItemStack(vazkii.botania.common.item.ModItems.manaResource, 1, 2));//mana pearl
		
		if(LibConfigs.GATEWAY_CRAFT_BLOOD_BINDING_CRYSTAL && BotanicalWorkshop.bloodMagicLoaded)
			bloodBindingCrystalRecipe = BotanicalWorkshopAPI.registerGatewayRecipe(
					new ItemStack(ModItems.bindingCrystal, 1, 1), //Blood Binding Crystal
					new ItemStack(ModItems.simpleCatalyst), 
					new ItemStack((Item) Item.itemRegistry.getObject("AWWayofTime:apprenticeBloodOrb")), //Apprentice Blood Orb
					new ItemStack((Item) Item.itemRegistry.getObject("AWWayofTime:bucketLife"))); //Bucket of Life Essence
		if(LibConfigs.GATEWAY_CRAFT_THAUMIC_BINDING_CRYSTAL && BotanicalWorkshop.thaumcraftLoaded)
			thaumicBindingCrystalRecipe = BotanicalWorkshopAPI.registerGatewayRecipe(
					new ItemStack(ModItems.bindingCrystal, 1, 2), //Thaumic Binding Crystal
					new ItemStack(ModItems.simpleCatalyst), 
					new ItemStack((Item) Item.itemRegistry.getObject("Thaumcraft:ItemThaumometer")), //Thaumometer
					new ItemStack((Item) Item.itemRegistry.getObject("Thaumcraft:ItemResource"), 1, 2), //Thaumium Ingot
					new ItemStack((Item) Item.itemRegistry.getObject("Thaumcraft:ItemResource"), 1, 14)); //Salis Mundus
		/*if(LibConfigs.GATEWAY_CRAFT_MECHANICAL_BINDING_CRYSTAL)
		mechanicalBindingCrystal = BotanicalWorkshopAPI.registerGatewayRecipe(
				new ItemStack(ModItems.bindingCrystal, 1, 3), //Mechanical Binding Crystal
				new ItemStack(ModItems.simpleCatalyst), //Simple Catalyst
				new ItemStack())*/
		if(LibConfigs.GATEWAY_CRAFT_MANA_CATALYST)
			manaCatalystRecipe = BotanicalWorkshopAPI.registerGatewayRecipe(
					new ItemStack(ModItems.manaCatalyst, 1, 0), //Mana Transfer Catalyst
					new ItemStack(ModItems.bindingCrystal, 1, 0), //Mana Binding Crystal
					new ItemStack(ModItems.botanicalResource, 1, 0),  //elven crystal
					new ItemStack(vazkii.botania.common.item.ModItems.manaResource, 1, 2), //mana pearl
					new ItemStack(Items.diamond), //diamond
					new ItemStack(Items.blaze_powder)); //blaze power
		if(LibConfigs.GATEWAY_CRAFT_THAUMIC_CORE && BotanicalWorkshop.thaumcraftLoaded)
			thaumicCoreRecipe = BotanicalWorkshopAPI.registerGatewayRecipe(
					new ItemStack(ModBlocks.thaumicCore), //Thaumic Core
					new ItemStack(ModItems.bindingCrystal, 1, 2), //Thaumic Binding Crystal
					new ItemStack(ModItems.botanicalResource, 1, 0), //Elven Crystal
					new ItemStack((Item) Item.itemRegistry.getObject("Thaumcraft:blockCosmeticSolid"), 1, 6), //Arcane Stone Block
					new ItemStack((Item) Item.itemRegistry.getObject("Thaumcraft:ItemResource"), 1, 8)); //Vis Filter
		if(LibConfigs.GATEWAY_CRAFT_THAUMIC_CATALYST && BotanicalWorkshop.thaumcraftLoaded)
			thaumicCatalystRecipe = BotanicalWorkshopAPI.registerGatewayRecipe(
					new ItemStack(ModItems.thaumicCatalyst), //Thaumic Catalyst
					new ItemStack(ModItems.bindingCrystal, 1, 2), //Thaumic Binding Crystal
					new ItemStack(ModItems.botanicalResource, 1, 0), //Elven Crystal
					new ItemStack((Item) Item.itemRegistry.getObject("Thaumcraft:FocusPortableHole")), //Portable Hole Focus
					new ItemStack((Item) Item.itemRegistry.getObject("Thaumcraft:ItemShard"), 1, 6)); //Balanced Shard
	}
}