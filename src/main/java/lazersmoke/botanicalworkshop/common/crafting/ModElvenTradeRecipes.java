package lazersmoke.botanicalworkshop.common.crafting;

import net.minecraft.item.ItemStack;
import vazkii.botania.api.recipe.RecipeElvenTrade;
import lazersmoke.botanicalworkshop.common.block.ModBlocks;
import lazersmoke.botanicalworkshop.common.item.ModItems;
import lazersmoke.botanicalworkshop.common.lib.LibConfigs;
import vazkii.botania.api.BotaniaAPI;

public class ModElvenTradeRecipes{

	public static RecipeElvenTrade defaultRecipe = new RecipeElvenTrade(
	        new ItemStack(ModItems.botanicalResource, 1, 1), new ItemStack(
	                ModItems.botanicalResource, 1, 1));
	public static RecipeElvenTrade elvenPoolRecipe = defaultRecipe;
	public static RecipeElvenTrade elvenCrystalRecipe = defaultRecipe;
	public static RecipeElvenTrade gatewayCoreRecipe = defaultRecipe;

	public static void init(){
		if(LibConfigs.PORTAL_CRAFT_ELVEN_POOL)
			elvenPoolRecipe = BotaniaAPI.registerElvenTradeRecipe(
			        new ItemStack(ModBlocks.elvenPool, 1, 0), new ItemStack(
			                vazkii.botania.common.block.ModBlocks.pool, 1, 0));
		if(LibConfigs.PORTAL_CRAFT_ELVEN_CRYSTAL)
			elvenCrystalRecipe = BotaniaAPI.registerElvenTradeRecipe(
			        new ItemStack(ModItems.botanicalResource, 1, 0),
			        new ItemStack(
			                vazkii.botania.common.item.ModItems.manaResource,
			                1, 2)/* mana pearl */);
		if(LibConfigs.PORTAL_CRAFT_GATEWAY_CORE)
			gatewayCoreRecipe = BotaniaAPI.registerElvenTradeRecipe(
			        new ItemStack(ModBlocks.gatewayCore), new ItemStack(
			                ModBlocks.weakGatewayCore));
	}
}
