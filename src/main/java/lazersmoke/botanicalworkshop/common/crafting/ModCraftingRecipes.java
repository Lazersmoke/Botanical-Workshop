package lazersmoke.botanicalworkshop.common.crafting;

import lazersmoke.botanicalworkshop.common.block.ModBlocks;
import lazersmoke.botanicalworkshop.common.lib.LibConfigs;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import vazkii.botania.api.BotaniaAPI;

public class ModCraftingRecipes {
	public static IRecipe recipeElvenPool;
	
	public static void init() {
		if(LibConfigs.CRAFT_ELVEN_POOL){
			addOreDictRecipe(new ItemStack(ModBlocks.elvenPool),
				"Q Q", "QQQ",
				'Q', new ItemStack(vazkii.botania.common.item.ModItems.quartz, 1, 5));
			recipeElvenPool = BotaniaAPI.getLatestAddedRecipe();
		}
	}
	
	private static void addOreDictRecipe(ItemStack output, Object... recipe) {
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(output, recipe));
	}

	private static void addShapelessOreDictRecipe(ItemStack output, Object... recipe) {
		CraftingManager.getInstance().getRecipeList().add(new ShapelessOreRecipe(output, recipe));
	}
}
