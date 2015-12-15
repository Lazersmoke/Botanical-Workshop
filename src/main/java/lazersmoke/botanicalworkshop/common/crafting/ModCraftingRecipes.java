package lazersmoke.botanicalworkshop.common.crafting;

import lazersmoke.botanicalworkshop.common.BotanicalWorkshop;
import lazersmoke.botanicalworkshop.common.block.ModBlocks;
import lazersmoke.botanicalworkshop.common.item.ModItems;
import lazersmoke.botanicalworkshop.common.lib.LibConfigs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import vazkii.botania.api.BotaniaAPI;

public class ModCraftingRecipes{
	//@formatter:off

	public static IRecipe defaultRecipe = new ShapelessOreRecipe(new ItemStack(ModItems.botanicalResource, 1, 1), new ItemStack(ModItems.botanicalResource, 1, 1));
	public static IRecipe recipeElvenPool = defaultRecipe;
	public static IRecipe recipeEmptyCatalyst = defaultRecipe;
	public static IRecipe recipeSimpleCatalyst = defaultRecipe;
	public static IRecipe recipeWeakGatewayCore = defaultRecipe;
	public static IRecipe recipeShiftedBoots = defaultRecipe;
	public static IRecipe recipeShiftedLeggings = defaultRecipe;
	public static IRecipe recipeShiftedChestplate = defaultRecipe;
	public static IRecipe recipeShiftedHelmet = defaultRecipe;
	public static IRecipe recipeShiftedHopsUpgrade = defaultRecipe;
	public static IRecipe recipeShiftedPhaseUpgrade = defaultRecipe;
	public static IRecipe recipeShiftedRevealingUpgrade = defaultRecipe;
	public static IRecipe recipeVoltmeter = defaultRecipe;
	public static IRecipe recipeLightningRod = defaultRecipe;

	public static void init(){
		if(LibConfigs.CRAFT_ELVEN_POOL){
			addOreDictRecipe(new ItemStack(ModBlocks.elvenPool),
					"Q Q",
					"QQQ",
					'Q', new ItemStack(vazkii.botania.common.item.ModItems.quartz, 1, 5));
			recipeElvenPool = BotaniaAPI.getLatestAddedRecipe();
		}

		if(LibConfigs.CRAFT_SIMPLE_CATALYST){
			addShapelessOreDictRecipe(new ItemStack(ModItems.simpleCatalyst),
					new ItemStack(Items.glowstone_dust),
					new ItemStack(Items.redstone),
					new ItemStack(Items.gunpowder),
					new ItemStack(vazkii.botania.common.item.ModItems.manaResource,1, 8)
					);
			recipeSimpleCatalyst = BotaniaAPI.getLatestAddedRecipe();
		}

		if(LibConfigs.CRAFT_EMPTY_CATALYST){
			addShapelessOreDictRecipe(new ItemStack(ModItems.emptyCatalyst),
					new ItemStack(ModItems.simpleCatalyst),
					new ItemStack(Items.bowl));
			recipeEmptyCatalyst = BotaniaAPI.getLatestAddedRecipe();
		}

		if(LibConfigs.CRAFT_WEAK_GATEWAY_CORE){
			addOreDictRecipe(new ItemStack(ModBlocks.weakGatewayCore),
					"DGD",
					"LEL",
					"DGD",
					'D', new ItemStack(Items.diamond),
					'G', new ItemStack(Items.gold_ingot),
					'L', new ItemStack(Items.dye, 1, 4), // Lapis
					'E', new ItemStack(vazkii.botania.common.block.ModBlocks.alfPortal)
					);
			recipeWeakGatewayCore = BotaniaAPI.getLatestAddedRecipe();
		}
		if(LibConfigs.CRAFT_SHIFTED_ARMOR){
			addOreDictRecipe(new ItemStack(ModItems.shiftedBoots),
					"SMS",
					"S S",
					'S', new ItemStack(ModItems.shiftedMatter),
					'M', new ItemStack(vazkii.botania.common.item.ModItems.manasteelBoots)
					);
			recipeShiftedBoots = BotaniaAPI.getLatestAddedRecipe();
			addOreDictRecipe(new ItemStack(ModItems.shiftedLeggings),
					"SSS",
					"SMS",
					"S S",
					'S', new ItemStack(ModItems.shiftedMatter),
					'M', new ItemStack(vazkii.botania.common.item.ModItems.manasteelLegs)
					);
			recipeShiftedLeggings = BotaniaAPI.getLatestAddedRecipe();
			addOreDictRecipe(new ItemStack(ModItems.shiftedChestplate),
					"SMS",
					"SSS",
					"SSS",
					'S', new ItemStack(ModItems.shiftedMatter),
					'M', new ItemStack(vazkii.botania.common.item.ModItems.manasteelChest)
					);
			recipeShiftedChestplate = BotaniaAPI.getLatestAddedRecipe();
			addOreDictRecipe(new ItemStack(ModItems.shiftedHelmet),
					"SSS",
					"SMS",
					'S', new ItemStack(ModItems.shiftedMatter),
					'M', new ItemStack(vazkii.botania.common.item.ModItems.manasteelHelm)
					);
			recipeShiftedHelmet = BotaniaAPI.getLatestAddedRecipe();
		}
		if(LibConfigs.CRAFT_SHIFTED_HOPS_UPGRADE){
			addOreDictRecipe(new ItemStack(ModItems.shiftedHopsUpgrade),
					"FDF",
					"DWD",
					"FDF",
					'F', new ItemStack(Items.feather),
					'D', new ItemStack(vazkii.botania.common.block.ModBlocks.dreamwood),
					'W', new ItemStack(Items.nether_star)
					);
			recipeShiftedHopsUpgrade = BotaniaAPI.getLatestAddedRecipe();
		}
		if(LibConfigs.CRAFT_SHIFTED_PHASE_UPGRADE){
			addOreDictRecipe(new ItemStack(ModItems.shiftedPhaseUpgrade),
					"CGC",
					"GIG",
					"CGC",
					'G', new ItemStack(Blocks.glass),
					'I', new ItemStack(vazkii.botania.common.item.ModItems.phantomInk),
					'C', new ItemStack(ModItems.botanicalResource, 1, 0)//Elven Crystal
					);
			recipeShiftedPhaseUpgrade = BotaniaAPI.getLatestAddedRecipe();
		}
		if(LibConfigs.CRAFT_SHIFTED_REVEALING_UPGRADE && BotanicalWorkshop.thaumcraftLoaded){
			addOreDictRecipe(new ItemStack(ModItems.shiftedRevealingUpgrade),
					"MEM",
					"EGE",
					"MEM",
					'G', new ItemStack((Item) Item.itemRegistry.getObject("Thaumcraft:ItemGoggles")),
					'M', new ItemStack(vazkii.botania.common.block.ModBlocks.manaGlass),
					'E', new ItemStack(vazkii.botania.common.block.ModBlocks.elfGlass)
					);
			recipeShiftedRevealingUpgrade = BotaniaAPI.getLatestAddedRecipe();
		}
		if(LibConfigs.CRAFT_VOLTMETER){
			addOreDictRecipe(new ItemStack(ModItems.voltmeter),
					"NGN",
					"BRB",
					"NSN",
					'N', new ItemStack(Items.gold_nugget),
					'G', new ItemStack(Blocks.glass),
					'B', new ItemStack(Items.gold_ingot),
					'R', new ItemStack(Items.redstone),
					'S', new ItemStack(ModItems.botanicalResource, 1, 3)//Scrap Metal
					);
			recipeVoltmeter = BotaniaAPI.getLatestAddedRecipe();
		}
		if(LibConfigs.CRAFT_LIGHTNING_ROD){
			addOreDictRecipe(new ItemStack(ModBlocks.lightningRod),
					" R ",
					" S ",
					"SIS",
					'I', new ItemStack(Items.iron_ingot),
					'R', new ItemStack(Items.redstone),
					'S', new ItemStack(ModItems.botanicalResource, 1, 3)//Scrap Metal
					);
			recipeLightningRod = BotaniaAPI.getLatestAddedRecipe();
		}
	}

	@SuppressWarnings("unchecked")
	private static void addOreDictRecipe(ItemStack output, Object... recipe){
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(output, recipe));
	}

	@SuppressWarnings("unchecked")
	private static void addShapelessOreDictRecipe(ItemStack output, Object... recipe){
		CraftingManager.getInstance().getRecipeList().add(new ShapelessOreRecipe(output, recipe));
	}
}
