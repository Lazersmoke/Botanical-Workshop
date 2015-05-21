package lazersmoke.botanicalworkshop.common.crafting;

import lazersmoke.botanicalworkshop.common.block.ModBlocks;
import lazersmoke.botanicalworkshop.common.item.ModItems;
import lazersmoke.botanicalworkshop.common.lib.LibConfigs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import vazkii.botania.api.BotaniaAPI;

public class ModCraftingRecipes {
	public static IRecipe recipeElvenPool;
	public static IRecipe recipeEmptyCatalyst;
	public static IRecipe recipeSimpleCatalyst;
	public static IRecipe recipeWeakGatewayCore;
	
	public static void init() {
		
		if(LibConfigs.CRAFT_ELVEN_POOL){
			addOreDictRecipe(new ItemStack(ModBlocks.elvenPool),
				"Q Q", "QQQ",
				'Q', new ItemStack(vazkii.botania.common.item.ModItems.quartz, 1, 5));
			recipeElvenPool = BotaniaAPI.getLatestAddedRecipe();
		}
		
		if(LibConfigs.CRAFT_SIMPLE_CATALYST){
			addShapelessOreDictRecipe(new ItemStack(ModItems.simpleCatalyst),
				new ItemStack(Items.glowstone_dust), new ItemStack(Items.redstone), new ItemStack(Items.gunpowder), new ItemStack(vazkii.botania.common.item.ModItems.manaResource, 1, 8));
			recipeSimpleCatalyst = BotaniaAPI.getLatestAddedRecipe();
		}
		
		if(LibConfigs.CRAFT_EMPTY_CATALYST){
			addShapelessOreDictRecipe(new ItemStack(ModItems.emptyCatalyst),
				new ItemStack(ModItems.simpleCatalyst), new ItemStack(Items.bowl));
			recipeEmptyCatalyst = BotaniaAPI.getLatestAddedRecipe();
		}
		
		if(LibConfigs.CRAFT_WEAK_GATEWAY_CORE){
			addOreDictRecipe(new ItemStack(ModBlocks.weakGatewayCore),
				"DGD", "LEL", "DGD",
				'D', new ItemStack(Items.diamond),
				'G', new ItemStack(Items.gold_ingot),
				'L', new ItemStack(Items.dye, 1, 4), //Lapis
				'E', new ItemStack(vazkii.botania.common.block.ModBlocks.alfPortal));
			recipeWeakGatewayCore = BotaniaAPI.getLatestAddedRecipe();
		}
	}
	
	private static void addOreDictRecipe(ItemStack output, Object... recipe) {
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(output, recipe));
	}

	private static void addShapelessOreDictRecipe(ItemStack output, Object... recipe) {
		CraftingManager.getInstance().getRecipeList().add(new ShapelessOreRecipe(output, recipe));
	}
}
