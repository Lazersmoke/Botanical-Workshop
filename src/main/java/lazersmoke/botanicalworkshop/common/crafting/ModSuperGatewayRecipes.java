package lazersmoke.botanicalworkshop.common.crafting;

import lazersmoke.botanicalworkshop.api.BotanicalWorkshopAPI;
import lazersmoke.botanicalworkshop.api.recipe.RecipeSuperGateway;
import lazersmoke.botanicalworkshop.common.BotanicalWorkshop;
import lazersmoke.botanicalworkshop.common.item.ModItems;
import lazersmoke.botanicalworkshop.common.lib.LibConfigs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ModSuperGatewayRecipes{
	
	public static RecipeSuperGateway manaBindingCrystal;
	public static RecipeSuperGateway bloodBindingCrystal;
	
	public static void init(){
		if(LibConfigs.SUPER_CRAFT_MANA_BINDING_CRYSTAL)
			manaBindingCrystal = BotanicalWorkshopAPI.registerSuperGatewayRecipe(new ItemStack(ModItems.bindingCrystal, 1, 0), new ItemStack(ModItems.simpleCatalyst), new ItemStack(vazkii.botania.common.item.ModItems.manaResource, 1, 2), new ItemStack(Items.nether_star), new ItemStack(vazkii.botania.common.item.ModItems.manaResource, 1, 3));
		if(LibConfigs.SUPER_CRAFT_BLOOD_BINDING_CRYSTAL && BotanicalWorkshop.bloodMagicLoaded){
			Item orb = (Item) Item.itemRegistry.getObject("AWWayofTime:apprenticeBloodOrb");
			bloodBindingCrystal = BotanicalWorkshopAPI.registerSuperGatewayRecipe(new ItemStack(ModItems.bindingCrystal, 1, 1), new ItemStack(ModItems.simpleCatalyst), new ItemStack(orb), new ItemStack(ModItems.bindingCrystal));
		}
	}
}