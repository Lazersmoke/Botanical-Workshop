package lazersmoke.botanicalworkshop.common.crafting;

import net.minecraft.item.ItemStack;
import vazkii.botania.api.recipe.RecipeElvenTrade;
import lazersmoke.botanicalworkshop.common.block.ModBlocks;
import lazersmoke.botanicalworkshop.common.lib.LibConfigs;
import vazkii.botania.api.BotaniaAPI;

public class ModElvenTradeRecipes {

	public static RecipeElvenTrade elvenPoolRecipe;
	
	public static void init() {
		if(LibConfigs.PORTAL_CRAFT_ELVEN_POOL)
			elvenPoolRecipe = BotaniaAPI.registerElvenTradeRecipe(new ItemStack(ModBlocks.elvenPool, 1, 0), new ItemStack(vazkii.botania.common.block.ModBlocks.pool, 1, 0));
	}

}
