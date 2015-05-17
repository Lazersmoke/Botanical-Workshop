package lazersmoke.botanicalworkshop.common.crafting;

import java.util.Arrays;

import lazersmoke.botanicalworkshop.api.BotanicalWorkshopAPI;
import lazersmoke.botanicalworkshop.api.recipe.RecipeSuperGateway;
import lazersmoke.botanicalworkshop.common.block.ModBlocks;
import lazersmoke.botanicalworkshop.common.item.ModItems;
import lazersmoke.botanicalworkshop.common.lib.LibConfigs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class ModSuperGatewayRecipes{
	
	public static RecipeSuperGateway manaBindingCrystal;
	
	public static void init(){
		if(LibConfigs.SUPER_CRAFT_MANA_BINDING_CRYSTAL)
			manaBindingCrystal = BotanicalWorkshopAPI.registerSuperGatewayRecipe(new ItemStack(ModItems.manaBindingCrystal, 1, 0), Arrays.asList(new ItemStack(Items.diamond), new ItemStack(Items.nether_star), new ItemStack(Items.ender_pearl)));
	}
}